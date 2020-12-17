/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Color;

import java.math.BigInteger;

import java.util.ArrayList;

import java.util.Random;

/**
 *
 * @author Jarod
 */
public class Utilidades {
    
    public static final Random rand = new Random();
    public static double rangoMaximo = -1;
    public static int probCrearFlor = 100;//si es 100 crea todo

    public static int maxMutaFlor = 100;
    public static int maxMutaAbeja = 100;

    public static int probPolenizarFlor = 70;//si es 0 visita todo

    
    public static ColorType getRandomColor(){
        
        int largo = ColorType.values().length;
        int index = rand.nextInt(largo);
        return ColorType.values()[index];
    }
    
    public static DireccionType getRandomDireccion(){
        int largo = DireccionType.values().length;
        int index = rand.nextInt(largo);
        return DireccionType.values()[index];
    }
    
    //la idea es utilizar pitagoras 
    //para obtener la distancia desde el centro
    //hasta una esquina
    public static void setRangoMaximo(int dimension){
        double cateto = dimension / 2;
        double hipotenusa = Math.sqrt(Math.pow(cateto, 2)+Math.pow(cateto, 2));
        rangoMaximo = hipotenusa;  
    }
    

    //corta el cromosoma
    //index = 6
    //string = "100100 | 100101001"
    //          012345|6789
    public static String[] cortarCromosoma(String cromosoma, int index){
        String primerParte = cromosoma.substring(0, index);
        String segundaParte = cromosoma.substring(index);
        String[] arrString = new String [2];
        arrString[0] = primerParte;
        arrString[1] = segundaParte;
        return arrString;
    }
    
    
    //dado un string que representa el color en bits
    //retorna el colo
    public static ColorType getColor(String bits){
        for (ColorType color: ColorType.values()) {
            if (color.getBits().equals(bits)){
                return color;
            }
        }
        return null;
    }
    
    public static DireccionType getDireccion(String bits){
        for (DireccionType dir: DireccionType.values()) {
            if (dir.getBits().equals(bits)){
                return dir;
            }
        }
        return null;
    }
    
    public static int getCantidadBitsRango(){
        double rang = Math.ceil(rangoMaximo);
        int r = (int) rang;
        String bits = new BigInteger(Integer.toString(r)).toString(2);
        return bits.length();
        
    }
    

    public static double distance(Point a,Point b){
        
        double distance = Math.sqrt(Math.pow(b.x-a.x,2)+Math.pow(b.y-a.y,2));
        //System.out.println("Distancia: "+distance);
        return distance;
    }
    
    public static double calculateAngle(Point origen, Point destino){//Calcula el angulo de una linea a partir de un punto de origen
        double x1 = destino.x; 
        double y1 = destino.y; 
        double x2 = origen.x; 
        double y2 = origen.y;
        double hypothenus=(double)Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2));
        double angle=(double)Math.toDegrees(Math.acos( (x1-x2)/hypothenus ));
        if(y1<y2) angle=360-angle;
        return angle;
    }
    
    public static Point calculatePoint(double angle, Point pCentral,double dist){
        double angle_div = Math.toRadians(angle);
        Point point = new Point((pCentral.x+Math.cos(angle_div)*dist),(pCentral.y+Math.sin(angle_div)*dist)); 
        return point;
    }

    
    public static boolean pointInCircle(double radius,Point p1, Point p2) {
        return Utilidades.distance(p1, p2) < radius ;
    }
    
    public static boolean pointInRangeRadio(Abeja abeja, Panal panal, Point point) {
        double radius = abeja.getDistanciaMax();
        Point pCentre = panal.getPoint();
        double min = Math.abs(abeja.getDireccionPreferencia().getDireccion() - abeja.getAnguloApertura()/2);
        double max = Math.abs(abeja.getDireccionPreferencia().getDireccion() + abeja.getAnguloApertura()/2);
        double anglePoint = Utilidades.calculateAngle(pCentre, point);

        while ( min >= max ){
            max += 360;
        }
        while ( min >= anglePoint ){
            anglePoint += 360;
        }        
        if(anglePoint >= min && anglePoint <= max){
            return Utilidades.pointInCircle(radius, pCentre, point);
        }
        return false; 
    }
    
    public static boolean pointInRangeRadio(double radius, double angle, Point pCentre, Point point) {
        double min = Math.abs(radius - angle/2);
        double max = Math.abs(radius + angle/2);
        double anglePoint = Utilidades.calculateAngle(pCentre, point);
        
        while ( min >= max ){
            max += 360;
        }
        while ( min >= anglePoint ){
            anglePoint += 360;
        }
        if(min >= anglePoint && max <= anglePoint){
            return Utilidades.pointInCircle(radius, pCentre, point);
        }
        return false; 
    }
    
    public static void sortByDistance(ArrayList<Flor> list, Point pCentre){
        //Ordena por distancia de los puntos con respecto al centro.
        //>>>> BubbleSort.
        double a;
        double b;
        Flor c;
        Flor d;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                a = Utilidades.distance(pCentre, list.get(j).getPoint());
                b = Utilidades.distance(pCentre, list.get(j + 1).getPoint());
                c = list.get(j);
                d = list.get(j + 1);
                //int compare = new Double(a).compareTo(new Double(b));
                if (a>b) {
                    Flor temp = d;
                    list.set(j + 1, c);//list.set(j + 1, list.get(j));
                    list.set(j, d);//list.set(j, list.get(j + 1));
                }
            }
        }
    }
    
    public static boolean probPolenizar(Flor flor, Abeja abeja) {
        if(flor.getColor() != abeja.getColorPreferencia().getColor()){
            if (Utilidades.rand.nextInt(100) <= Utilidades.probPolenizarFlor) {
               return false; 
            }
        }
        return true;
    }
    
    public static double setRangoMaximo(){
        double min = rangoMaximo/2;
        double max = rangoMaximo;
        return (rand.nextFloat()* (max - min)) + min;
    }

    public static int getMaxMutaFlor() {
        return maxMutaFlor;
    }

    public static void setMaxMutaFlor(int maxMutaFlor) {
        Utilidades.maxMutaFlor = maxMutaFlor;
    }

    public static int getMaxMutaAbeja() {
        return maxMutaAbeja;
    }

    public static void setMaxMutaAbeja(int maxMutaAbeja) {
        Utilidades.maxMutaAbeja = maxMutaAbeja;
    }
    

}
