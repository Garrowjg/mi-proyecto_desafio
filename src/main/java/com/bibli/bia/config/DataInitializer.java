package com.bibli.bia.config;

import com.bibli.bia.Model.Usuario;
import com.bibli.bia.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Verificamos si el usuario admin3 ya existe
        if (usuarioRepository.findByUsername("admin5").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setUsername("admin5"); // Nombre de usuario
            admin.setPassword(passwordEncoder.encode("1234567")); // Contraseña segura
            // Asignamos el rol con el prefijo ROLE_
            admin.setRoles(Set.of("ADMIN")); // Rol de admin con el prefijo ROLE_
            usuarioRepository.save(admin);
            System.out.println("Usuario admin4 creado con éxito");
        }
    }
}
