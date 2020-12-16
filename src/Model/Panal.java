/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Carlos
 */
public class Panal extends Casilla{
    private ArrayList<Abeja> abejasList ;
    private int poblacion;
    
    public Panal() {
        abejasList = new ArrayList<Abeja>();
    }
    
    public void crearAbejas (int pobAbejas){
        poblacion = pobAbejas;
        for (int i = 0; i < pobAbejas; i++) {
            abejasList.add(new Abeja(this.point, i));
        }
    }

    public ArrayList<Abeja> getAbejasList() {
        return abejasList;
    }

    public void setAbejasList(ArrayList<Abeja> abejasList) {
        this.abejasList = abejasList;
    }
    
    public void reproducir (){
        generarIndice();
        //estos osn numeros random entre 0 -1
        //para tomar las abejas que se reproduciran
        ArrayList<Abeja[]> parejas = asignarParejas();
        System.out.println("cantidad parejas abejas : " + parejas.size());
        System.out.println("parejas " + abejasList.size()/2 + " = " + parejas.size());
        ArrayList<String[]> strParejas = convertirABits(parejas);
        cruzarCromosomas (strParejas);
        mutarParejas(strParejas);
        crearNuevaGeneracion(strParejas, parejas);
        
        
        
    }
    
    public void mutarParejas(ArrayList<String[]> strParejas){
        System.out.println("deberian ser 2 : " + strParejas.size());
        ArrayList<String> str = new ArrayList();
        for (int i = 0; i < strParejas.size(); i++) {
            for (int j = 0; j < strParejas.get(i).length; j++) {
                str.add(strParejas.get(i)[j]);
            }
        }
        System.out.println("cant. crom : " + str.size());
        
        asignarMutaciones (str, getMutaciones());
        
        //cargar str al formato de strparejas
        
        
        
        
    }
    
    //******************************************************************************************************
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
        return Utilidades.rand.nextInt(Utilidades.maxMutaAbeja);
    }
    //******************************************************************************************************
    
    
    //crea la nueva generacion
    //ambos arraylist son homologos
    private void crearNuevaGeneracion(ArrayList<String[]> strParejas, ArrayList<Abeja[]> parejas){
        ArrayList<Abeja> nuevaGeneracion = new ArrayList();
        for (int i = 0; i < strParejas.size(); i++) {
            for (int j = 0; j < strParejas.get(i).length; j++) {
                nuevaGeneracion.add(new Abeja(strParejas.get(i)[j], parejas.get(i)));
            }
        }
        this.abejasList = nuevaGeneracion;
    }
    
    //se puede hacer en UTILIDADES
    //porque las flores hacen lo mismo
    
    //hace el cruce de cromosomas
    private void cruzarCromosomas(ArrayList<String[]> strParejas){
        System.out.println("parejas");
        for (int i = 0; i < strParejas.size(); i++) {
            for (int j = 0; j < strParejas.get(i).length; j++) {
                System.out.println(strParejas.get(i)[j]);
            }
        }
        
        for (int i = 0; i < strParejas.size(); i++) {
            String [] strPar = strParejas.get(i);
            System.out.println("cruzar");
            System.out.println(strParejas.get(i)[0].length());
            
            int index = Utilidades.rand.nextInt(strParejas.get(i)[0].length());
            String [] cortePrimero = Utilidades.cortarCromosoma(strPar[0], index);
            String [] corteSegundo = Utilidades.cortarCromosoma(strPar[1], index);
            
            String bitsCruzadosPrimero = "";
            String bitsCruzadosSegundo = "";
            
            //cruzar cromosomas
            bitsCruzadosPrimero = bitsCruzadosPrimero.concat(cortePrimero[0]).concat(corteSegundo[1]);
            bitsCruzadosSegundo = bitsCruzadosSegundo.concat(corteSegundo[0]).concat(cortePrimero[1]);
            
            strPar[0] = bitsCruzadosPrimero;
            strPar[1] = bitsCruzadosSegundo;
            
            strParejas.set(i, strPar);
        }
    }
    
    private ArrayList<String[]> convertirABits(ArrayList<Abeja[]> parejas){
        ArrayList<String[]> strParejas = new ArrayList();
        for (int i = 0; i < parejas.size(); i++) {
            String [] strPar = new String[2];
            for (int j = 0; j < parejas.get(i).length; j++) {
                strPar[j] = parejas.get(i)[j].aBits();
            }
            strParejas.add(strPar);
        }
        return strParejas;
    }
    
    
   
    
    
    //
    public ArrayList<Abeja[]> asignarParejas(){
        //es el array que se va creando
        ArrayList<Abeja[]> parejas = new ArrayList();
        //este es homologo a abejasList
        //para abejasList.get(0) indica la cantidad de veces que se
        //reproducira en cantidadAbejas.get(0)
        ArrayList<Integer> cantidadAbeja = seleccionarAbejas();
        for (int i = 0; i < cantidadAbeja.size(); i++) {
            System.out.println(i + " " + cantidadAbeja.get(i));
        }
        //me retorna los indice de cantidadAbeja que son distintos de 0
        ArrayList <Integer> indices = getIndices (cantidadAbeja);
        //generar random en indices dos veces para obtener la pareja
        while(!indices.isEmpty()){//mientra hayan indices
            Abeja[] par = new Abeja[2];
            for (int i = 0; i < 2; i++) {
                //genero un indice random para el indice de cantidad abeja
                int num = Utilidades.rand.nextInt(indices.size());
                //lo seteo en la posicion
                par[i] = abejasList.get(indices.get(num));
                //lo resto para no ir repitiendo las abejas que no deban
                int veces = cantidadAbeja.get(indices.get(num)) - 1;
                //si es cero se debe de eliminar de indices
                if (veces == 0){
                    indices.remove(num);
                }
                else{//en otro caso actualizo el valor de veces
                    cantidadAbeja.set(indices.get(num), veces);
                }
            }
            parejas.add(par);
        }
        return parejas;
    }
    
    //retorna los indices donde cant!= 0
    private ArrayList <Integer> getIndices (ArrayList <Integer> cantAbejas){
        ArrayList <Integer> indices = new ArrayList();
        for (int i = 0 ; i < cantAbejas.size(); i++){
            if (cantAbejas.get(i)!=0) {
                indices.add(i);
            }
        }
        return indices;
    }
    
    //selecciona las abejas que se reproduciran y las veces
    //la cantidad total tiene quqe ser igual
    //a la poblacion
    private ArrayList<Integer> seleccionarAbejas(){
        //este es un espejo de abejasList
        //lleva la cantidad de veces que se reproduce cada abeja
        ArrayList<Integer> cantidadAbeja = new ArrayList();
        //setea numeros randoms entre 0 -1 
        //la cantidad de numeros es la poblacion de abejas
        ArrayList<Double> randoms = getRandoms();
        System.out.println("# randoms : " + randoms.size());
        Collections.sort(randoms);
        
        double sumas = 0.0;
        for (int i = 0; i < randoms.size(); i++){
            sumas += abejasList.get(i).getIndiceNormalizado();
            cantidadAbeja.add(getVecesDentro(randoms,sumas));
        }
        
        /*for (Abeja abeja : abejasList){
            System.out.println("indice " + abeja.getIndiceNormalizado() );
            sumas += abeja.getIndiceNormalizado();
            System.out.println("# randoms : " + randoms.size());
            cantidadAbeja.add(getVecesDentro(randoms, sumas));
        }*/
        return cantidadAbeja;
    }
    
    //cantidad de veces que se reproducira la abeja
    private Integer getVecesDentro(ArrayList<Double> randoms, double techo) {
        int veces = 0;
        System.out.println("# randoms : " + randoms.size());
        System.out.println("techo : " + techo);
        if (randoms.isEmpty()){
            return 0;
        }
        System.out.println(randoms.get(0));
        while(randoms.get(0) < techo){
            if (randoms.get(0)< techo){
                veces++;
                randoms.remove(0);
            }
                
        }
        return veces;
    }
    
    //generar numeros random entre 0 - 1
    private ArrayList<Double> getRandoms(){
        ArrayList<Double> randoms = new ArrayList();
        int num = 10000;
        for (int i = 0; i < this.poblacion; i++) {
            Double dbl = (double) Utilidades.rand.nextInt(num)/num;
            randoms.add(dbl);
        }
        return randoms;
    }
    
    //asigan los indices de adaptabilidad
    private void generarIndice(){
        double sumaIndices = 0.0;
        for (Abeja abeja: abejasList) {
            abeja.asignarAdaptabilidad();
            sumaIndices += abeja.getIndiceAdaptibilidad();
        }
        for (Abeja abeja: abejasList) {
            abeja.asignarAdaptabilidadNormalizada(sumaIndices);
        }
    }

    public void moverAbejas( ArrayList<Flor> flores){
        for (Abeja abeja : this.abejasList) {
            System.out.println("abeja " + abeja.getNumAbeja());
            abeja.getMover().mover(abeja, this, flores);
        }
    }
    
    
    
}
