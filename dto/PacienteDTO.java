package com.example.citasmedicasME.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class PacienteDTO extends UsuarioDTO {

    @NotNull(message = "El NSS no puede ser nulo")
    private String NSS;
    @NotNull(message = "El número de tarjeta no puede ser nulo")
    private String numTarjeta;
    @NotNull(message = "El teléfono no puede ser nulo")
    private String telefono;
    @NotNull(message = "La dirección no puede ser nula")
    private String direccion;
}
