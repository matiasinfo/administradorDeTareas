package ar.edu.unlp.info.oo1.ManejadorDeTareas;



import java.time.LocalDate;
import java.time.Period;

import com.fasterxml.jackson.annotation.JsonProperty;
/*import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.Duration;
*/
class Tarea {
    private static int nextId = 1;
    private int id;
    private String description;
    private boolean estado;
    private int prioridad;
    @JsonProperty("fecha")
    private LocalDate fechaVencimiento;

    public Tarea(String description, int p, LocalDate vencimiento ) {
        this.description = description;
        this.estado = false;
        this.prioridad = p;
        this.fechaVencimiento = vencimiento;
        this.id = nextId++;    
    }
    
    public Tarea() {
        // Este constructor sin argumentos es necesario para que Jackson pueda deserializar la clase correctamente.
    }
    
    
    public int getId() {
        return id;
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

    public void camabiarPrioridad(int p){
        prioridad = p;
    }

    public LocalDate getFecha(){
        return this.fechaVencimiento;
    }

    public long cantidadDeDias(){
        LocalDate fechaActual = LocalDate.now();
        Period duracion = Period.between(fechaActual, fechaVencimiento);
        return duracion.getDays();
    }
   
/*    public String fomatearTiempoFaltante() {
        LocalDateTime fechaActual = LocalDateTime.now();
        Duration duracion = Duration.between(fechaActual, fechaVencimiento);

        long dias = duracion.toDays();
        long horas = duracion.toHoursPart();
        long minutos = duracion.toMinutesPart();
        long segundos = duracion.toSecondsPart();

        return String.format("%d días, %d horas, %d minutos y %d segundos", dias, horas, minutos, segundos);
    }

   /* public void setFechaVencimientoConHora() {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        System.out.println("Ingrese la fecha de vencimiento con hora (formato: yyyy-MM-dd HH:mm):");
        String fechaHoraInput = scanner.nextLine();

        try {
            LocalDateTime fechaVencimientoConHora = LocalDateTime.parse(fechaHoraInput, formatter);
            this.fechaVencimiento = fechaVencimientoConHora;
        } catch (Exception e) {
            System.out.println("Error al ingresar la fecha. Asegúrese de utilizar el formato correcto.");
        }
    }
}
    public static void main(String[] args) {
        Tarea tarea1 = new Tarea("Completar el informe", 3);
        
        String tiempoFaltante = tarea1.fomatearTiempoFaltante();
        System.out.println("Tiempo faltante para la fecha de vencimiento: " + tiempoFaltante);
        
        JOptionPane.showMessageDialog(null, "Tiempo faltante para la fecha de vencimiento: " + tiempoFaltante, "Aviso", JOptionPane.INFORMATION_MESSAGE);
        System.out.println("la cantidad de dias faltantes es: "+tarea1.cantidadDeDias());
        tarea1.setFechaVencimientoConHora();


        
        String tiempoFaltante2 = tarea1.fomatearTiempoFaltante();
        System.out.println("Tiempo faltante para la fecha de vencimiento: " + tiempoFaltante2);
        System.out.println("la cantidad de dias faltantes es: " + tarea1.cantidadDeDias());
        JOptionPane.showMessageDialog(null, "Tiempo faltante para la fecha de vencimiento: " + tiempoFaltante2, "Aviso", JOptionPane.INFORMATION_MESSAGE);

    
    }
} */

}

