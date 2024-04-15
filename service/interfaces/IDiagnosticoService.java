package com.example.citasmedicasME.service.interfaces;

import com.example.citasmedicasME.dto.DiagnosticoDTO;

import java.util.List;

public interface IDiagnosticoService {

    DiagnosticoDTO save(DiagnosticoDTO diagnosticoDTO);

    boolean deleteDiagnostico(Long id);

    DiagnosticoDTO updateDiagnostico(Long id, DiagnosticoDTO diagnosticoDTO);

    DiagnosticoDTO findById(Long id);

    List<DiagnosticoDTO> findAllDiagnosticos();
}
