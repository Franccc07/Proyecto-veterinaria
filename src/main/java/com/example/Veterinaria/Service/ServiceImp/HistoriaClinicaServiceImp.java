package com.example.Veterinaria.Service.ServiceImp;

import com.example.Veterinaria.Service.HistoriaClinicaService;
import com.example.Veterinaria.Entity.HistoriaClinica;
import com.example.Veterinaria.Entity.Mascota;
import com.example.Veterinaria.Repository.HistoriaClinicaRepository;
import com.example.Veterinaria.Repository.MascotaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoriaClinicaServiceImp implements HistoriaClinicaService {

    private final HistoriaClinicaRepository historiaClinicaRepository;
    private final MascotaRepository mascotaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<HistoriaClinica> listarTodas() {
        return historiaClinicaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public HistoriaClinica buscarPorId(Long id) {
        return historiaClinicaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Historia clínica no encontrada con el ID: " + id));
    }

    @Override
    @Transactional
    public HistoriaClinica crear(HistoriaClinica historia, Long mascotaId) {
        Mascota mascota = mascotaRepository.findById(mascotaId)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada con el ID: " + mascotaId));

        if (mascota.getHistoriaClinica() != null) {
            throw new RuntimeException("La mascota con ID " + mascotaId + " ya tiene una historia clínica registrada.");
        }

        historia.setMascota(mascota);
        mascota.setHistoriaClinica(historia);

        return historiaClinicaRepository.save(historia);
    }

    @Override
    @Transactional
    public HistoriaClinica actualizar(Long id, HistoriaClinica historiaDetalles) {
        HistoriaClinica historiaExistente = buscarPorId(id);

        historiaExistente.setFechaApertura(historiaDetalles.getFechaApertura());
        historiaExistente.setAntecedentes(historiaDetalles.getAntecedentes());
        historiaExistente.setObservaciones(historiaDetalles.getObservaciones());

        return historiaClinicaRepository.save(historiaExistente);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        HistoriaClinica historia = buscarPorId(id);

        if (historia.getMascota() != null) {
            historia.getMascota().setHistoriaClinica(null);
        }

        historiaClinicaRepository.delete(historia);
    }


}
