package com.example.constructora.repository;

import com.example.constructora.domain.CategoriaLaboral;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatLaboralRepository extends JpaRepository<CategoriaLaboral, String> {
}
