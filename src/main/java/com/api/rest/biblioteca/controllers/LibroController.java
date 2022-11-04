package com.api.rest.biblioteca.controllers;

import com.api.rest.biblioteca.entities.Biblioteca;
import com.api.rest.biblioteca.entities.Libro;
import com.api.rest.biblioteca.repositories.BibliotecaRepository;
import com.api.rest.biblioteca.repositories.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private BibliotecaRepository bibliotecaRepository;

    @GetMapping
    public ResponseEntity<Page<Libro>> listarLibros(Pageable pageable){
        return ResponseEntity.ok(libroRepository.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> listarLibrosPorId(@PathVariable Long id){

        Optional<Libro> libroOptional = libroRepository.findById(id);

        if(!libroOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(libroOptional.get());

    }

    @PostMapping
    public ResponseEntity<Libro> guardarLibro(@Valid @RequestBody Libro libro){

        Optional<Biblioteca> bibliotecaOptional = bibliotecaRepository.findById(libro.getBiblioteca().getId());

        if(!bibliotecaOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }

        libro.setBiblioteca(bibliotecaOptional.get());
        Libro libroGuardado = libroRepository.save(libro);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(libroGuardado.getId()).toUri();

        return ResponseEntity.created(ubicacion).body(libroGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizarLibro(@Valid @RequestBody Libro libro, @PathVariable Long id){

        Optional<Biblioteca> bibliotecaOptional = bibliotecaRepository.findById(libro.getBiblioteca().getId());

        if(!bibliotecaOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Libro> libroOptional = libroRepository.findById(id);
        if(!libroOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }

        libro.setBiblioteca(bibliotecaOptional.get());
        libro.setId(libroOptional.get().getId());
        libroRepository.save(libro);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Libro> elliminarLibro(@PathVariable Long id){
        Optional<Libro> libroOptional = libroRepository.findById(id);

        if(!libroOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }

        libroRepository.delete(libroOptional.get());

        return ResponseEntity.noContent().build();
    }

}
