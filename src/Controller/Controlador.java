/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Abeja;
import Model.Campo;
import Model.Casilla;
import Model.Flor;
import Model.Panal;
import View.Mapa;
import View.VistaAbeja;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Carlos
 */

//hacer funcion para que cargue flores al inicio

public class Controlador implements ActionListener{
    
    private Mapa mapa;
    private Campo campo;
    private VistaAbeja vistaAbeja;
    
    public Controlador (){
        this.mapa = new Mapa();
        this.campo = new Campo();
        this.vistaAbeja = new VistaAbeja();
        
        this.mapa.setVisible(true);
        
        _init_();
        
    }
    
    private void _init_() {
        //mapa------------------
        this.mapa.btnCargar.addActionListener(this);
        this.mapa.btnEmpezar.addActionListener(this);
        this.mapa.btnAnterior.addActionListener(this);
        this.mapa.btnSiguiente.addActionListener(this);
        this.mapa.btnVerAbejas.addActionListener(this);
        this.mapa.btnVerGen.addActionListener(this);
        //abejas--------------------------
        this.vistaAbeja.btnCargar.addActionListener(this);
        this.vistaAbeja.btnVerAnt1.addActionListener(this);
        this.vistaAbeja.btnVerAnt2.addActionListener(this);
        this.vistaAbeja.btnCerrar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.mapa.btnCargar)){
            mapa.createCampo();
            this.campo.iniciarTodo(mapa.getDimension(), mapa.getPobFlores(), mapa.getPobAbejas(), mapa.getNumGeneraciones());
            //
            update();
        }
        else if (e.getSource().equals(this.mapa.btnEmpezar)){
            this.campo.simular();
            Color[][] m = this.campo.getHistoria().getGeneracion().getFloresGeneraciones();
            updateMapa(m);
        }
        else if(e.getSource().equals(this.mapa.btnAnterior)){
            this.campo.getHistoria().decIndex();
            Color[][] m = this.campo.getHistoria().getGeneracion().getFloresGeneraciones();
            updateMapa(m);
        }
        else if (e.getSource().equals(this.mapa.btnSiguiente)){
            this.campo.getHistoria().incIndex();
            Color[][] m = this.campo.getHistoria().getGeneracion().getFloresGeneraciones();
            updateMapa(m);
        }
        else if (e.getSource().equals(this.mapa.btnVerGen)){
            int num = Integer.parseInt(this.mapa.txtGenActual.getText());
            this.campo.getHistoria().setIndex(num);
            Color[][] m = this.campo.getHistoria().getGeneracion().getFloresGeneraciones();
            updateMapa(m);
        }
        else if (e.getSource().equals(this.mapa.btnVerAbejas)){
            int maxAbejas = this.campo.getHistoria().getGeneracion().getAbejasGeneraciones().size();
            int gen = this.campo.getHistoria().getIndex();
            this.vistaAbeja.loadCmbAbejas(maxAbejas, gen);
            this.vistaAbeja.setVisible(true);
        }
        //Abeja
        else if (e.getSource().equals(this.vistaAbeja.btnCargar)){
            int numAbeja = Integer.parseInt(String.valueOf(this.vistaAbeja.cmbAbejas.getSelectedItem()));
            Abeja abeja = this.campo.getHistoria().getGeneracion().getAbejasGeneraciones().get(numAbeja);
            this.vistaAbeja.loadAbeja(abeja);
        }
        else if (e.getSource().equals(this.vistaAbeja.btnVerAnt1)){
            int numAbeja = Integer.parseInt(String.valueOf(this.vistaAbeja.cmbAbejas.getSelectedItem()));
            Abeja abeja = this.campo.getHistoria().getGeneracion().getAbejasGeneraciones().get(numAbeja);
            if (this.vistaAbeja.loadAntecesor(abeja,0,0)){//si la setea 
                //reduce el indece de generacion
                this.campo.getHistoria().decIndex();
                //recarga cmboBox
                //****no muy necesario pues es con indice = tamños poblacion
                //y este es el mismo siempre
                /*int maxAbejas = this.campo.getHistoria().getGeneracion().getAbejasGeneraciones().size();
                this.vistaAbeja.loadCmbAbejas(maxAbejas);*/
            }
        }
        else if (e.getSource().equals(this.vistaAbeja.btnVerAnt2)){
            int numAbeja = Integer.parseInt(String.valueOf(this.vistaAbeja.cmbAbejas.getSelectedItem()));
            Abeja abeja = this.campo.getHistoria().getGeneracion().getAbejasGeneraciones().get(numAbeja);
            if (this.vistaAbeja.loadAntecesor(abeja,1,0)){//si la setea 
                //reduce el indece de generacion
                this.campo.getHistoria().decIndex();
                //recarga cmboBox
                //****no muy necesario pues es con indice = tamños poblacion
                //y este es el mismo siempre
                /*int maxAbejas = this.campo.getHistoria().getGeneracion().getAbejasGeneraciones().size();
                this.vistaAbeja.loadCmbAbejas(maxAbejas);*/
            }
        }
        else if (e.getSource().equals(this.vistaAbeja.btnCerrar)){
            this.vistaAbeja.setVisible(false);
        }
        
    }

    
    private void update(){
        updateMapa();
    }
    
    private void updateMapa(){
        Casilla [][] casillas = campo.getMatrizFlores();
        Flor flr;
        int dim = casillas.length;
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                //mapa.pnlArray[i][j].set
                if (casillas[i][j] instanceof Panal){
                    mapa.pnlArray[i][j].setBackground(Color.BLACK);
                    
                }
                else if (casillas[i][j] instanceof Flor){
                    flr = (Flor) casillas[i][j];
                    mapa.pnlArray[i][j].setBackground(flr.getColor());     
                }
                else {//casilla normal
                    mapa.pnlArray[i][j].setBackground(Color.WHITE);
                }
            }
            
        }
    }
    
    
    private void updateMapa(Color[][] matrizFlores){
        int numGen = this.campo.getHistoria().getIndex();
        this.mapa.txtGenActual.setText(Integer.toString(numGen));
        double adapt = this.campo.getHistoria().getGeneracion().getPromedioAdaptabilidad();
        this.mapa.lblAdaptabilidad.setText(Double.toString(adapt));
        for (int i = 0; i < matrizFlores.length; i++) {
            for (int j = 0; j < matrizFlores[i].length; j++) {
                mapa.pnlArray[i][j].setBackground(matrizFlores[i][j]);
            }
        }
    }
    
    
}
