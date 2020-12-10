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
    //ir guardando cada matiz de colores de flores
    
    //private int probCrearFlor;
    
    public Campo(){
        
    }
    
    public Campo (int dimension, int pobFlores) {
        this.panal = new Panal();
        this.matrizFlores = new Casilla [dimension][dimension];
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
    
    public void reproducir(){
        //acá recorre todas las flores y las reproduce
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


   
    
    
}
