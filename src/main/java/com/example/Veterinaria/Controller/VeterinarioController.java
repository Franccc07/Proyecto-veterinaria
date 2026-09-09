package com.example.Veterinaria.Controller;

import com.example.Veterinaria.Entity.Veterinario;
import com.example.Veterinaria.Service.VeterinarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/veterinarios")
@RequiredArgsConstructor

public class VeterinarioController {

    private final VeterinarioService service;

    @GetMapping
    public List<Veterinario> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Veterinario buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<Veterinario> guardar(@Valid @RequestBody Veterinario veterinario) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.guardar(veterinario));
    }

    @PutMapping("/{id}")
    public Veterinario actualizar(@PathVariable Long id, @Valid @RequestBody Veterinario veterinario) {
        return service.actualizar(id, veterinario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
