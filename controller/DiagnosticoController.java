package com.example.citasmedicasME.controller;

import com.example.citasmedicasME.dto.DiagnosticoDTO;
import com.example.citasmedicasME.service.DiagnosticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/diagnosticos")
public class DiagnosticoController {

    private final DiagnosticoService diagnosticoService;

    @Autowired
    public DiagnosticoController(DiagnosticoService diagnosticoService) {
        this.diagnosticoService = diagnosticoService;
    }

    @PostMapping("/crear")
    public ResponseEntity<DiagnosticoDTO> createDiagnostico(@RequestBody DiagnosticoDTO diagnosticoDTO) {
        DiagnosticoDTO savedDiagnostico = diagnosticoService.saveDiagnostico(diagnosticoDTO);
        return new ResponseEntity<>(savedDiagnostico, HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<DiagnosticoDTO>> getAllDiagnosticos() {
        List<DiagnosticoDTO> diagnosticos = diagnosticoService.findAllDiagnosticos();
        return ResponseEntity.ok(diagnosticos);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> getDiagnosticoById(@PathVariable Long id) {
        DiagnosticoDTO diagnostico = diagnosticoService.findDiagnosticoById(id);
        if (diagnostico != null) {
            return ResponseEntity.ok(diagnostico);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> updateDiagnostico(@PathVariable Long id, @RequestBody DiagnosticoDTO diagnosticoDTO) {
        DiagnosticoDTO updatedDiagnostico = diagnosticoService.updateDiagnostico(id, diagnosticoDTO);
        if (updatedDiagnostico != null) {
            return ResponseEntity.ok(updatedDiagnostico);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> deleteDiagnostico(@PathVariable Long id) {
        if (diagnosticoService.deleteDiagnostico(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
