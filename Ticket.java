
package ClasesPrincipales;

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
    private boolean activo;
    
    public Ticket(int id, String matricula, LocalDateTime fechaHora, Ubicacion ubicacion) {
        this.id = id;
        this.matricula = matricula;
        this.fechaHora = fechaHora;
        this.ubicacion = ubicacion;
        this.activo = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public boolean isActivo() {
        return activo;
    }

    public boolean desactivar() {
        this.activo = false;
        return this.activo;
    }
    
    @Override
    public String toString() {
        return "Ticket{" + "id=" + id + ", matricula=" + matricula + ", fechaHora=" + fechaHora + ", ubicacion=" + ubicacion + '}';
    }

//    @Override
//    public int hashCode() {
//        int hash = 3;
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final Ticket other = (Ticket) obj;
//        return this.id == other.id;
//    }
    
    
    
}
