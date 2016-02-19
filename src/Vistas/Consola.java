/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Controladores.Controlador;
import Controladores.ISimpleControladorFactory;
import Controladores.SimpleControladorFactory;
import Utilidades.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.applet.Main;


 
 public class Consola {

    /**
     * @param args the command line arguments
     */
    
    private static Scanner scanner;
    private static Scanner in;
    private static DTO_Cuenta dto_cuenta;    
    private static DTO_Cliente dto_cliente;
    private static DTO_Transaccion dto_transaccion;
    private static DTO_Sistema dto_sistema;
    private static Controlador controlador;
    private static ISimpleControladorFactory miFabrica;
    
    public static void main(String[] args) throws InvocationTargetException {        
        dto_cuenta = new DTO_Cuenta();
        dto_cliente = new DTO_Cliente();
        dto_transaccion = new DTO_Transaccion();
        dto_sistema = new DTO_Sistema();
        miFabrica = new SimpleControladorFactory();
        controlador = miFabrica.crearControlador();
               
        
        do{
            new VistaGUI().setVisible(true);
            // TODO code application logic here
            System.out.println("CAJERO AUTOMATICO CARDLESS");
            System.out.println("Digite el número con la opción que desea realizar a continuación:");
            System.out.println("1) Crear cuenta");
            System.out.println("2) Cambiar PIN");
            System.out.println("3) Realizar Depósito en colones");
            System.out.println("4) Realizar Depósito con cambio de moneda");
            System.out.println("5) Realizar Retiro en colones");
            System.out.println("6) Realizar Retiro con compra de moneda");
            System.out.println("7) Realizar Transferencia (Unicamente moneda local)");
            System.out.println("8) Consultar tipo de cambio de compra de divisa extranjera");
            System.out.println("9) Consultar tipo de cambio de venta de divisa extranjera");
            System.out.println("10) Consultar Saldo Actual");
            System.out.println("11) Consultar Saldo Actual(Divisa extranjera)");
            System.out.println("12) Consultar Estado de Cuenta");
            System.out.println("13) Consutlar Estado de Cuenta (Divisa extrajera)");
            System.out.println("14) Modificar número de teléfono de la cuenta");
            System.out.println("15) Modificar dirección de email asociado");
            System.out.println("16) Consultar el estatus de una cuenta");   
            System.out.println("17) Salir");   

            Scanner in = new Scanner(System.in);
            int num = in.nextInt();
            menuPrincipal(num);
            //String next = scanner.nextLine();
            promptEnterKey();
        }        
        while(true);
    }
    
    private static void menuPrincipal(int seleccion) throws InvocationTargetException{
        int ncuenta;
        String pin; // PIN 
        int telefono;
        String email1;
        double monto; 
        
        switch(seleccion) {
        case 1: 
            scanner = new Scanner(System.in);
            in = new Scanner(System.in);
            
            System.out.println("1) CREAR CUENTA");
            System.out.println("1.1) Digite el PIN que será asignado a la cuenta:");                        
            pin = scanner.nextLine();
            //VALIDA PIN
            dto_cuenta.setPin(pin);
            
            System.out.println("1.2) Digite el Nombre completo del cliente que será asignado a la cuenta:");
            String nombre; 
            nombre= scanner.nextLine();
            dto_cliente.setNombre(nombre);
            
            System.out.println("1.3) Digite el Número de teléfono del cliente que será asignado a la cuenta:");            
            telefono = scanner.nextInt();
            //VALIDA TELEFONO
            dto_cliente.setTelefono(telefono);
            
            System.out.println("1.4) Digite la dirección de correo electronico del cliente que será asignado a la cuenta:");            
            Scanner in2 = new Scanner(System.in);            
            email1 = in2.nextLine();
            dto_cliente.setEmail(email1);
            
            System.out.println("1.5) Digite el monto del depósito inicial de la cuenta:");            
            monto = scanner.nextDouble();
            //VALIDA MONTO
            dto_cuenta.setSaldo(monto);
            
            dto_cuenta.setStatus("activa");            
                      
            
            //OBTENER EL ULTIMO NUMERO_DE_CUENTA e instancias 
            ArrayList<Object> registros = new ArrayList<>();
            ArrayList<Object> nuevo= new ArrayList<>();
            nuevo.add(dto_sistema);
            nuevo.add(dto_sistema);
            registros = controlador.consultarRegistrosInstancias(nuevo);
            registros.get(0);
            dto_cuenta.setNumCuenta((int)registros.get(0));
            dto_cliente.setId((int)registros.get(1));
            {
                try {
                    crearCuenta();
                } catch (InvocationTargetException | NoSuchMethodException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            break;
        case 2: 
            scanner = new Scanner(System.in);
            in = new Scanner(System.in);
            System.out.println("2) CAMBIAR PIN");
            
            System.out.println("2.1) Digite el número de cuenta: ");
            ncuenta = scanner.nextInt();
            dto_cuenta.setNumCuenta(ncuenta);
            //verifica que num de cuenta valido 
            //controlador.verificarCuentaValida(dto_cuenta);
            
            System.out.println("2.2) Digite el PIN ACTUAL de la cuenta: ");
            pin = scanner.nextLine();
            dto_cuenta.setPin(pin);
            //valida pin con cuenta
            
            System.out.println("2.3) Digite el NUEVO PIN de la cuenta: ");
            pin = scanner.nextLine();            
        //valida formato PIN
            dto_cuenta.setPin(pin);
        //controlador.cambiarPin(dto_cuenta);
            
            System.out.println("Estimado usuario, se ha cambiado satisfactoriamente el PIN de su cuenta "+ dto_cuenta.getNumCuenta());            
            break;
        case 3: 
            //XmlCliente xcliente = new XmlCliente(dto_cliente);
            //xcliente.agregarRegistro();
            
            scanner = new Scanner(System.in);
            System.out.println("3) REALIZAR DEPOSITO EN COLONES");
            System.out.println("3.1) Digite el Número de la cuenta: ");
            ncuenta = scanner.nextInt();
            dto_cuenta.setNumCuenta(ncuenta);
        //verifica que num de cuenta valido 
        //controlador.verificarCuentaValida(dto_cuenta);
            
            System.out.println("3.2) Digite el monto del depósito: ");
            
            System.out.println("Estimado usuario, se han recibido correctamente "+dto_transaccion.getMonto()+" Colones" );        
        //controlador.realizarDeposito(dto_transaccion, dto_cuenta);
            System.out.println("[El monto real depositado a su cuenta "+dto_cuenta.getNumCuenta() 
                    +" es de: "+ dto_transaccion.getMonto() +"colones]");
        //controlador 
            System.out.println("[El cobrado por concepto de comisión fue de: "+dto_transaccion.getComision()
                    +" colones, que fueron rebajados automáticamente de su saldo actual]");
            
            break;
        case 4: 
            System.out.println("4) REALIZAR DEPOSITO CON CAMBIO DE MONEDA");
            System.out.println("4.1) Ingrese el número de cuenta: ");
            //validar numCuenta
            System.out.println("4.2) Ingrese el monto del depósito en DÓLARES: ");
            //valida Monto
            
            System.out.println("4.3) Inserte los billetes en el dispensador... ");
            
            
            
            break;
        case 5: 
            System.out.println("5) REALIZAR RETIRO EN COLONES");
            System.out.println("5.1) Ingrese el número de cuenta: ");
            //validar numCuenta
            
            System.out.println("5.2) Digite el PIN de la cuenta: ");
            //validar pin de la cuenta
            
            //controlador.enviaPalabraClave(dto_cuenta)
            System.out.println("Estimado usuario, se ha enviado una palabra por mensaje de texto SMS, "
                    + "por favor revise sus mensajes y proceda a digitar la palabra enviada.");
            System.out.println("Ingrese la palabra clave enviada a su móvil: ");
            String pcUsuario;
            //dto_sistema.setPalabraClaveUsuario( pcUsuario)
            //controlador.validarPalabraClaveUsuario(dto_sistema);
            System.out.println("5.3) Digite el monto del retiro: ");
            double mRetiro = 0;
            //validar monto retiro
            //valida que existan fondos suficientes para realizar el retiro
            //controlador.validarFondosRetiro()
            //controlador.realizarRetiroColones(mRetiro)
            System.out.println("Estimado usuario, el monto de este retiro es "+mRetiro+", por favor tome el dinero dispensado.");
            System.out.println("[El monto cobrado por concepto de comision fue de "
                    + dto_transaccion.getComision()
                    +" colones, que fueron rebajados automáticamente de su saldo actual]");
            
            break;
        case 6: 
            System.out.println("6) REALIZAR RETIRO CON COMPRA DE MONEDA");
            
            System.out.println("6.1) Ingrese el número de cuenta: ");
            
            System.out.println("6.2) Ingrese el PIN de la cuenta: ");
            
            System.out.println("Estimado usuario, se ha enviado una palabra por mensaje de texto SMS, "
                    + "por favor revise sus mensajes y proceda a digitar la palabra enviada.");
            System.out.println("6.3) Ingrese la palabra clave enviada a su móvil: ");
            //validad palabra clave
            
            System.out.println("6.4) Digite el monto del retiro en DOLARES: ");            
            //validad monto sin decimales
            //revisar tipo de cambio con el API
            //validar fondos suficientes para realizar el retiro
            System.out.println("Estimado usuario, el monto de este retiro es de "+dto_transaccion.getMonto()
                    +" Dólares, por favor tome el dinero dispensado.");
            System.out.println("[Según el BCCR, el tipo de cambio de venta del dólar de hoy es de: "+
                    dto_transaccion.getVentaDolar()+"]");
            System.out.println("[El monto equivalente de su retiro es "+dto_transaccion.getMonto()+" colones]");
            System.out.println("[El monto cobrado por concepto de comisión fue de "+dto_transaccion.getComision()+" colones, "
                    + "que fueron rebajados automáticamente de su saldo actual.]");
            break;
        case 7: 
            System.out.println("7) REALIZAR TRANFERENCIA PARA MONEDA LOCAL");            
            System.out.println("7.1) Ingrese el número de cuenta: ");
            
            System.out.println("7.2) Ingrese el PIN de la cuenta: ");
            
            System.out.println("Estimado usuario, se ha enviado una palabra por mensaje de texto SMS, "
                    + "por favor revise sus mensajes y proceda a digitar la palabra enviada.");
            System.out.println("7.3) Ingrese la palabra clave enviada a su móvil: ");
            //validad palabra clave
            
            System.out.println("7.4) Digite el monto del retiro en COLONES: ");            
            //validad monto sin decimales
            //VALIDA fondos suficientes para el retiro
            System.out.println("7.5) Ingrese el número de cuenta al que transferir el dinero: ");
            //valida cuenta existente
            
            System.out.println("Estimado usuario, la transferencia de fondos se ejecutó satisfactoriamente.");
            System.out.println("El monto retirado de la cuenta origen y depositado en la cuenta destino es "
                    + dto_transaccion.getMonto()+" colones.");
            System.out.println("[El monto cobrado por concepto de comisión a la cuenta origen fue de "
                    + dto_transaccion.getComision()+" colones, que fueron rebajados automáticamente de su saldo actual]");
            
            break;
        case 8: 
            System.out.println("8) CONSULTAR TIPO DE CAMBIO DE COMPRA DE DIVISA EXTRANJERA");
            //consultar tipo cambio compra API
            System.out.println("El tipo de cambio de COMPRA actual es "
                    + dto_transaccion.getCompraDolar()+"");
            break;
        case 9: 
            System.out.println("9) CONSULTAR TIPO DE CAMBIO DE VENTA EN DIVISA EXTRANJERA");
            //consultar tipo cambio compra API
            System.out.println("El tipo de cambio de VENTA actual es "
                    + dto_transaccion.getVentaDolar()+"");
            
            break;
        case 10: 
            System.out.println("10) CONSULTAR SALDO ACTUAL");
            System.out.println("10.1) Ingrese el número de cuenta: ");
            System.out.println("10.2) Ingrese el PIN de la cuenta: ");
            
            System.out.println("Estimado usuario, el saldo actual de su cuenta es "
                    + dto_cuenta.getSaldo()+" colones.");
            
            break;
        case 11: 
            System.out.println("11) CONSULTAR SALDO ACTUAL (DIVISA EXTRANJERA)");
            System.out.println("11.1) Ingrese el número de cuenta: ");
            System.out.println("11.2) Ingrese el PIN de la cuenta: ");
            
            System.out.println("Estimado usuario, el saldo actual de su cuenta es "
                    + dto_cuenta.getSaldo()+" dólares.");
            System.out.println("Para esta conversión se utilizó el tipo de cambio del dólar, precio de compra.");
            System.out.println("[Según el BCCR, el tipo de cambio de compra del dólar de hoy es:"
                    + dto_transaccion.getCompraDolar()+"]");
            
            break;
        case 12: 
            System.out.println("12) CONSULTAR ESTADO DE CUENTA");
            System.out.println("12.1) Ingrese el número de cuenta: ");
            System.out.println("12.1) Ingrese el PIN de la cuenta: ");
            System.out.println(" ESTADO DE CUENTA \nCUENTA N°:"
                    + dto_cuenta.getNumCuenta()+" ");
            System.out.println("toda la info de movimientos de la cuenta X");
            
            break;
        case 13: 
            System.out.println("13) CONSULTAR ESTADO DE CUENTA (DIVISA EXTRANJERA)");
            System.out.println("13.1) Ingrese el número de cuenta: ");
            System.out.println("13.1) Ingrese el PIN de la cuenta: ");
            System.out.println(" ESTADO DE CUENTA \nCUENTA N°:"
                    + dto_cuenta.getNumCuenta()+" (montos en DOLARES)");
            
            System.out.println("toda la info de movimientos de la cuenta X EN DOLARES");
                        
            break;
        case 14: 
            System.out.println("14) MODIFICAR TELEFONO ASOCIADO A CUENTA");
            System.out.println("14.1) Ingrese el número de cuenta: ");
            System.out.println("14.2) Ingrese el PIN de la cuenta: ");
            int telefonoActual = dto_cliente.getTelefono();
            System.out.println("14.3) Ingrese el NUEVO número de teléfono: ");
            
            System.out.println("Estiamdo usuario, usted ha cambiado el número de teléfono"+
                    telefonoActual+" por el número "+dto_cliente.getTelefono()+".");
            
            break;
        case 15: 
            System.out.println("15) MODIFICAR EMAIL ASOCIADO A CUENTA");
            System.out.println("15.1) Ingrese el número de cuenta: ");
            System.out.println("15.2) Ingrese el PIN de la cuenta: ");
            String emailActual = dto_cliente.getEmail();
            
            System.out.println("Ingrese la nueva dirección de correo electrónico: ");
            
            System.out.println("Estimado usuario, usted ha cambiado la dirección de correo "
                    +emailActual+" por "+dto_cliente.getEmail()+"");
            
            break;
        case 16: 
            System.out.println("16) CONSULTAR ESTATUS DE CUENTA");
            System.out.println("16.1) Ingrese el número de cuenta: ");
            
            System.out.println("La cuenta número "
                    +dto_cuenta.getNumCuenta()+" tiene estatus de "+dto_cuenta.getStatus()+"");
            
            break;                                                                                                                                                
        case 17:  
            System.out.println("17) CERRANDO SISTEMA \n\n\t¡Hasta pronto!\n");
            System.exit(0);
            break;
        default: 
            System.out.println("Error: Ha digitado una opción no válida.");
            break;
        }
    }
    
    private static void crearCuenta() throws InvocationTargetException, NoSuchMethodException{
        
        controlador.crearCliente(dto_cliente);
        //crear objeto cuenta
        controlador.crearCuenta(dto_cuenta);
        //verificar en xml
                
        System.out.println("\nNúmero de cuenta: \t\t\t\t\t" + dto_cuenta.getNumCuenta() );
        System.out.println("Estatus de la cuenta: \t\t\t\t\t" + dto_cuenta.getStatus());
        System.out.println("Saldo actual: \t\t\t\t\t\t" + dto_cuenta.getSaldo());
        System.out.println("Nombre del dueño de la cuenta: \t\t\t\t" + dto_cliente.getNombre());
        System.out.println("Número de teléfono asociado a la cuenta: \t\t" + dto_cliente.getTelefono());
        System.out.println("Dirección de correo electrónico asociada a la cuenta: \t" + dto_cliente.getEmail());
        //controlador
        ArrayList<Object> dtos = new ArrayList<Object>();
        dtos.add(dto_cliente);
        dtos.add(dto_sistema);
        dtos.add(dto_cuenta);
        controlador.agregarRegistroXmlCliente(dtos);
        
    }
    private static void cambiarPin(){
        
    }
    
    public static void promptEnterKey(){        
        try {            
            System.out.println("Presione \"ENTER\" para continuar...");
            int read = System.in.read(new byte[2]);
            limpiarConsola();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void limpiarConsola(){
        for(int clear = 0; clear < 50; clear++)
        {
           System.out.println("\b") ;
        }
    }
}

