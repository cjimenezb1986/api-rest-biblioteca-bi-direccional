package com.api.rest.biblioteca.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name ="libros", uniqueConstraints = {@UniqueConstraint(columnNames = {"nombre"})})
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nombre;

    @ManyToOne(fetch =  FetchType.LAZY, optional = false)
    @JoinColumn(name = "biblioteca_id")
    @JsonProperty(access = Access.WRITE_ONLY)
    private Biblioteca biblioteca;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }
}
