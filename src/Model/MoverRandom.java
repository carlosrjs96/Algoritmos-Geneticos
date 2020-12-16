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
            System.out.println("punto flor " + flor.getPoint().toString());
            if (Utilidades.pointInRangeRadio(abeja, panal, flor.getPoint())) {
                listFloresInRange.add(flor);//añade las flores en el rango de la abeja
            }
        }
        System.out.println("panal " + panal.getPoint().toString());
        System.out.println("abeja " + abeja.getPoint().toString());
        Collections.shuffle(listFloresInRange);//ordenar las flores de manera aleatoria
        for (Flor flor : listFloresInRange) {
            System.out.println("abeja " + abeja.getNumAbeja());
            System.out.println(" " + abeja.getPoint().toString());
            System.out.println("flor : " + flor.getPoint().toString());
            double distance = Utilidades.distance(abeja.getPoint(), flor.getPoint());//distancia recorrida
            abeja.visitarFlor(flor);//visita la flor
            abeja.setPoint(flor.getPoint());//se posiciona en la flor que visito
            abeja.setDistanciaRecorrida(abeja.getDistanciaRecorrida() + distance);//añade la distancia que recorrio
            //System.out.println("dist abeja : " + abeja.getNumAbeja() + " " + abeja.getDistanciaRecorrida());
        }
        System.out.println("panal " + panal.getPoint().toString());
        System.out.println("abeja " + abeja.getPoint().toString());
        double distance = Utilidades.distance(abeja.getPoint(), panal.getPoint());//distancia recorrida
        abeja.setDistanciaRecorrida(abeja.getDistanciaRecorrida() + distance);
        abeja.setPoint(panal.getPoint());//devuelve la abeja al panal
    }
}
