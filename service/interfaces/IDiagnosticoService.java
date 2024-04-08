package com.example.citasmedicasME.service.interfaces;

import com.example.citasmedicasME.dto.DiagnosticoDTO;

import java.util.List;

public interface IDiagnosticoService {

    DiagnosticoDTO saveDiagnostico(DiagnosticoDTO diagnosticoDTO);

    boolean deleteDiagnostico(Long id);

    DiagnosticoDTO updateDiagnostico(Long id, DiagnosticoDTO diagnosticoDTO);

    DiagnosticoDTO findDiagnosticoById(Long id);

    List<DiagnosticoDTO> findAllDiagnosticos();
}
