/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmosgeneticos;

import java.awt.Color;

/**
 *
 * @author Carlos
 */
public enum ColorType {
    ROJO(Color.red),
    AZUL(Color.blue),
    AMARILLO(Color.yellow),
    VERDE(Color.green),
    NARANJA(Color.orange),
    MAGENTA(Color.MAGENTA),
    ROSA(Color.PINK),
    CYAN(Color.cyan);
    
    private Color color;

    private ColorType(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
    
    
}
