package com.example.citasmedicasME.model;

import com.example.citasmedicasME.dto.UsuarioDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED) // Seleccionamos la estrategia de herencia  JOINED
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellidos;

    @Column(nullable = false, unique = true)
    private String usuario;

    @Column(nullable = false)
    private String clave;


    public void updateWithDTO(UsuarioDTO usuario) {
        this.nombre = usuario.getNombre();
        this.apellidos = usuario.getApellidos();
        this.usuario = usuario.getUsuario();
        this.clave = usuario.getClave();
    }
}
