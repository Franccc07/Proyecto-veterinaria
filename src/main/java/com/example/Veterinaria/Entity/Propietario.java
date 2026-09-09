package com.example.Veterinaria.Entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "propietarios")
@Data

public class Propietario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String nombre;
    private String documento;
    private String telefono;
    private String correo;

    @OneToMany(mappedBy = "propietario", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("propietario") // Detiene el bucle hacia Mascota
    @Schema(hidden = true) // Oculta este campo del JSON de prueba en Swagger POST
    private List<Mascota> mascotas;


}
