package com.example.application.views.list;

import com.example.application.data.entity.Membri;
import com.example.application.data.service.CrmService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.security.PermitAll;

@Component
@Scope("prototype")
@PermitAll
@PageTitle("Premier League")
@Route(value = "", layout = MainLayout.class)

public class ListView extends VerticalLayout {
    Grid<Membri> grid = new Grid<>(Membri.class);
    TextField filtrareText = new TextField();
    ContactForm form;
    CrmService service;

    public ListView(CrmService service) {
        this.service = service;
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        configureForm();

        add(getToolbar(), getContent());
        updateList();
        closeEditor();
    }

    private HorizontalLayout getContent(){
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    private void configureForm() {
        form = new ContactForm(service.gasesteToateEchipele(), service.gasesteToateRolurile());
        form.setWidth("25em");
        form.addListener(ContactForm.SalveazaEvent.class, this::salveazaMembru);
        form.addListener(ContactForm.StergeEvent.class, this::stergeMembru);
        form.addListener(ContactForm.AnuleazaEvent.class, e -> closeEditor());
    }

    private void salveazaMembru(ContactForm.SalveazaEvent event){
        service.salveazaMembri(event.getMembri());
        updateList();
        closeEditor();
    }
    private void stergeMembru(ContactForm.StergeEvent event){
        service.stergeMembri(event.getMembri());
        updateList();
        closeEditor();
    }

    private HorizontalLayout getToolbar() {
        filtrareText.setPlaceholder("Filtreaza dupa nume...");
        filtrareText.setClearButtonVisible(true);
        filtrareText.setValueChangeMode(ValueChangeMode.LAZY);
        filtrareText.addValueChangeListener(e -> updateList());

        Button btnAdaugaMembru = new Button("Adauga Membru");
        btnAdaugaMembru.addClickListener(click -> adaugaMembru());

        HorizontalLayout toolbar = new HorizontalLayout(filtrareText, btnAdaugaMembru);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.setColumns("nume", "prenume", "nationalitate");
        grid.addColumn(membri -> membri.getRol().getNume()).setHeader("Rol");
        grid.addColumn(membri -> membri.getEchipa().getDenumireEchipa()).setHeader("Echipa");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(event -> editMembru(event.getValue()));
    }

    private void editMembru(Membri membri){
        if(membri == null){
            closeEditor();
        }else {
            form.setMembru(membri);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor(){
        form.setMembru(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void adaugaMembru(){
        grid.asSingleSelect().clear();
        editMembru(new Membri());
    }

    private void updateList() {
        grid.setItems(service.gasesteTotiMembrii(filtrareText.getValue()));
    }
}
