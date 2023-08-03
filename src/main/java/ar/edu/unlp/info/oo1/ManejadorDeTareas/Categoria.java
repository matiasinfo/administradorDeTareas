package ar.edu.unlp.info.oo1.ManejadorDeTareas;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.Comparator;
// podria genera una variable de tipo lista que valla conteniendo el id para la proxima tarea 
class Categoria {
    private String nombre;
    private List<Tarea> tareas;
    private Scanner scanner;

    public Categoria(String n) {
        this.nombre = n;
        this.tareas = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public String getNombre(){
        return this.nombre;
    }

    public void agregarTarea(String description, int prioridad, LocalDate fecha) {
        Tarea task = new Tarea(description, prioridad,fecha);
        tareas.add(task);
    }

    public void marcarComoRealizada(Tarea tarea) {
        if (tareas.contains(tarea)) {
            tarea.marcarComoRealizada();
        } else {
            System.out.println("Tarea no encontrada.");
        }
    }
    
    public void camabiarPrioridad(Tarea tarea, int p) {
        if (tareas.contains(tarea)) {
            tarea.camabiarPrioridad(p);
        } else {
            System.out.println("Tarea no encontrada.");
        }
    }
    
    public void eliminarTarea(Tarea tarea) {
        if (tareas.contains(tarea)) {
            tareas.remove(tarea);
        } else {
            System.out.println("Tarea no encontrada.");
        }
    }

   
    public void imprimirTareas() {
        System.out.println("Lista de tareas:");
        tareas.forEach(task -> System.out.println(task.getId() + ". " + task.getDescription() + " - " + (task.estado() ? "Hecha" : "Pendiente")+ " - " + " prioridad: " + task.getPrioridad()));
    }

    private Tarea buscarTareaPorId(int id) {
        return tareas.stream()
                .filter(tarea -> tarea.getId() == id)
                .findFirst()
                .orElse(null); // Retorna null si no se encuentra la tarea con el id especificado
    }

    public void imprimirPorPrioridad() {
        System.out.println("Lista de tareas:");
        
        List<Tarea> listaOrdenada = tareas.stream()
                .sorted(Comparator.comparingInt(Tarea::getPrioridad))
                .collect(Collectors.toList());

        listaOrdenada.forEach(task -> System.out.println(". " + task.getDescription() + " - " + (task.estado() ? "Hecha" : "Pendiente")+ " - " + " prioridad: " + task.getPrioridad()));
    }
    
    public void guardarEnArchivo(String nombreArchivo) {
    	ObjectMapper objectMapper = new ObjectMapper();
    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    	objectMapper.registerModule(new JavaTimeModule());

        try {
            File archivo = new File(nombreArchivo);
            objectMapper.writeValue(archivo, tareas);
            System.out.println("Información guardada en el archivo: " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al guardar la información en el archivo: " + e.getMessage());
        }
    }

    // Método para cargar la información de las categorías y tareas desde un archivo JSON
    public void cargarDesdeArchivo(String nombreArchivo) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        objectMapper.setDateFormat(dateFormat);

        try {
            File archivo = new File(nombreArchivo);
            CollectionType tipoListaTareas = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Tarea.class);
            tareas = objectMapper.readValue(archivo, tipoListaTareas);
            System.out.println("Información cargada desde el archivo: " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al cargar la información desde el archivo: " + e.getMessage());
        }
    }
    
    // metodo para agregar una tarea nueva a la lista de tareas 
    public void agregarNuevaTarea() {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.print("Descripción de la tarea: ");
        String description = scanner.nextLine();
        System.out.print("Nivel de prioridad: ");
        int priority = scanner.nextInt();
        System.out.println("Ingrese la fecha (formato: yyyy-MM-dd):");
        scanner.nextLine();
        String fechaInput = scanner.nextLine();
        LocalDate fechaVencimiento = LocalDate.parse(fechaInput, formatter);
        agregarTarea(description,priority,fechaVencimiento);
        System.out.println("Tarea agregada.");
    	
    }
    
    public void marcarComoRealizada() {
    	System.out.print("Índique el numero de la tarea a marcar como realizada: ");
        int taskId = scanner.nextInt();
        scanner.nextLine();
        Tarea tareaAMarcar = buscarTareaPorId(taskId);
        marcarComoRealizada(tareaAMarcar);
    }
    
    private void eliminarTarea() {
    	System.out.print("indique el numero de la tarea a eliminar");
        int tareaEliminarId = scanner.nextInt();
        scanner.nextLine();
        Tarea tareaAEliminar = buscarTareaPorId(tareaEliminarId);
        eliminarTarea(tareaAEliminar);
    		
    	}
    

    private void cambiarNivelDePrioridad() {
    	System.out.print("\u00CDndique el numero de la tarea a modificar su nivel prioridad: ");
    	int tareaModificarId = scanner.nextInt();
    	scanner.nextLine();
    	System.out.print("Índique nuevo nivel de prioridad del 1 al 3: ");
    	int priori = scanner.nextInt();
    	scanner.nextLine();
    	camabiarPrioridad(buscarTareaPorId(tareaModificarId), priori);

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
            System.out.println("7. Guardar en  Archivo");
            System.out.println("8. Cargar el  Archivo");
            
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
                    imprimirTareas();
                    break;
                case 6:
                    imprimirPorPrioridad();
                    break;
                case 7:
                	guardarEnArchivo("informacion.json");
                    break;
                case 8:
                	cargarDesdeArchivo("informacion.json");
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
    Categoria nueva = new Categoria("facultad");
    nueva.run();
}

}