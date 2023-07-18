package com.example.application.data.entity;

import org.hibernate.annotations.Formula;

import javax.annotation.Nullable;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Echipa extends EntitateAbstracta{
    @NotBlank
    private String denumireEchipa;
    private Double valoareLot;

    @OneToMany(mappedBy = "echipa")
    @Nullable
    private List<Membri> employees = new LinkedList<>();

    @Formula("(select count(m.id) from Membri m where m.echipa_id = id)")
    private int employeeCount;
    public int getEmployeeCount(){
        return employeeCount;
    }

    public String getDenumireEchipa() {
        return denumireEchipa;
    }

    public void setDenumireEchipa(String denumireEchipa) {
        this.denumireEchipa = denumireEchipa;
    }

    public Double getValoareLot() {
        return valoareLot;
    }

    public void setValoareLot(Double valoareLot) {
        this.valoareLot = valoareLot;
    }


    public List<Membri> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Membri> employees) {
        this.employees = employees;
    }
}
