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
public class AlgoritmosGeneticos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Color color = Color.lightGray;
        System.out.println("Color Rgb: "+ color.getRed());
        System.out.println("Color Rgb: "+ color.getGreen());
        System.out.println("Color Rgb: "+ color.getBlue());
        //--------------------------------------------------
        System.out.println("Color Rgb: "+ color.getRGB());
        
        
        Color c = new Color(color.getRGB(), true);
    System.out.println(c.getRed());
    System.out.println(c.getGreen());
    System.out.println(c.getBlue());
    System.out.println(c.getAlpha());
    System.out.println("Color: "+ c);
    }
    
}
