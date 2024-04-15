package com.example.citasmedicasME.controller;

import com.example.citasmedicasME.dto.MedicoDTO;
import com.example.citasmedicasME.service.MedicoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    private final MedicoServiceImpl medicoServiceImpl;

    @Autowired
    public MedicoController(MedicoServiceImpl medicoServiceImpl) {
        this.medicoServiceImpl = medicoServiceImpl;
    }

    @PostMapping
    public ResponseEntity<MedicoDTO> createMedico(@RequestBody MedicoDTO medicoDTO) {
        MedicoDTO savedMedico = medicoServiceImpl.saveMedico(medicoDTO);
        return new ResponseEntity<>(savedMedico, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllMedicos() {
        return ResponseEntity.ok(medicoServiceImpl.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMedicoById(@PathVariable Long id) {
        return ResponseEntity.ok(medicoServiceImpl.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMedico(@PathVariable Long id, @RequestBody MedicoDTO medicoDTO) {
        return ResponseEntity.ok(medicoServiceImpl.save(medicoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMedico(@PathVariable Long id) {
        medicoServiceImpl.delete(id);
        return ResponseEntity.ok().build();
    }




}
