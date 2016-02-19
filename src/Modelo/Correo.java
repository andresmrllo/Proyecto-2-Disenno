package Modelo;

import Utilidades.DTO_Cliente;
import Utilidades.DTO_Cuenta;
import Utilidades.DTO_Sistema;
import java.util.Properties;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 
 */
public class Correo implements IMensaje {

    private static final String HOST = "smtp.gmail.com";
    private static final String USER = "caciis2015@gmail.com";
    private static final String PASSWORD = "contrasena1234";
    private static final String PORT = "587";
    private static final String socketFactoryPORT = "465";
    private static final String FROM = "caciis2015@gmail.com";
    private static final String SOCKET_FACTORY = "javax.net.ssl.SSLSocketFactory";
    private static final String SUBJECT = "Su cuenta de Bank-ito ha sido bloqueada";
    /**
     * Default constructor
     */
    public Correo() {
    }

    private  synchronized void enviarCorreo(String destino, int numCuenta) 
    {
        //Se crea un objeto
        Properties props = new Properties();  
        props.put("mail.smtp.host", HOST);  
        props.put("mail.smtp.socketFactory.port", socketFactoryPORT);  
        props.put("mail.smtp.socketFactory.class",SOCKET_FACTORY);  
        props.put("mail.smtp.auth", "true");  
        props.put("mail.smtp.port", PORT);  
        try 
        {//Se obtiene la sesion de correo
            Session session = Session.getDefaultInstance(props,  
            new javax.mail.Authenticator() {  
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {  
            return new PasswordAuthentication(USER,PASSWORD);//change accordingly  
            }  
           }); 

            //Se construye el mensaje que se va a enviar
            MimeMessage message = new MimeMessage(session);
            message.setText("Estimado cliente"+"\n"+"Bank-ito le informa que su cuenta, identificada con el n�mero "+numCuenta+" ha sido bloqueada debido a un presunto intento de intrusi�n."+"\n"+"Si desea desbloquearla presentese a la sucursal de Bank-ito mas cercana a su localidad con su c�dula de identidad."+"\n"+"Gracias por su tiempo.");
            message.setSubject(SUBJECT);
            message.setFrom(new InternetAddress(FROM));
            message.addRecipient(RecipientType.TO, new InternetAddress(destino));
            message.saveChanges();
            Transport.send(message);
        } 
        catch (MessagingException e)
        {
            throw new RuntimeException(e);
        }
    }
    
    /**
     */
    @Override
    public DTO_Sistema enviar(DTO_Cuenta dto_Cuenta, DTO_Cliente dto_Cliente) {
        System.out.println("Correo enviado");
        enviarCorreo(dto_Cliente.getEmail(),dto_Cuenta.getNumCuenta());
        return null;
    }
    
}