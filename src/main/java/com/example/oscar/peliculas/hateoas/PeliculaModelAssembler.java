package com.example.oscar.peliculas.hateoas;

import com.example.oscar.peliculas.controller.PeliculaController;
import com.example.oscar.peliculas.model.Pelicula;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PeliculaModelAssembler implements RepresentationModelAssembler<Pelicula, EntityModel<Pelicula>> {
    @Override
    public EntityModel<Pelicula> toModel(Pelicula entity) {
        return EntityModel.of(
                entity,
                // Enlace al detalle de la película (GET /peliculas/{id})
                linkTo(methodOn(PeliculaController.class)
                        .obtenerPorId(entity.getId()))
                        .withSelfRel(),

                // Enlace para eliminar (DELETE /peliculas/{id})
                linkTo(methodOn(PeliculaController.class)
                        .eliminar(entity.getId()))
                        .withRel("delete"),

                // Enlace para actualizar (PUT /peliculas/{id}) – cuerpo ignorado aquí
                linkTo(methodOn(PeliculaController.class)
                        .actualizar(null))
                        .withRel("update"),

                // Enlace para ver todas las películas (GET /peliculas)
                linkTo(methodOn(PeliculaController.class)
                        .obtenerTodas())
                        .withRel("all")
        );
    }
}
