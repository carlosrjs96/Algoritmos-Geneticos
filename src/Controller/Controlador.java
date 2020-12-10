/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.Mapa;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Carlos
 */

//hacer funcion para que cargue flores al inicio

public class Controlador implements ActionListener{
    
    private Mapa mapa;
    
    public Controlador (){
        this.mapa = new Mapa();
        this.mapa.setVisible(true);
        _init_();
        
    }
    
    private void _init_() {
        this.mapa.btnCargar.addActionListener(this);
        this.mapa.btnEmpezar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.mapa.btnCargar)){
            System.out.println(mapa.getDimension());
            this.mapa.setVisible(false);
            mapa.createCampo();
            this.mapa.setVisible(true);
            //
        }
        else if (e.getSource().equals(this.mapa.btnEmpezar)){
            
        }
    }

    
    
}
