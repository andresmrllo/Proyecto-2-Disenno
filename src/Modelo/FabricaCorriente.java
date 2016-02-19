package Modelo;

import Utilidades.DTO_Cuenta;

/**
 * 
 */
public class FabricaCorriente extends FabricaCuenta {

    /**
     * Default constructor
     */
    public FabricaCorriente() {
    }

    /**
     * @return
     */
    @Override
    public Cuenta fabricarCuenta(DTO_Cuenta dto) {
        // TODO implement here
        return new Corriente(dto);
    }

}