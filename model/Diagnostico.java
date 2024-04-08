package com.example.citasmedicasME.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "diagnosticos")
public class Diagnostico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String valoracionEspecialista;

    private String enfermedad;

    @OneToOne(mappedBy = "diagnostico", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Cita cita;
}
