package Modelo;

import Utilidades.DTO_Cliente;
import Utilidades.DTO_Cuenta;
import Utilidades.DTO_Sistema;
import java.util.ArrayList;
import Utilidades.DTO_Sistema;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 
 */
public class XmlInstancias implements IXml {

    DTO_Cuenta dtoCuenta;
    DTO_Cliente dtoCliente;
    DTO_Sistema dtoSistema; 
    /**
     * Default constructor
     * @param dto
     */
    public XmlInstancias(ArrayList<Object> dto) {
        dtoCliente = (DTO_Cliente)dto.get(0);
        dtoSistema = (DTO_Sistema)dto.get(1);
        dtoCuenta = (DTO_Cuenta)dto.get(2);
    }

   

   
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
        ArrayList<Object> registros = null;
        try {
            registros = getCantInstanciasXML();
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(XmlInstancias.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Registros consultados");
        
        return registros;        
    }

     private ArrayList<Object> getCantInstanciasXML() throws ParserConfigurationException, SAXException, IOException{
        ArrayList<Object> cant = new ArrayList<Object>();
        String pathInstancias = "C:\\Users\\Alexander\\Documents\\NetBeansProjects\\TI5501_Proyecto2\\instancias.xml";
        File xmlFile = new File(pathInstancias);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        Document doc = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
        }
        catch (SAXException | ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        }
        
        Element root = doc.getDocumentElement(); 
        NodeList nList = doc.getElementsByTagName("instancia");
        for (int temp = 0; temp < nList.getLength(); temp++){
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE){
                Element eElement = (Element) node;
                System.out.println("cantidad cuentas : "  + eElement.getElementsByTagName("cantidadCuentas").item(0).getTextContent());
                System.out.println("cantidad clientes : "   + eElement.getElementsByTagName("cantClientes").item(0).getTextContent());                
                
                cant.add( eElement.getElementsByTagName("cantidadCuentas").item(0).getTextContent());
                cant.add( eElement.getElementsByTagName("cantClientes").item(0).getTextContent());
            }
        }
        return cant;
    }
}