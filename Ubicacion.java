package ClasesPrincipales;

/**
 * Clase que representa la ubicación de un vehículo en un aparcamiento.
 * La ubicación se define mediante dos propiedades: planta y plaza.
 * 
 * Esta clase permite acceder a la planta y plaza donde se encuentra estacionado
 * el vehículo, así como modificar estas propiedades si es necesario.
 * 
 * @author S
 */
public class Ubicacion {

    private int planta; // Representa la planta del aparcamiento (nivel donde se encuentra el vehículo)
    private int plaza;  // Representa la plaza dentro de la planta donde se encuentra el vehículo

    /**
     * Constructor de la clase Ubicación.
     * 
     * @param planta La planta en la que se encuentra la plaza.
     * @param plaza  La plaza específica dentro de la planta.
     */
    public Ubicacion(int planta, int plaza) {
        this.planta = planta;
        this.plaza = plaza;
    }

    /**
     * Obtiene el número de la planta donde se encuentra la plaza.
     * 
     * @return El número de la planta.
     */
    public int getPlanta() {
        return planta;
    }

    /**
     * Obtiene el número de la plaza dentro de la planta.
     * 
     * @return El número de la plaza.
     */
    public int getPlaza() {
        return plaza;
    }

    /**
     * Establece el número de la planta en la que se encuentra la plaza.
     * 
     * @param planta El número de la nueva planta donde se desea ubicar la plaza.
     */
    public void setPlanta(int planta) {
        this.planta = planta;
    }

    /**
     * Establece el número de la plaza dentro de la planta.
     * 
     * @param plaza El número de la nueva plaza dentro de la planta.
     */
    public void setPlaza(int plaza) {
        this.plaza = plaza;
    }

    /**
     * Método que devuelve una representación en forma de cadena de la ubicación,
     * incluyendo la planta y la plaza en la que se encuentra el vehículo.
     * 
     * @return Una cadena que representa la ubicación del vehículo.
     */
    @Override
    public String toString() {
        return "Planta: " + planta + ", plaza: " + plaza;
    }

}

