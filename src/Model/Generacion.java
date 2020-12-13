/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Jarod
 */
public class Generacion {
    private ArrayList<Abeja> abejasGeneraciones;
    private Color[][] floresGeneraciones;
    private double promedioAdaptabilidad;
    private int gen;
    
    public Generacion (int gen){
        this.gen = gen;
    }

    public ArrayList<Abeja> getAbejasGeneraciones() {
        return abejasGeneraciones;
    }

    public void setAbejasGeneraciones(ArrayList<Abeja> abejasGeneraciones) {
        setPromedioAdaptabilidad (abejasGeneraciones);
        this.abejasGeneraciones = abejasGeneraciones;
    }

    public Color[][] getFloresGeneraciones() {
        return floresGeneraciones;
    }

    public void setFloresGeneraciones(Casilla[][] casillas) {
        int num = floresGeneraciones.length;
        Color[][] campo = new Color[num][num];
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                if (casillas[i][j] instanceof Panal){
                    campo[i][j]  = Color.BLACK;
                }
                else if (casillas[i][j] instanceof Flor){
                    Flor flr = (Flor) casillas[i][j];
                    campo[i][j]  = flr.getColor();     
                }
                else {//casilla normal
                    campo[i][j]  = Color.WHITE;
                }
            }
        }
        
        
        this.floresGeneraciones = floresGeneraciones;
    }

    private void setPromedioAdaptabilidad(ArrayList<Abeja> abejasGeneraciones) {
        double promedio=0;
        for (Abeja abeja : abejasGeneraciones) {
            promedio += abeja.getIndiceNormalizado();
        }
        this.promedioAdaptabilidad = promedio / abejasGeneraciones.size();
    }

   

 
    
    
}
