package Modelo;

import Utilidades.DTO_Cliente;
/**
 * 
 */
public interface IFabricaCliente {

    /**
     * @param dto
     * @return
     */
    public Cliente fabricarCliente(DTO_Cliente dto);

}