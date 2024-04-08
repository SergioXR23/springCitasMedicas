package com.example.citasmedicasME.mapper;

import com.example.citasmedicasME.dto.CitaDTO;
import com.example.citasmedicasME.model.Cita;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CitaMapper {

    @Mapping(target = "idPaciente", source = "paciente.id")
    @Mapping(target = "idMedico", source = "medico.id")
    @Mapping(target = "idDiagnostico", source = "diagnostico.id")
    CitaDTO citaToCitaDTO(Cita cita);

    @Mapping(target = "paciente.id", source = "idPaciente")
    @Mapping(target = "medico.id", source = "idMedico")
    @Mapping(target = "diagnostico.id", source = "idDiagnostico")
    Cita citaDTOToCita(CitaDTO citaDTO);
}
