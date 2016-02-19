package Modelo;

import java.util.*;

/**
 * 
 */
public class FabricaXmlInstancias extends FabricaArchivo {

    /**
     * Default constructor
     */
    public FabricaXmlInstancias() {
    }

    /**
     * 
     */
    public void FabricaXmlInstancias() {
        // TODO implement here
    }

    @Override
   public IXml registrarTransaccion(ArrayList<Object> dto) {
        return new XmlCliente(dto);
   }

}