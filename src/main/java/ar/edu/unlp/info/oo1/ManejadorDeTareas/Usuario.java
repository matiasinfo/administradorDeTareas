package ar.edu.unlp.info.oo1.ManejadorDeTareas;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

class Usuario {
    private List<Categoria> categorias;
    private String name;
    private String pasword;

    @JsonCreator
    public Usuario(@JsonProperty("name") String name, @JsonProperty("pasword") String pasword) {
        this.categorias = new ArrayList<>();
        this.name = name;
        this.pasword = pasword;
    }
    
    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }
    
    
    public String getName() {
    	return name;
    }
    public String getPasword() {
    	return pasword;
    }
    public void agregarCategoria(Categoria categoria) {
        categorias.add(categoria);
    }
    

    public void eliminarCategoria(Categoria categoria) {
        if (categorias.contains(categoria)) {
            categorias.remove(categoria);
        } else {
            System.out.println("Índice de tarea inválido.");
        }
    }

    public void imprimirCategorias() {
        
        System.out.println("Lista de tareas:");
        categorias.forEach(cat -> System.out.println(categorias.indexOf(cat)+1+ ". " + cat.getNombre()));
    }

    public void modificarCategoria(Categoria categoria) {
        if (categorias.contains(categoria)) {
        	MenuCategoria menu = new MenuCategoria(categoria);
            menu.run();
        } else {
            System.out.println("Categoria no encontrada.");
        }
    }
    public Categoria buscarCategoriaPorId(int id) {
        return categorias.stream()
                .filter(categoria -> categorias.indexOf(categoria) == id -1 )
                .findFirst()
                .orElse(null); // Retorna null si no se encuentra la tarea con el id especificado
    }
}  
    
   