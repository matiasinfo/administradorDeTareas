package ar.edu.unlp.info.oo1.ManejadorDeTareas;

import java.time.LocalDate;

import java.time.Period;
class Tarea {

	private String description;
    private boolean estado;
    private int prioridad;
    private String fechaVencimiento;

    public Tarea(String description, int p, String vencimiento ) {
        this.description = description;
        this.estado = false;
        this.prioridad = p;
        this.fechaVencimiento = vencimiento;  
        
    }
    
    public Tarea() {
        // Este constructor sin argumentos es necesario para que Jackson pueda deserializar la clase correctamente.
    }
    
    
    public String getDescription() {
        return description;
    }

    public boolean estado() {
        return estado;
    }

    public void marcarComoRealizada() {
        estado = true;
    }

    public void marcarComoNoRealizada() {
        estado = false;
    }

    public int getPrioridad() {
        return this.prioridad;
    }

    public void cambiarPrioridad(int p){
        prioridad = p;
    }

    public String getFechaVencimiento(){
        return this.fechaVencimiento;
    }

    public long cantidadDeDias(){
        LocalDate fechaActual = LocalDate.now();
        Period duracion = Period.between(fechaActual, LocalDate.parse(fechaVencimiento));
        return duracion.getDays();
    }
   public void cambiarFechaVencimiento(String nueva) {
	   this.fechaVencimiento = nueva;
   }

   @Override
   public String toString() {
	   return (this.getDescription() + " - "  + this.getFechaVencimiento()+ " - " + this.cantidadDeDias());
	   
   }
}

