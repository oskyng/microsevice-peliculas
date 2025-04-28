package com.example.oscar.peliculas;

import com.example.oscar.peliculas.controller.PeliculaController;
import com.example.oscar.peliculas.hateoas.PeliculaModelAssembler;
import com.example.oscar.peliculas.model.Pelicula;
import com.example.oscar.peliculas.services.PeliculaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PeliculaController.class)
class PeliculaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("removal")
    @MockBean
    private PeliculaService peliculaService;

    @SuppressWarnings("removal")
    @MockBean
    private PeliculaModelAssembler peliculaAssembler;

    @Test
    @WithMockUser(username = "admin", password = "admin123", roles = { "ADMIN" })
    void testObtenerPorId() throws Exception {
        Pelicula pelicula = new Pelicula(1L, "Matrix", 1999, "Wachowski", "Acción", "Ciencia ficción");

        EntityModel<Pelicula> peliculaModel = EntityModel.of(pelicula,
                linkTo(methodOn(PeliculaController.class).obtenerPorId(1L)).withSelfRel(),
                linkTo(methodOn(PeliculaController.class).eliminar(1L)).withRel("delete"),
                linkTo(methodOn(PeliculaController.class).actualizar(null)).withRel("update"),
                linkTo(methodOn(PeliculaController.class).obtenerTodas()).withRel("all"));

        when(peliculaService.obtenerPorId(1L)).thenReturn(pelicula);
        when(peliculaAssembler.toModel(pelicula)).thenReturn(peliculaModel);

        mockMvc.perform(get("/peliculas/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.data[0].id").value(1))
                .andExpect(jsonPath("$.data[0].titulo").value("Matrix"))
                .andExpect(jsonPath("$.data[0].anno").value(1999))
                .andExpect(jsonPath("$.data[0].genero").value("Acción"))
                .andExpect(jsonPath("$.data[0].sinopsis").value("Ciencia ficción"))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("Película encontrada"))
                .andExpect(jsonPath("$.cantidad").value(1));
    }
}
