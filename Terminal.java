/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author S
 */
public class Terminal extends JFrame implements TerminalInterface {
  
    private final Maquina maquina;
    
    public Terminal(Maquina maquina) {
        this.maquina = maquina;
        this.initComponents();
    }

    private void initComponents() {
    }
    
    @Override
    public void meterCoche() {
        Ubicacion ubicacionPlaza = this.maquina.encontrarEspacioLibre();
        if (ubicacionPlaza == null) {
            JOptionPane.showMessageDialog(this, "no hay plazas disponibles", "info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Ticket ticket = this.maquina.reservarPlaza(ubicacionPlaza, "123");
        System.out.println(ticket);
    }

    @Override
    public void sacarCoche(Ticket ticket) {
        this.maquina.liberarPlaza(ticket);
    }
    
    
    
    
}
