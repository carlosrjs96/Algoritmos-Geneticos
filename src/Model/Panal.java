/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Carlos
 */
public class Panal extends Casilla{
    private ArrayList<Abeja> abejasList ;
    
    public Panal() {
        abejasList = new ArrayList<Abeja>();
    }
    
    public void crearAbejas (int pobAbejas){
        for (int i = 0; i < pobAbejas; i++) {
            abejasList.add(new Abeja());
        }
    }

    public ArrayList<Abeja> getAbejasList() {
        return abejasList;
    }

    public void setAbejasList(ArrayList<Abeja> abejasList) {
        this.abejasList = abejasList;
    }
    
    
    
}
