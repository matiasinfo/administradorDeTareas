import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.Comparator;

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

    public void agregarTarea(String description, int prioridad) {
        Tarea task = new Tarea(description, prioridad);
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
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Descripción de la tarea: ");
                    String description = scanner.nextLine();
                    System.out.print("Nivel de prioridad: ");
                    int priority = scanner.nextInt();
                    agregarTarea(description,priority);
                    System.out.println("Tarea agregada.");
                    break;
                case 2:
                System.out.print("Índique el numero de la tarea a marcar como realizada: ");
                int taskId = scanner.nextInt();
                scanner.nextLine();
                Tarea tareaAMarcar = buscarTareaPorId(taskId);
                marcarComoRealizada(tareaAMarcar);
                break;
                case 3:
                    System.out.print("indique el numero de la tarea a eliminar");
                    int tareaEliminarId = scanner.nextInt();
                    scanner.nextLine();
                    Tarea tareaAEliminar = buscarTareaPorId(tareaEliminarId);
                    eliminarTarea(tareaAEliminar);
                    break;

                case 4:
                    System.out.print("\u00CDndique el numero de la tarea a modificar su nivel prioridad: ");
                    int tareaModificarId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Índique nuevo nivel de prioridad del 1 al 3: ");
                    int priori = scanner.nextInt();
                    scanner.nextLine();
                    camabiarPrioridad(buscarTareaPorId(tareaModificarId), priori);
                    break;     
                case 5:
                    imprimirTareas();
                    break;
                case 6:
                    imprimirPorPrioridad();
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
    nueva.agregarTarea("comprar ticketes", 1);
    nueva.agregarTarea("Iniscribirme al examen de ingles", 2);
    nueva.agregarTarea("preparar el examen de fod", 3);
    nueva.agregarTarea("prepararme para el examen de objetos", 3);
    nueva.agregarTarea("anotarme a dbd", 1);
    nueva.run();
}
}