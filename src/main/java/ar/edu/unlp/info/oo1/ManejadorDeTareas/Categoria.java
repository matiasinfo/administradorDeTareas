package ar.edu.unlp.info.oo1.ManejadorDeTareas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;

// podria genera una variable de tipo lista que valla conteniendo el id para la proxima tarea 
class Categoria {
    private String nombre;
    private List<Tarea> tareas;

    public Categoria(String n) {
        this.nombre = n;
        this.tareas = new ArrayList<>();
    }
    public Categoria(String nombre, List<Tarea> tareas) {
    	this.nombre = nombre;
    	this.tareas = tareas;
    }

    
    public String getNombre(){
        return this.nombre;
    }

    public List<Tarea> getTareas(){
        return this.tareas;
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

    public Tarea buscarTareaPorId(int id) {
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
    
}