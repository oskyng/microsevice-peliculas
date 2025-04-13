package com.example.oscar.peliculas.controller;

import java.util.List;

import com.example.oscar.peliculas.entity.CreatePeliculaRequest;
import com.example.oscar.peliculas.entity.UpdatePeliculaRequest;
import com.example.oscar.peliculas.model.ResponseWrapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay películas registradas actualmente");
        }
        return ResponseEntity.ok(new ResponseWrapper<>(HttpStatus.OK.value(), "Películas encontradas", peliculas.size(), peliculas));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        Pelicula pelicula = peliculaService.obtenerPorId(id);
        return ResponseEntity.ok(new ResponseWrapper<>(HttpStatus.OK.value(), "Película encontrada", 1, List.of(pelicula)));
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<Pelicula>> guardar(@Valid @RequestBody CreatePeliculaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper<>(HttpStatus.CREATED.value(), "Película creada exitosamente", 1, List.of(peliculaService.guardar(request))));
    }

    @PutMapping
    public ResponseEntity<ResponseWrapper<Pelicula>> actualizar(@Valid @RequestBody UpdatePeliculaRequest request) {
        return ResponseEntity.ok(new ResponseWrapper<>(HttpStatus.CREATED.value(),"Película creada exitosamente", 1, List.of(peliculaService.actualizar(request))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Pelicula>> eliminar(@PathVariable Long id) {
        peliculaService.eliminar(id);
        return ResponseEntity.ok(new ResponseWrapper<>(HttpStatus.OK.value(), "Pelicula eliminada exitosamente", 1, null));
    }
}
