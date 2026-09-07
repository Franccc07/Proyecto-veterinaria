package com.example.Veterinaria.Service.ServiceImp;

import com.example.Veterinaria.Entity.Propietario;
import com.example.Veterinaria.Repository.PropietarioRepository;
import com.example.Veterinaria.Service.PropietarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropietarioServiceImp implements PropietarioService {
    private final PropietarioRepository propietarioRepository;

    @Override
    public List<Propietario> listarTodos() {
        return propietarioRepository.findAll();
    }

    @Override
    public Propietario buscarPorId(Long id) {
        return propietarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Propietario no encontrado con el ID: " + id));
    }

    @Override
    public Propietario guardar(Propietario propietario) {
        return propietarioRepository.save(propietario);
    }

    @Override
    public Propietario actualizar(Long id, Propietario propietarioDetalles) {
        // Buscamos si existe el propietario antes de actualizar
        Propietario propietarioExistente = buscarPorId(id);

        // Actualizamos sus campos básicos
        propietarioExistente.setNombre(propietarioDetalles.getNombre());
        propietarioExistente.setDocumento(propietarioDetalles.getDocumento());
        propietarioExistente.setTelefono(propietarioDetalles.getTelefono());
        propietarioExistente.setCorreo(propietarioDetalles.getCorreo());

        return propietarioRepository.save(propietarioExistente);
    }

    @Override
    public void eliminar(Long id) {
        Propietario propietario = buscarPorId(id);
        propietarioRepository.delete(propietario);
    }
}
