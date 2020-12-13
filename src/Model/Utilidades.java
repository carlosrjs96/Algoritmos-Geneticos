/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Color;
import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author Jarod
 */
public class Utilidades {
    
    public static final Random rand = new Random();
    public static double rangoMaximo = -1;
    public static int probCrearFlor = 50;
    public static int maxMutaFlor = 10;
    public static int maxMutaAbeja = 10;
    
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
    
    //corta el cromosoma
    //index = 6
    //string = "100100|100101001"
    //          012345|6789
    public static String[] cortarCromosoma(String cromosoma, int index){
        String primerParte = cromosoma.substring(0, index);
        String segundaParte = cromosoma.substring(index);
        String[] arrString = new String [2];
        arrString[0] = primerParte;
        arrString[1] = segundaParte;
        return arrString;
    }
    
    
    //dado un string que representa el color en bits
    //retorna el colo
    public static ColorType getColor(String bits){
        for (ColorType color: ColorType.values()) {
            if (color.getBits().equals(bits)){
                return color;
            }
        }
        return null;
    }
    
    public static DireccionType getDireccion(String bits){
        for (DireccionType dir: DireccionType.values()) {
            if (dir.getBits().equals(bits)){
                return dir;
            }
        }
        return null;
    }
    
    public static int getCantidadBitsRango(){
        double rang = Math.ceil(rangoMaximo);
        int r = (int) rang;
        String bits = new BigInteger(Integer.toString(r)).toString(2);
        return bits.length();
        
    }
    
    
}
