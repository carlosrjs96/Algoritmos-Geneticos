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
public class Panal {
    private ArrayList<Abeja> abejasList = new ArrayList<Abeja>();
    public static Random r = new Random();
    
    public Panal() {}

    public ArrayList<Abeja> getAbejasList() {
        return abejasList;
    }

    public void setAbejasList(ArrayList<Abeja> abejasList) {
        this.abejasList = abejasList;
    }
    
    
    
}
