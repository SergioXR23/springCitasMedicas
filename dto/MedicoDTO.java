package com.example.citasmedicasME.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class MedicoDTO extends UsuarioDTO{

    @NotNull(message = "El número de colegiado no puede ser nulo")
    private String numColegiado;
}
