package com.example.Veterinaria.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "mascotas")
@Data
@NoArgsConstructor
@AllArgsConstructor



public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String especie;
    private String raza;
    private Integer edad;
    private Double peso;

    // Muchas Mascotas pertenecen a 1 Propietario
    @ManyToOne
    @JoinColumn(name = "propietario_id", nullable = false)
    private Propietario propietario;

    // 1 Mascota tiene 1 única Historia Clínica
    @OneToOne(mappedBy = "mascota", cascade = CascadeType.ALL, orphanRemoval = true)
    private HistoriaClinica historiaClinica;

    // Relación ManyToMany con Veterinarios
    @ManyToMany
    @JoinTable(
            name = "mascota_veterinario",
            joinColumns = @JoinColumn(name = "mascota_id"),
            inverseJoinColumns = @JoinColumn(name = "veterinario_id")
    )
    private List<Veterinario> veterinarios;

    // Método auxiliar para asegurar la relación bidireccional en el OneToOne
    public void setHistoriaClinica(HistoriaClinica historiaClinica) {
        this.historiaClinica = historiaClinica;
        if (historiaClinica != null) {
            historiaClinica.setMascota(this);
        }
    }




}