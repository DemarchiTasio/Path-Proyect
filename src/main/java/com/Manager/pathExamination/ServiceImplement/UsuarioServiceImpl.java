package com.Manager.pathExamination.ServiceImplement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Manager.pathExamination.Model.Empleado;
import com.Manager.pathExamination.Model.Role;
import com.Manager.pathExamination.Model.Usuario;
import com.Manager.pathExamination.Repository.EmpleadoRepository;
import com.Manager.pathExamination.Repository.RoleRepository;
import com.Manager.pathExamination.Repository.UsuarioRepository;
import com.Manager.pathExamination.Service.EmpleadoService;
import com.Manager.pathExamination.Service.UsuarioService;

import lombok.extern.slf4j.Slf4j;

@Service("userDetailsService")
@Slf4j
public class UsuarioServiceImpl implements UserDetailsService, UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username);

        if (usuario == null) {
            throw new UsernameNotFoundException(username);
        }

        var roles = new ArrayList<GrantedAuthority>();

        for (Role rol : usuario.getRoles()) {
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        }

        return new User(usuario.getUsername(), usuario.getPassword(), roles);

    }

    public void guardarUsuario(Empleado e, String rol) {

        String pwDefault = "Path@2022";

        String email = "@pathexamination.com";

        e.setEmail(e.getEmail() + email);

        Usuario u = new Usuario(e.getEmail(), pwDefault);
        u.setRoles(Arrays.asList(new Role(rol)));
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        e.setUsuario(u);
        e.setRol(rol);
        usuarioRepository.save(u);
        empleadoRepository.save(e);

    }

    public void deshabilitarUsuario(Usuario u) {

        String pwDisable = "PathDisable";

        u.setPassword(passwordEncoder.encode(pwDisable));
        usuarioRepository.save(u);
    }

    public List<Usuario> listarUsuarios() {

        return usuarioRepository.findAll();
    }

    @Override
    public void generarUsuario(String email) {

        int count = 0;

        for (Usuario usuario : usuarioRepository.findAll()) {

            if (usuario.getUsername().equals(email)) {
                count++;
            }

        }
        if (count == 0) {

            String pwDefault = "Path@2022institucion";
            String rolDefault = "ROLE_INSTITUCION";
            Usuario usuario = new Usuario(email, passwordEncoder.encode(pwDefault));
            usuario.setRoles(Arrays.asList(new Role(rolDefault)));
            guardarUsuarioInstitucion(usuario);
        }

    }

    @Override
    public void guardarUsuarioInstitucion(Usuario u) {
        usuarioRepository.save(u);
    }
}
