package com.example.application.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Membri extends EntitateAbstracta{
    @NotEmpty
    private String nume = "";
    @NotEmpty
    private String prenume = "";
    @NotEmpty
    private String nationalitate = "";
    @ManyToOne
    @JoinColumn(name = "echipa_id")
    @NotNull
    @JsonIgnoreProperties({"employees"})
    private Echipa echipa;

    @ManyToOne
    @NotNull
    private Rol rol;

    public Rol getRol() {
        return rol;
    }
    public void setRol(Rol rol) {
        this.rol = rol;
    }
    public Echipa getEchipa() {
        return echipa;
    }
    public void setEchipa(Echipa echipa) {
        this.echipa = echipa;
    }
    public String getNume() {
        return nume;
    }
    public void setNume(String nume) {this.nume = nume;}
    public String getPrenume() {
        return prenume;
    }
    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }
    public String getNationalitate() {
        return nationalitate;
    }
    public void setNationalitate(String nationalitate) {
        this.nationalitate = nationalitate;
    }


    @Override
    public String toString() {
        return "Membri{" +
                "nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", echipa=" + echipa +
                '}';
    }
}
