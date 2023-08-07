package ar.edu.unlp.info.oo1.ManejadorDeTareas;

import static org.junit.jupiter.api.Assertions.*;

//import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TareaTest {
	
Tarea tarea1, tarea2;
	
	@BeforeEach
	void setUp() throws Exception {
		tarea1 = new Tarea("anotarme a comedor",3,"2023-08-16");
		tarea2 = new Tarea("Comer",2,"2023-08-08");
		
	}
	
	@Test
    public void testFecha() {
        assertEquals(10, tarea1.cantidadDeDias());
        assertEquals(2,tarea2.cantidadDeDias());
    }
	@Test
    public void testCambiarPrioridad() {
        assertEquals(3, tarea1.getPrioridad());
        assertNotEquals(1,tarea2.getPrioridad());
        
        tarea2.cambiarPrioridad(1);
        assertEquals(1,tarea2.getPrioridad());
	}
	
	@Test
    public void testCambiarFechaVencimiento() {
        assertEquals(10, tarea1.cantidadDeDias());
        assertNotEquals(0,tarea2.cantidadDeDias());
        
        tarea2.cambiarFechaVencimiento("2023-08-07");
        assertEquals(1,tarea2.cantidadDeDias());
	}

	public void testModificarEstado() {
        assertFalse(tarea1.estado());
        tarea1.marcarComoRealizada();
        assertTrue(tarea1.estado());
    }
}
