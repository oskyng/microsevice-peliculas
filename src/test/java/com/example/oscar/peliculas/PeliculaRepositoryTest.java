package com.example.oscar.peliculas;

import com.example.oscar.peliculas.model.Pelicula;
import com.example.oscar.peliculas.repository.IPeliculaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class PeliculaRepositoryTest {
    @Autowired
    private IPeliculaRepository peliculaRepository;

    @Test
    void testGuardarYBuscar() {
        Pelicula pelicula = new Pelicula(null, "Titanic", 1997, "Cameron", "Romance", "Historia del barco");

        peliculaRepository.save(pelicula);

        Optional<Pelicula> encontrada = peliculaRepository.findById(pelicula.getId());

        assertTrue(encontrada.isPresent());

        assertEquals("Titanic", encontrada.get().getTitulo());
    }
}
