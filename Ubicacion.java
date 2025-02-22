/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica;
/**
 *
 * @author S
 */
public class Ubicacion {
    
    private int planta;
    private int plaza;

    public Ubicacion(int planta, int plaza) {
        this.planta = planta;
        this.plaza = plaza;
    }

    @Override
    public String toString() {
        return "Ubicacion{" + "planta=" + planta + ", plaza=" + plaza + '}';
    }
    
}
