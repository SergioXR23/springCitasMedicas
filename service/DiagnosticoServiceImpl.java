package com.example.citasmedicasME.service;

import com.example.citasmedicasME.dto.DiagnosticoDTO;
import com.example.citasmedicasME.mapper.DiagnosticoMapper;
import com.example.citasmedicasME.model.Cita;
import com.example.citasmedicasME.model.Diagnostico;
import com.example.citasmedicasME.repository.CitaRepository;
import com.example.citasmedicasME.repository.DiagnosticoRepository;
import com.example.citasmedicasME.service.interfaces.IDiagnosticoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiagnosticoServiceImpl implements IDiagnosticoService {

    private final DiagnosticoRepository diagnosticoRepository;
    private final DiagnosticoMapper diagnosticoMapper;
    CitaRepository citaRepository;

    public DiagnosticoServiceImpl(DiagnosticoRepository diagnosticoRepository, CitaRepository citaRepository, DiagnosticoMapper diagnosticoMapper) {
        this.diagnosticoRepository = diagnosticoRepository;
        this.diagnosticoMapper = diagnosticoMapper;
        this.citaRepository = citaRepository;
    }
    @Transactional
    @Override
    public DiagnosticoDTO saveDiagnostico(DiagnosticoDTO diagnosticoDTO) {
        Diagnostico diagnostico = diagnosticoMapper.diagnosticoDTOToDiagnostico(diagnosticoDTO);
        if (diagnosticoDTO.getIdCita() != null) {
            Cita cita = citaRepository.findById(diagnosticoDTO.getIdCita())
                    .orElseThrow(() -> new EntityNotFoundException("Cita no encontrada con el ID: " + diagnosticoDTO.getIdCita()));
            diagnostico.setCita(cita);
            cita.setDiagnostico(diagnostico);
        }
        diagnostico = diagnosticoRepository.save(diagnostico);

        return diagnosticoMapper.diagnosticoToDiagnosticoDTO(diagnostico);
    }

    @Transactional
    @Override
    public boolean deleteDiagnostico(Long id) {
        return diagnosticoRepository.findById(id).map(diagnostico -> {
            Cita cita = diagnostico.getCita();
            if (cita != null) {
                cita.setDiagnostico(null); // Elimina la referencia al diagnóstico
                citaRepository.save(cita); // Guarda la cita sin el diagnóstico
            }
            diagnosticoRepository.delete(diagnostico); // ahora SI SE PUEDE eliminar el diagnóstico
            return true;
        }).orElse(false);
    }



    @Transactional
    @Override
    public DiagnosticoDTO updateDiagnostico(Long id, DiagnosticoDTO diagnosticoDTO) {
        Diagnostico diagnostico = diagnosticoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Diagnostico no encontrado"));


        if (diagnosticoDTO.getIdCita() != null) {
            Cita cita = citaRepository.findById(diagnosticoDTO.getIdCita())
                    .orElseThrow(() -> new EntityNotFoundException("Cita no encontrada"));
            diagnostico.setCita(cita);
             cita.setDiagnostico(diagnostico);
             citaRepository.save(cita);
             diagnosticoRepository.save(diagnostico);
        }
        diagnostico.setValoracionEspecialista(diagnosticoDTO.getValoracionEspecialista());
        diagnostico.setEnfermedad(diagnosticoDTO.getEnfermedad());
        diagnostico.setCita(diagnostico.getCita());
        diagnosticoRepository.save(diagnostico);

        return diagnosticoMapper.diagnosticoToDiagnosticoDTO(diagnostico);
    }


    @Override
    public DiagnosticoDTO findDiagnosticoById(Long id) {
        return diagnosticoRepository.findById(id)
                .map(diagnosticoMapper::diagnosticoToDiagnosticoDTO)
                .orElse(null);
    }

    @Override
    public List<DiagnosticoDTO> findAllDiagnosticos() {
        return diagnosticoRepository.findAll().stream()
                .map(diagnosticoMapper::diagnosticoToDiagnosticoDTO)
                .collect(Collectors.toList());
    }
}
