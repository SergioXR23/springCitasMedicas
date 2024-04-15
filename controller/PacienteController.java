package com.example.citasmedicasME.controller;

import com.example.citasmedicasME.dto.PacienteDTO;
import com.example.citasmedicasME.service.PacienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final PacienteServiceImpl pacienteServiceImpl;

    @Autowired
    public PacienteController(PacienteServiceImpl pacienteServiceImpl) {
        this.pacienteServiceImpl = pacienteServiceImpl;
    }

    @PostMapping
    public ResponseEntity<PacienteDTO> createPaciente(@RequestBody PacienteDTO pacienteDTO) {
        PacienteDTO savedPaciente = pacienteServiceImpl.savePaciente(pacienteDTO);
        return new ResponseEntity<>(savedPaciente, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PacienteDTO>> getAllPacientes() {
        List<PacienteDTO> pacientes = pacienteServiceImpl.findAllPacientes();
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPacienteById(@PathVariable Long id) {
        PacienteDTO pacienteDTO = pacienteServiceImpl.findById(id);
        return ResponseEntity.ok(pacienteDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePaciente(@PathVariable Long id, @RequestBody PacienteDTO pacienteDTO) {
        PacienteDTO updatedPaciente = pacienteServiceImpl.updatePaciente(id, pacienteDTO);
        return ResponseEntity.ok(updatedPaciente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePaciente(@PathVariable Long id) {
        boolean deleted = pacienteServiceImpl.deletePaciente(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}


