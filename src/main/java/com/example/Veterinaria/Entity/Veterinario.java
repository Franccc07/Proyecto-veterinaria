package com.example.Veterinaria.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "veterinarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Veterinario {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(name = "tarjeta_profesional")
    private String tarjetaProfesional;

    private String especialidad;
    private String correo;

    // 1 Veterinario puede atender varias Mascotas (lado inverso de la relación ManyToMany)
    @ManyToMany(mappedBy = "veterinarios")
    private List<Mascota> mascotas;


}