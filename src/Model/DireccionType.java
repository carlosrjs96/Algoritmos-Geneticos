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
    NORTE(270,"000","N"),
    SUR(90,"001","S"),
    ESTE(0,"010","E"),
    OESTE(180,"011","O"),
    NE(315,"100","NE"),
    NO(225,"101","NO"),
    SE(45,"110","SE"),
    SO(135,"111","SO");
    
    private double direccion;
    private String bits;
    private String dir;
    
    private DireccionType(double direccion,String bits, String dir) {
        this.direccion = direccion;
        this.bits= bits;
        this.dir = dir;
    }

    public double getDireccion() {
        return direccion;
    }

    public String getBits() {
        return bits;
    }

    public String getDir() {
        return dir;
    }

   
    
    
}
