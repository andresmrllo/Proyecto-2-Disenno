package Modelo;

import java.util.ArrayList;

/**
 * 
 */
public class FabricaXmlCliente extends FabricaArchivo {

    /**
     * Default constructor
     */
    public FabricaXmlCliente() {
    }

    @Override
    public IXml registrarTransaccion(ArrayList<Object> dto) {
        return new XmlCliente(dto);
    }
}