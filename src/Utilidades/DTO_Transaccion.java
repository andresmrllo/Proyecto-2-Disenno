package Utilidades;

import java.util.*;

/**
 * 
 */
public class DTO_Transaccion {

    /**
     * Default constructor
     */
    public DTO_Transaccion() {
    }

    /**
     Declaracion de Atributos
     */
    private int cantTransacciones;
    private String tipoTransaccion;
    private String claseLlamar;
    private double comision;
    private double montoTransaccion;
    private int numeroCuenta;
    private Date fechaTransaccion;
    private double compraDolar;
    private double ventaDolar;
    private String fechaFinal;
    private String fechaInicio;
    private String indicadorEconomico;

    /**
     * @param n
     */
    public void setCantTransacciones(int n) {
        // TODO implement here
    }

    /**
     * @return
     */
    public int getCantTransacciones() {
        // TODO implement here
        return 0;
    }

    /**
     * @param t
     */
    public void setTipoTransaccion(String t) {
        // TODO implement here
    }

    /**
     * @return
     */
    public String getTipoTransaccion() {
        // TODO implement here
        return "";
    }

    /**
     * @param c
     */
    public void setComision(double c) {
        // TODO implement here
    }

    /**
     * @return
     */
    public double getComision() {
        // TODO implement here
        return 0.0d;
    }

    /**
     * @param m
     */
    public void setMonto(double m) {
        // TODO implement here
    }

    /**
     * @return
     */
    public double getMonto() {
        // TODO implement here
        return 0.0d;
    }

    /**
     * @param n
     */
    public void setNumeroCuenta(int n) {
        // TODO implement here
    }

    /**
     * @return
     */
    public int getNumeroCuenta() {
        // TODO implement here
        return 0;
    }

    /**
     * @param f
     */
    public void setFechaTransaccion(Date f) {
        // TODO implement here
    }

    /**
     * @return
     */
    public Date getFechaTransaccion() {
        // TODO implement here
        return null;
    }

    /**
     * @param c
     */
    public void setCompraDolar(double c) {
        compraDolar = c;
    }

    /**
     * @return
     */
    public double getCompraDolar() {
        // TODO implement here
        return compraDolar;
    }

    /**
     * @param v
     */
    public void setVentaDolar(double v) {
        ventaDolar = v;
    }

    /**
     * @return
     */
    public double getVentaDolar() {
        // TODO implement here
        return ventaDolar;
    }
    
    public void setNombreClase(String nombreClase) {
        claseLlamar = nombreClase;
    }
        
    public String getNombreClase() {
       return claseLlamar;
    }
    
     public void setFechaInicio(String fecha)
    {
        fechaInicio = fecha;
    }
    
    public String getFechaInicio()
    {
        return fechaInicio ;
    }
    
    public void setFechaFinal(String fecha)
    {
        fechaFinal = fecha;
    }
    
    public String getFechaFinal()
    {
        return fechaFinal ;
    }
    
    public void setIndicadorEconomico(String i)
    {
        indicadorEconomico = i;
    }
    
    public String getIndicadorEconomico()
    {
        return indicadorEconomico;
    }

}