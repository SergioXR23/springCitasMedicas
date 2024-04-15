package com.example.citasmedicasME.service;

import com.example.citasmedicasME.dto.MedicoDTO;
import com.example.citasmedicasME.mapper.MedicoMapper;
import com.example.citasmedicasME.repository.MedicoRepository;
import com.example.citasmedicasME.service.interfaces.IMedicoService;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MedicoServiceImpl implements IMedicoService {
    private final MedicoRepository medicoRepository;
    private final MedicoMapper medicoMapper;

    public MedicoServiceImpl(MedicoRepository medicoRepository, MedicoMapper medicoMapper) {
        this.medicoRepository = medicoRepository;
        this.medicoMapper = medicoMapper;
    }

    /**
     * Encuentra todos los médicos disponibles.
     * @return una lista de DTOs de médicos
     */
    public List<MedicoDTO> findAll() {
        return medicoRepository.findAll().stream()
                .map(medicoMapper::medicoToMedicoDTO)
                .collect(Collectors.toList());
    }

    /**
     * Encuentra un médico por su ID.
     * @param id el ID del médico a buscar
     * @return el DTO del médico si se encuentra
     * @throws ResponseStatusException si el médico no se encuentra
     */
    public MedicoDTO findById(Long id) {
        return medicoRepository.findById(id)
                .map(medicoMapper::medicoToMedicoDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Médico no encontrado con el ID: " + id));
    }

    /**
     * Guarda o actualiza un médico en la base de datos.
     * @param medicoDTO el DTO del médico a guardar
     * @return el DTO del médico guardado
     * @throws IllegalArgumentException si los datos del médico son inválidos
     */
    public MedicoDTO save(MedicoDTO medicoDTO) {
        if (medicoDTO == null || StringUtils.isBlank(medicoDTO.getNombre())) {
            throw new IllegalArgumentException("Datos del médico inválidos");
        }
        return medicoMapper.medicoToMedicoDTO(medicoRepository.save(medicoMapper.medicoDTOToMedico(medicoDTO)));
    }

    /**
     * Elimina un médico de la base de datos.
     * @param id el ID del médico a eliminar
     */
    public void delete(Long id) {
        medicoRepository.deleteById(id);
    }
}
