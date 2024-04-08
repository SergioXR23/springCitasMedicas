package com.example.citasmedicasME.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class MedicoDTO extends UsuarioDTO{
    @JsonIgnore
    private Long id;
    @NotNull(message = "El nombre no puede ser nulo")
    private String nombre;
    @NotNull(message = "Apellidos no puede ser nulo")
    private String apellidos;
    @NotNull(message = "El nombre de usuario no puede ser nulo")
    private String usuario;
    @NotNull(message = "El número de colegiado no puede ser nulo")
    private String numColegiado;
}
