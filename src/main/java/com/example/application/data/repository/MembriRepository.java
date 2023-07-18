package com.example.application.data.repository;

import com.example.application.data.entity.Membri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MembriRepository extends JpaRepository<Membri, Long> {
    @Query("select c from Membri c " +
            "where lower(c.nume) like lower(concat('%', :searchTerm, '%'))" +
            "or lower(c.prenume) like lower(concat('%', :searchTerm, '%'))")
    List<Membri> search(@Param("searchTerm") String searchTerm);
}
