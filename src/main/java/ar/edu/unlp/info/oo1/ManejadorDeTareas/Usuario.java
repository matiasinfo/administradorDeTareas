package ar.edu.unlp.info.oo1.ManejadorDeTareas;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Usuario {
    private List<Categoria> categorias;
    private String name;
    private String pasword;
    private Scanner scanner;

    public Usuario(String name, String pasword) {
        this.categorias = new ArrayList<>();
        this.name = name;
        this.pasword = pasword;
        this.scanner = new Scanner(System.in);
    }
    
    public String getName() {
    	return name;
    }
    public String getPasword() {
    	return pasword;
    }
    
    public void agregarCategoria(String description) {
        Categoria nueva = new Categoria(description);
        categorias.add(nueva);
    }

    public void eliminarCategoria(int index) {
        if (index >= 0 && index < categorias.size()) {
            categorias.remove(index);
        } else {
            System.out.println("Índice de tarea inválido.");
        }
    }

    public void imprimirCategorias() {
        System.out.println("Lista de tareas:");
        for (Categoria categoria : categorias) {
            int indice = categorias.indexOf(categoria);
            System.out.println(indice + ". " + categoria.getNombre());

        }
    }

    public void modificarCategoria(int index) {
        if (index >= 0 && index < categorias.size()) {
            MenuManejadorDeTareas menu = new MenuManejadorDeTareas (categorias.get(index));
            menu.run();
            ;
        } else {
            System.out.println("Índice de tarea inválido.");
        }
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
                    System.out.print("Nombre de la categoria: ");
                    String description = scanner.nextLine();
                    agregarCategoria(description);
                    System.out.println("Categoria agregada.");
                    break;

                case 2:
                    System.out.print("Índice de la Categoria a eliminar: ");
                    int deleteIndex = scanner.nextInt();
                    scanner.nextLine();
                    eliminarCategoria(deleteIndex);
                    break;

                case 3:
                    imprimirCategorias();
                    break;
                case 4:
                    System.out.print("Indique que categoria quiere modificar ");
                    int categoria = scanner.nextInt();
                    scanner.nextLine();
                    modificarCategoria(categoria);
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
        Usuario taskManager = new Usuario("matias","123456");
        taskManager.run();
    }
}