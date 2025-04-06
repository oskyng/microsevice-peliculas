package com.example.oscar.peliculas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PELICULAS")
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column(nullable = false, length = 100)
    private String titulo;
    @Column(nullable = false, precision = 4)
    @Max(value = 9999, message = "El año no puede ser mayor a 9999")
    private Integer anno;
    @Column(nullable = false, length = 100)
    private String director;
    @Column(nullable = false, length = 50)
    private String genero;
    @Column(nullable = false, length = 255)
    private String sinopsis;
}
