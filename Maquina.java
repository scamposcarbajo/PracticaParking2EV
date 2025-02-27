/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClasesPrincipales;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Maquina {

    private static final int NUM_MAX_PLANTAS = 3;
    private static final int NUM_MAX_PLAZAS_POR_PLANTA = 20;
    private static int asignadorClave = 0;

    private static double precioMinuto;
    private Deposito deposito = new Deposito(100, 100, 100, 100, 100, 100, 100, 100, 100);
    // el genérico es Ticket porque vamos a usar Objetos Ticket dentro
    // de la lista
    private ArrayList<Ticket> listaTickets = new ArrayList<>();

    private int[][] plano = new int[NUM_MAX_PLANTAS][NUM_MAX_PLAZAS_POR_PLANTA];

    //constructor principal con precio por minuto creado desde PRINCIPAL
    public Maquina(double precioMinuto) {
        this.precioMinuto = precioMinuto;
        // tenemos que inicializar el deposito
        // Maquina en su constructor solo recibe precio/minuto

        // no olvidar los diamantes para el genérico
        System.out.println("constructor normal");
        rellenarPlano();
        mostrarPlano();
        Terminal terminal = new Terminal();
        terminal.setVisible(true);
    }

    //constructor vacio para acceder desde TERMINAL 
    public Maquina() {
        System.out.println("Constructor por defecto");
    }

    public ArrayList<Ticket> getListaTickets() {
        return listaTickets;
    }

    public int[][] getPlano() {
        return plano;
    }

    //terminado
    public void rellenarPlano() {
        for (int i = 0; i < plano.length; i++) {
            for (int j = 0; j < plano[i].length; j++) {
                plano[i][j] = 0;
            }
        }
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

    public void introducirDinero(String dineroIntroducido) {
        double dinero = Double.parseDouble(dineroIntroducido);
        Deposito vuelta = new Deposito(0, 0, 0, 0, 0, 0, 0, 0, 0);
//        if (dinero != 0) {
//            do {
//                deposito.setBilletes20(deposito.getBilletes20() + 1);
//                dinero = dinero - 20.0;
//                vuelta.setBilletes20(vuelta.getBilletes20()+1);
//                System.out.println("introducido: billete de 20");
//                System.out.println("Dinero restante: " + dinero);
//            } while (dinero >= 20 && dinero != 0);
//        }
//        if (dinero >= 10 && dinero != 0) {
//            do {
//                deposito.setBilletes10(deposito.getBilletes10() + 1);
//                dinero = dinero - 10.0;
//                vuelta.setBilletes10(vuelta.getBilletes10()+1);
//                System.out.println("introducido: billete de 10");
//
//            } while (dinero >= 10 && dinero != 0);
//        }
//        if (dinero >= 5 && dinero != 0) {
//            do {
//                deposito.setBilletes5(deposito.getBilletes5() + 1);
//                dinero = dinero - 5.0;
//                vuelta.setBilletes5(vuelta.getBilletes5()+1);
//                System.out.println("introducido: billete de 5");
//            } while (dinero >= 5 && dinero != 0);
//        }
//        if (dinero >= 2 && dinero != 0) {
//            do {
//                deposito.setMonedas2(deposito.getMonedas2() + 1);
//                dinero = dinero - 2.0;
//                vuelta.setMonedas2(vuelta.getMonedas2()+1);
//                System.out.println("introducido: moneda de 2");
//            } while (dinero >= 2 && dinero != 0);
//        }
//        if (dinero >= 1 && dinero != 0) {
//            do {
//                deposito.setMonedas1(deposito.getMonedas1() + 1);
//                dinero = dinero - 1.0;
//                vuelta.setMonedas1(vuelta.getMonedas1()+1);
//                System.out.println("introducido: moneda de 1");
//            } while (dinero >= 1 && dinero != 0);
//        }
//        if (dinero >= 0.5 && dinero != 0) {
//            do {
//                deposito.setMonedas50(deposito.getMonedas50() + 1);
//                dinero = dinero - 0.5;
//                vuelta.setMonedas50(vuelta.getMonedas50()+1);
//                System.out.println("introducido: moneda de 0.50");
//            } while (dinero >= 0.5 && dinero != 0);
//        }
//        if (dinero >= 0.2 && dinero != 0) {
//            do {
//                deposito.setMonedas20(deposito.getMonedas20() + 1);
//                dinero = dinero - 0.2;
//                vuelta.setMonedas20(vuelta.getMonedas20()+1);
//                System.out.println("introducido: moneda de 0.20");
//            } while (dinero >= 0.2 && dinero != 0);
//        }
//        if (dinero >= 0.1 && dinero != 0) {
//            do {
//                deposito.setMonedas10(deposito.getMonedas10() + 1);
//                dinero = dinero - 0.1;
//                vuelta.setMonedas10(vuelta.getMonedas10()+1);
//                System.out.println("introducido: moneda de 0.10");
//            } while (dinero >= 0.1 && dinero != 0);
//        }
//        if (dinero >= 0.05 && dinero != 0) {
//            do {
//                deposito.setMonedas5(deposito.getMonedas5() + 1);
//                dinero = dinero - 0.05;
//                vuelta.setMonedas5(vuelta.getMonedas5()+1);
//                System.out.println("introducido: moneda de 0.05");
//            } while (dinero >= 0.05 && dinero != 0);
//        }
//        System.out.println("Vuelta: " + vuelta.toString());

        while (dinero > 0) {
            if (dinero >= 20) {
                deposito.setBilletes20(deposito.getBilletes20() + 1);
                dinero -= 20.0;
                System.out.println("introducido: billete de 20");
            } else if (dinero >= 10) {
                deposito.setBilletes10(deposito.getBilletes10() + 1);
                dinero -= 10.0;
                System.out.println("introducido: billete de 10");
            } else if (dinero >= 5) {
                deposito.setBilletes5(deposito.getBilletes5() + 1);
                dinero -= 5.0;
                System.out.println("introducido: billete de 5");
            } else if (dinero >= 2) {
                deposito.setMonedas2(deposito.getMonedas2() + 1);
                dinero -= 2.0;
                System.out.println("introducido: moneda de 2");
            } else if (dinero >= 1) {
                deposito.setMonedas1(deposito.getMonedas1() + 1);
                dinero -= 1.0;
                System.out.println("introducido: moneda de 1");
            } else if (dinero >= 0.5) {
                deposito.setMonedas50(deposito.getMonedas50() + 1);
                dinero -= 0.5;
                System.out.println("introducido: moneda de 0.50");
            } else if (dinero >= 0.2) {
                deposito.setMonedas20(deposito.getMonedas20() + 1);
                dinero -= 0.2;
                System.out.println("introducido: moneda de 0.20");
            } else if (dinero >= 0.1) {
                deposito.setMonedas10(deposito.getMonedas10() + 1);
                dinero -= 0.1;
                System.out.println("introducido: moneda de 0.10");
            } else if (dinero >= 0.05) {
                deposito.setMonedas5(deposito.getMonedas5() + 1);
                dinero -= 0.05;
                System.out.println("introducido: moneda de 0.05");
            }
        }
    }

//    public double devolverCambio(String cambioString) {
//
//        double cambio = Double.parseDouble(cambioString);
//        double devolver = 0.0;
//        deposito = new Deposito();
//
//        if (cambio > deposito.getBilletes20()) {
//            deposito.setBilletes20(deposito.getBilletes20() - 1);
//            devolver += 20.0;
//
//        } else if (cambio > deposito.getBilletes10()) {
//            deposito.setBilletes10(deposito.getBilletes10() - 1);
//            devolver += 10.0;
//
//        } else if (cambio > deposito.getBilletes5()) {
//            deposito.setBilletes5(deposito.getBilletes5() - 1);
//            devolver += 5.0;
//
//        }
//
//        return devolver;
//    }
//    public void devolver(int dinero) {
//        
//        switch (dinero) {
//            case 50:
//                if (deposito.getMonedas2() == 0) {
//                    System.out.println("no hay monedas");
//                    break;
//                }
//                if (dinero > 2 && dinero % 2 == 0) {
//                    vuelta.setMonedas2(vuelta.getMonedas2() - 1);
//                }
//                if (dinero > 1 && dinero % 1 == 0) {
//                    vuelta.setMonedas2(vuelta.getMonedas2() - 1);
//                }
//
//                break;
//            default:
//                throw new AssertionError();
//        }
//    }
}
