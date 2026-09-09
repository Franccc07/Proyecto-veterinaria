package com.example.Veterinaria.Service.ServiceImp;

import com.example.Veterinaria.Entity.Propietario;
import com.example.Veterinaria.Repository.PropietarioRepository;
import com.example.Veterinaria.Service.PropietarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropietarioServiceImp implements PropietarioService {

    private final PropietarioRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Propietario> listarTodos() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Propietario buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Propietario no encontrado con el ID: " + id));
    }

    @Override
    @Transactional
    public Propietario guardar(Propietario propietario) {
        return repository.save(propietario);
    }

    @Override
    @Transactional
    public Propietario actualizar(Long id, Propietario propietarioDatos) {
        Propietario actual = buscarPorId(id);
        actual.setNombre(propietarioDatos.getNombre());
        actual.setDocumento(propietarioDatos.getDocumento());
        actual.setTelefono(propietarioDatos.getTelefono());
        actual.setCorreo(propietarioDatos.getCorreo());
        return repository.save(actual);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        Propietario propietario = buscarPorId(id);
        repository.delete(propietario);
    }
}
