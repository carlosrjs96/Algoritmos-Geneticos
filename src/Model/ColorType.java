/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Color;

/**
 *
 * @author Carlos
 */
public enum ColorType {
    ROJO(Color.red,"000"),
    AZUL(Color.blue,"001"),
    AMARILLO(Color.yellow,"010"),
    VERDE(Color.green,"011"),
    NARANJA(Color.orange,"100"),
    MAGENTA(Color.MAGENTA,"101"),
    ROSA(Color.PINK,"110"),
    CYAN(Color.cyan,"111");
    
    private Color color;
    private String bits;

    private ColorType(Color color,String bits) {
        this.color = color;
        this.bits = bits;
    }

    public Color getColor() {
        return color;
    }

    public String getBits() {
        return bits;
    }

    public void setBits(String bits) {
        this.bits = bits;
    }
    
}
