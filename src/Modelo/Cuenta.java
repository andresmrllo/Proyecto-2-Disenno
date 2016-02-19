package Modelo;

import Utilidades.DTO_Cuenta;
import java.util.*;

/**
 * 
 */
public abstract class Cuenta {

    private static int cantInstancias = 0;
    private Date fechaCreaccion;
    private String status = "activa";
    private String moneda = "Colones";
    private int saldo;
    private String pin;
    private int cantTransacciones = 0;
    private Cliente cliente ;
    
    /**
     * Default constructor
     * @param dto
     */
    public Cuenta(DTO_Cuenta dto) 
    {
        System.out.println("Cuenta creada");
    }

    /**
     * @param dto 
     * @return
     */
    public double deposito(Object dto) {
        // TODO implement here
        return 0.0d;
    }

    /**
     * @param dto 
     * @return
     */
    public double retiro(Object dto) {
        // TODO implement here
        return 0.0d;
    }

    /**
     * @param dto 
     * @return
     */
    public double calcularComision(Object dto) {
        // TODO implement here
        return 0.0d;
    }

    /**
     * @param dto 
     * @return
     */
    public boolean validarComision(Object dto) {
        // TODO implement here
        return false;
    }
     public void setCliente(Cliente clt)
     {
         cliente = clt;
     }
}