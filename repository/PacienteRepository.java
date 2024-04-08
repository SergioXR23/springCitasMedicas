package com.example.citasmedicasME.repository;

import com.example.citasmedicasME.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Paciente findByNombre(String nombre);


}
