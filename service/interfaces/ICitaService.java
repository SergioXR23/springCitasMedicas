package com.example.citasmedicasME.service.interfaces;

import com.example.citasmedicasME.dto.CitaDTO;

import java.util.List;

public interface ICitaService {

    CitaDTO saveCita(CitaDTO citaDTO);

    boolean deleteCita(Long id);

    CitaDTO updateCita(Long id, CitaDTO citaDTO);

    CitaDTO findById(Long id);

    List<CitaDTO> findAllCitas();
}
