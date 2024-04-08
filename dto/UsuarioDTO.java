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
public class UsuarioDTO {
    @JsonIgnore
    private Long id;
    @NotNull(message = "El nombre no puede ser nulo")
    private String nombre;
    @NotNull(message = "Los apellidos no pueden ser nulos")
    private String apellidos;
    @NotNull(message = "El nombre no puede ser nulo")

    private String usuario;
    @NotNull(message = "El nombre no puede ser nulo")
    private String clave;

}
