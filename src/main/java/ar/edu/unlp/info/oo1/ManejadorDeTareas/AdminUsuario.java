package ar.edu.unlp.info.oo1.ManejadorDeTareas;


import java.util.ArrayList;
import java.util.List;

public class AdminUsuario {
    private List<Usuario> usuarios;

    public AdminUsuario() {
        this.usuarios = new ArrayList<>();
    }

    public void agregarUsuario(Usuario user) {
        usuarios.add(user);
    }

    public Usuario buscarUsuario(String nombre) {
        return usuarios.stream()
                .filter(user -> user.getName().equals(nombre))
                .findFirst()
                .orElse(null);
    }

    public boolean chechPasword(Usuario user, String pasword) {
        return user.getPasword().equals(pasword);
    }

    public void registrarUsuario(String nombre, String contraseña) {
        Usuario nuevoUsuario = new Usuario(nombre, contraseña);
        usuarios.add(nuevoUsuario);
        System.out.println("Usuario registrado.");
    }

    
}
