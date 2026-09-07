package com.example.Veterinaria.Service.ServiceImp;

import com.example.Veterinaria.Service.HistoriaClinicaService;
import com.example.Veterinaria.Entity.HistoriaClinica;
import com.example.Veterinaria.Entity.Mascota;
import com.example.Veterinaria.Repository.HistoriaClinicaRepository;
import com.example.Veterinaria.Repository.MascotaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoriaClinicaServiceImp implements HistoriaClinicaService {

    private final HistoriaClinicaRepository historiaClinicaRepository;
    private final MascotaRepository mascotaRepository;

    @Override
    public List<HistoriaClinica> listarTodas() {
        return historiaClinicaRepository.findAll();
    }

    @Override
    public HistoriaClinica buscarPorId(Long id) {
        return historiaClinicaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Historia clínica no encontrada con el ID: " + id));
    }

    @Override
    public HistoriaClinica crear(HistoriaClinica historia, Long mascotaId) {
        // Buscamos la mascota que tendrá esta historia clínica (Relación 1:1)
        Mascota mascota = mascotaRepository.findById(mascotaId)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada con el ID: " + mascotaId));

        // Validamos que la mascota no tenga ya otra historia clínica creada
        if (mascota.getHistoriaClinica() != null) {
            throw new RuntimeException("La mascota con ID " + mascotaId + " ya tiene una historia clínica registrada.");
        }

        // Seteamos la relación bidireccional
        historia.setMascota(mascota);
        mascota.setHistoriaClinica(historia);

        return historiaClinicaRepository.save(historia);
    }

    @Override
    public HistoriaClinica actualizar(Long id, HistoriaClinica historiaDetalles) {
        HistoriaClinica historiaExistente = buscarPorId(id);

        historiaExistente.setFechaApertura(historiaDetalles.getFechaApertura());
        historiaExistente.setAntecedentes(historiaDetalles.getAntecedentes());
        historiaExistente.setObservaciones(historiaDetalles.getObservaciones());

        return historiaClinicaRepository.save(historiaExistente);
    }

    @Override
    public void eliminar(Long id) {
        HistoriaClinica historia = buscarPorId(id);

        // Rompemos la relación con la mascota antes de borrar para evitar conflictos
        if (historia.getMascota() != null) {
            historia.getMascota().setHistoriaClinica(null);
        }

        historiaClinicaRepository.delete(historia);
    }


}
