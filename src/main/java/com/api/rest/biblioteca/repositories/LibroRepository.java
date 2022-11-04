package com.api.rest.biblioteca.repositories;

import com.api.rest.biblioteca.entities.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository <Libro, Long>{
}
