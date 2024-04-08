package com.example.citasmedicasME.mapper;

import com.example.citasmedicasME.dto.DiagnosticoDTO;
import com.example.citasmedicasME.model.Diagnostico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DiagnosticoMapper {

    @Mapping(target = "idCita", source = "cita.id")
    @Mapping(source = "id", target = "idDiagnostico")
    DiagnosticoDTO diagnosticoToDiagnosticoDTO(Diagnostico diagnostico);

    @Mapping(target = "cita.id", source = "idCita")
    @Mapping(source = "idDiagnostico", target = "id")
    Diagnostico diagnosticoDTOToDiagnostico(DiagnosticoDTO diagnosticoDTO);
}

