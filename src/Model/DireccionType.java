/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Carlos
 */
public enum DireccionType {
    NORTE(270,"000"),
    SUR(90,"001"),
    ESTE(0,"010"),
    OESTE(180,"011"),
    NE(315,"100"),
    NO(225,"101"),
    SE(45,"110"),
    SO(135,"111");
    
    private double direccion;
    private String bits;
    
    private DireccionType(double direccion,String bits) {
        this.direccion = direccion;
        this.bits= bits;
    }

    public double getDireccion() {
        return direccion;
    }

    public String getBits() {
        return bits;
    }
    
}
