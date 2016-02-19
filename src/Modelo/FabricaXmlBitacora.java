package Modelo;

import java.util.ArrayList;

/**
 * 
 */
public class FabricaXmlBitacora extends FabricaArchivo {

    /**
     * Default constructor
     */
    public FabricaXmlBitacora() {
    }

    /**
     * @param dto 
     * @return
     */


    @Override
    public IXml registrarTransaccion(ArrayList<Object> dto) {
        return new XmlBitacora(dto);
    }

}