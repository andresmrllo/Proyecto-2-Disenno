package Modelo;

import Utilidades.DTO_Cliente;

/**
 * 
 */
public abstract class FabricaCliente implements IFabricaCliente {

    /**
     * Default constructor
     */
    public FabricaCliente() {
    }

    /**
     * @return
     */
    @Override
    public abstract Cliente fabricarCliente(DTO_Cliente dto);
}