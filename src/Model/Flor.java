/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Color;
import java.util.Hashtable;
import java.util.Random;

/**
 *
 * @author Carlos
 */
public class Flor extends Casilla {
    //private Point point;
    //(color.getRGB(),cantidad de polen)
    private Hashtable<Integer, Integer> contenedorPolen = new Hashtable<Integer, Integer>();
    private Color color;
    private int x;
    private int y;

    public Flor (int x, int y) {
        this.x = x;
        this.y = y;
        this.color = Utilidades.getRandomColor();
    }
    
    public Flor(Point point, Color color) {
        super();
        this.point = point;
        this.color = color;
    }
    
    
    

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Hashtable<Integer, Integer> getContenedorPolen() {
        return contenedorPolen;
    }

    public void setContenedorPolen(Hashtable<Integer, Integer> contenedorPolen) {
        this.contenedorPolen = contenedorPolen;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    
    
    
}
