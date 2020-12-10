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
    
    public static Color getRandomColor(){
        Random rand = new Random();
        int largo = ColorType.values().length;
        int index = rand.nextInt(largo);
        return ColorType.values()[index].getColor();
    }
    
    
}
