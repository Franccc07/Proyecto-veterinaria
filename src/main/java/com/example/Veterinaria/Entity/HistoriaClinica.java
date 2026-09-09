package com.example.Veterinaria.Entity;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "historias_clinicas")
@Data
public class HistoriaClinica {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column(name = "fecha_apertura")
    private LocalDate fechaApertura;

    @Column(columnDefinition = "TEXT")
    private String antecedentes;

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    // 1 Historia Clínica pertenece a 1 única Mascota (Clave Foránea aquí)
    @OneToOne
    @JoinColumn(name = "mascota_id", nullable = false, unique = true)
    @JsonIgnoreProperties("historiaClinica")
    private Mascota mascota;
}