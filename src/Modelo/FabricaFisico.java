package Modelo;

import Utilidades.DTO_Cliente;

/**
 * 
 */
public class FabricaFisico extends FabricaCliente {

    /**
     * Default constructor
     */
    public FabricaFisico() {
    }

    /**
     * @param dto
     * @return
     */
    @Override
    public Cliente fabricarCliente(DTO_Cliente dto) {
        // TODO implement here
        return new Fisico(dto);
    }

}