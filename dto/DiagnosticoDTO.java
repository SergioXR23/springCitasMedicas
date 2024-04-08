package com.example.citasmedicasME.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DiagnosticoDTO {
    @JsonIgnore
    private Long idDiagnostico;
    @NotNull(message = "La valoración del especialista no puede ser nula")
    private String valoracionEspecialista;
    @NotNull(message = "La enfermedad no puede ser nula")
    private String enfermedad;
    @NotNull(message = "El id de la cita no puede ser nulo para el diagnóstico")
    private Long idCita; // Usar solo el ID aquí.
}
