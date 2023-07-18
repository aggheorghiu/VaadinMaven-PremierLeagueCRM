package com.example.application.data.repository;

import com.example.application.data.entity.Stadioane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StadioaneRepository extends JpaRepository<Stadioane, Long> {
    @Query("select c from Stadioane c " +
            "where lower(c.nume) like lower(concat('%', :searchTerm, '%'))")
    List<Stadioane> search(@Param("searchTerm") String searchTerm);
}
