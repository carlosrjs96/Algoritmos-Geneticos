/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgoritmosGeneticos;

import Controller.Controlador;
import Model.Utilidades;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Jarod
 */
public class AlgoritmosGeneticos {
    public static void main(String[] args) {
        new Controlador();

        BigInteger bigNum = new BigInteger("360");
        String str = bigNum.toString(2);
        int num = 8 - str.length();
        for (int i = 0; i < num; i++) {
            str = "0" + str;
        }
System.out.println( str );

    }
}
