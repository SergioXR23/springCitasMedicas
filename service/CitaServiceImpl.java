package com.example.citasmedicasME.service;

import com.example.citasmedicasME.dto.CitaDTO;
import com.example.citasmedicasME.mapper.CitaMapper;
import com.example.citasmedicasME.model.Cita;
import com.example.citasmedicasME.model.Diagnostico;
import com.example.citasmedicasME.model.Medico;
import com.example.citasmedicasME.model.Paciente;
import com.example.citasmedicasME.repository.CitaRepository;
import com.example.citasmedicasME.repository.DiagnosticoRepository;
import com.example.citasmedicasME.repository.MedicoRepository;
import com.example.citasmedicasME.repository.PacienteRepository;
import com.example.citasmedicasME.service.interfaces.ICitaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CitaServiceImpl implements ICitaService {

    private final CitaRepository citaRepository;
    private final CitaMapper citaMapper;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;
    private final DiagnosticoRepository diagnosticoRepository;

    public CitaServiceImpl(CitaRepository citaRepository, MedicoRepository medicoRepository, PacienteRepository pacienteRepository, DiagnosticoRepository diagnosticoRepository, CitaMapper citaMapper) {
        this.citaRepository = citaRepository;
        this.citaMapper = citaMapper;
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
        this.diagnosticoRepository = diagnosticoRepository;
    }

    public CitaDTO saveCita(CitaDTO citaDTO) {
        Cita cita = new Cita();

        Medico medico = medicoRepository.findById(citaDTO.getIdMedico())
                .orElseThrow(() -> new RuntimeException("Médico no encontrado con el ID: " + citaDTO.getIdMedico()));
        cita.setMedico(medico);

        Paciente paciente = pacienteRepository.findById(citaDTO.getIdPaciente())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con el ID: " + citaDTO.getIdPaciente()));
        cita.setPaciente(paciente);

        cita.setFechaHora(citaDTO.getFechaHora());
        cita.setMotivoCita(citaDTO.getMotivoCita());

        Cita savedCita = citaRepository.save(cita);

        return citaMapper.citaToCitaDTO(savedCita);
    }

    public boolean deleteCita(Long id) {
        if (citaRepository.existsById(id)) {
            citaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public CitaDTO updateCita(Long id, CitaDTO citaDTO) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cita no encontrada con el ID: " + id));

        // Mapea los cambios en la cita desde el DTO
        cita.setFechaHora(citaDTO.getFechaHora());
        cita.setMotivoCita(citaDTO.getMotivoCita());

        if (citaDTO.getIdDiagnostico() != null) {
            Diagnostico diagnostico = diagnosticoRepository.findById(citaDTO.getIdDiagnostico())
                    .orElseThrow(() -> new EntityNotFoundException("Diagnostico no encontrado con el ID: " + citaDTO.getIdDiagnostico()));

            diagnostico = diagnosticoRepository.save(diagnostico);

            cita.setDiagnostico(diagnostico);
        }

        Cita updatedCita = citaRepository.save(cita);
        return citaMapper.citaToCitaDTO(updatedCita);
    }
    public CitaDTO findCitaById(Long id) {
        return citaRepository.findById(id)
                .map(citaMapper::citaToCitaDTO)
                .orElse(null);
    }

    public List<CitaDTO> findAllCitas() {
        return citaRepository.findAll().stream()
                .map(citaMapper::citaToCitaDTO)
                .collect(Collectors.toList());
    }

}
