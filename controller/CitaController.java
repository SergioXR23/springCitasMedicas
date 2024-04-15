package com.example.citasmedicasME.controller;

import com.example.citasmedicasME.dto.CitaDTO;
import com.example.citasmedicasME.service.CitaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping("/api/citas")
public class CitaController {
    @Autowired
    private CitaServiceImpl citaServiceImpl;

    public CitaController(CitaServiceImpl citaServiceImpl) {
        this.citaServiceImpl = citaServiceImpl;
    }

    @PostMapping
    public ResponseEntity<CitaDTO> createCita(@RequestBody CitaDTO citaDTO) {
        CitaDTO savedCita = citaServiceImpl.saveCita(citaDTO);
        return new ResponseEntity<>(savedCita, HttpStatus.CREATED);
    }

    // Usa @GetMapping para listar todas las citas
    @GetMapping
    public ResponseEntity<?> getAllCitas() {
        return ResponseEntity.ok(citaServiceImpl.findAllCitas());
    }

    // Usa @GetMapping para buscar una cita por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getCitaById(@PathVariable Long id) {
        return ResponseEntity.ok(citaServiceImpl.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCita(@PathVariable Long id, @RequestBody CitaDTO citaDTO) {
        return ResponseEntity.ok(citaServiceImpl.updateCita(id, citaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCita(@PathVariable Long id) {
        boolean deleted = citaServiceImpl.deleteCita(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
