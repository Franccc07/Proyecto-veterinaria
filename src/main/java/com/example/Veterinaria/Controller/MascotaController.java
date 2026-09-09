package com.example.Veterinaria.Controller;

import com.example.Veterinaria.Entity.Mascota;
import com.example.Veterinaria.Service.MascotaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
@RequiredArgsConstructor

public class MascotaController {

    private final MascotaService service;

    @GetMapping
    public List<Mascota> listar() {
        return service.listarTodas();
    }

    @GetMapping("/{id}")
    public Mascota buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping("/propietario/{propietarioId}")
    public List<Mascota> buscarPorPropietario(@PathVariable Long propietarioId) {
        return service.buscarPorPropietario(propietarioId);
    }

    @PostMapping("/propietario/{propietarioId}")
    public ResponseEntity<Mascota> guardar(@Valid @RequestBody Mascota mascota, @PathVariable Long propietarioId) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.guardar(mascota, propietarioId));
    }

    @PutMapping("/{id}")
    public Mascota actualizar(@PathVariable Long id, @Valid @RequestBody Mascota mascota) {
        return service.actualizar(id, mascota);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{mascotaId}/veterinarios/{veterinarioId}")
    public Mascota asignarVeterinario(@PathVariable Long mascotaId, @PathVariable Long veterinarioId) {
        return service.asignarVeterinario(mascotaId, veterinarioId);
    }


}
