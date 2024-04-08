package com.example.citasmedicasME.service;

import com.example.citasmedicasME.dto.UsuarioDTO;
import com.example.citasmedicasME.mapper.UsuarioMapper;
import com.example.citasmedicasME.model.Usuario;
import com.example.citasmedicasME.repository.UsuarioRepository;
import com.example.citasmedicasME.service.interfaces.IUsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    // Constructor con inyección de dependencias
    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public UsuarioDTO saveUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioMapper.usuarioDTOToUsuario(usuarioDTO);
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return usuarioMapper.usuarioToUsuarioDTO(savedUsuario);
    }

    @Override
    public List<UsuarioDTO> findAllUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(usuarioMapper::usuarioToUsuarioDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO findUsuarioById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado")); // Considera usar una excepción más específica
        return usuarioMapper.usuarioToUsuarioDTO(usuario);
    }

    @Override
    public UsuarioDTO updateUsuario(Long id, UsuarioDTO usuarioDTO) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        Usuario usuario = usuarioMapper.usuarioDTOToUsuario(usuarioDTO);
        usuario.setId(id); // Asegúrate de que el ID esté seteado para la actualización
        Usuario updatedUsuario = usuarioRepository.save(usuario);
        return usuarioMapper.usuarioToUsuarioDTO(updatedUsuario);
    }

    @Override
    public void deleteUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        usuarioRepository.deleteById(id);
    }
}
