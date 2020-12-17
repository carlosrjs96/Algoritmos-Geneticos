/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Campo;
import Model.Casilla;
import Model.Flor;
import Model.Panal;
import View.Mapa;
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
    
    public Controlador (){
        this.mapa = new Mapa();
        this.campo = new Campo();
        
        this.mapa.setVisible(true);
        
        _init_();
        
    }
    
    private void _init_() {
        this.mapa.btnCargar.addActionListener(this);
        this.mapa.btnEmpezar.addActionListener(this);
        this.mapa.btnAnterior.addActionListener(this);
        this.mapa.btnSiguiente.addActionListener(this);
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
