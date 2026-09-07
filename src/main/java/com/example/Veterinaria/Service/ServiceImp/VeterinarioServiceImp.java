package com.example.Veterinaria.Service.ServiceImp;

import com.example.Veterinaria.Entity.HistoriaClinica;
import com.example.Veterinaria.Service.VeterinarioService;
import com.example.Veterinaria.Entity.Veterinario;
import com.example.Veterinaria.Repository.VeterinarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VeterinarioServiceImp implements VeterinarioService {

    private final VeterinarioRepository veterinarioRepository;

    @Override
    public List<Veterinario> listarTodos() {
        return veterinarioRepository.findAll();
    }

    @Override
    public Veterinario buscarPorId(Long id) {
        return veterinarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veterinario no encontrado con el ID: " + id));
    }

    @Override
    public Veterinario guardar(Veterinario veterinario) {
        return veterinarioRepository.save(veterinario);
    }

    @Override
    public Veterinario actualizar(Long id, Veterinario veterinarioDetalles) {
        Veterinario veterinarioExistente = buscarPorId(id);

        veterinarioExistente.setNombre(veterinarioDetalles.getNombre());
        veterinarioExistente.setTarjetaProfesional(veterinarioDetalles.getTarjetaProfesional());
        veterinarioExistente.setEspecialidad(veterinarioDetalles.getEspecialidad());
        veterinarioExistente.setCorreo(veterinarioDetalles.getCorreo());

        return veterinarioRepository.save(veterinarioExistente);
    }

    @Override
    public void eliminar(Long id) {
        Veterinario veterinario = buscarPorId(id);
        veterinarioRepository.delete(veterinario);
    }


}
