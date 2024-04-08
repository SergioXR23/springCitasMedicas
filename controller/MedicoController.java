package com.example.citasmedicasME.controller;

import com.example.citasmedicasME.dto.MedicoDTO;
import com.example.citasmedicasME.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    private final MedicoService medicoService;

    @Autowired
    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @PostMapping("/crear")
    public ResponseEntity<MedicoDTO> createMedico(@RequestBody MedicoDTO medicoDTO) {
        MedicoDTO savedMedico = medicoService.saveMedico(medicoDTO);
        return new ResponseEntity<>(savedMedico, HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<?> getAllMedicos() {
        return ResponseEntity.ok(medicoService.findAll());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> getMedicoById(@PathVariable Long id) {
        return ResponseEntity.ok(medicoService.findById(id));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> updateMedico(@PathVariable Long id, @RequestBody MedicoDTO medicoDTO) {
        return ResponseEntity.ok(medicoService.save(medicoDTO));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> deleteMedico(@PathVariable Long id) {
        medicoService.delete(id);
        return ResponseEntity.ok().build();
    }




}
