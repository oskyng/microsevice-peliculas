package com.example.oscar.peliculas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.oscar.peliculas.model.Pelicula;

@Repository
public interface IPeliculaRepository extends JpaRepository<Pelicula, Long> {

}
