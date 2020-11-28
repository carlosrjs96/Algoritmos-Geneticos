/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmosgeneticos;

import java.awt.Color;
import java.util.Hashtable;
import java.util.Random;

/**
 *
 * @author Carlos
 */
public class Abeja {
    private Point point;
    private ColorType color;
    private DireccionType direccion;
    private double anguloPreferencia;
    private double distanciaMax;
    private Abeja mama;
    private Abeja papa;
    //(color.getRGB(),cantidad de polen)
    private Hashtable<Integer, Integer> contenedorPolen = new Hashtable<Integer, Integer>();
    private Mover mover;

    public Abeja(Point point, ColorType color, DireccionType direccion,
            double anguloPreferencia, double distanciaMax, Abeja mama, Abeja papa,int random) {
        this.point = point;
        this.color = color;
        this.direccion = direccion;
        this.anguloPreferencia = anguloPreferencia;
        this.distanciaMax = distanciaMax;
        this.mama = mama;
        this.papa = papa;
        this.setMover(random);
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
    
    public Abeja getHijoAbeja(Abeja mama, Abeja papa, int random){
        Point point = new Point(0,0);// hay que revisarlo
        Color color = getCromosomaColor(mama.getColor().getColor(), papa.getColor().getColor());
        DireccionType direccion = getCromosomaDireccion(mama.getDireccion(),papa.getDireccion());
        double anguloPreferencia = getCromosomaAngulo(mama.getAnguloPreferencia(),papa.getAnguloPreferencia());
        double distanciaMax = getCromosomaDist(mama.getDistanciaMax(),papa.getDistanciaMax());
        return new Abeja(point, null, direccion, anguloPreferencia, distanciaMax, mama, papa, random); 
    }
    
    
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

    public Abeja getMama() {
        return mama;
    }

    public void setMama(Abeja mama) {
        this.mama = mama;
    }

    public Abeja getPapa() {
        return papa;
    }

    public void setPapa(Abeja papa) {
        this.papa = papa;
    }

    

    public Hashtable<Integer, Integer> getContenedorPolen() {
        return contenedorPolen;
    }

    public void setContenedorPolen(Hashtable<Integer, Integer> contenedorPolen) {
        this.contenedorPolen = contenedorPolen;
    }
    
    

    public ColorType getColor() {
        return color;
    }

    public void setColor(ColorType color) {
        this.color = color;
    }

    public DireccionType getDireccion() {
        return direccion;
    }

    public void setDireccion(DireccionType direccion) {
        this.direccion = direccion;
    }

    public double getAnguloPreferencia() {
        return anguloPreferencia;
    }

    public void setAnguloPreferencia(double anguloPreferencia) {
        this.anguloPreferencia = anguloPreferencia;
    }

    public double getDistanciaMax() {
        return distanciaMax;
    }

    public void setDistanciaMax(double distanciaMax) {
        this.distanciaMax = distanciaMax;
    }
    
    
}
