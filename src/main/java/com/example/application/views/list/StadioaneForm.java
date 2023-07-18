package com.example.application.views.list;

import com.example.application.data.entity.Stadioane;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;

import java.util.List;

public class StadioaneForm extends FormLayout {
    private Stadioane stadioane;
    TextField nume = new TextField("Nume Stadion");
    TextField locuri = new TextField("Capacitate");
    ComboBox<Stadioane> stadioaneComboBox = new ComboBox<>("Stadioane");
    Binder<Stadioane> binder = new BeanValidationBinder<>(Stadioane.class);

    StadioaneForm(List<Stadioane> stadioaneList){
        binder.bindInstanceFields(this);
        stadioaneComboBox.setItems(stadioaneList);
        stadioaneComboBox.setItemLabelGenerator(Stadioane::getNume);

        add(nume,
                locuri,
                stadioaneComboBox);
    }

}
