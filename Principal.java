/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ClasesPrincipales;

/**
 *
 * @author S
 */
public class Principal {

    private static final double PRECIO_MINUTO = 0.5;

    /**
     * @param args the command line arguments
     */
    //la clase principal solo tiene que crear la instancia de maquina con precio por minuto
    public static void main(String[] args) {
        Maquina maquina = new Maquina(PRECIO_MINUTO);
    }

}
