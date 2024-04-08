package com.example.citasmedicasME.mapper;

import com.example.citasmedicasME.dto.PacienteDTO;
import com.example.citasmedicasME.model.Paciente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface PacienteMapper {

    PacienteDTO pacienteToPacienteDTO(Paciente paciente);
    @Mapping(target = "citas", ignore = true)
    Paciente pacienteDTOToPaciente(PacienteDTO pacienteDTO);


}
