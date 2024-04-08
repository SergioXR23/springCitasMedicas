package com.example.citasmedicasME.mapper;

import com.example.citasmedicasME.dto.MedicoDTO;
import com.example.citasmedicasME.model.Medico;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MedicoMapper {
    MedicoDTO medicoToMedicoDTO(Medico medico);

    Medico medicoDTOToMedico(MedicoDTO medicoDTO);
}
