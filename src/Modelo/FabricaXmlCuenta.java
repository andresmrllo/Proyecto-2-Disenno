package Modelo;

import java.util.*;

/**
 * 
 */
public class FabricaXmlCuenta extends FabricaArchivo {

    /**
     * Default constructor
     */
    public FabricaXmlCuenta() {
    }

    /**
     * 
     */
    public void FabricaXmlCuenta() {
        // TODO implement here
    }

    /**
     * @param dto 
     * @return
     */

    @Override
    public IXml registrarTransaccion(ArrayList<Object> dto) {
        return new XmlCuenta(dto);
    }

}