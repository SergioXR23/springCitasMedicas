package com.example.citasmedicasME.service;

import com.example.citasmedicasME.dto.PacienteDTO;
import com.example.citasmedicasME.mapper.PacienteMapper;
import com.example.citasmedicasME.model.Paciente;
import com.example.citasmedicasME.repository.PacienteRepository;
import com.example.citasmedicasME.service.interfaces.IPacienteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PacienteService implements IPacienteService {

    private final PacienteRepository pacienteRepository;
    private final PacienteMapper pacienteMapper;

    public PacienteService(PacienteRepository pacienteRepository, PacienteMapper pacienteMapper) {
        this.pacienteRepository = pacienteRepository;
        this.pacienteMapper = pacienteMapper;
    }


    public Optional<PacienteDTO> getPacienteById(Long id) {
        return pacienteRepository.findById(id)
                .map(pacienteMapper::pacienteToPacienteDTO);
    }

    public PacienteDTO savePaciente(PacienteDTO pacienteDTO) {
        Paciente paciente = pacienteMapper.pacienteDTOToPaciente(pacienteDTO);
        paciente = pacienteRepository.save(paciente);
        return pacienteMapper.pacienteToPacienteDTO(paciente);
    }

    public boolean deletePaciente(Long id) {
        if (pacienteRepository.existsById(id)) {
            pacienteRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<PacienteDTO> updatePaciente(Long id, PacienteDTO pacienteDTO) {
        if (pacienteRepository.existsById(id)) {
            Paciente paciente = pacienteMapper.pacienteDTOToPaciente(pacienteDTO);
            paciente.setId(id); // Asegura que se actualice el paciente correcto
            paciente = pacienteRepository.save(paciente);
            return Optional.of(pacienteMapper.pacienteToPacienteDTO(paciente));
        }
        return Optional.empty();
    }

    public List<PacienteDTO> findAllPacientes() {
        return pacienteRepository.findAll().stream()
                .map(pacienteMapper::pacienteToPacienteDTO)
                .collect(Collectors.toList());
    }
}
