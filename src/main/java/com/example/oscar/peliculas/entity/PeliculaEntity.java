package com.example.oscar.peliculas.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PeliculaEntity {
    private String titulo;
    private Integer anno;
    private String director;
    private String genero;
    private String sinopsis;
}
