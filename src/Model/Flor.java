/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Carlos
 */
public class Flor extends Casilla {
    //private Point point;
    //(color.getRGB(),cantidad de polen)
    //no recuerdo porque dos Integer
    private Hashtable<Integer, Integer> contenedorPolen = new Hashtable<Integer, Integer>();
    
    private Hashtable<Color, Integer> polenGuardado = new Hashtable<Color, Integer>();
    private Color color;
    private int x;
    private int y;

    public Flor (int x, int y) {
        this.x = x;
        this.y = y;
        point = new Point(x,y);
        this.color = Utilidades.getRandomColor().getColor();
    }
    
    public Flor(Point point, Color color) {
        super();
        this.point = point;
        this.color = color;
    }
    
    public Flor (Color color) {
        this.color = color;
    }
    
    
   /*
    HACER UNO QUE RECIBA UN ARRAYLIST
    O HASHTABLE DE COLORRES
    PORQUE UNA ABEJA PUDO HABER
    VISITADO YA VARIAS FLORES
    Y TIENE MUCHOS
    */
    public Flor getClone(){
        return new Flor(this.color);
    }
    
    
    //simplemente darle el polen a la flor
    public void polinizada(Color polen){
        //si no contiene la llave
        if (!polenGuardado.containsKey(polen)){
            polenGuardado.put(polen, 1);
        }
        else {
            polenGuardado.put(polen, polenGuardado.get(polen) + 1);
        }
        
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

    //nueva generación
    //el campo debe guardar la mariz de colores
    public void reproducir(){
        int numPolen=0;
        double num = Utilidades.rand.nextInt(1000)/1000;
        double sumados = 0;
        Color colorRepro;
        ArrayList<Double> probPolen = new ArrayList();
        //contar el polen total
        Set<Color> colorKeys = polenGuardado.keySet();
        for (Color clr : colorKeys) { 
            numPolen += polenGuardado.get(clr);
        }
        //sacar la probabilidad de cada uno
        for (Color clr : colorKeys) { 
            double prob = polenGuardado.get(clr) / numPolen;
            probPolen.add(prob);
        }
        int i = 0;
        for (Color clr : colorKeys) { 
            sumados += probPolen.get(i);
            if (num<=sumados){
                colorRepro = clr;
                break;
            }
            i++;
        }
        
        //acá ya tengo el color con el que se reproduce
        //hacer entrecruzamiento
        //convertir los valores a binarios
        
        
        
    }
    
    
    
}
