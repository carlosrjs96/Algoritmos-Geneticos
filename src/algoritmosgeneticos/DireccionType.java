/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmosgeneticos;

/**
 *
 * @author Carlos
 */
public enum DireccionType {
    NORTE(270),
    SUR(90),
    ESTE(0),
    OESTE(180),
    NE(315),
    NO(225),
    SE(45),
    SO(135);
    
    private double direccion;

    private DireccionType(double direccion) {
        this.direccion = direccion;
    }

    public double getDireccion() {
        return direccion;
    }
    
    
}
