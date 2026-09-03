package com.example.Veterinaria.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "propietarios")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Propietario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String documento;
    private String telefono;
    private String correo;

    // 1 Propietario puede tener muchas Mascotas
    @OneToMany(mappedBy = "propietario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mascota> mascotas;


}
