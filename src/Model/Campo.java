/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Carlos
 */
public class Campo {
    private Panal panal;
    private Casilla[][] matrizFlores ;
    private Color[][][] historial;
    private Historial historia;
    //ir guardando cada matiz de colores de flores
    
    //private int probCrearFlor;
    
    public Campo(){
        
    }
    
    public Campo (int dimension, int pobFlores) {
        this.panal = new Panal();
        this.matrizFlores = new Casilla [dimension][dimension];
        this.historia = new Historial();
        Utilidades.setRangoMaximo(dimension);
        //this.probCrearFlor = 50;
        inicializarMatriz();
        setPanalCentro();
        cargarFlores(pobFlores);
        
    }
    
    public void iniciarTodo(int dimension, int pobFlores, int pobAbejas) {
        this.panal = new Panal();
        this.matrizFlores = new Casilla [dimension][dimension];
        Utilidades.setRangoMaximo(dimension);
        
        //this.probCrearFlor = 50;
        inicializarMatriz();
        setPanalCentro();
        cargarFlores(pobFlores);
        this.panal.crearAbejas(pobAbejas);
    }
    
    //posiciona al panal en el centro
    private void setPanalCentro(){
        int pos = matrizFlores.length/2;
        panal.setPoint(new Point (pos,pos));
        matrizFlores[pos][pos] = panal;
    }
    
    //crea y posiciona las flores en el tablero
    private void cargarFlores (int pob) { 
        int index;
        int max;
        int [] par;
        int prob;
        //los pares estan [x,y]
        //obtengo todos los pares donde puede setear la flor
        ArrayList <int[]> arrPares = getAllPosititions();
        max = arrPares.size();
        if (pob > max){
            pob = max;
        }
        int i =0;
        while (pob>0){
            index = Utilidades.rand.nextInt(max);
            par = arrPares.get(index);
            prob = Utilidades.rand.nextInt(100);
            if (prob<=Utilidades.probCrearFlor){
                System.out.println(i++);
                matrizFlores[par[0]][par[1]] = new Flor(par[0],par[1]);
            } 
            arrPares.remove(index);
            max = arrPares.size();
            pob--;
        }
        
    }
    
    //obtengo todas las posiciones disponibles
    private ArrayList <int[]> getAllPosititions(){
        ArrayList <int[]> array = new ArrayList ();
        for (int i = 0; i < matrizFlores.length; i++) {
            for (int j = 0; j < matrizFlores[i].length; j++) {
                if ( ! (matrizFlores[i][j] instanceof Panal)
                 &&! (matrizFlores[i][j] instanceof Flor)){
                    int [] par = {j,i};
                    array.add(par);
                }
            } 
        }
        return array;
    }
    
    //crea casillas en todas las posiciones
    private void inicializarMatriz(){
        for (int i = 0; i < matrizFlores.length; i++) {
            for (int j = 0; j < matrizFlores[i].length; j++) {
                matrizFlores[i][j] = new Casilla();
            }
        }
    }
    
    public void siguienteGeneracion(){
        historia.addGeneracion(this.matrizFlores,panal.getAbejasList());
        reproducir();
    }
    
    public void reproducir(){
        //acá recorre todas las flores y las reproduce
        
        reproducirFlores();
        panal.reproducir();
        //acá llama al panal y reproduce las abejas
    }
    

    public Panal getPanal() {
        return panal;
    }

    public void setPanal(Panal panal) {
        this.panal = panal;
    }

    public Casilla[][] getMatrizFlores() {
        return matrizFlores;
    }

    public void setMatrizFlores(Casilla[][] matrizFlores) {
        this.matrizFlores = matrizFlores;
    }
    
    //se encarga de reproducir las dlores
    public void reproducirFlores(){
        ArrayList<String> matrizCromosomas = getCromosomasFlores();
        int mutaciones = getMutaciones();
        if (mutaciones != 0){
            asignarMutaciones(matrizCromosomas, mutaciones);
        }
        asignarNuevaGeneracion(matrizCromosomas);
        
    }
    
    //dada la matriz de cromosomas crea todas las nuevas flores
    private void asignarNuevaGeneracion(ArrayList<String> matrizCromosomas) {
        for (int i = 0; i < matrizFlores.length; i++) {
            for (int j = 0; j < matrizFlores[i].length; j++) {
                if (matrizFlores[i][j] instanceof Flor){
                    Color color = Utilidades.getColor(matrizCromosomas.get(0)).getColor();
                    Point punto = new Point(j,i);
                    matrizFlores[i][j] = new Flor(punto, color);
                    matrizCromosomas.remove(0);
                }
            }
        }
        
    }
    
    //dada la cantidad de mutaciones las asigna a los bits
    private void asignarMutaciones (ArrayList<String> matrizCromosomas, int cantidadMutaciones){
        //todas las posiciones donde su mutara
        ArrayList<int[]> posiciones = getAllPositions(matrizCromosomas.get(0).length(), matrizCromosomas.size());
        int index;
        int[] par;
        while (cantidadMutaciones>0){
            index = Utilidades.rand.nextInt(posiciones.size());
            par = posiciones.get(index);
            mutar (matrizCromosomas, par);
            posiciones.remove(index);  
            cantidadMutaciones--;
        }
    }
    
    //aplica la mutación en donde se indica en el par
    //mutacion pasa de 0 a 1 o al revez
    private void mutar(ArrayList<String> matrizCromosomas, int[] par){
        String cromosoma = matrizCromosomas.get(par[1]);
        int index = par [0];
        cambiarBit(cromosoma, index);
        
        matrizCromosomas.set(par[1], cromosoma);
        
    }
    
    //dado un indice cambia de 0 a 1 en el string
    private void cambiarBit(String cromosoma, int index){
        Character letra = cromosoma.charAt(index);
        String nueva;
        if (letra.equals('0')){
            nueva = "1";
        }
        else {//es '1'
            nueva = "0";   
        }
        cromosoma = cromosoma.substring(0, index) 
                    + nueva
                    + cromosoma.substring(index + 1); 
    }
    
    
    //dado dos dimensiones
    //retorna un arraylist con toda la cantidad de pares dentro
    private ArrayList<int[]> getAllPositions (int x, int y){
        ArrayList<int[]> pares = new ArrayList();
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                int [] par = {j,i};
                pares.add(par);
            }
        }
        return pares;
    }
    
    //me da un numero random para la cantidad de mutaciones
    private int getMutaciones(){
        return Utilidades.rand.nextInt(Utilidades.maxMutaFlor);
    }
    
    
    //me da una matriz con los cromosomas de todas las flores
    //los cromosomas ya están cruzados
    public ArrayList<String> getCromosomasFlores(){
        
        ArrayList<String> matrizCromosomas = new ArrayList();
        Flor flor;
        
        for (int i = 0; i < matrizFlores.length; i++) {
            for (int j = 0; j < matrizFlores[i].length; j++) {
                if (matrizFlores[i][j] instanceof Flor){
                    flor = (Flor) matrizFlores[i][j];
                    matrizCromosomas.add(flor.getCromosoma());
                }
            }
        }
        return matrizCromosomas;
        
    }




   
    
    
}
