package Modelo;

import Utilidades.DTO_Cliente;
import java.util.*;

/**
 * 
 */
public abstract class Cliente {

    
    private static int cantInstancias = 0;
    private String nombre;
    private String email;
    private int telefono;
    private ArrayList<Cuenta> cuentasCliente;
    
    /**
     * Default constructor
     * @param dto
     */
    public Cliente(DTO_Cliente dto) {
        cantInstancias+=1;
        System.out.println("Cliente creado");
    }

    /**
     * @param cta
     */
    public void setCuenta(Cuenta cta) {
        cuentasCliente.add(cta);
    }

}