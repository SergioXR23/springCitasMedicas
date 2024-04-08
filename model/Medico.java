package com.example.citasmedicasME.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "medicos")
@PrimaryKeyJoinColumn(name = "usuario_id")
public class Medico extends Usuario {

    @Column(nullable = false)
    private String numColegiado;
}
