package com.example.oscar.peliculas.services;

import com.example.oscar.peliculas.exception.PeliculaNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.oscar.peliculas.model.Pelicula;
import com.example.oscar.peliculas.repository.IPeliculaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PeliculaService {
    private final List<Pelicula> peliculas = new ArrayList<>();
    
    @Autowired
    private IPeliculaRepository peliculaRepository;

    public List<Pelicula> obtenerTodas() {
        return peliculaRepository.findAll();
    }

    public Pelicula obtenerPorId(Long id) {
        return peliculaRepository.findById(id).orElseThrow(() -> new PeliculaNotFoundException(id));
    }

    public Pelicula guardar(Pelicula pelicula) {
        peliculas.add(pelicula);
        return pelicula;
    }

    public Pelicula actualizar(Long id, Pelicula pelicula) {
        Optional<Pelicula> findPelicula = peliculas.stream().filter(p -> p.getId().equals(id)).findFirst();
        return findPelicula.map(p -> {
            p.setTitulo((pelicula.getTitulo() != null) ? pelicula.getTitulo() : p.getTitulo());
            p.setAnno((pelicula.getAnno() != null) ? pelicula.getAnno() : p.getAnno());
            p.setDirector((pelicula.getDirector() != null) ? pelicula.getDirector() : p.getDirector());
            p.setGenero((pelicula.getGenero() != null) ? pelicula.getGenero() : p.getGenero());
            p.setSinopsis((pelicula.getSinopsis() != null) ? pelicula.getSinopsis() : p.getSinopsis());
            return p;
        }).orElse(null);
    }
}
