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
        this.abejasGeneraciones = new ArrayList();
    }

    public ArrayList<Abeja> getAbejasGeneraciones() {
        return abejasGeneraciones;
    }

    public void setAbejasGeneraciones(ArrayList<Abeja> abejasGeneraciones) {
        this.abejasGeneraciones = new ArrayList();
        //System.out.println("set adaptabilidad");
        setPromedioAdaptabilidad (abejasGeneraciones);
        this.abejasGeneraciones = abejasGeneraciones;
    }

    public Color[][] getFloresGeneraciones() {
        return floresGeneraciones;
    }

    public void setFloresGeneraciones(Casilla[][] casillas) {
        int num = casillas.length;
        this.floresGeneraciones = new Color[num][num]; 
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
        
        
        this.floresGeneraciones = campo;
    }

    private void setPromedioAdaptabilidad(ArrayList<Abeja> abejasGeneraciones) {
        double promedio=0;
        for (Abeja abeja : abejasGeneraciones) {
            //System.out.println(abeja.getIndiceNormalizado());
            promedio += abeja.getIndiceNormalizado();
        }
        this.promedioAdaptabilidad = promedio / abejasGeneraciones.size();
    }

    public double getPromedioAdaptabilidad() {
        return promedioAdaptabilidad;
    }

    public void setPromedioAdaptabilidad(double promedioAdaptabilidad) {
        this.promedioAdaptabilidad = promedioAdaptabilidad;
    }

    public int getGen() {
        return gen;
    }

    public void setGen(int gen) {
        this.gen = gen;
    }

    
   

 
    
    
}
