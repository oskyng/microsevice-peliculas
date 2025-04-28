package com.example.oscar.peliculas;

import com.example.oscar.peliculas.exception.PeliculaNotFoundException;
import com.example.oscar.peliculas.model.Pelicula;
import com.example.oscar.peliculas.repository.IPeliculaRepository;
import com.example.oscar.peliculas.services.PeliculaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PeliculaServiceTest {
    private IPeliculaRepository peliculaRepository;
    private PeliculaService peliculaService;

    @BeforeEach
    public void setUp() {
        peliculaRepository = mock(IPeliculaRepository.class);
        peliculaService = new PeliculaService(peliculaRepository);
    }

    @Test
    public void testObtenerTodas() {
        Pelicula p1 = new Pelicula(1L, "Pelicula 1", 2020, "Director 1", "Acción", "Sinopsis 1");
        Pelicula p2 = new Pelicula(2L, "Pelicula 2", 2021, "Director 2", "Comedia", "Sinopsis 2");

        when(peliculaRepository.findAll(Sort.by("id").ascending())).thenReturn(Arrays.asList(p1, p2));

        List<Pelicula> resultado = peliculaService.obtenerTodas();

        assertEquals(2, resultado.size());
        assertEquals("Pelicula 1", resultado.get(0).getTitulo());
    }

    @Test
    public void testObtenerPorId_existente() {
        Pelicula pelicula = new Pelicula(1L, "Pelicula Test", 2022, "Director X", "Drama", "Sinopsis");

        when(peliculaRepository.findById(1L)).thenReturn(Optional.of(pelicula));

        Pelicula resultado = peliculaService.obtenerPorId(1L);

        assertEquals("Pelicula Test", resultado.getTitulo());
        assertEquals(2022, resultado.getAnno());
    }

    @Test
    public void testObtenerPorId_noExistente() {
        when(peliculaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(PeliculaNotFoundException.class, () -> {
            peliculaService.obtenerPorId(99L);
        });
    }
}
