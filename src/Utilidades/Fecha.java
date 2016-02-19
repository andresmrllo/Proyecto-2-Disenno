/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author andre_000
 */
public class Fecha {
    
    public static String getFechaActual() 
    {
        Calendar calendar = Calendar.getInstance();
        String fechaActual = calendar.get(Calendar.DATE) 
                + "/" + (calendar.get(Calendar.MONTH) + 1) 
                + "/" + calendar.get(Calendar.YEAR);
        return fechaActual;
    }
    
    public static String getFechaActualhora() 
    {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
