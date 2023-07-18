package com.example.application.data.service;

import com.example.application.data.entity.Echipa;
import com.example.application.data.entity.Membri;
import com.example.application.data.entity.Rol;
import com.example.application.data.repository.EchipaRepository;
import com.example.application.data.repository.MembriRepository;
import com.example.application.data.repository.RolRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrmService {
    private final MembriRepository membriRepository;
    private final EchipaRepository echipaRepository;
    private final RolRepository rolRepository;

    public CrmService(MembriRepository membriRepository,
                      EchipaRepository echipaRepository,
                      RolRepository rolRepository){
        this.membriRepository = membriRepository;
        this.echipaRepository = echipaRepository;
        this.rolRepository = rolRepository;
    }

    public List<Membri> gasesteTotiMembrii(String stringFilter){
        if(stringFilter == null || stringFilter.isEmpty()){
            return membriRepository.findAll();
        } else {
            return membriRepository.search(stringFilter);
        }
    }

    public long numaraMembri(){
        return membriRepository.count();
    }

    public void stergeMembri(Membri membri){
        membriRepository.delete(membri);
    }

    public void salveazaMembri(Membri membri){
        if(membri == null){
            System.err.println("Membrul nu exista. Ai completat formularul corect?");
            return;
        }
        membriRepository.save(membri);
    }

    public List<Echipa> gasesteToateEchipele(){
        return echipaRepository.findAll();
    }

    public List<Rol> gasesteToateRolurile(){
        return rolRepository.findAll();
    }
}
