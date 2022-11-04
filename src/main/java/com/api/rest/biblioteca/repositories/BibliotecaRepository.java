package com.api.rest.biblioteca.repositories;

import com.api.rest.biblioteca.entities.Biblioteca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BibliotecaRepository extends JpaRepository <Biblioteca, Long> {
}
