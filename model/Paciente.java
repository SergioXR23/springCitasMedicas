package com.example.citasmedicasME.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
@Table(name = "pacientes")
@PrimaryKeyJoinColumn(name = "usuario_id")
public class Paciente extends Usuario {

    @Column(nullable = false)
    private String NSS; // Número de Seguridad Social

    @Column(nullable = false)
    private String numTarjeta; // Número de Tarjeta Sanitaria

    @Column(nullable = false)
    private String telefono;

    @Column(nullable = false)
    private String direccion;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private Set<Cita> citas;



}
