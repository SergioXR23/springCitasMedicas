package com.example.citasmedicasME.service;

import com.example.citasmedicasME.dto.CitaDTO;
import com.example.citasmedicasME.dto.DiagnosticoDTO;
import com.example.citasmedicasME.mapper.CitaMapper;
import com.example.citasmedicasME.mapper.DiagnosticoMapper;
import com.example.citasmedicasME.model.Cita;
import com.example.citasmedicasME.model.Diagnostico;
import com.example.citasmedicasME.repository.DiagnosticoRepository;
import com.example.citasmedicasME.service.interfaces.IDiagnosticoService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DiagnosticoServiceImpl implements IDiagnosticoService {

    @Autowired
    private DiagnosticoRepository diagnosticoRepository;

    @Autowired
    private DiagnosticoMapper diagnosticoMapper;

    @Lazy
    @Autowired
    private CitaServiceImpl citaService;

    @Autowired
    private CitaMapper citaMapper;
    @PersistenceContext
    private EntityManager entityManager;

//    @Override
//    public DiagnosticoDTO save(DiagnosticoDTO diagnosticoDTO) {
//        Diagnostico diagnostico = diagnosticoMapper.diagnosticoDTOToDiagnostico(diagnosticoDTO);
//        if (diagnosticoDTO.getIdCita() != null) {
//            CitaDTO citaDTO = citaService.findById(diagnosticoDTO.getIdCita());
//            Cita cita = citaMapper.citaDTOToCita(citaDTO);
//            diagnostico.setCita(cita);
//        }
//        diagnostico = diagnosticoRepository.save(diagnostico);
//        return diagnosticoMapper.diagnosticoToDiagnosticoDTO(diagnostico);
//    }
public DiagnosticoDTO save(DiagnosticoDTO diagnosticoDTO) {
    Diagnostico diagnostico = diagnosticoMapper.diagnosticoDTOToDiagnostico(diagnosticoDTO);
    if (diagnosticoDTO.getIdCita() != null) {
        Cita cita = entityManager.find(Cita.class, diagnosticoDTO.getIdCita());
        if (cita == null) {
            throw new EntityNotFoundException("Cita no encontrada con ID: " + diagnosticoDTO.getIdCita());
        }
        diagnostico.setCita(cita);
        cita.setDiagnostico(diagnostico);
    }
    diagnostico = diagnosticoRepository.save(diagnostico);
    return diagnosticoMapper.diagnosticoToDiagnosticoDTO(diagnostico);
}


    @Override
    public boolean deleteDiagnostico(Long id) {
        Diagnostico diagnostico = diagnosticoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Diagnostico no encontrado con el ID: " + id));
        Cita cita = diagnostico.getCita();

        CitaDTO citaDTO = citaMapper.citaToCitaDTO(cita);
        if (cita != null) {
            cita.setDiagnostico(null);
            citaService.saveCita(citaDTO);
        }
        diagnosticoRepository.delete(diagnostico);
        return true;
    }

    @Override
    public DiagnosticoDTO updateDiagnostico(Long id, DiagnosticoDTO diagnosticoDTO) {
        Diagnostico diagnostico = diagnosticoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Diagnostico no encontrado con el ID: " + id));
        if (diagnosticoDTO.getIdCita() != null) {
            CitaDTO citaDTO = citaService.findById(diagnosticoDTO.getIdCita());
            Cita cita = citaMapper.citaDTOToCita(citaDTO);

            diagnostico.setCita(cita);

        }
        diagnostico.setValoracionEspecialista(diagnosticoDTO.getValoracionEspecialista());
        diagnostico.setEnfermedad(diagnosticoDTO.getEnfermedad());
        diagnostico = diagnosticoRepository.save(diagnostico);
        return diagnosticoMapper.diagnosticoToDiagnosticoDTO(diagnostico);
    }

    @Override
    public DiagnosticoDTO findById(Long id) {
        return diagnosticoRepository.findById(id)
                .map(diagnosticoMapper::diagnosticoToDiagnosticoDTO)
                .orElseThrow(() -> new RuntimeException("Diagnostico no encontrado con el ID: " + id));
    }

    @Override
    public List<DiagnosticoDTO> findAllDiagnosticos() {
        return diagnosticoRepository.findAll().stream()
                .map(diagnosticoMapper::diagnosticoToDiagnosticoDTO)
                .collect(Collectors.toList());
    }
}
