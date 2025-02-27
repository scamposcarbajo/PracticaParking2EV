package ClasesPrincipales;

import java.time.LocalDateTime;

/**
 * un identificador único, matrícula del coche, fecha/hora de entrada
 */
public class Ticket {

    private int id;
    private String matricula;
    private LocalDateTime fechaHora;
    private Ubicacion ubicacion;
    private static int contId = 0;

    public Ticket(int id, String matricula, LocalDateTime fechaHora, Ubicacion ubicacion) {
        this.id = id;
        this.matricula = matricula;
        this.fechaHora = fechaHora;
        this.ubicacion = ubicacion;
    }

    public Ticket() {
    }

    public int getId() {
        return id;
    }

    public String getMatricula() {
        return matricula;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public static int getContId() {
        return contId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public static void setContId(int contId) {
        Ticket.contId = contId;
    }

    @Override
    public String toString() {
        return "Ticket{" + "id=" + id + ", matricula=" + matricula + ", fechaHora=" + fechaHora + ubicacion + '}';
    }

}
