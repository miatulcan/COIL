package com.ejemplo.coilmatching.model;

import jakarta.persistence.*;

@Entity
public class Curso {
    @Id
    private String codigo;
    private String nombre;
    private String idioma;
    private String tematica;

    @ManyToOne
    private Universidad universidad;

    // Getters y Setters
}