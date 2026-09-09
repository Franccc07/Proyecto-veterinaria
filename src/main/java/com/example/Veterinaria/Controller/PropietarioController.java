package com.example.Veterinaria.Controller;

import com.example.Veterinaria.Entity.Propietario;
import com.example.Veterinaria.Service.PropietarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/propietarios")
@RequiredArgsConstructor

public class PropietarioController {

    private final PropietarioService service;

    @GetMapping
    public List<Propietario> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Propietario buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<Propietario> guardar(@Valid @RequestBody Propietario propietario) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.guardar(propietario));
    }

    @PutMapping("/{id}")
    public Propietario actualizar(@PathVariable Long id, @Valid @RequestBody Propietario propietario) {
        return service.actualizar(id, propietario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
