package modelo;

public class Jugador {
    // Constante para el nombre predeterminado
    private static final String NOMBRE_PREDETERMINADO = "Jugador 1";
    
    private String nombre;
    
    public Jugador(){
        // Usar el nombre predeterminado
        this.nombre = NOMBRE_PREDETERMINADO;
    }
    
    public Jugador(String nombre){
        // Validar y establecer el nombre proporcionado
        setNombre(nombre);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        // Validar que el nombre no sea nulo o esté en blanco
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre;
        } else {
            // Si el nombre no es válido, establecer el nombre predeterminado
            this.nombre = NOMBRE_PREDETERMINADO;
        }
    }
}
