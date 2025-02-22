
package practica;

import java.time.LocalDateTime;

/**
un identificador único, matrícula del coche,
fecha/hora de entrada
 */
public class Ticket {
    
    private int id;
    private String matricula;
    private LocalDateTime fechaHora;
    private Ubicacion ubicacion;

    public Ticket(int id, String matricula, LocalDateTime fechaHora, Ubicacion ubicacion) {
        this.id = id;
        this.matricula = matricula;
        this.fechaHora = fechaHora;
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return "Ticket{" + "id=" + id + ", matricula=" + matricula + ", fechaHora=" + fechaHora + ", ubicacion=" + ubicacion + '}';
    }
    
}
