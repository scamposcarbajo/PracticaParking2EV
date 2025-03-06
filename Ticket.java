package ClasesPrincipales;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Representa un ticket de estacionamiento, que incluye información sobre un coche
 * estacionado en una ubicación específica, con una fecha y hora de entrada, 
 * un identificador único, y su estado de actividad.
 */
public class Ticket {

    // Atributos de la clase Ticket
    private int id;  // Identificador único del ticket
    private String matricula;  // Matrícula del vehículo estacionado
    private LocalDateTime fechaHora;  // Fecha y hora de entrada del vehículo
    private Ubicacion ubicacion;  // Ubicación en la que se encuentra el vehículo estacionado
    private boolean activo;  // Estado del ticket (activo o desactivado)

    /**
     * Constructor de la clase Ticket. Inicializa los atributos con los valores proporcionados.
     * 
     * @param id Identificador único del ticket.
     * @param matricula Matrícula del vehículo.
     * @param fechaHora Fecha y hora de entrada del vehículo.
     * @param ubicacion Ubicación en la que está estacionado el vehículo.
     */
    public Ticket(int id, String matricula, LocalDateTime fechaHora, Ubicacion ubicacion) {
        this.id = id;
        this.matricula = matricula;
        this.fechaHora = fechaHora;
        this.ubicacion = ubicacion;
        this.activo = true; // El ticket está activo al principio
    }

    // Métodos getter y setter para los atributos de la clase

    /**
     * Obtiene el ID del ticket.
     * 
     * @return El identificador del ticket.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID del ticket.
     * 
     * @param id El identificador del ticket.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene la matrícula del vehículo.
     * 
     * @return La matrícula del vehículo.
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * Establece la matrícula del vehículo.
     * 
     * @param matricula La matrícula del vehículo.
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    /**
     * Obtiene la fecha y hora de entrada del vehículo.
     * 
     * @return La fecha y hora de entrada del vehículo.
     */
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    /**
     * Establece la fecha y hora de entrada del vehículo.
     * 
     * @param fechaHora La fecha y hora de entrada.
     */
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    /**
     * Obtiene la ubicación en la que está estacionado el vehículo.
     * 
     * @return La ubicación del vehículo.
     */
    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    /**
     * Establece la ubicación en la que está estacionado el vehículo.
     * 
     * @param ubicacion La nueva ubicación del vehículo.
     */
    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * Obtiene el estado de actividad del ticket (si está activo o no).
     * 
     * @return true si el ticket está activo, false si está desactivado.
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * Desactiva el ticket cambiando su estado a false.
     * 
     * @return El nuevo estado del ticket.
     */
    public boolean desactivar() {
        this.activo = false;
        return this.activo;
    }

    /**
     * Representación en cadena del ticket con la información más relevante: ID, matrícula, 
     * fecha de entrada y ubicación.
     * 
     * @return La representación en formato texto del ticket.
     */
    @Override
    public String toString() {
        return "\t\nID: " + id + "\t\nMatrícula: " + matricula + "\t\nFecha de entrada: "
                + fechaHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + "\t\n"
                + ubicacion + "\n";
    }
}

