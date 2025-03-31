package ClasesPrincipales;

/**
 * Clase principal que contiene el método principal(main) para inicializar la aplicación.
 * Esta clase se encarga de crear una instancia de la máquina con un precio por minuto.
 * 
 * @author S
 */
public class Principal {

    // Constante que define el precio por minuto para el uso de la máquina.
    private static final double PRECIO_MINUTO = 0.5;

    /**
     * Método principal que se ejecuta al iniciar la aplicación.
     * Crea una instancia de la clase Maquina con el precio por minuto definido.
     *
     * @param args 
     */
    public static void main(String[] args) {
        // Creación de una nueva instancia de la clase Maquina con el precio por minuto especificado
        Maquina maquina = new Maquina(PRECIO_MINUTO);
    }
}

