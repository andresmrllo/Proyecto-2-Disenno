package Modelo;

import Utilidades.DTO_Cliente;
import Utilidades.DTO_Cuenta;
import Utilidades.DTO_Sistema;

/**
 * 
 */
public interface IMensaje {

    /**
     * @param dto_Cuenta
     * @param dto_Cliente 
     * @return
     */
    public DTO_Sistema enviar(DTO_Cuenta dto_Cuenta, DTO_Cliente dto_Cliente);

}