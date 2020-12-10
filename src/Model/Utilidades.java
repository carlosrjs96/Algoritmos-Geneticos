/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Color;
import java.util.Random;

/**
 *
 * @author Jarod
 */
public class Utilidades {
    
    public static final Random rand = new Random();
    public static double rangoMaximo = -1;
    public static int probCrearFlor = 50;
    
    public static ColorType getRandomColor(){
        
        int largo = ColorType.values().length;
        int index = rand.nextInt(largo);
        return ColorType.values()[index];
    }
    
    public static DireccionType getRandomDireccion(){
        int largo = DireccionType.values().length;
        int index = rand.nextInt(largo);
        return DireccionType.values()[index];
    }
    
    //la idea es utilizar pitagoras 
    //para obtener la distancia desde el centro
    //hasta una esquina
    public static void setRangoMaximo(int dimension){
        double cateto = dimension / 2;
        double hipotenusa = Math.sqrt(Math.pow(cateto, 2)+Math.pow(cateto, 2));
        rangoMaximo = hipotenusa;  
    }
    
    
}
