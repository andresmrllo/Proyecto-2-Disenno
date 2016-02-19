package Modelo;

import java.util.ArrayList;

/**
 * 
 */
public abstract class FabricaArchivo implements IFabricaArchivo {

    /**
     * Default constructor
     */
    public FabricaArchivo() {
    }

    
    @Override
    public abstract IXml registrarTransaccion(ArrayList<Object> dto);

}