/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Color;
import java.util.Hashtable;
import java.util.Random;

/**
 *
 * @author Carlos
 */
public class Abeja {
    private Point point;
    private ColorType colorPreferencia;
    //no recuerdo para que servia esta direccion
    //por eso la nombre de nuevo
    private DireccionType direccionPreferencia;
    //este es el angulo de apertura
    //que tan ampio será su area de busqueda
    private double anguloApertura;
    private double distanciaMax;
    private Abeja mamaAbeja;
    private Abeja papaAbeja;
    //(color.getRGB(),cantidad de polen)
    private Hashtable<Integer, Integer> contenedorPolen = new Hashtable<Integer, Integer>();
    private Mover mover;
    
    public Abeja(){
        colorPreferencia = Utilidades.getRandomColor();
        direccionPreferencia = Utilidades.getRandomDireccion();
        //obtener la distancia maxima desde el centro del panal hasta el extremo
        distanciaMax = Utilidades.rand.nextInt((int)Utilidades.rangoMaximo);
        anguloApertura = Utilidades.rand.nextInt(180);
        mamaAbeja = null;
        papaAbeja = null;
        mover = getRandomMover();
    }
    
    
    

    public Abeja(Point point, ColorType color, DireccionType direccion,
            double anguloPreferencia, double distanciaMax, Abeja mama, Abeja papa,int random) {
        this.point = point;
        //this.color = color;
        //this.direccion = direccion;
        //this.anguloPreferencia = anguloPreferencia;
        this.distanciaMax = distanciaMax;
        //this.mama = mama;
        //this.papa = papa;
        this.setMover(random);
    }
    
    private Mover getRandomMover(){
        int num = Utilidades.rand.nextInt(3);
        Mover mover= null;
        switch (num) {
            case 0:
                mover = new MoverAnchura();
                break;
            case 1:
                mover = new MoverProfundidad();
                break;
            default:
                mover = new MoverRandom();
                break;
        }
        return mover;
    }
    
    
    
    public Color getCromosomaColor( Color mama, Color papa){
        return null;
    }
    
    public DireccionType getCromosomaDireccion( DireccionType mama, DireccionType papa){
        return null;
    }
    
    public double getCromosomaAngulo( double mama, double papa){
        return 0;
    }
    
    public double getCromosomaDist( double mama, double papa){
        return 0;
    }
    
    /*public Abeja getHijoAbeja(Abeja mama, Abeja papa, int random){
        Point point = new Point(0,0);// hay que revisarlo
        Color color = getCromosomaColor(mama.getColor().getColor(), papa.getColor().getColor());
        DireccionType direccion = getCromosomaDireccion(mama.getDireccion(),papa.getDireccion());
        double anguloPreferencia = getCromosomaAngulo(mama.getAnguloPreferencia(),papa.getAnguloPreferencia());
        double distanciaMax = getCromosomaDist(mama.getDistanciaMax(),papa.getDistanciaMax());
        return new Abeja(point, null, direccion, anguloPreferencia, distanciaMax, mama, papa, random); 
    }*/
    
    
    private void setMover(int random){
        switch (random%3){
            case 0://anchura
                this.mover = new MoverAnchura();
                break;
            case 1://profundidad
                this.mover = new MoverProfundidad();
                break;
            case 2://random
                this.mover = new MoverRandom();
                break;
        }
    }

    public Mover getMover() {
        return mover;
    }

    public void setMover(Mover mover) {
        this.mover = mover;
    }

    public Point getPoint(){
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

    public ColorType getColorPreferencia() {
        return colorPreferencia;
    }

    public void setColorPreferencia(ColorType colorPreferencia) {
        this.colorPreferencia = colorPreferencia;
    }

    public DireccionType getDireccionPreferencia() {
        return direccionPreferencia;
    }

    public void setDireccionPreferencia(DireccionType direccionPreferencia) {
        this.direccionPreferencia = direccionPreferencia;
    }

    public double getAnguloApertura() {
        return anguloApertura;
    }

    public void setAnguloApertura(double anguloApertura) {
        this.anguloApertura = anguloApertura;
    }

    public Abeja getMamaAbeja() {
        return mamaAbeja;
    }

    public void setMamaAbeja(Abeja mamaAbeja) {
        this.mamaAbeja = mamaAbeja;
    }

    public Abeja getPapaAbeja() {
        return papaAbeja;
    }

    public void setPapaAbeja(Abeja papaAbeja) {
        this.papaAbeja = papaAbeja;
    }
    
    

   

 

    public double getDistanciaMax() {
        return distanciaMax;
    }

    public void setDistanciaMax(double distanciaMax) {
        this.distanciaMax = distanciaMax;
    }
    
    public double distance(Point a,Point b){
        double distance = Math.sqrt(Math.pow(b.x-a.x,2)+Math.pow(b.y-a.y,2));
        //System.out.println("Distancia: "+distance);
        return distance;
    }
    
    public double calculateAngle(Point origen, Point destino){//Calcula el angulo de una linea a partir de un punto de origen
        double x1 = destino.x; 
        double y1 = destino.y; 
        double x2 = origen.x; 
        double y2 = origen.y;
        double hypothenus=(double)Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2));
        double angle=(double)Math.toDegrees(Math.acos( (x1-x2)/hypothenus ));
        if(y1<y2) angle=360-angle;
        return angle;
    }
    
    public Point calculatePoint(double angle, Point pCentral,double dist){
        double angle_div = Math.toRadians(angle);
        Point point = new Point((pCentral.x+Math.cos(angle_div)*dist),(pCentral.y+Math.sin(angle_div)*dist)); 
        return point;
    }
    
}
