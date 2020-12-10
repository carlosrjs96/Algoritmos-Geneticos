/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Carlos
 */
public class Campo {
    private Panal panal;
    private Casilla[][] matrizFlores ;
    private int probCrearFlor;
    
    public Campo (int dimension, int pobFlores) {
        this.panal = new Panal();
        this.matrizFlores = new Casilla [dimension][dimension];
        this.probCrearFlor = 50;
        setPanal();
        cargarFlores(pobFlores);
        
    }
    
    //posiciona al panal en el centro
    private void setPanal(){
        int pos = matrizFlores.length;
        matrizFlores[pos][pos] = panal;
    }
    
    //crea y posiciona las flores en el tablero
    private void cargarFlores (int pob) {
        int dim = matrizFlores.length;
        int maxFlores = (dim * dim)-1;//restar el panal
        int contador = 0;
        int x = 0;
        int y = 0;
        int valor;
        //esto para que no se exceda la cantidad de flores a insertar
        if (pob > maxFlores) {
            pob = maxFlores;
        }
        
        //hacer un primer recorrido random
        while (pob>0){
            x = Utilidades.rand.nextInt(dim);
            y = Utilidades.rand.nextInt(dim);
            valor = Utilidades.rand.nextInt(100);
            if ( ! (matrizFlores[y][x] instanceof Panal)
                 &&! (matrizFlores[y][x] instanceof Flor)){
                    if (valor<this.probCrearFlor){
                         matrizFlores[y][x] = new Flor(x,y);
                         pob--;
                    } 
            }
            contador ++;
            //esto para detener en caso de exceos
            if (contador > maxFlores+1.5){
                break;
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

   
    
    
}
