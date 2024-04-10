package com.example.citasmedicasME.controller;

import com.example.citasmedicasME.dto.CitaDTO;
import com.example.citasmedicasME.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping("/api/citas")
public class CitaController {
    @Autowired
    private CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @PostMapping("/crear")
    public ResponseEntity<CitaDTO> createCita(@RequestBody CitaDTO citaDTO) {
        CitaDTO savedCita = citaService.saveCita(citaDTO);
        return new ResponseEntity<>(savedCita, HttpStatus.CREATED);
    }

    // Usa @GetMapping para listar todas las citas
    @GetMapping("/listar")
    public ResponseEntity<?> getAllCitas() {
        return ResponseEntity.ok(citaService.findAllCitas());
    }

    // Usa @GetMapping para buscar una cita por ID
    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> getCitaById(@PathVariable Long id) {
        return ResponseEntity.ok(citaService.findCitaById(id));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> updateCita(@PathVariable Long id, @RequestBody CitaDTO citaDTO) {
        return ResponseEntity.ok(citaService.updateCita(id, citaDTO));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> deleteCita(@PathVariable Long id) {
        boolean deleted = citaService.deleteCita(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
