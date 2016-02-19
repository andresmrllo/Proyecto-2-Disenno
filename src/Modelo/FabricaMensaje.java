package Modelo;

import java.util.*;

/**
 * 
 */
public abstract class FabricaMensaje implements IFabricaMensaje {

    /**
     * Default constructor
     */
    public FabricaMensaje() {
    }

    /**
     * @param dto 
     * @return
     */
    @Override
    public abstract IMensaje CrearMensaje(Object dto);

}