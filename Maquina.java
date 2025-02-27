/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
        this.deposito = new Deposito();
        // no olvidar los diamantes para el genérico
        this.listaTickets = new ArrayList<>();
        this.plano = new int[NUM_MAX_PLANTAS][NUM_MAX_PLAZAS_POR_PLANTA];

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
        int id = asignadorClave++;
        this.plano[ubicacionPlaza.getPlanta()][ubicacionPlaza.getPlaza()] = id;

        Ticket ticket = new Ticket(id, matricula, LocalDateTime.now(), ubicacionPlaza);
        this.listaTickets.add(ticket);

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

    public int calcularTiempoTranscurrido(Ticket ticket) {

        LocalDateTime fechaEntrada = ticket.getFechaHora();
        LocalDateTime fechaSalida = LocalDateTime.now();

        LocalDateTime tiempoTranscurrido = fechaSalida.minusMinutes((long) fechaEntrada.getMinute());
        return tiempoTranscurrido.getMinute();

    }

    public void introducirDinero(String dineroIntroducido) {

        double dinero = Double.parseDouble(dineroIntroducido);

        if (dinero > deposito.getBilletes20()) {
            deposito.setBilletes20(deposito.getBilletes20() + 1);
            dinero -= 20.0;
            System.out.println("introducido: billete de 20");
        }

        if (dinero > deposito.getBilletes10()) {
            deposito.setBilletes10(deposito.getBilletes10() + 1);
            dinero -= 10.0;
            System.out.println("introducido: billete de 10");
        }

        if (dinero > deposito.getBilletes5()) {
            deposito.setBilletes5(deposito.getBilletes5() + 1);
            dinero -= 5.0;
            System.out.println("introducido: billete de 5");
        }

        if (dinero > deposito.getMonedas2()) {
            deposito.setMonedas2(deposito.getMonedas2() + 1);
            dinero -= 2.0;
            System.out.println("introducido: moneda de 2");
        }

        if (dinero > deposito.getMonedas1()) {
            deposito.setMonedas1(deposito.getMonedas1() + 1);
            dinero -= 1.0;
            System.out.println("introducido: moneda de 1");
        }

        if (dinero > deposito.getMonedas50()) {
            deposito.setMonedas50(deposito.getMonedas50() + 1);
            dinero -= 0.5;
            System.out.println("introducido: moneda de 50");
        }

        if (dinero > deposito.getMonedas20()) {
            deposito.setMonedas20(deposito.getMonedas20() + 1);
            dinero -= 0.2;
            System.out.println("introducido: moneda de 20");
        }

        if (dinero > deposito.getMonedas10()) {
            deposito.setMonedas10(deposito.getMonedas10() + 1);
            dinero -= 0.1;
            System.out.println("introducido: moneda de 10");
        }

        if (dinero > deposito.getMonedas5()) {
            deposito.setMonedas5(deposito.getMonedas5() + 1);
            dinero -= 0.05;
            System.out.println("introducido: moneda de 5");
        }

        if (dinero == 0) {
            return;
        }

    }

    public double devolverCambio(String cambioString) {

        double cambio = Double.parseDouble(cambioString);
        double devolver = 0.0;
        deposito = new Deposito();

        if (cambio > deposito.getBilletes20()) {
            deposito.setBilletes20(deposito.getBilletes20() - 1);
            devolver += 20.0;

        } else if (cambio > deposito.getBilletes10()) {
            deposito.setBilletes10(deposito.getBilletes10() - 1);
            devolver += 10.0;

        } else if (cambio > deposito.getBilletes5()) {
            deposito.setBilletes5(deposito.getBilletes5() - 1);
            devolver += 5.0;

        }

        return devolver;
    }

}
