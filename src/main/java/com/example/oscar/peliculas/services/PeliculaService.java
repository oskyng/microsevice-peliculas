package com.example.oscar.peliculas.services;

import com.example.oscar.peliculas.entity.CreatePeliculaRequest;
import com.example.oscar.peliculas.entity.UpdatePeliculaRequest;
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
    @Autowired
    private IPeliculaRepository peliculaRepository;

    public List<Pelicula> obtenerTodas() {
        return peliculaRepository.findAll();
    }

    public Pelicula obtenerPorId(Long id) {
        return peliculaRepository.findById(id).orElseThrow(() -> new PeliculaNotFoundException(id));
    }

    public Pelicula guardar(CreatePeliculaRequest request) {
        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo(request.getTitulo());
        pelicula.setAnno(request.getAnno());
        pelicula.setGenero(request.getGenero());
        pelicula.setDirector(request.getDirector());
        pelicula.setSinopsis(request.getSinopsis());

        return peliculaRepository.save(pelicula);
    }

    public Pelicula actualizar(UpdatePeliculaRequest request) {
        Optional<Pelicula> findPelicula = peliculaRepository.findById(request.getId());
        return findPelicula.map(p -> {
            p.setId(request.getId());
            p.setTitulo((request.getTitulo() != null) ? request.getTitulo() : p.getTitulo());
            p.setAnno((request.getAnno() != null) ? request.getAnno() : p.getAnno());
            p.setDirector((request.getDirector() != null) ? request.getDirector() : p.getDirector());
            p.setGenero((request.getGenero() != null) ? request.getGenero() : p.getGenero());
            p.setSinopsis((request.getSinopsis() != null) ? request.getSinopsis() : p.getSinopsis());

            return peliculaRepository.save(p);

        }).orElseThrow(() -> new PeliculaNotFoundException(request.getId()));
    }

    public void eliminar(Long id) {
        Pelicula findPelicula = peliculaRepository.findById(id).orElseThrow(() -> new PeliculaNotFoundException(id));
        peliculaRepository.delete(findPelicula);
    }
}
