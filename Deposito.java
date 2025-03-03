package ClasesPrincipales;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Clase que representa un depósito de monedas y billetes en un sistema de pago.
 * Esta clase gestiona el dinero almacenado en el depósito, incluyendo billetes y monedas
 * de diferentes denominaciones. Permite introducir dinero, devolver cambio y validar monedas.
 */
public class Deposito {

    // Atributos que representan las diferentes denominaciones de dinero almacenado en el depósito
    private int billetes20 = 100;  // Billetes de 20 euros
    private int billetes10 = 100;  // Billetes de 10 euros
    private int billetes5 = 100;   // Billetes de 5 euros
    private int monedas2 = 100;    // Monedas de 2 euros
    private int monedas1 = 100;    // Monedas de 1 euro
    private int centimos50 = 100;  // Monedas de 0.50 euros
    private int centimos20 = 100;  // Monedas de 0.20 euros
    private int centimos10 = 100;  // Monedas de 0.10 euros
    private int centimos5 = 100;   // Monedas de 0.05 euros
    protected double totalDeposito = ((monedas2*2) + (monedas1) + (centimos50*0.5) + (centimos20*0.2) + (centimos10*0.1) + (centimos5*0.05));

    /**
     * Constructor por defecto que inicializa todos los billetes y monedas con valores predeterminados.
     */
    public Deposito() {
        // Los valores ya están inicializados a 100 por defecto;
    }

    /**
     * Constructor que permite crear un objeto Deposito con valores específicos de monedas.
     *
     * @param monedas2 Cantidad de monedas de 2 euros
     * @param monedas1 Cantidad de monedas de 1 euro
     * @param centimos50 Cantidad de monedas de 0.50 euros
     * @param centimos20 Cantidad de monedas de 0.20 euros
     * @param centimos10 Cantidad de monedas de 0.10 euros
     * @param centimos5 Cantidad de monedas de 0.05 euros
     */
    public Deposito(int monedas2, int monedas1, int centimos50, int centimos20, int centimos10, int centimos5) {
        this.monedas2 = monedas2;
        this.monedas1 = monedas1;
        this.centimos50 = centimos50;
        this.centimos20 = centimos20;
        this.centimos10 = centimos10;
        this.centimos5 = centimos5;
    }

    // Métodos getters y setters para acceder y modificar las cantidades de billetes y monedas.

    public int getBilletes20() {
        return billetes20;
    }

    public void setBilletes20(int billetes20) {
        this.billetes20 = billetes20;
    }

    public int getBilletes10() {
        return billetes10;
    }

    public void setBilletes10(int billetes10) {
        this.billetes10 = billetes10;
    }

    public int getBilletes5() {
        return billetes5;
    }

    public void setBilletes5(int billetes5) {
        this.billetes5 = billetes5;
    }

    public int getMonedas2() {
        return monedas2;
    }

    public void setMonedas2(int monedas2) {
        this.monedas2 = monedas2;
    }

    public int getMonedas1() {
        return monedas1;
    }

    public void setMonedas1(int monedas1) {
        this.monedas1 = monedas1;
    }

    public int getCentimos50() {
        return centimos50;
    }

    public void setCentimos50(int centimos50) {
        this.centimos50 = centimos50;
    }

    public int getCentimos20() {
        return centimos20;
    }

    public void setCentimos20(int centimos20) {
        this.centimos20 = centimos20;
    }

    public int getCentimos10() {
        return centimos10;
    }

    public void setCentimos10(int centimos10) {
        this.centimos10 = centimos10;
    }

    public int getCentimos5() {
        return centimos5;
    }

    public void setCentimos5(int centimos5) {
        this.centimos5 = centimos5;
    }

    /**
     * Método toString que devuelve una representación del estado actual del depósito,
     * mostrando la cantidad de billetes y monedas almacenadas.
     *
     * @return Un String con la representación detallada del depósito.
     */
    @Override
    public String toString() {
        return "Deposito:\nBilletes de 20€: " + billetes20 + "\nBilletes de 10€: " + billetes10 + "\nBilletes de 5€:" + billetes5
                + "\nMonedas de 2€: " + monedas2 + "\nMonedas de 1€: " + monedas1
                + "\nMonedas de 0,50€: " + centimos50 + "\nMonedas de 0,20€: " + centimos20
                + "\nMonedas de 0,10€: " + centimos10 + "\nMonedas de 0,05€: " + centimos5;
    }

    /**
     * Método que devuelve una representación del cambio que se va a devolver,
     * indicando la cantidad de monedas y su valor.
     *
     * @param vueltas Objeto de tipo Deposito que contiene las monedas a devolver como cambio.
     * @return Un String con el cambio a devolver detallado.
     */
    public String toStringMonedas(Deposito vueltas) {
        String resultado = "\n";
        double totalDevuelto = 0.0;

        // Array de valores de monedas
        double[] valores = {2.0, 1.0, 0.5, 0.2, 0.1, 0.05};
        // Array con las cantidades de las monedas
        int[] cantidades = {vueltas.monedas2, vueltas.monedas1, vueltas.centimos50, vueltas.centimos20, vueltas.centimos10, vueltas.centimos5};
        // Nombres de las monedas
        String[] nombres = {"Monedas de 2€", "Monedas de 1€", "Monedas de 0.50€", "Monedas de 0.20€", "Monedas de 0.10€", "Monedas de 0.05€"};

        // Recorremos las monedas para calcular el cambio
        for (int i = 0; i < valores.length; i++) {
            if (cantidades[i] > 0) {
                resultado += nombres[i] + ": " + cantidades[i] + "\n"; // Concatenamos el nombre y la cantidad
                totalDevuelto += cantidades[i] * valores[i]; // Sumamos el total devuelto
            }
        }

        // Agregamos el total devuelto al resultado
        resultado += "\nCambio devuelto: " + totalDevuelto + "€";
        return resultado;
    }

    /**
     * Método para validar que el dinero introducido es válido,
     * es decir, que no contiene céntimos de 0.01€ o 0.02€.
     *
     * @param dineroIntroducido Dinero que se ha introducido.
     * @param terminal Instancia del JFrame desde donde se llama este método (para mostrar mensajes).
     */
    public void validarCentimos(String dineroIntroducido, JFrame terminal) {
        double centimos = (Double.parseDouble(dineroIntroducido) * 100);

        // Comprobamos si el valor de los céntimos es divisible entre 5 (para evitar 0.01 y 0.02 céntimos)
        if (centimos % 5 != 0) {
            JOptionPane.showMessageDialog(terminal, "No puedes introducir monedas de 0,01 o 0,02 céntimos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Método para introducir dinero en el depósito, actualizando las cantidades
     * de billetes y monedas en función de lo introducido.
     *
     * @param dineroIntroducido0 Dinero introducido como String.
     */
    public void introducirDinero(String dineroIntroducido0) {
        double dinero = Double.parseDouble(dineroIntroducido0);

        // Array de valores de billetes y monedas de mayor a menor
        double[] valores = {20.0, 10.0, 5.0, 2.0, 1.0, 0.5, 0.2, 0.1, 0.05};

        // Recorremos el array de valores
        for (int i = 0; i < valores.length; i++) {
            while (dinero >= valores[i]) {
                // Actualizamos la cantidad de monedas y billetes en el depósito según el valor introducido
                switch (i) {
                    case 0: this.billetes20++; break;
                    case 1: this.billetes10++; break;
                    case 2: this.billetes5++; break;
                    case 3: this.monedas2++; break;
                    case 4: this.monedas1++; break;
                    case 5: this.centimos50++; break;
                    case 6: this.centimos20++; break;
                    case 7: this.centimos10++; break;
                    case 8: this.centimos5++; break;
                }
                dinero -= valores[i]; // Reducimos el dinero restante
            }
        }
    }

    /**
     * Método que devuelve el cambio correspondiente a una cantidad de dinero introducida.
     * Este método calcula el cambio y lo devuelve en un objeto `Deposito` con las monedas correspondientes.
     *
     * @param dineroIntroducido Dinero que se ha introducido para calcular el cambio.
     * @return Un objeto Deposito con las monedas que deben devolverse como cambio.
     */
    public Deposito devolverCambio(String dineroIntroducido) {
        double dinero = Double.parseDouble(dineroIntroducido);
        double cambio = dinero - 0.5;  // Suponemos que el importe a pagar es 0.50€

        Deposito vueltas = new Deposito(0, 0, 0, 0, 0, 0); // Crear un nuevo depósito para almacenar el cambio

        // Array con valores de monedas
        double[] valores = {2.0, 1.0, 0.5, 0.2, 0.1, 0.05};

        // Recorremos cada tipo de moneda
        for (int i = 0; i < valores.length; i++) {
            while (cambio >= valores[i]) {
                // Verificamos si hay monedas suficientes y las restamos del depósito
                switch (i) {
                    case 0: 
                        if (this.monedas2 > 0) {
                            this.monedas2--;
                            vueltas.monedas2++;
                            cambio -= valores[i];
                        } else {
                            return vueltas;
                        }
                        break;
                    case 1:
                        if (this.monedas1 > 0) {
                            this.monedas1--;
                            vueltas.monedas1++;
                            cambio -= valores[i];
                        } else {
                            return vueltas;
                        }
                        break;
                    case 2:
                        if (this.centimos50 > 0) {
                            this.centimos50--;
                            vueltas.centimos50++;
                            cambio -= valores[i];
                        } else {
                            return vueltas;
                        }
                        break;
                    case 3:
                        if (this.centimos20 > 0) {
                            this.centimos20--;
                            vueltas.centimos20++;
                            cambio -= valores[i];
                        } else {
                            return vueltas;
                        }
                        break;
                    case 4:
                        if (this.centimos10 > 0) {
                            this.centimos10--;
                            vueltas.centimos10++;
                            cambio -= valores[i];
                        } else {
                            return vueltas;
                        }
                        break;
                    case 5:
                        if (this.centimos5 > 0) {
                            this.centimos5--;
                            vueltas.centimos5++;
                            cambio -= valores[i];
                        } else {
                            return vueltas;
                        }
                        break;
                }
            }
        }

        // Si no se puede devolver todo el cambio, notificamos
        if (cambio > 0) {
            System.out.println("No se puede devolver el cambio completo. Falta: " + cambio + "€");
        }

        return vueltas;
    }
}
