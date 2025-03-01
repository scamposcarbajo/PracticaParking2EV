package practica;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Deposito2 {

    // declaramos los atributos de la clase
    private int billetes20 = 100;
    private int billetes10 = 100;
    private int billetes5 = 100;
    private int monedas2 = 100;
    private int monedas1 = 100;
    private int centimos50 = 100;
    private int centimos20 = 100;
    private int centimos10 = 100;
    private int centimos5 = 100;

    // constructor depósito general
    public Deposito2() {
        // los valores ya están inicializados a 100 por defecto;
    }

    // constructor con parámetros para crear un objeto con valores específicos (vueltas)
    public Deposito2(int monedas2, int monedas1,
            int centimos50, int centimos20, int centimos10, int centimos5) {
        this.monedas2 = monedas2;
        this.monedas1 = monedas1;
        this.centimos50 = centimos50;
        this.centimos20 = centimos20;
        this.centimos10 = centimos10;
        this.centimos5 = centimos5;
    }

    // getters y setters de los atributos
    public int getBilletes20() {return billetes20;}
    public void setBilletes20(int billetes20) {this.billetes20 = billetes20;}

    public int getBilletes10() {return billetes10;}
    public void setBilletes10(int billetes10) {this.billetes10 = billetes10;}

    public int getBilletes5() {return billetes5;}
    public void setBilletes5(int billetes5) {this.billetes5 = billetes5;}

    public int getMonedas2() {return monedas2;}
    public void setMonedas2(int monedas2) {this.monedas2 = monedas2;}

    public int getMonedas1() {return monedas1;}
    public void setMonedas1(int monedas1) {this.monedas1 = monedas1;}

    public int getCentimos50() {return centimos50;}
    public void setCentimos50(int centimos50) {this.centimos50 = centimos50;}

    public int getCentimos20() {return centimos20;}
    public void setCentimos20(int centimos20) {this.centimos20 = centimos20;}

    public int getCentimos10() {return centimos10;}
    public void setCentimos10(int centimos10) {this.centimos10 = centimos10;}

    public int getCentimos5() {return centimos5;}
    public void setCentimos5(int centimos5) {this.centimos5 = centimos5;}

    // toString 
    @Override
    public String toString() {
        return "Deposito:\nBilletes de 20€: " + billetes20 + "\nBilletes de 10€: " + billetes10 + "\nBilletes de 5€:" + billetes5
                + "\nMonedas de 2€: " + monedas2 + "\nMonedas de 1€: " + monedas1
                + "\nMonedas de 0,50€: " + centimos50 + "\nMonedas de 0,20€: " + centimos20
                + "\nMonedas de 0,10€: " + centimos10 + "\nMonedas de 0,05€: " + centimos5;

    } // FIN DEL TOSTRING
    
    public String toStringMonedas() {
        String resultado = "Monedas:\n";
    
        if (this.monedas2 > 0) resultado += "Monedas de 2€: " + this.monedas2 + "\n";
        if (this.monedas1 > 0) resultado += "Monedas de 1€: " + this.monedas1 + "\n";
        if (this.centimos50 > 0) resultado += "Monedas de 0.50€: " + this.centimos50 + "\n";
        if (this.centimos20 > 0) resultado += "Monedas de 0.20€: " + this.centimos20 + "\n";
        if (this.centimos10 > 0) resultado += "Monedas de 0.10€: " + this.centimos10 + "\n";
        if (this.centimos5 > 0) resultado += "Monedas de 0.05€: " + this.centimos5 + "\n";
    
        return resultado;
        
    } // FIN TOSTRING PARA VUELTAS
    
    // método para validar que no se introduzcan monedas de 0.01 o 0.02
    public void validarCentimos(String dineroIntroducido, JFrame terminal) {
        double centimos = (Double.parseDouble(dineroIntroducido) * 100);

        // verificamos si el valor de los céntimos es divisible entre 5
        // si se introducen 1 o 2 céntimos, el resto no dará 0
        if (centimos % 5 != 0) {
            // si no es múltiplo de 0.05, significa que tiene centimos de 0.01 o 0.02
            JOptionPane.showMessageDialog(terminal, "No puedes introducir monedas de 0,01 o 0,02 céntimos", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // si el dinero si es válido
            System.out.println("Dinero valido: " + dineroIntroducido);
        }
        
    } // FIN MÉTODO VALIDARCENTIMOS
    
    // método para introducir el dinero (usamos billetes y monedas)
    public void introducirDinero(String dineroIntroducido0) {
        // hacemos un parse del dinero que se ha introducido por el JtextField
        double dinero = Double.parseDouble(dineroIntroducido0);
        
        // definimos un array de doubles (double[]) para recoger ahí los valores de los billetes
        // y monedas en orden de menor a mayor
        // + array de prueba con Strings para ver que se va introduciendo
        double[] valores = {20.0, 10.0, 5.0, 2.0, 1.0, 0.5, 0.2, 0.1, 0.05};
        String[] nombresValores = {"billete de 20", "billete de 10", "billete de 5", "moneda de 2",
            "moneda de 1", "moneda de 0.50", "moneda de 0.20", "moneda de 0.10",
            "moneda de 0.05"};

        // recorremos valores, donde están los valores de los billetes y monedas, de menor a mayor
        // así introducimos siempre el valor de billete o moneda más alto primero
        for (int i = 0; i < valores.length; i++) {
            // bucle while para hacer los casos siempre y cuando el dinero introducido sea mayor o
            // igual a la posición en i de los valores
            // así, si dinero es mayor que el valor en posicion i (ej. 20) se 
            // resta esa cantidad del dinero y se incrementa el contador correspondiente del tipo de 
            // billete o moneda (si se mete mas de 20, entonces el valor de billetes 20 pasará de ser
            // 100 a 101)
            while (dinero >= valores[i]) {
                // actualizamos los valores del deposito con los billetes o monedas introducidos
                switch (i) {
                    // el número de los casos equivale a la posicion de la i en el array
                    // el caso 0 será en el valor 20.0, el 1 eb 10.0, y así sucesivamente
                    case 0: // billetes de 20
                        // no necesitamos usar getters o setters porque estamos en la propia clase
                        // es una forma más directa y nos ahorramos código
                        this.billetes20++;
                        // los break nos sacan del bucle switch para volver al bucle for,
                        // y así poder incrementar i en 1 hasta recorrer todo el array
                        break;
                    case 1: // billetes de 10
                        this.billetes10++;
                        break;
                    case 2: // billetes de 5
                        this.billetes5++;
                        break;
                    case 3: // monedas de 2 
                        this.monedas2++;
                        break;
                    case 4: // monedas de 1
                        this.monedas1++;
                        break;
                    case 5: // monedas de 0.5
                        this.centimos50++;
                        break;
                    case 6: // monedas de 0.2
                        this.centimos20++;
                        break;
                    case 7: // monedas de 0.1
                        this.centimos10++;
                        break;
                    case 8: // monedas de 0.05
                        this.centimos5++;
                        break;
                }
                // reducimos el dinero restante con el dinero en la posición i que hemos obtenido
                dinero -= valores[i];

            }
        } 
    } // FIN DEL MÉTODO INTRODUCIRDINERO
    
    // método para devolver el cambio en monedas

    public Deposito2 devolverCambio(String dineroIntroducido) {
        // como en el método anterior, realizamos un parse
        double dinero = Double.parseDouble(dineroIntroducido);
        // creamos un depósito vacío para solo almacenar las vuetlas
        Deposito2 vueltas = new Deposito2(0, 0, 0, 0, 0, 0);
        
        //como antes, creamos un array
        double[] valores  = {2.0,1.0,0.5,0.2,0.1,0.05};
        
        // recorremos los valores de monedas
        for (int i = 0; i < valores.length; i++) {
            while (dinero >= valores[i]) {
                switch(i) {
                    case 0: // modenas de 2
                        // eliminamos una moneda de 2 del depósito (ej: 100-1 = 99 monedas de 2)
                        this.monedas2--;
                        // añadimos una nueva moneda al deposito de vueltas
                        vueltas.monedas2++;
                        break;
                    case 1: // monedas de 1
                        this.monedas1--;
                        vueltas.monedas1++;
                        break;
                    case 2: // monedas de 0.5
                        this.centimos50--;
                        vueltas.centimos50++;
                        break;
                    case 3: // monedas de 0.2
                        this.centimos20--;
                        vueltas.centimos20++;
                        break;
                    case 4: // monedas de 0.1
                        this.centimos10--;
                        vueltas.centimos10++;
                        break;
                    case 5: // monedas de 0.05
                        this.centimos5--;
                        vueltas.centimos5++;
                        break;
                }
                // eliminamos del dinero el valor de la i dentro del bucle for
                dinero -= valores[i];
            }
        }
        
        // si no podemos devolver el cambio por falta de monedas
        if (dinero > 0) {
            System.out.println("No se puede devolver el cambio");
        } else {
            System.out.println("Dinero devuelto: " + vueltas.toString());
        }
        
        return vueltas;
    } // FIN DEL MÉTODO DEVOLVERDINERO

}
