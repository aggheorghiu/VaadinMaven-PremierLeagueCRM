package com.example.application.data.entity;

import javax.persistence.Entity;

@Entity
public class Rol extends EntitateAbstracta {
    private String nume;

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Rol() {
    }

    public Rol(String nume) {
        this.nume = nume;
    }
}
