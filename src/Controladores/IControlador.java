package Controladores;

import Utilidades.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * 
 */
public interface IControlador 
{
    public String crearCliente(DTO_Cliente dto)throws InvocationTargetException;
    
    public String crearCuenta(DTO_Cuenta dto) throws InvocationTargetException, NoSuchMethodException;
    
    public void transferencia(Object dto);
    
    public String depositarDinero(DTO_Cuenta dto);
    
    public String retirarDinero(DTO_Cuenta dto);
    
    public boolean verificarCuenta(DTO_Cuenta dto) throws NoSuchMethodException;
    
    public DTO_Sistema enviarMensaje(DTO_Cliente dto_Clt, DTO_Cuenta dto_Cta);
    
    public DTO_Transaccion conectarWebService(DTO_Transaccion dto) throws ClassNotFoundException;
    
    public void agregarRegistroXmlBitacora(ArrayList<Object> dto) throws InvocationTargetException;
    
    public void agregarRegistroXmlCliente(ArrayList<Object> dto) throws InvocationTargetException;
    
    public void agregarRegistroXmlCuenta(ArrayList<Object> dto) throws InvocationTargetException;
    
    public void agregarRegistroXmlInstancias(ArrayList<Object> dto) throws InvocationTargetException;
    
    public void actualizarDocumentoBitacora(ArrayList<Object> dto) throws InvocationTargetException;
    
    public void actualizarDocumentoCliente(ArrayList<Object> dto) throws InvocationTargetException;
    
    public void actualizarDocumentoCuenta(ArrayList<Object> dto) throws InvocationTargetException;
    
    public void actualizarDocumentoInstancias(ArrayList<Object> dto) throws InvocationTargetException;
    
    public  ArrayList<Object> consultarRegistrosBitacora(ArrayList<Object> dto) throws InvocationTargetException;
    
    public  ArrayList<Object> consultarRegistrosCliente(ArrayList<Object> dto) throws InvocationTargetException;
    
    public  ArrayList<Object> consultarRegistrosCuenta(ArrayList<Object> dto) throws InvocationTargetException;
    
    public  ArrayList<Object> consultarRegistrosInstancias(ArrayList<Object> dto) throws InvocationTargetException;
    
    public DTO_Sistema consultarCantInstanciasXML(ArrayList<Object> dto);
}