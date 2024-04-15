package com.example.citasmedicasME.controller;

import com.example.citasmedicasME.dto.PacienteDTO;
import com.example.citasmedicasME.service.PacienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        Optional<PacienteDTO> pacienteDTO = pacienteServiceImpl.getPacienteById(id);
        return pacienteDTO.map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePaciente(@PathVariable Long id, @RequestBody PacienteDTO pacienteDTO) {
        Optional<PacienteDTO> updatedPaciente = pacienteServiceImpl.updatePaciente(id, pacienteDTO);
        return updatedPaciente.map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
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


