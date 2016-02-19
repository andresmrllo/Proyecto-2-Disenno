package Utilidades;

import java.util.*;

/**
 * 
 */
public class DTO_Cuenta {

    private int numCuenta;
    private String pin;
    private Date fechaCreacion;
    private String status;
    private String moneda;
    private double saldo;
    private int cantTransaccionesCuenta;
    private String claseLlamar;
    private int idCliente;
    
    /**
     * Default constructor
     */
    public DTO_Cuenta() {
    }
    
    public void setNumCuenta(int n) {
        // TODO implement here
    }

    /**
     * @return
     */
    public int getNumCuenta() {
        // TODO implement here
        return 0;
    }

    /**
     * @param p
     */
    public void setPin(String p) {
        // TODO implement here
    }

    /**
     * @return
     */
    public String getPin() {
        // TODO implement here
        return "";
    }

    /**
     * @param f
     */
    public void setFechaCreacion(Date f) {
        // TODO implement here
    }

    /**
     * @return
     */
    public Date getFechaCreacion() {
        // TODO implement here
        return null;
    }

    /**
     * @param s
     */
    public void setStatus(String s) {
        // TODO implement here
    }

    /**
     * @return
     */
    public String getStatus() {
        // TODO implement here
        return "";
    }

    /**
     * @param m
     */
    public void setMoneda(String m) {
        // TODO implement here
    }

    /**
     * @return
     */
    public String getMoneda() {
        // TODO implement here
        return "";
    }

    /**
     * @param s
     */
    public void setSaldo(double s) {
        // TODO implement here
    }

    /**
     * @return
     */
    public double getSaldo() {
        // TODO implement here
        return 0.0d;
    }

    /**
     * @param n
     */
    public void setCantTransaccionesCuenta(int n) {
        // TODO implement here
    }

    /**
     * @return
     */
    public int getCantTransaccionesCuenta() {
        // TODO implement here
        return 0;
    }

    public String getNombreClase() {
        return claseLlamar;
    }
    
    public void setNombreClase(String nombreClase) {
        claseLlamar = nombreClase;
    }
    
    public void setIdCliente(int id) {
        this.idCliente = id;
    }

    public int getIdCliente() {
        return this.idCliente;
    }

}