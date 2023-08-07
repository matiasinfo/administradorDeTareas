package ar.edu.unlp.info.oo1.ManejadorDeTareas;


	import com.fasterxml.jackson.databind.ObjectMapper;
	import com.fasterxml.jackson.databind.SerializationFeature;
	import java.io.File;
	import java.io.IOException;

	public class ArchivoUsuario {
	    private ObjectMapper objectMapper;

	    public ArchivoUsuario() {
	        this.objectMapper = new ObjectMapper();
	        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
	    }

	    public void guardarUsuarioEnArchivo(Usuario user) {
	        try {
	            File archivo = new File(user.getName() + ".json");
	            objectMapper.writeValue(archivo, user);
	            System.out.println("Información del usuario guardada en el archivo: " + archivo.getName());
	        } catch (IOException e) {
	            System.out.println("Error al guardar la información del usuario en el archivo: " + e.getMessage());
	        }
	    }

	    public Usuario cargarUsuarioDesdeArchivo(String nombreUsuario) {
	        try {
	            File archivo = new File(nombreUsuario + ".json");
	            return objectMapper.readValue(archivo, Usuario.class);
	        } catch (IOException e) {
	            System.out.println("Error al cargar la información del usuario desde el archivo: " + e.getMessage());
	            return null;
	        }
	    }

	    // Otros métodos relevantes para trabajar con archivos de usuario...
	}

