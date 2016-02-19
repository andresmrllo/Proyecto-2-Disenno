package Modelo;

import Utilidades.DTO_Cuenta;

/**
 * 
 */
public interface IFabricaCuenta {

    /**
     * @param dto
     * @return
     */
    public Cuenta fabricarCuenta(DTO_Cuenta dto);

}