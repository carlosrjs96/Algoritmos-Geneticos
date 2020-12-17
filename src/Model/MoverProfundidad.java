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
public class MoverProfundidad  extends Mover{
    
    @Override
    public void mover(Abeja abeja, Panal panal, ArrayList<Flor> listFlores) {
        //System.out.println(" abeja : " +abeja.getNumAbeja() + " *******");
        ArrayList<Flor> listFloresInRange = new ArrayList<Flor>();

        for (Flor flor : listFlores) {
            if (Utilidades.pointInRangeRadio(abeja, panal, flor.getPoint())) {
                listFloresInRange.add(flor);//añade las flores en el rango de la abeja
            }
        }
        //System.out.println(listFloresInRange.size());
        
        Point pointProfundidad;
        /*System.out.println("abeja : " + abeja.getNumAbeja());
        System.out.println(abeja.getDireccionPreferencia() + " : " + abeja.getDireccionPreferencia().getDireccion());
        System.out.println("ang " + abeja.getAnguloApertura());*/
        if(Utilidades.rand.nextInt(2) == 1){
            pointProfundidad = Utilidades.calculatePoint(abeja.getDireccionPreferencia().getDireccion(),
                                                            panal.getPoint(),
                                                            abeja.getDistanciaMax());
            //System.out.println("point prof : " + pointProfundidad);
        }else{
            pointProfundidad = panal.getPoint();
        }
        
        Utilidades.sortByDistance(listFloresInRange, pointProfundidad);//ordena las flores por distancia con respecto al fondo del rango de la abeja
        //System.out.println(listFloresInRange.size());
        for (Flor flor : listFloresInRange) {
            //System.out.println("flor punto : " + flor.getPoint().toString() + " Color : " + flor.getColor());
            //System.out.println("puntos : " + abeja.getPoint().toString() + " " + flor.getPoint().toString() );
            double distance = Utilidades.distance(abeja.getPoint(), flor.getPoint());//distancia recorrida
            
            abeja.visitarFlor(flor);//visita la flor
            abeja.setPoint(flor.getPoint());//se posiciona en la flor que visito
            abeja.setDistanciaRecorrida(abeja.getDistanciaRecorrida() + distance);//añade la distancia que recorrio
            
            //System.out.println("pro : "+  " " + distance);
        }
        double distance = Utilidades.distance(abeja.getPoint(), panal.getPoint());//distancia recorrida
        abeja.setDistanciaRecorrida(abeja.getDistanciaRecorrida() + distance);
        //System.out.println("dist abeja : " + abeja.getNumAbeja() + " " + abeja.getDistanciaRecorrida());
        abeja.setPoint(panal.getPoint());//devuelve la abeja al panal
    }

    @Override
    public String getNombre() {
        return "Profundidad";
    }
    
    
}
