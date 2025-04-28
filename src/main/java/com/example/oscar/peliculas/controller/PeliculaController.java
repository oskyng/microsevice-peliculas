package com.example.oscar.peliculas.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.example.oscar.peliculas.entity.CreatePeliculaRequest;
import com.example.oscar.peliculas.entity.UpdatePeliculaRequest;
import com.example.oscar.peliculas.hateoas.PeliculaModelAssembler;
import com.example.oscar.peliculas.model.ResponseWrapper;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.oscar.peliculas.services.PeliculaService;
import com.example.oscar.peliculas.model.Pelicula;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@RestController
@RequestMapping("/peliculas")
public class PeliculaController {
    private final PeliculaService peliculaService;
    private final PeliculaModelAssembler assembler;
    
    public PeliculaController(PeliculaService peliculaService, PeliculaModelAssembler assembler) {
        this.peliculaService = peliculaService;
        this.assembler = assembler;
    }

    @GetMapping
    public ResponseEntity<?> obtenerTodas() {
        log.info("GET /peliculas - Obteniendo todas las películas");

        List<Pelicula> peliculas = peliculaService.obtenerTodas();

        if (peliculas.isEmpty()) {
            log.warn("No hay películas registradas actualmente");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CollectionModel.empty());
        }

        List<EntityModel<Pelicula>> modelos = peliculas.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(modelos, linkTo(methodOn(PeliculaController.class).obtenerTodas()).withSelfRel()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        log.info("GET /peliculas/{} - Buscando película por ID", id);
        Pelicula pelicula = peliculaService.obtenerPorId(id);
        return ResponseEntity.ok(new ResponseWrapper<>(HttpStatus.OK.value(), "Película encontrada", 1, List.of(pelicula)));
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<Pelicula>> guardar(@Valid @RequestBody CreatePeliculaRequest request) {
        log.info("POST /peliculas - Creando película: {}", request.getTitulo());
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper<>(HttpStatus.CREATED.value(), "Película creada exitosamente", 1, List.of(peliculaService.guardar(request))));
    }

    @PutMapping
    public ResponseEntity<ResponseWrapper<Pelicula>> actualizar(@Valid @RequestBody UpdatePeliculaRequest request) {
        log.info("PUT /peliculas/{} - Actualizando película", request.getTitulo());
        return ResponseEntity.ok(new ResponseWrapper<>(HttpStatus.CREATED.value(),"Película creada exitosamente", 1, List.of(peliculaService.actualizar(request))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Pelicula>> eliminar(@PathVariable Long id) {
        log.warn("DELETE /peliculas/{} - Eliminando película", id);
        peliculaService.eliminar(id);
        return ResponseEntity.ok(new ResponseWrapper<>(HttpStatus.OK.value(), "Pelicula eliminada exitosamente", 1, null));
    }
}
