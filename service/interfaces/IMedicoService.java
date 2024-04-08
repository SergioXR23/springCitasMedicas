package com.example.citasmedicasME.service.interfaces;

import com.example.citasmedicasME.dto.MedicoDTO;

import java.util.List;

public interface IMedicoService {
    public List<MedicoDTO> findAll();

    public MedicoDTO findById(Long id);

    public MedicoDTO save(MedicoDTO medicoDTO);

    public void delete(Long id);

}
