package ar.edu.unlp.info.oo1.ManejadorDeTareas;

import java.util.Scanner;

public class MenuUsuario {
	
	private Scanner scanner;
	private Usuario user;
	
	public MenuUsuario(Usuario usuario) {
		this.user = usuario;
		this.scanner = new Scanner(System.in);
	}
	
	public void agregarCategoria() {
		   System.out.print("Nombre de la categoria: ");
	        String description = scanner.nextLine();
	        Categoria nuevaCategoria = new Categoria(description);
	        user.agregarCategoria(nuevaCategoria);
	        System.out.println("Categoria agregada.");
		
	}
	
	public void eliminarCategoria() {
		
		
	        System.out.print("Índice de la Categoria a eliminar: ");
	        user.imprimirCategorias();
	        int deleteIndex = scanner.nextInt();
	        scanner.nextLine();
	        Categoria categoriaAEliminar = user.buscarCategoriaPorId(deleteIndex);
	        user.eliminarCategoria(categoriaAEliminar);
	    
		
	}
	
	public void modificarCategoria() {
	
		System.out.print("Indique que categoria quiere modificar ");
		user.imprimirCategorias();
        int categoria = scanner.nextInt();
        scanner.nextLine(); 
        user.modificarCategoria(user.buscarCategoriaPorId(categoria));
	}
	
	
    public void run() {
        int option;
        do {
            System.out.println("\n--- Administrador de Categorias ---");
            System.out.println("1. Agregar Categoria");
            System.out.println("2. Eliminar Categoria");
            System.out.println("3. Mostrar Categorias");
            System.out.println("4. Modificar Categoria");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                	agregarCategoria();
                    break;

                case 2:
                	eliminarCategoria();
                    break;

                case 3:
                    user.imprimirCategorias();
                    break;
                case 4:
                    modificarCategoria();
                    break;
              
                case 0:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (option != 0);

    }
	

}
