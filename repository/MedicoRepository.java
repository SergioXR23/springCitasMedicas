package com.example.citasmedicasME.repository;

import com.example.citasmedicasME.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
