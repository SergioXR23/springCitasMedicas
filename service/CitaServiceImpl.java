package com.example.citasmedicasME.service;

import com.example.citasmedicasME.dto.CitaDTO;
import com.example.citasmedicasME.dto.DiagnosticoDTO;
import com.example.citasmedicasME.dto.MedicoDTO;
import com.example.citasmedicasME.dto.PacienteDTO;
import com.example.citasmedicasME.mapper.CitaMapper;
import com.example.citasmedicasME.mapper.DiagnosticoMapper;
import com.example.citasmedicasME.mapper.MedicoMapper;
import com.example.citasmedicasME.mapper.PacienteMapper;
import com.example.citasmedicasME.model.Cita;
import com.example.citasmedicasME.model.Diagnostico;
import com.example.citasmedicasME.model.Medico;
import com.example.citasmedicasME.model.Paciente;
import com.example.citasmedicasME.repository.CitaRepository;
import com.example.citasmedicasME.service.interfaces.ICitaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CitaServiceImpl implements ICitaService {


    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private MedicoServiceImpl medicoService;

    @Autowired
    private PacienteServiceImpl pacienteService;

    @Lazy
    @Autowired
    private DiagnosticoServiceImpl diagnosticoServices;

    @Autowired
    private CitaMapper citaMapper;

    @Autowired
    private MedicoMapper medicoMapper;

    @Autowired
    private PacienteMapper pacienteMapper;

    @Autowired
    private DiagnosticoMapper diagnosticoMapper;

    public CitaDTO saveCita(CitaDTO citaDTO) {
        Cita cita = new Cita();
        MedicoDTO medicoDTO = medicoService.findById(citaDTO.getIdMedico());
        Medico medico = medicoMapper.medicoDTOToMedico(medicoDTO);
        cita.setMedico(medico);
        PacienteDTO pacienteDTO = pacienteService.findById(citaDTO.getIdPaciente());
        Paciente paciente = pacienteMapper.pacienteDTOToPaciente(pacienteDTO);
        cita.setPaciente(paciente);
        cita.setFechaHora(citaDTO.getFechaHora());
        cita.setMotivoCita(citaDTO.getMotivoCita());
        return citaMapper.citaToCitaDTO(citaRepository.save(cita));
    }

    public boolean deleteCita(Long id) {
        if (!citaRepository.existsById(id)) {
            throw new EntityNotFoundException("Cita no encontrada con el ID: " + id);
        }
        citaRepository.deleteById(id);
        return true;
    }

    public CitaDTO updateCita(Long id, CitaDTO citaDTO) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cita no encontrada con el ID: " + id));
        cita.setFechaHora(citaDTO.getFechaHora());
        cita.setMotivoCita(citaDTO.getMotivoCita());
        if (citaDTO.getIdDiagnostico() != null) {
            DiagnosticoDTO diagnosticoDTO = diagnosticoServices.findById(citaDTO.getIdDiagnostico());
            Diagnostico diagnostico = diagnosticoMapper.diagnosticoDTOToDiagnostico(diagnosticoDTO);
            cita.setDiagnostico(diagnostico);
        }
        return citaMapper.citaToCitaDTO(citaRepository.save(cita));
    }

    public CitaDTO findById(Long id) {
        return citaRepository.findById(id)
                .map(citaMapper::citaToCitaDTO)
                .orElseThrow(() -> new EntityNotFoundException("Cita no encontrada con el ID: " + id));
    }

    public List<CitaDTO> findAllCitas() {
        return citaRepository.findAll().stream()
                .map(citaMapper::citaToCitaDTO)
                .collect(Collectors.toList());
    }
}
