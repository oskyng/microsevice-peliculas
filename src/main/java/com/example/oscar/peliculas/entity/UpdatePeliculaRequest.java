package com.example.oscar.peliculas.entity;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePeliculaRequest {
    @NotNull(message = "El ID no puede ser nulo")
    @Positive(message = "El ID debe ser un número positivo")
    private Long id;
    @NotBlank(message = "El título no puede estar vacío")
    @Size(min = 1, max = 100, message = "El título debe tener entre 1 y 100 caracteres")
    private String titulo;
    @Min(value = 1880, message = "El año debe ser mayor a 1880")
    @Max(value = 2100, message = "El año debe ser razonable (antes del 2100)")
    private Integer anno;
    @NotBlank(message = "El nombre del director no puede estar vacío")
    @Size(min = 1, max = 150, message = "El nombre del director debe tener entre 1 y 100 caracteres")
    private String director;
    @NotBlank(message = "El género no puede estar vacío")
    @Size(min = 1, max = 50, message = "El género debe tener entre 1 y 50 caracteres")
    private String genero;
    @NotBlank(message = "La sinopsis no puede estar vacío")
    @Size(max = 255, message = "La sinopsis no debe superar los 255 caracteres")
    private String sinopsis;
}
