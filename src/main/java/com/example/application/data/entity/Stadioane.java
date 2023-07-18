package com.example.application.data.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Stadioane extends EntitateAbstracta{
    @NotBlank
    private String nume;
    @ManyToOne
    @JoinColumn(name = "echipa_id")
    private Echipa echipa;

    private Integer locuri;

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Integer getLocuri() {
        return locuri;
    }

    public void setLocuri(Integer locuri) {
        this.locuri = locuri;
    }

    public Echipa getEchipa() {
        return echipa;
    }

    public void setEchipa(Echipa echipa) {
        this.echipa = echipa;
    }

    public Stadioane() {
    }

    public Stadioane(String nume, Echipa echipa, Integer locuri) {
        this.nume = nume;
        this.echipa = echipa;
        this.locuri = locuri;
    }
}
