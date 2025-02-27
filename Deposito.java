/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClasesPrincipales;

public class Deposito {

    private int billetes20;
    private int billetes10;
    private int billetes5;
    private int monedas1;
    private int monedas2;
    private int monedas50;
    private int monedas20;
    private int monedas10;
    private int monedas5;

    public Deposito(int billetes20, int billetes10, int billetes5, int monedas1, int monedas2, int monedas50, int monedas20, int monedas10, int monedas5) {
        this.billetes20 = billetes20;
        this.billetes10 = billetes10;
        this.billetes5 = billetes5;
        this.monedas1 = monedas1;
        this.monedas2 = monedas2;
        this.monedas50 = monedas50;
        this.monedas20 = monedas20;
        this.monedas10 = monedas10;
        this.monedas5 = monedas5;
    }

    public Deposito(int monedas1, int monedas2, int monedas50, int monedas20, int monedas10, int monedas5) {
        this.monedas1 = monedas1;
        this.monedas2 = monedas2;
        this.monedas50 = monedas50;
        this.monedas20 = monedas20;
        this.monedas10 = monedas10;
        this.monedas5 = monedas5;
    }
    

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

    public int getMonedas1() {
        return monedas1;
    }

    public void setMonedas1(int monedas1) {
        this.monedas1 = monedas1;
    }

    public int getMonedas2() {
        return monedas2;
    }

    public void setMonedas2(int monedas2) {
        this.monedas2 = monedas2;
    }

    public int getMonedas50() {
        return monedas50;
    }

    public void setMonedas50(int monedas50) {
        this.monedas50 = monedas50;
    }

    public int getMonedas20() {
        return monedas20;
    }

    public void setMonedas20(int monedas20) {
        this.monedas20 = monedas20;
    }

    public int getMonedas10() {
        return monedas10;
    }

    public void setMonedas10(int monedas10) {
        this.monedas10 = monedas10;
    }

    public int getMonedas5() {
        return monedas5;
    }

    public void setMonedas5(int monedas5) {
        this.monedas5 = monedas5;
    }

    @Override
    public String toString() {
        return "{" + "billetes20=" + billetes20 + ", billetes10=" + billetes10
                + ", billetes5=" + billetes5 + ", monedas1=" + monedas1 + ", monedas2=" + monedas2
                + ", monedas50=" + monedas50 + ", monedas20=" + monedas20 + ", monedas10=" + monedas10
                + ", monedas5=" + monedas5 + '}';
    }

}
