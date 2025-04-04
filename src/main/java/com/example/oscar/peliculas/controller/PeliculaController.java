package com.example.oscar.peliculas.controller;

import java.util.List;

import com.example.oscar.peliculas.model.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> obtenerTodas() {
        List<Pelicula> peliculas = peliculaService.obtenerTodas();
        if (peliculas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No hay películas registradas actualmente");
        }

        ResponseWrapper<Pelicula> respuesta = new ResponseWrapper<>(
                "OK",
                peliculas.size(),
                peliculas);

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{id}")
    public Pelicula obtenerPorId(@PathVariable Long id) {
        return peliculaService.obtenerPorId(id);
    }
}
