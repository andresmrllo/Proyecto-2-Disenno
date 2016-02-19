/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

/**
 *
 * @author andre_000
 */
public class GeneradorPalabras 
{
    public static String generarPalabra()
    { 
        String palabra = "";
       
        int r =(int) (Math.random()*(7-(4-1)) + 4);
         for(int i=1; i<=r; i++)
        {
            palabra += (char)Math.floor(Math.random()*(90-65+1)+65);
        }
        return palabra;
    }
}
