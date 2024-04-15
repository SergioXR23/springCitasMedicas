package com.example.citasmedicasME.service;

import com.example.citasmedicasME.dto.UsuarioDTO;
import com.example.citasmedicasME.mapper.UsuarioMapper;
import com.example.citasmedicasME.model.Usuario;
import com.example.citasmedicasME.repository.UsuarioRepository;
import com.example.citasmedicasME.service.interfaces.IUsuarioService;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UsuarioServiceImpl implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public UsuarioDTO saveUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioMapper.usuarioDTOToUsuario(usuarioDTO);
        if (usuarioRepository.findByUsuario(usuario.getUsuario()) != null) {
            throw new EntityNotFoundException("El nombre de usuario ya existe");
        }
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
    public UsuarioDTO findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + id));
        return usuarioMapper.usuarioToUsuarioDTO(usuario);
    }

    @Override
    public UsuarioDTO updateUsuario(Long id, UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + id));

        if (usuarioRepository.findByUsuario(usuarioDTO.getUsuario()) != null) {
            throw new EntityNotFoundException("El nombre de usuario ya existe");
        }
        usuario.updateWithDTO(usuarioDTO); // Asumimos un método para actualizar los datos específicos
        Usuario updatedUsuario = usuarioRepository.save(usuario);
        return usuarioMapper.usuarioToUsuarioDTO(updatedUsuario);
    }

    @Override
    public void deleteUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuario no encontrado con ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    public UsuarioDTO login(String usuario, String contrasena) {
        Usuario us = usuarioRepository.findByUsuarioAndClave(usuario, contrasena);
        if (us == null) {
            throw new EntityNotFoundException("Credenciales inválidas para el usuario: " + usuario);
        }
        return usuarioMapper.usuarioToUsuarioDTO(us);
    }

}
