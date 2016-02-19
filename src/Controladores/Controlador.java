package Controladores;

import Modelo.*;
import Utilidades.*;
import java.lang.reflect.InvocationTargetException;
import Modelo.IFabricaArchivo;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 */
public class Controlador implements IControlador {

    private IFabricaArchivo miFabricaXml;
    private IFabricaCliente miFabricaCliente;
    private IFabricaCuenta miFabricaCuenta;
    private IFabricaMensaje miFabricaMensaje;
    private IFabricaWebService miFabricaWebService;
    
    private Cliente cliente;
    private Cuenta cuenta;
    private IXml xml;
    private IMensaje mensaje;
    private IConexionWebService webService;
    
    /**
     * Default constructor
     */
    public Controlador() 
    {
    }
    
   /* private IXml reflexionXml(Object dto) throws ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        miFabricaXml = (IFabricaArchivo)Class.forName(((DTO_Transaccion)dto).getNombreClase()).newInstance();      
        return (IXml) miFabricaXml.registrarTransaccion(dto);  
    }*/
    
    /**********************   Inicio  Funciones Cliente     ********************/
    /**
     *
     * @param dto
     * @return
     * @throws InvocationTargetException
     */
    @Override
    public String crearCliente(DTO_Cliente dto) throws InvocationTargetException
    {
        //Cliente instancia = null;
        try {
            miFabricaCliente = (IFabricaCliente)Class.forName(dto.getNombreClase()).newInstance();
            cliente = miFabricaCliente.fabricarCliente(dto);
        } 
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SecurityException | IllegalArgumentException e) {
        }
        return "Cliente creado";
    }
    
    /**********************   Inicio  Funciones Cuentas       *************/
    /**
     *
     * @param dto
     * @return
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    @Override
    public String crearCuenta(DTO_Cuenta dto) throws InvocationTargetException, NoSuchMethodException
    {
        //Cuenta instancia = null;
        try {
            miFabricaCuenta = (IFabricaCuenta)Class.forName(dto.getNombreClase()).newInstance();
            cuenta = (Cuenta)miFabricaCuenta.fabricarCuenta(dto);  
        } 
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SecurityException | IllegalArgumentException e) {
        }
        return "Cuenta creada";
    }
    
    /**
     * @param dto
     */
    @Override
    public void transferencia(Object dto) 
    {
        // TODO implement here
    }
        
    /**
     *
     * @param dto
     * @return
     */
    @Override
    public String depositarDinero(DTO_Cuenta dto)
    {
        return "Se ha depositado"+cuenta.deposito(dto)+ "ha su cuenta #"+dto.getNumCuenta();
    }
    
    @Override
    public String retirarDinero(DTO_Cuenta dto)
    {
        return "Se ha retirado"+cuenta.retiro(dto)+ "de su cuenta #"+dto.getNumCuenta();
    }
    
    /**
     *
     * @param dto
     * @return
     * @throws NoSuchMethodException
     */
    @Override
    public boolean verificarCuenta(DTO_Cuenta dto) throws NoSuchMethodException
    {
        boolean resultado = false;
        ArrayList<Object> nuevo = null;
        ArrayList<Object> consultarRegistrosCuenta ;
        nuevo.add(3,dto);
        try {
            consultarRegistrosCuenta = consultarRegistrosCuenta(nuevo);
            DTO_Cuenta temp = null;
            for( Object obj: consultarRegistrosCuenta)
            {
                temp = (DTO_Cuenta)obj;
                if(temp.getNumCuenta() == dto.getNumCuenta())
                {
                    crearCuenta(temp);
                    return true;
                }
            }
        } catch (InvocationTargetException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
     /**********************   Inicio  Funciones Sistema   *************/
    
    /**@param dto_Clt 
     * @param dto_Cta 
     * @return  
    */
    @Override
    public DTO_Sistema enviarMensaje(DTO_Cliente dto_Clt, DTO_Cuenta dto_Cta)
    {
        DTO_Sistema resultado = null;
        try {
            miFabricaMensaje = (IFabricaMensaje)Class.forName(dto_Clt.getNombreClase()).newInstance();
            mensaje = (IMensaje)miFabricaMensaje.CrearMensaje(dto_Clt);
            resultado = mensaje.enviar(dto_Cta, dto_Clt);
        } 
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SecurityException | IllegalArgumentException e) {
        }
        return resultado;
    }
    
    /**
     *
     * @param dto
     * @return
     * @throws java.lang.ClassNotFoundException
     */
    @Override
    public DTO_Transaccion conectarWebService(DTO_Transaccion dto) throws ClassNotFoundException
     {
        DTO_Transaccion resultado = null;
        try {
            miFabricaWebService = (IFabricaWebService)Class.forName(dto.getNombreClase()).newInstance();
            webService = (IConexionWebService)miFabricaWebService.fabricarWebService();
            resultado = (DTO_Transaccion) webService.Conectar(dto);
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
     }
    
    
    
    
    /**********************   Inicio  Funciones archivos XML      *************/
    
     /**
     * @param dto 
     * @throws java.lang.reflect.InvocationTargetException 
     */
    @Override
    public void agregarRegistroXmlBitacora(ArrayList<Object> dto) throws InvocationTargetException
    {
        DTO_Cuenta dtoCuenta;
        DTO_Cliente dtoCliente;
        DTO_Sistema dtoSistema;       
        DTO_Transaccion dtoTransaccion;
        
        try
        {
            dtoCliente = (DTO_Cliente)dto.get(0);
            dtoSistema = (DTO_Sistema)dto.get(1);
            dtoCuenta = (DTO_Cuenta)dto.get(2);
            dtoTransaccion = (DTO_Transaccion)dto.get(3);
            miFabricaXml = (IFabricaArchivo)Class.forName(dtoTransaccion.getNombreClase()).newInstance();      
            miFabricaXml.registrarTransaccion(dto).agregarRegistro();
        } 
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SecurityException | IllegalArgumentException e) {
        }
    }
    
    @Override
    public void agregarRegistroXmlCliente(ArrayList<Object> dto) throws InvocationTargetException
    {
        DTO_Cuenta dtoCuenta;
        DTO_Cliente dtoCliente;
        DTO_Sistema dtoSistema;       
        
        try
        {
            dtoCliente = (DTO_Cliente)dto.get(0);
            dtoSistema = (DTO_Sistema)dto.get(1);
            dtoCuenta = (DTO_Cuenta)dto.get(2);
            miFabricaXml = (IFabricaArchivo)Class.forName(dtoCliente.getNombreClase()).newInstance();      
            miFabricaXml.registrarTransaccion(dto).agregarRegistro();
        } 
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SecurityException | IllegalArgumentException e) {
        }
    }
    
    @Override
    public void agregarRegistroXmlCuenta(ArrayList<Object> dto) throws InvocationTargetException
    {
        DTO_Cuenta dtoCuenta;
        DTO_Cliente dtoCliente;
        DTO_Sistema dtoSistema;       
        
        try
        {
            dtoCliente = (DTO_Cliente)dto.get(0);
            dtoSistema = (DTO_Sistema)dto.get(1);
            dtoCuenta = (DTO_Cuenta)dto.get(2);
            miFabricaXml = (IFabricaArchivo)Class.forName(dtoCuenta.getNombreClase()).newInstance();      
            miFabricaXml.registrarTransaccion(dto).agregarRegistro();
        } 
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SecurityException | IllegalArgumentException e) {
        }
    }
    
    @Override
    public void agregarRegistroXmlInstancias(ArrayList<Object> dto) throws InvocationTargetException
    {
        DTO_Cuenta dtoCuenta;
        DTO_Cliente dtoCliente;
        DTO_Sistema dtoSistema;       
        
        try
        {
            dtoCliente = (DTO_Cliente)dto.get(0);
            dtoSistema = (DTO_Sistema)dto.get(1);
            dtoCuenta = (DTO_Cuenta)dto.get(2);
            miFabricaXml = (IFabricaArchivo)Class.forName(dtoSistema.getNombreClase()).newInstance();      
            miFabricaXml.registrarTransaccion(dto).agregarRegistro();
        } 
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SecurityException | IllegalArgumentException e) {
        }
    }
    
    /**
     *
     * @param dto
     * @throws InvocationTargetException
     */
    @Override
    public void actualizarDocumentoBitacora(ArrayList<Object> dto) throws InvocationTargetException
    {
        DTO_Cuenta dtoCuenta;
        DTO_Cliente dtoCliente;
        DTO_Sistema dtoSistema;       
        DTO_Transaccion dtoTransaccion;
        
        try
        {
            dtoCliente = (DTO_Cliente)dto.get(0);
            dtoSistema = (DTO_Sistema)dto.get(1);
            dtoCuenta = (DTO_Cuenta)dto.get(2);
            dtoTransaccion = (DTO_Transaccion)dto.get(3);
            miFabricaXml = (IFabricaArchivo)Class.forName(dtoTransaccion.getNombreClase()).newInstance();      
            miFabricaXml.registrarTransaccion(dto).actualizarDocumento();
        } 
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SecurityException | IllegalArgumentException e) {
        }
    }
    
    @Override
    public void actualizarDocumentoCliente(ArrayList<Object> dto) throws InvocationTargetException
    {
        DTO_Cuenta dtoCuenta;
        DTO_Cliente dtoCliente;
        DTO_Sistema dtoSistema;       
        
        try
        {
            dtoCliente = (DTO_Cliente)dto.get(0);
            dtoSistema = (DTO_Sistema)dto.get(1);
            dtoCuenta = (DTO_Cuenta)dto.get(2);
            miFabricaXml = (IFabricaArchivo)Class.forName(dtoCliente.getNombreClase()).newInstance();      
            miFabricaXml.registrarTransaccion(dto).actualizarDocumento();
        } 
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SecurityException | IllegalArgumentException e) {
        }
    }
    
    @Override
    public void actualizarDocumentoCuenta(ArrayList<Object> dto) throws InvocationTargetException
    {
        DTO_Cuenta dtoCuenta;
        DTO_Cliente dtoCliente;
        DTO_Sistema dtoSistema;       
        
        try
        {
            dtoCliente = (DTO_Cliente)dto.get(0);
            dtoSistema = (DTO_Sistema)dto.get(1);
            dtoCuenta = (DTO_Cuenta)dto.get(2);
            miFabricaXml = (IFabricaArchivo)Class.forName(dtoCuenta.getNombreClase()).newInstance();      
            miFabricaXml.registrarTransaccion(dto).actualizarDocumento();
        } 
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SecurityException | IllegalArgumentException e) {
        }
    }
    
    @Override
    public void actualizarDocumentoInstancias(ArrayList<Object> dto) throws InvocationTargetException
    {
        DTO_Cuenta dtoCuenta;
        DTO_Cliente dtoCliente;
        DTO_Sistema dtoSistema;       
        
        try
        {
            dtoCliente = (DTO_Cliente)dto.get(0);
            dtoSistema = (DTO_Sistema)dto.get(1);
            dtoCuenta = (DTO_Cuenta)dto.get(2);
            miFabricaXml = (IFabricaArchivo)Class.forName(dtoSistema.getNombreClase()).newInstance();      
            miFabricaXml.registrarTransaccion(dto).actualizarDocumento();
        } 
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SecurityException | IllegalArgumentException e) {
        }
    }
    
    
    /**
     *
     * @param dto
     * @return
     * @throws InvocationTargetException
     */
    @Override
    public  ArrayList<Object> consultarRegistrosBitacora(ArrayList<Object> dto) throws InvocationTargetException
    {
        ArrayList<Object> registros = null;
        try 
        {
            DTO_Transaccion dtoNuevo =  (DTO_Transaccion)dto.get(3);;
            miFabricaXml = (IFabricaArchivo)Class.forName(dtoNuevo.getNombreClase()).newInstance();      
            registros = miFabricaXml.registrarTransaccion(dto).consultarRegistros();
        } 
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SecurityException | IllegalArgumentException e) {
        }
        return registros;
    }
    
    @Override
    public  ArrayList<Object> consultarRegistrosCliente(ArrayList<Object> dto) throws InvocationTargetException
    {
        ArrayList<Object> registros = null;
        try 
        {
            DTO_Cliente dtoNuevo = (DTO_Cliente)dto.get(0);
            miFabricaXml = (IFabricaArchivo)Class.forName(dtoNuevo.getNombreClase()).newInstance();      
            registros = miFabricaXml.registrarTransaccion(dto).consultarRegistros();
        } 
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SecurityException | IllegalArgumentException e) {
        }
        return registros;
    }
     
    @Override
    public  ArrayList<Object> consultarRegistrosCuenta(ArrayList<Object> dto) throws InvocationTargetException
    {
        ArrayList<Object> registros = null;
        try 
        {
            DTO_Cuenta dtoNuevo = (DTO_Cuenta)dto.get(2);
            miFabricaXml = (IFabricaArchivo)Class.forName(dtoNuevo.getNombreClase()).newInstance();      
            registros = miFabricaXml.registrarTransaccion(dto).consultarRegistros();
        } 
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SecurityException | IllegalArgumentException e) {
        }
        return registros;
    }
    
    @Override
    public  ArrayList<Object> consultarRegistrosInstancias(ArrayList<Object> dto) throws InvocationTargetException
    {
        ArrayList<Object> registros = null;
        try 
        {
            DTO_Sistema dtoNuevo = (DTO_Sistema)dto.get(1);
            miFabricaXml = (IFabricaArchivo)Class.forName(dtoNuevo.getNombreClase()).newInstance();      
            miFabricaXml.registrarTransaccion(dto).consultarRegistros();
        } 
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SecurityException | IllegalArgumentException e) {
        }
        return registros;
    }
    
    @Override
    public DTO_Sistema consultarCantInstanciasXML(ArrayList<Object> dto){
        ArrayList<Object> instancias;
        DTO_Sistema dto_sis = (DTO_Sistema)dto.get(1);
        try {
            miFabricaXml = (IFabricaArchivo)Class.forName(dto_sis.getNombreClase()).newInstance();
            instancias = miFabricaXml.registrarTransaccion(dto).consultarRegistros();
            String cantCuentas = "";
            cantCuentas = (String)instancias.get(0);
            int cantCuentasI = Integer.parseInt(cantCuentas);
            
            String cantClientes = "";
            cantClientes = (String)instancias.get(1);
            int cantClientesI = Integer.parseInt(cantClientes);
            
            System.out.println(""+cantClientes);
            System.out.println(""+cantCuentas);
            
            dto_sis.setCantClientes(cantClientesI);
            dto_sis.setCantCuentas(cantCuentasI);
            
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dto_sis;
    }

}