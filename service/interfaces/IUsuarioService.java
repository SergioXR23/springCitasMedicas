package com.example.citasmedicasME.service.interfaces;

import com.example.citasmedicasME.dto.UsuarioDTO;

import java.util.List;

public interface IUsuarioService {

    UsuarioDTO saveUsuario(UsuarioDTO usuarioDTO);

    List<UsuarioDTO> findAllUsuarios();

    UsuarioDTO findUsuarioById(Long id);

    UsuarioDTO updateUsuario(Long id, UsuarioDTO usuarioDTO);

    void deleteUsuario(Long id);

}
