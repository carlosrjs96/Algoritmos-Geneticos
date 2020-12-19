/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Color;
import java.math.BigInteger;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Carlos
 */
public class Abeja {
    private int numAbeja;
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

    private Hashtable<Color, Integer> polenGuardado = new Hashtable<Color, Integer>();

    private Mover mover;    
    private int cantFloresVisitadas = 0;
    private double distanciaRecorrida = 0;


    
    private double indiceAdaptibilidad;
    private double indiceNormalizado;
    private Abeja[] antecesores;
    private boolean reproduce = false; 
    private int vecesReproduce = 0;
    
    public Abeja(Point punto, int numAbeja){
        this.numAbeja = numAbeja;
        this.point = punto;
        colorPreferencia = Utilidades.getRandomColor();
        direccionPreferencia = Utilidades.getRandomDireccion();
        //obtener la distancia maxima desde el centro del panal hasta el extremo
        distanciaMax = Utilidades.setRangoMaximo();
        anguloApertura = Utilidades.rand.nextInt(180);
        mamaAbeja = null;
        papaAbeja = null;
        //this.mover = new MoverRandom();
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
        //this.mover = new MoverRandom();
        this.mover = getRandomMover();
        
    }

    public Abeja(String string, Abeja[] abejas,Point punto, int numAbeja) {
        this.antecesores = abejas;
        this.point = punto;
        this.numAbeja = numAbeja;
        //this.mover = new MoverRandom();
        mover = getRandomMover();
        asignarValores(string);
    }
    
    private void asignarValores(String cromosoma){
        String strColor, strDir, strAng, strDist;
        strColor = cromosoma.substring(0, 3);
        strDir = cromosoma.substring(3, 6);
        strAng = cromosoma.substring(6, 6+9);
        strDist = cromosoma.substring(6+9);
        this.colorPreferencia = Utilidades.getColor(strColor);
        this.direccionPreferencia = Utilidades.getDireccion(strDir);
        this.anguloApertura = Integer.parseInt(strAng, 2)%360;
        this.distanciaMax = Integer.parseInt(strDist, 2);
        
        
    }
    
     public String aBits(){
        String bits = "";
        bits = bits.concat(getBitsColor());
        bits = bits.concat(getBitsDireccion());
        bits = bits.concat(getBitsAngulo());
        bits = bits.concat(getBitsDistancia());
        return bits;
    }
     
     
     private String getBitsDistancia(){
        String bits = "";
        int dist = (int) this.distanciaMax;
        
        
        
        bits = new BigInteger(Integer.toString(dist)).toString(2);
        int num = Utilidades.getCantidadBitsRango() - bits.length();
        for (int i = 0; i < num; i++) {
            bits = "0" + bits;
        }
        return bits;
     }
     
    //pase el angulo a entero
    private String getBitsAngulo (){
        String bits = "";
        int ang = (int) this.anguloApertura;
        bits = new BigInteger(Integer.toString(ang)).toString(2);
        int num = 9 - bits.length();
        for (int i = 0; i < num; i++) {
            bits = "0" + bits;
        }
        return bits;
    }
     
    private String getBitsDireccion(){
        String bits = null;
        for (DireccionType direccionEnum : DireccionType.values()) {
            if (direccionEnum.equals(this.direccionPreferencia)){
                bits = direccionEnum.getBits();
                break;
            }
        }
        return bits;
    }
     
     
    private String getBitsColor(){
        String bits = null;
        for (ColorType colorEnum : ColorType.values()) {
            if (colorEnum.equals(this.colorPreferencia)){
                bits = colorEnum.getBits();
                break;
            }
        }
        return bits;
    }
    
    
    
    public void asignarAdaptabilidad(){
        this.distanciaRecorrida = this.distanciaRecorrida / 100;
        if (cantFloresVisitadas == 0 || distanciaRecorrida == 0){
            indiceAdaptibilidad = 0;
        }
        else {
            indiceAdaptibilidad = (double) cantFloresVisitadas * cantFloresVisitadas / distanciaRecorrida;
            //indiceAdaptibilidad = (double) cantFloresVisitadas / distanciaRecorrida;
            //indiceAdaptibilidad = (double) cantFloresVisitadas * cantFloresVisitadas / distanciaRecorrida + this.anguloApertura;
            //indiceAdaptibilidad = (double) cantFloresVisitadas * cantFloresVisitadas / distanciaRecorrida ;//+ this.anguloApertura ;/// getDistaciaRecorrida();
            
        }
    }
    
    public void asignarAdaptabilidadNormalizada (double sumaIndices){
        
        /*System.out.println("num abeja : " + this.numAbeja);
        System.out.println("suma : " + sumaIndices);
        System.out.println("indice  " + indiceAdaptibilidad);
        System.out.println("visitadas : " + cantFloresVisitadas);
        System.out.println("dist " + distanciaRecorrida);*/
        if (indiceAdaptibilidad==0){
            indiceNormalizado = 0;
        }
        else {
            indiceNormalizado = (double) indiceAdaptibilidad  / sumaIndices;
        }
        /*System.out.println("normal : " + indiceNormalizado);
        System.out.println("--------------------------------------");*/
    }
    
    private double getDistaciaRecorrida(){
        
        return this.distanciaRecorrida;
    }
    
    private double getFloresVisitadas(){
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
    
    
    private Mover getRandomMover(){
        int num = Utilidades.rand.nextInt(3);
        //num = 1;
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

    public Hashtable<Color, Integer> getPolenGuardado() {
        return polenGuardado;
    }

    public void setPolenGuardado(Hashtable<Color, Integer> polenGuardado) {
        this.polenGuardado = polenGuardado;
    }

    public double getIndiceAdaptibilidad() {
        return indiceAdaptibilidad;
    }

    public void setIndiceAdaptibilidad(double indiceAdaptibilidad) {
        this.indiceAdaptibilidad = indiceAdaptibilidad;
    }

    public double getIndiceNormalizado() {
        return indiceNormalizado;
    }

    public void setIndiceNormalizado(double indiceNormalizado) {
        this.indiceNormalizado = indiceNormalizado;
    }
    
    
    
    public void visitarFlor(Flor flor){
        
        if(Utilidades.probPolenizar(flor, this)){
            this.cantFloresVisitadas++;
            //AQUI LE INTRODUCE EL POLEN A LA FLOR
            Hashtable <Color, Integer> flrTmp = deepCopy(flor.getPolenGuardado());
            Hashtable <Color, Integer> abjTmp = deepCopy(this.polenGuardado);
            addPolen(flrTmp);
            flor.addPolen(abjTmp);
            flor.setPolenizada(true);
        } 
    }
    
    public Hashtable<Color, Integer> deepCopy(Hashtable<Color, Integer> original) {
        Hashtable<Color, Integer> copy = new Hashtable<Color, Integer>();
        Set<Color> keys = original.keySet();
        Iterator<Color> itr = keys.iterator();
        Color key;
        while (itr.hasNext()) {
            key = itr.next();
            copy.put(key, original.get(key));
        }
        return copy;
    }

    
    public void addPolen(Color key){
        int anterior = 0;
        if (this.polenGuardado.containsKey(key)){
                anterior = this.polenGuardado.get(key);
        }
        anterior += 1;
        this.polenGuardado.put(key, anterior);
    }
    
    //añade el polen de la flor al polen de la abeja
    public void addPolen (Hashtable<Color, Integer> polenGuardado){
        //System.out.println("tama ño : " + polenGuardado.size());
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
            anterior += polenGuardado.get(key);//este se refiere a la entrada
            this.polenGuardado.put(key, anterior);
        } 
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

    public int getCantFloresVisitadas() {
        return cantFloresVisitadas;
    }

    public void setCantFloresVisitadas(int cantFloresVisitadas) {
        this.cantFloresVisitadas = cantFloresVisitadas;
    }

    public double getDistanciaRecorrida() {
        return distanciaRecorrida;
    }

    public void setDistanciaRecorrida(double distanciaRecorrida) {
        this.distanciaRecorrida = distanciaRecorrida;
    }

    public int getNumAbeja() {
        return numAbeja;
    }

    public void setNumAbeja(int numAbeja) {
        this.numAbeja = numAbeja;
    }

    public Abeja[] getAntecesores() {
        return antecesores;
    }

    public void setAntecesores(Abeja[] antecesores) {
        this.antecesores = antecesores;
    }

    public boolean isReproduce() {
        return reproduce;
    }

    public void setReproduce(boolean reproduce) {
        this.reproduce = reproduce;
    }

    public int getVecesReproduce() {
        return vecesReproduce;
    }

    public void setVecesReproduce(int vecesReproduce) {
        this.vecesReproduce = vecesReproduce;
    }

    
    
    
    
    
    
}
