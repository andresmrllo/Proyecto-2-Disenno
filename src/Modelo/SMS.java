package Modelo;

import Utilidades.DTO_Cliente;
import Utilidades.DTO_Cuenta;
import Utilidades.DTO_Sistema;
import Utilidades.GeneradorPalabras;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 
 */
public class SMS implements IMensaje {

    
    private final  String username = "andresmrllo"; 
    private final  String password = "contrasena1234"; 
    private final  String smtphost = "smtp.upsidewireless.com"; 
    private String to;
    private final  String from = "andresmrllo@smtp.upsidewireless.com"; 
    private Transport tr;
    private Properties props;
    private String textoSMS;

    
    /**
     * Default constructor
     */
    public SMS() {
    }

    /**
     */
    @Override
    public DTO_Sistema enviar(DTO_Cuenta dto_Cuenta, DTO_Cliente dto_Cliente) {
        try {
            //DTO_Cuenta h = (DTO_Cuenta)dto_Cuenta;
            int num = dto_Cliente.getTelefono();
            enviarSMS(num);
        } catch (MessagingException ex) {
            Logger.getLogger(SMS.class.getName()).log(Level.SEVERE, null, ex);
        }
         return null;
    }

    private void enviarSMS(int telefono) throws AddressException, MessagingException 
    { 
        String compression = "Importante"; // insert compression option here if desired 
        to = "+506"+telefono+"@sms.upsidewireless.com"; 
        textoSMS = GeneradorPalabras.generarPalabra(); 
        props = System.getProperties(); 
        props.put("mail.smtp.auth", "true");
        
        // Get a Session object 
        Session mailSession = Session.getDefaultInstance(props, null);
        
        // Se construye un nuevo objeto  mensaje
        Message msg = new MimeMessage(mailSession);
        
        // Se construye el mensaje
        msg.setFrom(new InternetAddress(from)); InternetAddress[] address = { new InternetAddress(to) }; 
        msg.setRecipients(Message.RecipientType.TO, address); 
        msg.setSubject(compression); 
        msg.setText(textoSMS);
        msg.setSentDate(new Date());
        tr = mailSession.getTransport("smtp"); 
        tr.connect(smtphost, username, password); 
        msg.saveChanges(); 
        tr.sendMessage(msg, msg.getAllRecipients()); 
        tr.close(); 
    } 

}