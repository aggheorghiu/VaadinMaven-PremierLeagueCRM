package com.example.application.views.list;

import com.example.application.data.entity.Echipa;
import com.example.application.data.entity.Membri;
import com.example.application.data.entity.Rol;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class ContactForm extends FormLayout {
    private Membri membri;
    TextField nume = new TextField("Nume");
    TextField prenume = new TextField("Prenume");
    TextField nationalitate = new TextField("Nationalitate");
    ComboBox<Rol> rol = new ComboBox<>("Rol");
    ComboBox<Echipa> echipa = new ComboBox<>("Echipa");

    Button salveaza = new Button("Salveaza");
    Button sterge = new Button("Sterge");
    Button anuleaza = new Button("Anuleaza");

    Binder<Membri> binder = new BeanValidationBinder<>(Membri.class);

    public ContactForm(List<Echipa> echipe, List<Rol> roluri){
        addClassName("contact-form");
        binder.bindInstanceFields(this);

        echipa.setItems(echipe);
        echipa.setItemLabelGenerator(Echipa::getDenumireEchipa);
        rol.setItems(roluri);
        rol.setItemLabelGenerator(Rol::getNume);

        add(nume,
                prenume,
                nationalitate,
                echipa,
                rol,
                createButtonsLayout());
    }

    private Component createButtonsLayout(){
        salveaza.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        sterge.addThemeVariants(ButtonVariant.LUMO_ERROR);
        anuleaza.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        salveaza.addClickShortcut(Key.ENTER);
        anuleaza.addClickShortcut(Key.ESCAPE);

        salveaza.addClickListener(event -> valideazaSiSalveaza());
        sterge.addClickListener(event -> fireEvent(new StergeEvent(this, membri)));
        anuleaza.addClickListener(event -> fireEvent(new AnuleazaEvent(this)));

        binder.addStatusChangeListener(e -> salveaza.setEnabled(binder.isValid()));
        return new HorizontalLayout(salveaza, sterge, anuleaza);
    }

    private void valideazaSiSalveaza(){
        try {
            binder.writeBean(membri);
            fireEvent(new SalveazaEvent(this, membri));
        } catch (ValidationException e){
            e.printStackTrace();
        }
    }

    public void setMembru(Membri membri){
        this.membri = membri;
        binder.readBean(membri);
    }

    public static abstract class ContactFormEvent extends ComponentEvent<ContactForm>{
        private Membri membri;

        protected ContactFormEvent(ContactForm source, Membri membri){
            super(source, false);
            this.membri = membri;
        }
        public Membri getMembri(){
            return membri;
        }
    }
    public static class SalveazaEvent extends ContactFormEvent{
        SalveazaEvent(ContactForm source, Membri membri){
            super(source, membri);
        }
    }
    public static class StergeEvent extends ContactFormEvent{
        StergeEvent(ContactForm source, Membri membri){
            super(source, membri);
        }
    }
    public static class AnuleazaEvent extends ContactFormEvent{
        AnuleazaEvent(ContactForm source){
            super(source, null);
        }
    }
    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener){
        return getEventBus().addListener(eventType, listener);
    }


}
