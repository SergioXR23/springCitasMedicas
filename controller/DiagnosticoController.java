package com.example.citasmedicasME.controller;

import com.example.citasmedicasME.dto.DiagnosticoDTO;
import com.example.citasmedicasME.service.DiagnosticoServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/diagnosticos")
public class DiagnosticoController {

    private final DiagnosticoServiceImpl diagnosticoServiceImpl;

    public DiagnosticoController(DiagnosticoServiceImpl diagnosticoServiceImpl) {
        this.diagnosticoServiceImpl = diagnosticoServiceImpl;
    }


    @PostMapping
    public ResponseEntity<DiagnosticoDTO> createDiagnostico(@RequestBody DiagnosticoDTO diagnosticoDTO) {
        DiagnosticoDTO savedDiagnostico = diagnosticoServiceImpl.saveDiagnostico(diagnosticoDTO);
        return new ResponseEntity<>(savedDiagnostico, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DiagnosticoDTO>> getAllDiagnosticos() {
        List<DiagnosticoDTO> diagnosticos = diagnosticoServiceImpl.findAllDiagnosticos();
        return ResponseEntity.ok(diagnosticos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDiagnosticoById(@PathVariable Long id) {
        DiagnosticoDTO diagnostico = diagnosticoServiceImpl.findDiagnosticoById(id);
        if (diagnostico != null) {
            return ResponseEntity.ok(diagnostico);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDiagnostico(@PathVariable Long id, @RequestBody DiagnosticoDTO diagnosticoDTO) {
        DiagnosticoDTO updatedDiagnostico = diagnosticoServiceImpl.updateDiagnostico(id, diagnosticoDTO);
        if (updatedDiagnostico != null) {
            return ResponseEntity.ok(updatedDiagnostico);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDiagnostico(@PathVariable Long id) {
        if (diagnosticoServiceImpl.deleteDiagnostico(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
