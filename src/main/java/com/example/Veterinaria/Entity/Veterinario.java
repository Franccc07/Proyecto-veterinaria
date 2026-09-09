package com.example.Veterinaria.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "veterinarios")
@Data

public class Veterinario {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String nombre;

    @Column(name = "tarjeta_profesional")
    private String tarjetaProfesional;

    private String especialidad;
    private String correo;

    // 1 Veterinario puede atender varias Mascotas (lado inverso de la relación ManyToMany)
    @ManyToMany(mappedBy = "veterinarios")
    @JsonIgnoreProperties("veterinarios")
    @Schema(hidden = true)
    private List<Mascota> mascotas;


}