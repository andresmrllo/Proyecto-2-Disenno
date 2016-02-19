package Modelo;

import java.util.*;

/**
 * 
 */
public interface IFabricaArchivo {

    /**
     * @param dto 
     * @return
     */
    public IXml registrarTransaccion(ArrayList<Object> dto);

}