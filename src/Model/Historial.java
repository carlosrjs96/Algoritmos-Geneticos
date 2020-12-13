/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author Jarod
 */
public class Historial {
    private ArrayList<Generacion> generaciones;
    
    public Historial (){
        generaciones = new ArrayList();
    }
    
    public void addAbejas(ArrayList<Abeja> abejasList){
        
    }
    
    public void addFlores(Casilla[][] matrizFlores ){
        
    }

    void addGeneracion(Casilla[][] matrizFlores, ArrayList<Abeja> abejasList) {
        int gen = generaciones.size();
        Generacion newG = new Generacion(gen);
        newG.setAbejasGeneraciones(abejasList);
        newG.setFloresGeneraciones(matrizFlores);
    }
    
}
