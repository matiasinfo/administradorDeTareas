package ar.edu.unlp.info.oo1.ManejadorDeTareas;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Comparator;

class Categoria {
    private String nombre;
    private List<Tarea> tareas;

    @JsonCreator
    public Categoria(@JsonProperty("nombre") String nombre) {
        this.nombre = nombre;
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

    public void agregarTarea(Tarea task) {
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
            tarea.cambiarPrioridad(p);
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
        tareas.forEach(task -> System.out.println(tareas.indexOf(task)+1+ ". " + task.getDescription() + " - " + (task.estado() ? "Hecha" : "Pendiente")+ " - " + " prioridad: " + task.getPrioridad()+" - Fecha "+task.getFechaVencimiento()));
    }

    public Tarea buscarTareaPorId(int id) {
        return tareas.stream()
                .filter(tarea -> tareas.indexOf(tarea) == id -1 )
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
    public List<Tarea> proximosYVencidos() {
    	
    	System.out.println("Las Siguientes taras estan por vencer");
    	return tareas.stream()
    			.filter(tarea -> tarea.cantidadDeDias() <= 5)
    			.collect(Collectors.toList());
    }
   
    
}