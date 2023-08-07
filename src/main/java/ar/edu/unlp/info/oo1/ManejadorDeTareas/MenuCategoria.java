package ar.edu.unlp.info.oo1.ManejadorDeTareas;

//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MenuCategoria{
	
	private Scanner scanner;
	private Categoria categoria;
	
	public MenuCategoria(Categoria cat) {
		this.scanner = new Scanner(System.in);
		this.categoria = cat;
	}
	    
	    public void agregarNuevaTarea() {
	        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        System.out.print("Descripción de la tarea: ");
	        String description = scanner.nextLine();
	        System.out.print("Nivel de prioridad: ");
	        int priority = scanner.nextInt();
	        System.out.println("Ingrese la fecha (formato: yyyy-MM-dd):");
	        scanner.nextLine();
	        String fechaVencimiento = scanner.nextLine();
	       // LocalDate fechaVencimiento = LocalDate.parse(fechaInput, formatter);
	        categoria.agregarTarea(new Tarea(description, priority, fechaVencimiento));
	        System.out.println("Tarea agregada.");
	    }
	    
	    public void marcarComoRealizada() {
	    	System.out.print("Índique el numero de la tarea a marcar como realizada: ");
	        int taskId = scanner.nextInt();
	        scanner.nextLine();
	        Tarea tareaAMarcar = categoria.buscarTareaPorId(taskId);
	        categoria.marcarComoRealizada(tareaAMarcar);
	    } 
	    
	    private void eliminarTarea() {
	    	System.out.print("indique el numero de la tarea a eliminar");
	        int tareaEliminarId = scanner.nextInt();
	        scanner.nextLine();
	        Tarea tareaAEliminar = categoria.buscarTareaPorId(tareaEliminarId);
	        categoria.eliminarTarea(tareaAEliminar);
	    		
	    	}
	    

	    private void cambiarNivelDePrioridad() {
	    	System.out.print("Indique el numero de la tarea a modificar su nivel prioridad: ");
	    	int tareaModificarId = scanner.nextInt();
	    	scanner.nextLine();
	    	System.out.print("Índique nuevo nivel de prioridad del 1 al 3: ");
	    	int priori = scanner.nextInt();
	    	scanner.nextLine();
	    	categoria.camabiarPrioridad(categoria.buscarTareaPorId(tareaModificarId), priori);

	    	}
	    public void imprimirProximos() {
	    	categoria.proximosYVencidos().forEach(tarea -> System.out.println(tarea.toString()) );
	        ;
	    }
	
	public void run() {
        int option;
        do {
            System.out.println("\n--- Administrador de Tareas ---");
            System.out.println("1. Agregar tarea nueva");
            System.out.println("2. Marcar tarea como realizada");
            System.out.println("3. Eliminar una tarea");
            System.out.println("4. Modificar Nivel de Prioridad");
            System.out.println("5. Mostrar tareas");
            System.out.println("6. Imprimir la de tareas por nivel de prioridad");
            System.out.println("7. imprimir proximos a vencer"); 
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                	agregarNuevaTarea();
                    break;
                case 2:
                	marcarComoRealizada();
                break;
                case 3:
                    eliminarTarea();
                    break;

                case 4:
                	cambiarNivelDePrioridad();
                	break;     
                case 5:
                    categoria.imprimirTareas();
                    break;
                case 6:
                    categoria.imprimirPorPrioridad();
                    break;
                case 7:
                	imprimirProximos();
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
	        Categoria nueva = new Categoria("Facultad");
	        MenuCategoria menu = new MenuCategoria(nueva); 
	        menu.run();
	    }
}



