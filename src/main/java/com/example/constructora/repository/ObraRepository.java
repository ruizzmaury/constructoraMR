package com.example.constructora.repository;

import com.example.constructora.domain.Obra;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;



@Repository
public interface ObraRepository extends JpaRepository <Obra, Long> {
}
