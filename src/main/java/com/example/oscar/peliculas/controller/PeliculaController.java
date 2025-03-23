package com.example.oscar.peliculas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.oscar.peliculas.services.PeliculaService;
import com.example.oscar.peliculas.model.Pelicula;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {
    @Autowired
    private final PeliculaService peliculaService;
    
    public PeliculaController(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    @GetMapping
    public List<Pelicula> obtenerTodas() {
        return peliculaService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public Optional<Pelicula> obtenerPorId(@PathVariable Long id) {
        return peliculaService.obtenerPorId(id);
    }
}
