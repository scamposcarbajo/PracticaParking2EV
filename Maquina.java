package practica;

import java.time.Duration;
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
    private List<Ticket> listaTickets = new ArrayList<>();
    ;
    private int[][] plano;

    public Maquina(double precioMinuto) {
        this.precioMinuto = precioMinuto;
        // tenemos que inicializar el deposito
        // Maquina en su constructor solo recibe precio/minuto
        this.deposito = new Deposito();
        // no olvidar los diamantes para el genérico
        this.listaTickets = listaTickets;
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

        // calculamos duracion entre las dos fechas en minutos
        Duration duracion = Duration.between(fechaEntrada, fechaSalida);
        long minutosTranscurridos = duracion.toMinutes(); // minutos completos
        long segundosTranscurridos = duracion.getSeconds() % 60; // segundos restantes

        if (segundosTranscurridos > 0) {
            minutosTranscurridos++;
        }

        return (int) minutosTranscurridos;
    }

    public double calcularDineroPagar(int minutosTranscurridos) {

        double aPagar = minutosTranscurridos * precioMinuto;
        return aPagar;

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

    public double devolverCambio(double dinero) {

        double cambioRestante = dinero;  // total de cambio que se debe devolver
        Deposito vuelta = new Deposito();  // nuevo depósito para el cambio

        // iteramos dependiendo del cambio a devolver
        while (cambioRestante > 0) {

            if (cambioRestante >= 2.00 && deposito.getMonedas2() > 0) {
                deposito.setMonedas2(deposito.getMonedas2() - 1);
                vuelta.setMonedas2(vuelta.getMonedas2() + 1);
                cambioRestante -= 2.00;

            } else if (cambioRestante >= 1.00 && deposito.getMonedas1() > 0) {
                deposito.setMonedas1(deposito.getMonedas1() - 1);
                vuelta.setMonedas1(vuelta.getMonedas1() + 1);
                cambioRestante -= 1.00;

            } else if (cambioRestante >= 0.50 && deposito.getMonedas50() > 0) {
                deposito.setMonedas50(deposito.getMonedas50() - 1);
                vuelta.setMonedas50(vuelta.getMonedas50() + 1);
                cambioRestante -= 0.50;

            } else if (cambioRestante >= 0.20 && deposito.getMonedas20() > 0) {
                deposito.setMonedas20(deposito.getMonedas20() - 1);
                vuelta.setMonedas20(vuelta.getMonedas20() + 1);
                cambioRestante -= 0.20;

            } else if (cambioRestante >= 0.10 && deposito.getMonedas10() > 0) {
                deposito.setMonedas10(deposito.getMonedas10() - 1);
                vuelta.setMonedas10(vuelta.getMonedas10() + 1);
                cambioRestante -= 0.10;

            } else if (cambioRestante >= 0.05 && deposito.getMonedas5() > 0) {
                deposito.setMonedas5(deposito.getMonedas5() - 1);
                vuelta.setMonedas5(vuelta.getMonedas5() + 1);
                cambioRestante -= 0.05;

            } else {
                System.out.println("no hay cambio suficiente");
                return dinero; // devolvemos el dinero sin cambios
            }
        }

        // comprobar el deposito de vuelta
        System.out.println("contenido del depósito vuelta: " + vuelta.toString());

        System.out.println("cambio devuelto correctamente");
        return dinero - cambioRestante;  // devolvemos el total de cambio entregado
    }

}
