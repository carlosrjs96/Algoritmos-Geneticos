/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Carlos
 */
public class MoverRandom extends Mover{

    @Override
    public void mover(Abeja abeja, Panal panal, ArrayList<Flor> listFlores) {
        
        ArrayList<Flor> listFloresInRange = new ArrayList<Flor>();
        for (Flor flor : listFlores) {
            if (Utilidades.pointInRangeRadio(abeja, panal, flor.getPoint())) {
                listFloresInRange.add(flor);//añade las flores en el rango de la abeja
            }
        }
        /*System.out.println("abeja : " + abeja.getNumAbeja());
        System.out.println(abeja.getDireccionPreferencia() + " : " + abeja.getDireccionPreferencia().getDireccion());
        System.out.println("ang " + abeja.getAnguloApertura());*/
        Collections.shuffle(listFloresInRange);//ordenar las flores de manera aleatoria
        for (Flor flor : listFloresInRange) {
            double distance = Utilidades.distance(abeja.getPoint(), flor.getPoint());//distancia recorrida
            abeja.visitarFlor(flor);//visita la flor
            abeja.setPoint(flor.getPoint());//se posiciona en la flor que visito
            abeja.setDistanciaRecorrida(abeja.getDistanciaRecorrida() + distance);//añade la distancia que recorrio
            //System.out.println("dist abeja : " + abeja.getNumAbeja() + " " + abeja.getDistanciaRecorrida());
        }
        double distance = Utilidades.distance(abeja.getPoint(), panal.getPoint());//distancia recorrida
        abeja.setDistanciaRecorrida(abeja.getDistanciaRecorrida() + distance);
        abeja.setPoint(panal.getPoint());//devuelve la abeja al panal
    }

    @Override
    public String getNombre() {
        return "Random";
    }
}
