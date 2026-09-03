package com.example.Veterinaria.Entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "historias_clinicas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoriaClinica {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Mascota mascota;
}