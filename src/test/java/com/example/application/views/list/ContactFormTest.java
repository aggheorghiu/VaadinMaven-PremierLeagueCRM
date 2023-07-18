package com.example.application.views.list;

import com.example.application.data.entity.Echipa;
import com.example.application.data.entity.Membri;
import com.example.application.data.entity.Rol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactFormTest {
    private List<Echipa> echipe;
    private List<Rol> roluri;
    private Membri gheorghiuAlin;
    private Echipa echipa1;
    private Echipa echipa2;
    private Rol rol1;
    private Rol rol2;

    @BeforeEach
    public void setupData() {
        echipe = new ArrayList<>();
        echipa1 = new Echipa();
        echipa1.setDenumireEchipa("PL Ltd");
        echipa2 = new Echipa();
        echipa2.setDenumireEchipa("IT Mill");
        echipe.add(echipa1);
        echipe.add(echipa2);

        roluri = new ArrayList<>();
        rol1 = new Rol();
        rol1.setNume("Rol 1");
        rol2 = new Rol();
        rol2.setNume("Rol 2");
        roluri.add(rol1);
        roluri.add(rol2);

        gheorghiuAlin = new Membri();
        gheorghiuAlin.setNume("Gheorghiu");
        gheorghiuAlin.setPrenume("Alin");
        gheorghiuAlin.setNationalitate("Romana");
        gheorghiuAlin.setRol(rol1);
        gheorghiuAlin.setEchipa(echipa2);
    }

    @Test
    public void formFieldsPopulated() {
        ContactForm form = new ContactForm(echipe, roluri);
        form.setMembru(gheorghiuAlin);
        assertEquals("Gheorghiu", form.nume.getValue());
        assertEquals("Alin", form.prenume.getValue());
        assertEquals("Romana", form.nationalitate.getValue());
        assertEquals(echipa2, form.echipa.getValue());
        assertEquals(rol1, form.rol.getValue());
    }


    @Test
    public void saveEventHasCorrectValues() {
        ContactForm form = new ContactForm(echipe, roluri);
        Membri membri = new Membri();
        form.setMembru(membri);
        form.nume.setValue("Spinu");
        form.prenume.setValue("Marius");
        form.echipa.setValue(echipa1);
        form.nationalitate.setValue("Romana");
        form.rol.setValue(rol2);

        AtomicReference<Membri> savedContactRef = new AtomicReference<>(null);
        form.addListener(ContactForm.SalveazaEvent.class, e -> {
            savedContactRef.set(e.getMembri());
        });
        form.salveaza.click();
        Membri membruSalvat = savedContactRef.get();

        assertEquals("Spinu", membruSalvat.getNume());
        assertEquals("Marius", membruSalvat.getPrenume());
        assertEquals("Romana", membruSalvat.getNationalitate());
        assertEquals(echipa1, membruSalvat.getEchipa());
        assertEquals(rol2, membruSalvat.getRol());
    }
}