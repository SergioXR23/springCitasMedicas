    package com.example.citasmedicasME.mapper;

    import com.example.citasmedicasME.dto.UsuarioDTO;
    import com.example.citasmedicasME.model.Usuario;
    import org.mapstruct.Mapper;

    @Mapper(componentModel = "spring")
    public interface UsuarioMapper {

        UsuarioDTO usuarioToUsuarioDTO(Usuario usuario);
        Usuario usuarioDTOToUsuario(UsuarioDTO usuarioDTO);
    }
