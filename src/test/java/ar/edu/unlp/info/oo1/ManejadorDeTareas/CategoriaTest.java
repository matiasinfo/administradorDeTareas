package ar.edu.unlp.info.oo1.ManejadorDeTareas;

import static org.junit.jupiter.api.Assertions.*;
//import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CategoriaTest {


	Categoria facultad, deportes;
	Tarea tarea1, tarea2,tarea3,tarea4,tarea5;
		
		@BeforeEach
		void setUp() throws Exception {
			facultad = new Categoria("Deportes");
			deportes = new Categoria("Facultad");
			tarea1 = new Tarea("Estudiar",3,"2023-08-08");
			tarea2 = new Tarea("Comer",2,"2023-08-10");
			tarea3 = new Tarea("Dormir",3,"2023-08-15");
			tarea4 = new Tarea("Bañarme",1,"2023-08-20");
			tarea5 = new Tarea("Jugar",1,"2023-08-08");
			facultad.agregarTarea(tarea1);
			facultad.agregarTarea(tarea2);
			deportes.agregarTarea(tarea3);
			deportes.agregarTarea(tarea4);
			
		}
		
		
		@Test
	    public void testBuscarPorId() {
	        
	        assertEquals(tarea1, facultad.buscarTareaPorId(facultad.getTareas().indexOf(tarea1)+1));
	        assertNotEquals(tarea2,facultad.buscarTareaPorId(facultad.getTareas().indexOf(tarea1)+1));
	    }
		
		@Test
	    public void testEliminar() {
			assertEquals(2,facultad.getTareas().size());
			facultad.eliminarTarea(tarea1);
			assertNotEquals(2,facultad.getTareas().size());
			assertEquals(1,facultad.getTareas().size());
			facultad.eliminarTarea(tarea1);
		}
	    @Test
	    public void testImprimir() {
	    	facultad.imprimirPorPrioridad();
	    	System.out.println("antes de agregar otra nueva ");
	    	
	    	facultad.agregarTarea(tarea5);
	    	facultad.imprimirTareas();
	    	
	    	System.out.println("despues de agregar la nueva imprimo proximos a vencer");
	    	facultad.proximosYVencidos().forEach(t -> System.out.println(t.toString()));;
	    	
	    }
	    @Test
	    public void testProximos() {
	    	
	    }
}
