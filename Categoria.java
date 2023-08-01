import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.xml.crypto.Data;

import java.util.Collections;
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

    public void marcarComoRealizada(int index) {
        if (index >= 0 && index < tareas.size()) {
            tareas.get(index).marcarComoRealizada();
        } else {
            System.out.println("Índice de tarea inválido.");
        }
    }

    public void camabiarPrioridad(int index, int p) {
        if (index >= 0 && index < tareas.size()) {
            tareas.get(index).camabiarPrioridad(p);
        } else {
            System.out.println("Índice de tarea inválido.");
        }
    }

    public void eliminarTarea(int index) {
        if (index >= 0 && index < tareas.size()) {
            tareas.remove(index);
        } else {
            System.out.println("Índice de tarea inválido.");
        }
    }

    public void imprimirTareas() {
        System.out.println("Lista de tareas:");
        tareas.stream().forEach(task -> System.out.println(". " + task.getDescription() + " - " + (task.estado() ? "Hecha" : "Pendiente")+ " - " + " prioridad: " + task.getPrioridad())  );
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
            System.out.println("1. Agregar tarea");
            System.out.println("2. Marcar tarea como hecha");
            System.out.println("3. Eliminar tarea");
            System.out.println("4. Modificar Prioridad");
            System.out.println("5. Mostrar tareas");
            System.out.println("6. Imprimir la lista por orden de prioridad");
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
                    System.out.print("Índice de la tarea a marcar como hecha: ");
                    int doneIndex = scanner.nextInt() - 1;
                    scanner.nextLine();
                    marcarComoRealizada(doneIndex);
                    break;
                case 3:
                    System.out.print("Índice de la tarea a eliminar: ");
                    int deleteIndex = scanner.nextInt() - 1;
                    scanner.nextLine();
                    eliminarTarea(deleteIndex);
                    break;

                case 4:
                    System.out.print("Índice de la tarea a modificar la prioridad: ");
                    int modificarIndex = scanner.nextInt() - 1;
                    scanner.nextLine();
                    System.out.print("Índique nuevo nivel de prioridad: ");
                    int priori = scanner.nextInt();
                    scanner.nextLine();
                    camabiarPrioridad(modificarIndex, priori);
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
    nueva.run();
}
}