/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Carlos
 */
public class MoverAnchura extends Mover{

    @Override
    public void mover(Abeja abeja, Panal panal, ArrayList<Flor> listFlores) {
        
        ArrayList<Flor> listFloresInRange = new ArrayList<Flor>();
        
        for (Flor flor : listFlores) {
            if (Utilidades.pointInRangeRadio(abeja, panal, flor.getPoint())) {
                listFloresInRange.add(flor);//añade las flores en el rango de la abeja
            }
        }
        
        Utilidades.sortByDistance(listFloresInRange, panal.getPoint());//ordena las flores por distancia con respecto al panal
        
        for (Flor flor : listFloresInRange) {
            double distance = Utilidades.distance(abeja.getPoint(), flor.getPoint());//distancia recorrida
            abeja.visitarFlor(flor);//visita la flor
            abeja.setPoint(flor.getPoint());//se posiciona en la flor que visito
            abeja.setDistanciaRecorrida(abeja.getDistanciaRecorrida() + distance);//añade la distancia que recorrio
        }
        
        abeja.setPoint(panal.getPoint());//devuelve la abeja al panal
        
    }

    
}
