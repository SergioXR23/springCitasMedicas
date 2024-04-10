package com.example.citasmedicasME.repository;

import com.example.citasmedicasME.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    //todos los metodos CRUD ya estan implementados en JpaRepository
    Usuario findByUsuario(String usuario);
    Usuario findByUsuarioAndClave(String usuario, String clave);

}
