package com.example.Veterinaria.Repository;

import com.example.Veterinaria.Entity.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeterinarioRepository extends JpaRepository <Veterinario, Long> {
}
