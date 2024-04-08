package com.example.citasmedicasME.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CitaDTO {
    @JsonIgnore
    private Long id;
    @NotNull(message = "La fecha y hora no puede ser nula")
    private LocalDateTime fechaHora;
    @NotNull(message = "El motivo no puede ser nulo")
    private String motivoCita;
    @NotNull(message = "El paciente no puede ser nulo")
    private Long idPaciente;
    private Long idDiagnostico;
    @NotNull(message = "El médico no puede ser nulo")
    private Long idMedico;
}

