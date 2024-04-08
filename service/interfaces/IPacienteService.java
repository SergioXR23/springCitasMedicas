package com.example.citasmedicasME.service.interfaces;

import com.example.citasmedicasME.dto.PacienteDTO;

import java.util.List;
import java.util.Optional;

public interface IPacienteService {

        Optional<PacienteDTO> getPacienteById(Long id);

        PacienteDTO savePaciente(PacienteDTO pacienteDTO);

        boolean deletePaciente(Long id);

        Optional<PacienteDTO> updatePaciente(Long id, PacienteDTO pacienteDTO);

        List<PacienteDTO> findAllPacientes();
}
