package com.example.Veterinaria.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "mascotas")
@Data




public class Mascota {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String nombre;
    private String especie;
    private String raza;
    private Integer edad;
    private Double peso;

    // Muchas Mascotas pertenecen a 1 Propietario
    @ManyToOne
    @JoinColumn(name = "propietario_id", nullable = false)
    @JsonIgnoreProperties("mascotas")
    private Propietario propietario;

    // 1 Mascota tiene 1 única Historia Clínica
    @OneToOne(mappedBy = "mascota", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("mascota")
    private HistoriaClinica historiaClinica;

    // Relación ManyToMany con Veterinarios
    @ManyToMany
    @JoinTable(
            name = "mascota_veterinario",
            joinColumns = @JoinColumn(name = "mascota_id"),
            inverseJoinColumns = @JoinColumn(name = "veterinario_id")
    )
    @JsonIgnoreProperties("mascotas") // Evita bucle circular en GET
    @Schema(hidden = true)            // Oculta el campo en el POST de Swagger
    private List<Veterinario> veterinarios;



    // Método auxiliar para asegurar la relación bidireccional en el OneToOne
    public void setHistoriaClinica(HistoriaClinica historiaClinica) {
        this.historiaClinica = historiaClinica;
        if (historiaClinica != null) {
            historiaClinica.setMascota(this);
        }
    }




}