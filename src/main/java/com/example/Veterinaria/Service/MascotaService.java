package com.example.Veterinaria.Service;

import com.example.Veterinaria.Entity.Mascota;

import java.util.List;

public interface MascotaService {
    List<Mascota> listarTodas();

    Mascota buscarPorId(Long id);

    Mascota guardar( Mascota mascota, Long propietarioId);

    Mascota actualizar(Long id, Mascota mascota    );

    void eliminar(Long id);

    List<Mascota> buscarPorPropietario(Long propietarioId );

    Mascota asignarVeterinario(Long mascotaId, Long veterinarioId );
}
