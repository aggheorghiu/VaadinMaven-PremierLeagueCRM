package com.example.application.views.list;

import com.example.application.data.entity.Stadioane;
import com.example.application.data.service.CrmService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@Route(value = "stadioane", layout = MainLayout.class)
@PermitAll
@PageTitle("Stadioane PL")

public class StadioaneView extends VerticalLayout {
    Grid<Stadioane> grid = new Grid<>(Stadioane.class);
    TextField filtrareText = new TextField();
    CrmService service;

    StadioaneView(CrmService service){
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        this.service = service;
        setSizeFull();
        configureGrid();
        add(getToolbar(), getContent());
    }

    private HorizontalLayout getToolbar() {
        filtrareText.setPlaceholder("Filtreaza dupa numele stadionului");
        filtrareText.setClearButtonVisible(true);
        filtrareText.setWidth("500px");
        filtrareText.setValueChangeMode(ValueChangeMode.LAZY);
        HorizontalLayout toolbar = new HorizontalLayout(filtrareText);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

        private HorizontalLayout getContent(){
        HorizontalLayout content = new HorizontalLayout(grid);
        content.setSizeFull();
        return content;
    }

    private void configureGrid(){
        grid.setSizeFull();
        grid.setColumns("nume", "locuri");
        grid.addColumn(stadioane -> stadioane.getEchipa().getDenumireEchipa()).setHeader("Echipa");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }
}
