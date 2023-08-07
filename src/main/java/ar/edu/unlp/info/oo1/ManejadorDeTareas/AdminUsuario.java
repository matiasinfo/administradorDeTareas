package ar.edu.unlp.info.oo1.ManejadorDeTareas;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class AdminUsuario {
		
    	private Scanner scanner;
    	private List<Usuario> usuarios;
		
		public AdminUsuario() {
			this.usuarios = new ArrayList<Usuario>();
			this.scanner = new Scanner(System.in);
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
		
		public boolean chechPasword(Usuario user,String pasword) {
				
			return user.getPasword().equals(pasword);
		}
		
		public void registrarUsuario() {
			
			System.out.println(" Ingrese su nuevo nombre de usuario");
			String nombre = scanner.nextLine();
			System.out.println(" Ingrese Contraseña");
			String contraseña = scanner.nextLine();
			Usuario nuevoUsuario = new Usuario(nombre,contraseña);
			usuarios.add(nuevoUsuario);
			guardarUsuarioEnArchivo(nuevoUsuario);
			
			System.out.println("Usuario registrado");
		}
		
		public void iniciarSesion() {
			
			System.out.println(" ingrese el nombre de usuario");
			String usuario = scanner.nextLine();
			Usuario user = buscarUsuario(usuario);
			if (user != null) {
				System.out.println(" ingrese la contraseña");
				String pass = scanner.nextLine();
				if(chechPasword(user, pass)) {
					MenuUsuario menu = new MenuUsuario(user);
					menu.run();
				} else  
				System.out.println("contraseña incorrecta");
			
				}
			else 
			System.out.println("nombre de usuario incorrecto");
		}
	    public void guardarUsuarioEnArchivo(Usuario user) {
	        ObjectMapper objectMapper = new ObjectMapper();
	        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

	        try {
	            File archivo = new File(user.getName() + ".json");
	            objectMapper.writeValue(archivo, user);
	            System.out.println("Información del usuario y categorías guardada en el archivo: " + archivo.getName());
	        } catch (IOException e) {
	            System.out.println("Error al guardar la información del usuario en el archivo: " + e.getMessage());
	        }
	    }

	    public void cargarUsuarioDesdeArchivo(Usuario user) {
	        ObjectMapper objectMapper = new ObjectMapper();

	        try {
	            File archivo = new File(user.getName() + ".json");
	            Usuario usuarioCargado = objectMapper.readValue(archivo, Usuario.class);
	            if (usuarioCargado != null) {
	                List<Categoria> categoriasCargadas = usuarioCargado.getCategorias();
	                user.setCategorias(categoriasCargadas);
	                System.out.println("Usuario y categorías cargados exitosamente.");
	            } else {
	                System.out.println("No se pudo cargar el usuario.");
	            }
	        } catch (IOException e) {
	            System.out.println("Error al cargar la información del usuario desde el archivo: " + e.getMessage());
	        }
	    }

	    public void guardarSistemaEnArchivo() {
	        ObjectMapper objectMapper = new ObjectMapper();
	        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

	        try {
	            File archivo = new File("Usuarios.json");
	            objectMapper.writeValue(archivo, usuarios);
	            System.out.println("Información del sistema guardada en el archivo: " + archivo.getName());
	        } catch (IOException e) {
	            System.out.println("Error al guardar la información del sistema en el archivo: " + e.getMessage());
	        }
	    }

	    public void cargarSistemaDesdeArchivo() {
	        ObjectMapper objectMapper = new ObjectMapper();

	        try {
	            File archivo = new File("Usuarios.json");
	            Usuario[] usuariosCargados = objectMapper.readValue(archivo, Usuario[].class);
	            if (usuariosCargados != null) {
	                this.usuarios = new ArrayList<>(Arrays.asList(usuariosCargados));
	                System.out.println("Sistema cargado exitosamente.");
	            } else {
	                System.out.println("No se pudo cargar el sistema.");
	            }
	        } catch (IOException e) {
	            System.out.println("Error al cargar la información del sistema desde el archivo: " + e.getMessage());
	        }
	    }
		
		public void run() {
	        int option;
	        do {
	            System.out.println("\n--- Administrador de Categorias ---");
	            System.out.println("1. iniciar sesion");
	            System.out.println("2. Registrarse");

	            System.out.println("0. Salir");
	            System.out.print("Opción: ");
	            
	            option = scanner.nextInt();
	            scanner.nextLine();
	            
	            switch (option) {
	                case 1:
	                	iniciarSesion();
	                    break;

	                case 2:
	                	registrarUsuario();
	                	
	                    break;
	                case 0:
	                    System.out.println("¡Hasta luego!");
	                    break;
	                default:
	                    System.out.println("Opción inválida.");
	            }
	             
	        } while (option != 0);

	    }
	public static void main(String[] args) {
	        AdminUsuario sistema = new AdminUsuario();
	        sistema.cargarSistemaDesdeArchivo();
	        sistema.run();
	        sistema.guardarSistemaEnArchivo();

	}
}
