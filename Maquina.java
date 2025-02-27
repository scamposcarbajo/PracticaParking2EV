/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClasesPrincipales;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author S
 */
public class Maquina {

    private static final int NUM_MAX_PLANTAS = 3;
    private static final int NUM_MAX_PLAZAS_POR_PLANTA = 20;
    private int asignadorClave = 0;

    private double precioMinuto;
    private Deposito deposito;
    // el genérico es Ticket porque vamos a usar Objetos Ticket dentro
    // de la lista
    private List<Ticket> listaTickets;
    private int[][] plano;

    public Maquina(double precioMinuto) {
        this.precioMinuto = precioMinuto;
        // tenemos que inicializar el deposito
        // Maquina en su constructor solo recibe precio/minuto
        this.deposito = new Deposito(100, 100, 100, 100, 100, 100, 100, 100, 100);
        // no olvidar los diamantes para el genérico
        this.listaTickets = new ArrayList<>();
        this.plano = new int[NUM_MAX_PLANTAS][NUM_MAX_PLAZAS_POR_PLANTA];
        // Terminal para que se inicie cuando se inicie la clase Maquina
        Terminal terminal = new Terminal();
        terminal.setVisible(true);

    }

    public Maquina() {
    }
    

    public Ubicacion encontrarEspacioLibre() {

        for (int i = 0; i < NUM_MAX_PLANTAS; i++) {
            for (int j = 0; j < NUM_MAX_PLAZAS_POR_PLANTA; j++) {
                if (this.plano[i][j] == 0) {
                    // significa que esta posición está vacía
                    Ubicacion ubicacion = new Ubicacion(i, j);
                    return ubicacion;
                }
            }
        }
        return null;
    }

    public Ticket asignarPlaza(Ubicacion ubicacionPlaza, String matricula) {
        // le pasamos la ubicacion y un ID
        //creo que el metodo deberia llamar al metodo de arriba de encontrar ubicacion para asignarla
        int id = asignadorClave++;
        this.plano[ubicacionPlaza.getPlanta()][ubicacionPlaza.getPlaza()] = id;
        Ticket ticket = new Ticket(id, matricula, LocalDateTime.now(), ubicacionPlaza);
        return ticket;
    }

}
