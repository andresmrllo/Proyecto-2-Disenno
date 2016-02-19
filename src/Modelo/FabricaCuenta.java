package Modelo;

import Utilidades.DTO_Cuenta;

/**
 * 
 */
public abstract class FabricaCuenta implements IFabricaCuenta {

    /**
     * Default constructor
     */
    public FabricaCuenta() {
    }

    /**
     * @param dto
     * @return
     */
    @Override
    public abstract Cuenta fabricarCuenta(DTO_Cuenta dto);

}