package com.example.citasmedicasME.controller;

import com.example.citasmedicasME.dto.PacienteDTO;
import com.example.citasmedicasME.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping("/crear")
    public ResponseEntity<PacienteDTO> createPaciente(@RequestBody PacienteDTO pacienteDTO) {
        PacienteDTO savedPaciente = pacienteService.savePaciente(pacienteDTO);
        return new ResponseEntity<>(savedPaciente, HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<PacienteDTO>> getAllPacientes() {
        List<PacienteDTO> pacientes = pacienteService.findAllPacientes();
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> getPacienteById(@PathVariable Long id) {
        Optional<PacienteDTO> pacienteDTO = pacienteService.getPacienteById(id);
        return pacienteDTO.map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> updatePaciente(@PathVariable Long id, @RequestBody PacienteDTO pacienteDTO) {
        Optional<PacienteDTO> updatedPaciente = pacienteService.updatePaciente(id, pacienteDTO);
        return updatedPaciente.map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> deletePaciente(@PathVariable Long id) {
        boolean deleted = pacienteService.deletePaciente(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}


