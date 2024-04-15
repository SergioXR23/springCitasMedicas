package com.example.citasmedicasME.service.interfaces;

import com.example.citasmedicasME.dto.PacienteDTO;

import java.util.List;

public interface IPacienteService {

        PacienteDTO findById(Long id);

        PacienteDTO savePaciente(PacienteDTO pacienteDTO);

        boolean deletePaciente(Long id);

        PacienteDTO updatePaciente(Long id, PacienteDTO pacienteDTO);

        List<PacienteDTO> findAllPacientes();
}
