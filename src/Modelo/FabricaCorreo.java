package Modelo;

import java.util.*;

/**
 * 
 */
public class FabricaCorreo extends FabricaMensaje {

    /**
     * Default constructor
     */
    public FabricaCorreo() {
    }

    /**
     * @param dto
     * @return 
     */
    @Override
    public IMensaje CrearMensaje(Object dto) 
    {
        return new Correo();
    }

}