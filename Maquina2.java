/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica;

import java.time.LocalDateTime;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Maquina2 {

    private static final int NUM_MAX_PLANTAS = 3;
    private static final int NUM_MAX_PLAZAS_POR_PLANTA = 20;
    private static int asignadorClave = 0;//asignar el id del ticket PREGUNTAR
    private static double precioMinuto;
    private Deposito deposito = new Deposito(100, 100, 100, 100, 100, 100, 100, 100, 100);
    // el genérico es Ticket porque vamos a usar Objetos Ticket dentro
    // de la lista
    private ArrayList<Ticket> listaTickets = new ArrayList<>();

    private static Integer[][] plano = rellenarPlano();

    private Terminal terminal;

    //constructor principal con precio por minuto creado desde PRINCIPAL
    public Maquina2(double precioMinuto) {
        this.precioMinuto = precioMinuto;
        // tenemos que inicializar el deposito
        // Maquina en su constructor solo recibe precio/minuto

        // no olvidar los diamantes para el genérico
        System.out.println("constructor normal");

        rellenarPlano();
        mostrarPlano();
        terminal = new Terminal();
        terminal.setVisible(true);
    }

    //constructor vacio para acceder desde TERMINAL 
    public Maquina2() {
        System.out.println("Constructor por defecto");
    }

    public ArrayList<Ticket> getListaTickets() {
        return listaTickets;
    }

    public Integer[][] getPlano() {
        return plano;
    }

    //terminado
    public static Integer[][] rellenarPlano() {
        plano = new Integer[NUM_MAX_PLANTAS][NUM_MAX_PLAZAS_POR_PLANTA];
        for (int i = 0; i < plano.length; i++) {
            for (int j = 0; j < plano[i].length; j++) {
                plano[i][j] = 0;
            }
        }
        return plano;
    }

    //terminado
    public void mostrarPlano() {
        for (int i = 0; i < plano.length; i++) {
            for (int j = 0; j < plano[i].length; j++) {
                System.out.print(plano[i][j]);
            }
            System.out.println("");
        }
    }

    //terminado
    public Ubicacion encontrarEspacioLibre() {
        for (int i = 0; i < plano.length; i++) {
            for (int j = 0; j < plano[i].length; j++) {
                if (plano[i][j] == 0) {
                    // significa que esta posición está vacía
                    Ubicacion ubicacion = new Ubicacion(i, j);
                    return ubicacion;
                }
            }
        }
        return null;
    }

    //terminado
    public Ticket asignarPlaza(Ubicacion ubicacionPlaza, String matricula) {
        // le pasamos la ubicacion y un ID
        //PREGUNTAR SI EL ID LO TIENE QUE HACER TICKET O MAQUINA
        asignadorClave++;
        int id = asignadorClave;
        System.out.println("ID " + id);
        Ticket ticket = new Ticket(id, matricula, LocalDateTime.now(), ubicacionPlaza);
        this.plano[ubicacionPlaza.getPlanta()][ubicacionPlaza.getPlaza()] = id;
        System.out.println("ticket del metodo " + ticket.toString());
        this.listaTickets.add(ticket);
        mostrarPlano();
        return ticket;
    }

    public void liberarPlaza(Ticket ticket) {

        // 1. obtener ubicacion del ticket
        // 2. Buscar en la matriz la ubicacion[i][j] y ponerlo a 0
        // la ubicacion es un objeto asi que podemos guardar los dos valores asi
        Ubicacion ubicacion = ticket.getUbicacion();
        this.plano[ubicacion.getPlanta()][ubicacion.getPlaza()] = 0;

        // se puede hacer con id
    }

    //terminado
    public int calcularTiempoTranscurrido(Ticket ticket) {
        //devuelve el tiempo transcurrido calculando la diferencia de tiempo del ticket
        //con la hora en la que se le llama al metodo
        LocalDateTime fechaEntrada = ticket.getFechaHora();
        LocalDateTime fechaSalida = LocalDateTime.now();
        LocalDateTime tiempoTranscurrido = fechaSalida.minusMinutes((int) fechaEntrada.getMinute());
        return (int) tiempoTranscurrido.getMinute() + 1;
        //el +1 solo es para redondear los segundos, en el momento que haya 1
        //segundo ya lo cuenta como un minuto mas

    }

    //terminado
    public double totalDineroDevolver(int tiempo) {
        System.out.println("precio por minuto " + this.precioMinuto);
        return (double) (tiempo * this.precioMinuto);
    }
    
    /**
     * método para verificar que no se pueda introducir un coche con una matrícula que ya se encuentre
     * en el aparcamiento
     * 
     * @param matricula matricula a comprobar
     */
    public void comprobarMatricula(String matricula) {
        for (Ticket ticket : listaTickets) {
            if (ticket.getMatricula().equals(matricula) && ticket.isActivo()) {
                JOptionPane.showMessageDialog(terminal, "el coche ya está en el parking", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else {

            }
        }

    }

}
