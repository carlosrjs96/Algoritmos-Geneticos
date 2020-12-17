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
public abstract class Mover {
    public abstract void mover(Abeja abeja, Panal panal, ArrayList<Flor> listFlores);
    public abstract String getNombre();
}