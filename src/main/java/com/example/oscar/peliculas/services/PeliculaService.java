package com.example.oscar.peliculas.services;

import org.springframework.stereotype.Service;

import com.example.oscar.peliculas.model.Pelicula;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PeliculaService {
    private final List<Pelicula> peliculas = new ArrayList<>();
    
    public PeliculaService() {
        peliculas.add(new Pelicula(1L, "Interstellar", 2014, "Christopher Nolan", "Ciencia Ficción", "Exploración del espacio y el tiempo."));
        peliculas.add(new Pelicula(2L, "Inception", 2010, "Christopher Nolan", "Acción", "Los sueños dentro de los sueños."));
        peliculas.add(new Pelicula(3L, "Matrix", 1999, "Lana Wachowski", "Ciencia Ficción", "La realidad no es lo que parece."));
        peliculas.add(new Pelicula(4L, "Gladiador", 2000, "Ridley Scott", "Acción", "La venganza de un general traicionado."));
        peliculas.add(new Pelicula(5L, "Titanic", 1997, "James Cameron", "Drama", "Historia de amor en el trágico hundimiento."));
    }

    public List<Pelicula> obtenerTodas() {
        return peliculas;
    }

    public Optional<Pelicula> obtenerPorId(Long id) {
        return peliculas.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public Pelicula guardar(Pelicula pelicula) {
        peliculas.add(pelicula);
        return pelicula;
    }

    public Pelicula actualizar(Long id, Pelicula pelicula) {
        Optional<Pelicula> findPelicula = peliculas.stream().filter(p -> p.getId().equals(id)).findFirst();
        return findPelicula.map(p -> {
            p.setTitulo((pelicula.getTitulo() != null) ? pelicula.getTitulo() : p.getTitulo());
            p.setAño((pelicula.getAño() != null) ? pelicula.getAño() : p.getAño());
            p.setDirector((pelicula.getDirector() != null) ? pelicula.getDirector() : p.getDirector());
            p.setGenero((pelicula.getGenero() != null) ? pelicula.getGenero() : p.getGenero());
            p.setSinopsis((pelicula.getSinopsis() != null) ? pelicula.getSinopsis() : p.getSinopsis());
            return p;
        }).orElse(null);
    }
}
