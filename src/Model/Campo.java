/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author Carlos
 */
public class Campo {
    private Panal panal;
    private ArrayList<Casilla> floresList ;

    public Campo(Panal panal, ArrayList<Casilla> floresList) {
        this.panal = panal;
        this.floresList = floresList;
    }

    public Panal getPanal() {
        return panal;
    }

    public void setPanal(Panal panal) {
        this.panal = panal;
    }

    public ArrayList<Casilla> getFloresList() {
        return floresList;
    }

    public void setFloresList(ArrayList<Casilla> floresList) {
        this.floresList = floresList;
    }
    
    
}
