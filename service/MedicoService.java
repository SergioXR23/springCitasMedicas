package com.example.citasmedicasME.service;

import com.example.citasmedicasME.dto.MedicoDTO;
import com.example.citasmedicasME.mapper.MedicoMapper;
import com.example.citasmedicasME.repository.MedicoRepository;
import com.example.citasmedicasME.service.interfaces.IMedicoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicoService implements IMedicoService {
    private final MedicoRepository medicoRepository;

    private final MedicoMapper medicoMapper;

    public MedicoService(MedicoRepository medicoRepository, MedicoMapper medicoMapper) {
        this.medicoRepository = medicoRepository;
        this.medicoMapper = medicoMapper;
    }

    public List<MedicoDTO> findAll() {
        return medicoRepository.findAll().stream().map(medicoMapper::medicoToMedicoDTO).collect(Collectors.toList());
    }

    public MedicoDTO findById(Long id) {
        return medicoMapper.medicoToMedicoDTO(medicoRepository.findById(id).orElse(null));
    }

    @Override
    public MedicoDTO save(MedicoDTO medicoDTO) {
        return medicoMapper.medicoToMedicoDTO(medicoRepository.save(medicoMapper.medicoDTOToMedico(medicoDTO)));
    }

    public MedicoDTO saveMedico(MedicoDTO medicoDTO) {
        return medicoMapper.medicoToMedicoDTO(medicoRepository.save(medicoMapper.medicoDTOToMedico(medicoDTO)));
    }

    public void delete(Long id) {
        medicoRepository.deleteById(id);
    }


}
