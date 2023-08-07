package ar.edu.unlp.info.oo1.ManejadorDeTareas;

import java.util.Scanner;

public class MenuSistema {
	


	    private Scanner scanner;
	    private AdminUsuario adminUsuario;
	    private ArchivoUsuario archivoUsuario;

	    public MenuSistema() {
	        this.scanner = new Scanner(System.in);
	        this.adminUsuario = new AdminUsuario();
	        this.archivoUsuario = new ArchivoUsuario();
	    }

	    public void run() {
	        int option;
	        do {
	            System.out.println("\n--- Administrador de Categorias ---");
	            System.out.println("1. Iniciar sesión");
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

	    public void iniciarSesion() {
	        System.out.println("Ingrese el nombre de usuario");
	        String usuario = scanner.nextLine();
	        Usuario user = adminUsuario.buscarUsuario(usuario);
	        if (user != null) {
	            System.out.println("Ingrese la contraseña");
	            String pass = scanner.nextLine();
	            if (adminUsuario.chechPasword(user, pass)) {
	                // Iniciar sesión exitosa
	                MenuUsuario menuUsuario = new MenuUsuario(user);
	                menuUsuario.run();
	            } else {
	                System.out.println("Contraseña incorrecta");
	            }
	        } else {
	            System.out.println("Nombre de usuario incorrecto");
	        }
	    }

	    public void registrarUsuario() {
	        System.out.println("Ingrese su nuevo nombre de usuario");
	        String nombre = scanner.nextLine();
	        System.out.println("Ingrese Contraseña");
	        String contraseña = scanner.nextLine();
	        adminUsuario.registrarUsuario(nombre, contraseña);
	        Usuario nuevoUsuario = adminUsuario.buscarUsuario(nombre);
	        if (nuevoUsuario != null) {
	            archivoUsuario.guardarUsuarioEnArchivo(nuevoUsuario);
	        }
	    }

	    public static void main(String[] args) {
	        MenuSistema menuPrincipal = new MenuSistema();
	        menuPrincipal.run();
	    }
	}


	