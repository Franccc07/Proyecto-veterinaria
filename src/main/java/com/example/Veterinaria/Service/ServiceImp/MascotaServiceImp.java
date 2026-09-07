package com.example.Veterinaria.Service.ServiceImp;

import com.example.Veterinaria.Entity.Mascota;
import com.example.Veterinaria.Entity.Propietario;
import com.example.Veterinaria.Entity.Veterinario;
import com.example.Veterinaria.Repository.MascotaRepository;
import com.example.Veterinaria.Repository.PropietarioRepository;
import com.example.Veterinaria.Repository.VeterinarioRepository;
import com.example.Veterinaria.Service.MascotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MascotaServiceImp implements MascotaService {

    private final MascotaRepository mascotaRepository;
    private final PropietarioRepository propietarioRepository;
    private final VeterinarioRepository veterinarioRepository;

    @Override
    public List<Mascota> listarTodas() {
        return mascotaRepository.findAll();
    }

    @Override
    public Mascota buscarPorId(Long id) {
        return mascotaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada con el ID: " + id));
    }

    @Override
    public Mascota guardar(Mascota mascota, Long propietarioId) {
        Propietario propietario = propietarioRepository.findById(propietarioId)
                .orElseThrow(() -> new RuntimeException("Propietario no encontrado con el ID: " + propietarioId));

        mascota.setPropietario(propietario);
        return mascotaRepository.save(mascota);
    }

    @Override
    public Mascota actualizar(Long id, Mascota mascotaDetalles) {
        Mascota mascotaExistente = buscarPorId(id);

        mascotaExistente.setNombre(mascotaDetalles.getNombre());
        mascotaExistente.setEspecie(mascotaDetalles.getEspecie());
        mascotaExistente.setRaza(mascotaDetalles.getRaza());
        mascotaExistente.setEdad(mascotaDetalles.getEdad());
        mascotaExistente.setPeso(mascotaDetalles.getPeso());

        return mascotaRepository.save(mascotaExistente);
    }

    @Override
    public void eliminar(Long id) {
        Mascota mascota = buscarPorId(id);
        mascotaRepository.delete(mascota);
    }

    @Override
    public List<Mascota> buscarPorPropietario(Long propietarioId) {
        return mascotaRepository.findByPropietarioId(propietarioId);
    }

    @Override
    public Mascota asignarVeterinario(Long mascotaId, Long veterinarioId) {
        Mascota mascota = buscarPorId(mascotaId);
        Veterinario veterinario = veterinarioRepository.findById(veterinarioId)
                .orElseThrow(() -> new RuntimeException("Veterinario no encontrado con el ID: " + veterinarioId));

        if (!mascota.getVeterinarios().contains(veterinario)) {
            mascota.getVeterinarios().add(veterinario);
        }

        return mascotaRepository.save(mascota);
    }
}
