/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClasesPrincipales;

/**
 *
 * @author S
 */
public class Deposito {

    protected int billete20;
    protected int billete10;
    protected int billete5;
    protected int moneda2;
    protected int moneda1;
    protected int moneda05;
    protected int moneda02;
    protected int moneda01;
    protected int moneda005;

    public Deposito(int billete20, int billete10, int billete5, int moneda2, int moneda1, int moneda05, int moneda02, int moneda01, int moneda005) {
        this.billete20 = billete20;
        this.billete10 = billete10;
        this.billete5 = billete5;
        this.moneda2 = moneda2;
        this.moneda1 = moneda1;
        this.moneda05 = moneda05;
        this.moneda02 = moneda02;
        this.moneda01 = moneda01;
        this.moneda005 = moneda005;
    }

    public int getBillete20() {
        return billete20;
    }

    public int getBillete10() {
        return billete10;
    }

    public int getBillete5() {
        return billete5;
    }

    public int getMoneda2() {
        return moneda2;
    }

    public int getMoneda1() {
        return moneda1;
    }

    public int getMoneda05() {
        return moneda05;
    }

    public int getMoneda02() {
        return moneda02;
    }

    public int getMoneda01() {
        return moneda01;
    }

    public int getMoneda005() {
        return moneda005;
    }

    public void setBillete20(int billete20) {
        this.billete20 = billete20;
    }

    public void setBillete10(int billete10) {
        this.billete10 = billete10;
    }

    public void setBillete5(int billete5) {
        this.billete5 = billete5;
    }

    public void setMoneda2(int moneda2) {
        this.moneda2 = moneda2;
    }

    public void setMoneda1(int moneda1) {
        this.moneda1 = moneda1;
    }

    public void setMoneda05(int moneda05) {
        this.moneda05 = moneda05;
    }

    public void setMoneda02(int moneda02) {
        this.moneda02 = moneda02;
    }

    public void setMoneda01(int moneda01) {
        this.moneda01 = moneda01;
    }

    public void setMoneda005(int moneda005) {
        this.moneda005 = moneda005;
    }

    @Override
    public String toString() {
        return "Deposito{" + "billete20=" + billete20 + ", billete10=" + billete10 + ", billete5=" + billete5 + ", moneda2=" + moneda2 + ", moneda1=" + moneda1 + ", moneda05=" + moneda05 + ", moneda02=" + moneda02 + ", moneda01=" + moneda01 + ", moneda005=" + moneda005 + '}';
    }

}
