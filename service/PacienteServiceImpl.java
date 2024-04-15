package com.example.citasmedicasME.service;

import com.example.citasmedicasME.dto.PacienteDTO;
import com.example.citasmedicasME.mapper.PacienteMapper;
import com.example.citasmedicasME.model.Paciente;
import com.example.citasmedicasME.repository.PacienteRepository;
import com.example.citasmedicasME.service.interfaces.IPacienteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PacienteServiceImpl implements IPacienteService {

    private final PacienteRepository pacienteRepository;
    private final PacienteMapper pacienteMapper;

    public PacienteServiceImpl(PacienteRepository pacienteRepository, PacienteMapper pacienteMapper) {
        this.pacienteRepository = pacienteRepository;
        this.pacienteMapper = pacienteMapper;
    }

    public PacienteDTO findById(Long id) {
        return pacienteRepository.findById(id)
                .map(pacienteMapper::pacienteToPacienteDTO)
                .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado con ID: " + id));
    }

    public PacienteDTO savePaciente(PacienteDTO pacienteDTO) {
        Paciente paciente = pacienteMapper.pacienteDTOToPaciente(pacienteDTO);
        if(pacienteRepository.findByNombre(paciente.getNombre()) != null) {
            throw new EntityNotFoundException("El paciente ya existe");
        }
        paciente = pacienteRepository.save(paciente);
        return pacienteMapper.pacienteToPacienteDTO(paciente);
    }

    public boolean deletePaciente(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new EntityNotFoundException("Paciente no encontrado con ID: " + id);
        }
        pacienteRepository.deleteById(id);
        return true;
    }

    public PacienteDTO updatePaciente(Long id, PacienteDTO pacienteDTO) {
        if (!pacienteRepository.existsById(id)) {
            throw new EntityNotFoundException("Paciente no encontrado con ID: " + id);
        }
        Paciente paciente = pacienteMapper.pacienteDTOToPaciente(pacienteDTO);
        paciente.setId(id); // Asegura que se actualice el paciente correcto
        paciente = pacienteRepository.save(paciente);
        return pacienteMapper.pacienteToPacienteDTO(paciente);
    }

    public List<PacienteDTO> findAllPacientes() {
        return pacienteRepository.findAll().stream()
                .map(pacienteMapper::pacienteToPacienteDTO)
                .collect(Collectors.toList());
    }
}
