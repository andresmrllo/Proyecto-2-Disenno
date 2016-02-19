package Modelo;

import Utilidades.DTO_Cliente;
import Utilidades.DTO_Cuenta;
import Utilidades.DTO_Sistema;
import Utilidades.DTO_Transaccion;
import java.util.ArrayList;

/**
 * 
 */
public class XmlBitacora implements IXml {

    DTO_Cuenta dtoCuenta;
    DTO_Cliente dtoCliente;
    DTO_Sistema dtoSistema;       
    DTO_Transaccion dtoTransaccion;
    
    public XmlBitacora(ArrayList<Object> dto) {
        //dtoTransaccion = (DTO_Transaccion) dto;
        dtoCliente = (DTO_Cliente)dto.get(0);
        dtoSistema = (DTO_Sistema)dto.get(1);
        dtoCuenta = (DTO_Cuenta)dto.get(2);
        dtoTransaccion = (DTO_Transaccion)dto.get(3);
    }

    /**
     */


    /**
     * 
     */
    @Override
    public void agregarRegistro() {
        // TODO implement here
        System.out.println("Registro agregado");
    }

    /**
     * 
     */
    @Override
    public void actualizarDocumento() {
        // TODO implement here
        System.out.println("Documento agregado");
    }


    /**
     * @return
     */
    @Override
    public ArrayList<Object> consultarRegistros() {
        // TODO implement here
        System.out.println("Registros consultados");
        return null;
    }

}