/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Carlos
 */
public class Flor extends Casilla {

    private Point point;

    //(color.getRGB(),cantidad de polen)
    //no recuerdo porque dos Integer
    private Hashtable<Integer, Integer> contenedorPolen = new Hashtable<Integer, Integer>();

    private Hashtable<Color, Integer> polenGuardado = new Hashtable<Color, Integer>();
    private Color color;

    
    public Flor (int x, int y) {
        point = new Point(x,y);

        this.color = Utilidades.getRandomColor().getColor();
        addPolenGuardado();
    }

    public Flor(Point point, Color color) {
        super();
        this.point = point;
        this.color = color;
        addPolenGuardado();
    }

    public Flor(Color color) {
        this.color = color;
        addPolenGuardado();
    }

    /*
    HACER UNO QUE RECIBA UN ARRAYLIST
    O HASHTABLE DE COLORRES
    PORQUE UNA ABEJA PUDO HABER
    VISITADO YA VARIAS FLORES
    Y TIENE MUCHOS
     */
    public Flor getClone() {
        return new Flor(this.color);
    }
    
    

    //simplemente darle el polen a la flor
    public void polinizada(Color polen) {
        //si no contiene la llave
        if (!polenGuardado.containsKey(polen)) {
            polenGuardado.put(polen, 1);
        } else {
            polenGuardado.put(polen, polenGuardado.get(polen) + 1);
        }

    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Hashtable<Integer, Integer> getContenedorPolen() {
        return contenedorPolen;
    }

    public void setContenedorPolen(Hashtable<Integer, Integer> contenedorPolen) {
        this.contenedorPolen = contenedorPolen;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }


    //nueva generación
    //el campo debe guardar la mariz de colores
    //******************************
    /*
    ESTAMOS USANDO LA MISMA REFERNCIA DE FLOR PARA TODAS LAS GENERACIONES
    */
    //******************************
    public void reproducir() {
        if (polenGuardado.isEmpty()) {
            this.color = Utilidades.getRandomColor().getColor();  
        } else {//hay polen
            //cruzar();

        }

    }
    
    //esta la llama campo
    public String getCromosoma(){
        String cromosoma;
        if (esVacia()) {
            //System.out.println("no visitada " + this.point.toString());
            //cambiar esto luego
            cromosoma = getBitsColor(Utilidades.getRandomColor().getColor()); 
            
        } else {//hay polen
            //System.out.println("visitada");
            cromosoma = cruzarCromosoma();
        }
        //cambiarlo
        //cromosoma = getBitsColor(this.color);
        return cromosoma;
    }
    
    private boolean esVacia(){
        if (polenGuardado.size() == 1 && polenGuardado.get(this.color) == 1){
            return true;
        }
        return false;
    }
    
    
    //se cruzaran unicamente los colores
    private String cruzarCromosoma(){
        Color colorACruzar = getCouple();
        String bitsActual = getBitsColor(this.color);
        String bitsCouple = getBitsColor(colorACruzar);
        
        String bitsCruzadosActual = "";
        String bitsCruzadosCouple = "";
        
        //generar index random
        int index = Utilidades.rand.nextInt(bitsActual.length());
        //***********NO ESTA VALIDADDO EN CASO DE UN STRING NULL
        String [] corteActual = Utilidades.cortarCromosoma(bitsActual, index);
        String [] corteCouple = Utilidades.cortarCromosoma(bitsCouple, index);
        
        //acá los cruza
        bitsCruzadosActual = bitsCruzadosActual.concat(corteActual[0]).concat(corteCouple[1]);
        bitsCruzadosCouple = bitsCruzadosCouple.concat(corteCouple[0]).concat(corteActual[1]);
        Color color;
        int num = Utilidades.rand.nextInt(2);
        if (num==0){//usar bitsCruzadosActual
            return bitsCruzadosActual;
        }else{//usar bitsCruzadosCouple
            return bitsCruzadosCouple;
        }
       
        
    }
    
    
    
    
    //esta funcion puede cambiar 
    /*
    *****************************
    en la especificación dice que se use RGB
    pero NO estamos usando RGB
    *****************************
    */
    
    private String getBitsColor(Color color){
        String bits = null;
        for (ColorType colorEnum : ColorType.values()) {
            if (colorEnum.getColor().equals(color)){
                bits = colorEnum.getBits();
                break;
            }
        }

        return bits;
    }
    
    //retorna el color con el que se reproduce
    private Color getCouple(){
        double prob = (double)Utilidades.rand.nextInt(1000)/1000;
        Hashtable<Color, Double> porcentajes = getPorcentajesPolen();
        Color key = null;
        double suma=0.0;
        Set<Color> keys = polenGuardado.keySet();
        Iterator<Color> itr = keys.iterator();
        while (itr.hasNext()) {
            // Getting Key
            key = itr.next();
            suma += polenGuardado.get(key);
            if (prob<suma){
                break;
            }
        }
        return key; 
        
        
    }

    public Hashtable<Color, Integer> getPolenGuardado() {
        return polenGuardado;
    }

    public void setPolenGuardado(Hashtable<Color, Integer> polenGuardado) {
        this.polenGuardado = polenGuardado;
    }
    
    
    
    
    //retorna un hash con los valores del porcentje que representa
    //cada color
    private Hashtable<Color, Double> getPorcentajesPolen(){
        //hash de retorno
        Hashtable<Color, Double> porcentajes = new Hashtable();
        Color key;
        int totalPolen = getTotalPolen();
        //porcentaje para cada llave
        double porcentaje;
        Set<Color> keys = polenGuardado.keySet();
        //para iterar sobre las llaves
        Iterator<Color> itr = keys.iterator();
        while (itr.hasNext()) {
            // Getting Key
            key = itr.next();
            porcentaje = (double) polenGuardado.get(key)/totalPolen;
            porcentajes.put(key,porcentaje);
            
        }
        return porcentajes;  
    }

    //retorna la cantidad total de polen que hay en polenGuardado
    private int getTotalPolen() {
        int total = 0;
        Color key;
        //obtengo todas las llaves
        Set<Color> keys = polenGuardado.keySet();
        //para iterar sobre las llaves
        Iterator<Color> itr = keys.iterator();

        //recorre el iterador
        while (itr.hasNext()) {
            // Getting Key
            key = itr.next();
            total += polenGuardado.get(key);
        }
        return total;
    }

    
    public void addPolen (Hashtable<Color, Integer> polenGuardado){
        Set<Color> keys = polenGuardado.keySet();
        //para iterar sobre las llaves
        Iterator<Color> itr = keys.iterator();
        Color key;
        while (itr.hasNext()) {
            int anterior = 0;
            // Getting Key
            key = itr.next();
            if (this.polenGuardado.containsKey(key)){
                anterior = this.polenGuardado.get(key);
            }
            anterior += polenGuardado.get(key);
            this.polenGuardado.put(key, anterior);
        } 
    }
    
    private void addPolenGuardado(){
        int anterior = 0;
         if (this.polenGuardado.containsKey(this.color)){
                anterior = this.polenGuardado.get(this.color);
        }
        anterior += 1;
        this.polenGuardado.put(this.color, anterior);
    }

}
