package Modelo;

import Utilidades.*;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

public class XmlCuenta implements IXml {

    DTO_Cuenta dtoCuenta;
    DTO_Cliente dtoCliente;
    DTO_Sistema dtoSistema; 

    public XmlCuenta(ArrayList<Object> dto) {
        dtoCliente = (DTO_Cliente)dto.get(0);
        dtoSistema = (DTO_Sistema)dto.get(1);
        dtoCuenta = (DTO_Cuenta)dto.get(2);
    }

   
    @Override
    public void agregarRegistro() {
        // TODO implement here
        System.out.println("Registro agregado");
    }
    
    private void agregarCuenta(Document doc, DTO_Cliente dto_cliente, DTO_Sistema dto_sis, DTO_Cuenta dto_cta) {
        Element root = doc.getDocumentElement();
        if(!verificarCliente(doc, 
                dto_sis.getCantClientes()+1+"",
                dto_cliente.getNombre(), 
                ""+dto_cta.getNumCuenta())){
            
            Element nCliente = doc.createElement("cliente"); 
            nCliente.setAttribute("id", dto_sis.getCantClientes()+1+""); 

            Element childofchild1 = doc.createElement("nombre");
            childofchild1.appendChild(doc.createTextNode(dto_cliente.getNombre()));
            nCliente.appendChild(childofchild1);

            Element childofchild2 = doc.createElement("email");
            childofchild2.appendChild(doc.createTextNode(dto_cliente.getEmail()));
            nCliente.appendChild(childofchild2);

            Element childofchild3 = doc.createElement("telefono");
            childofchild3.appendChild(doc.createTextNode(""+dto_cliente.getTelefono()));
            nCliente.appendChild(childofchild3);
            
            Element childofchild4 = doc.createElement("cuenta");
            childofchild3.appendChild(doc.createTextNode(""+dto_cta.getNumCuenta()));
            nCliente.appendChild(childofchild4);

            root.appendChild(nCliente); //agrega el nuevo nodo al nodo raiz
            System.out.println("Cliente agregado a la base de datos");
        }
        else{
            System.out.println("ERROR: Cliente YA EXISTE en la base de datos");
        }
    }
    
    public boolean verificarCliente(Document doc, String id, String nom, String numCuenta){
        boolean respuesta = false;
        Element root = doc.getDocumentElement();
        NodeList nList = doc.getElementsByTagName("cliente");
        for (int temp = 0; temp < nList.getLength(); temp++){
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE){
                Element eElement = (Element) node;               
                
                System.out.println("usuario id : "    + eElement.getAttribute("id"));
                System.out.println("Nombre : "  + eElement.getElementsByTagName("nombre").item(0).getTextContent());
                System.out.println("cuenta : "   + eElement.getElementsByTagName("cuenta").item(0).getTextContent());
                
                
                String ident = eElement.getAttribute("id");
                String nombre = (eElement.getElementsByTagName("nombre").item(0).getTextContent());
                String cuenta = eElement.getElementsByTagName("cuenta").item(0).getTextContent();
                if(ident.equals(id)
                        ||((nombre.equals(nom)) &&(cuenta.equals(numCuenta)) )){
                    respuesta = true;
                }
            }
        
        }
        return respuesta;
    }
    
    @Override
    public void actualizarDocumento() {
        // TODO implement here
        System.out.println("Documento agregado");
    }

    @Override
    public ArrayList<Object> consultarRegistros() {
        ArrayList<Object> lista = new ArrayList<>();
                
        String filePath = System.getProperty("user.dir")+"\\cuentas.xml";
        File xmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            
            Element root = doc.getDocumentElement();
            NodeList nList = doc.getElementsByTagName("cuenta");
            for (int temp = 0; temp < nList.getLength(); temp++){
                Node node = nList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE){
                    Element eElement = (Element) node;               
                    
                    //System.out.println("usuario id : "    + eElement.getAttribute("id"));
                    //System.out.println("Nombre : "  + eElement.getElementsByTagName("nombre").item(0).getTextContent());
                    //System.out.println("cuenta : "   + eElement.getElementsByTagName("cuenta").item(0).getTextContent());
                    
                    DTO_Cuenta tempDtoClte = new DTO_Cuenta();
                    
                    tempDtoClte.setNumCuenta(Integer.parseInt(eElement.getAttribute("id")));
                    tempDtoClte.setPin(eElement.getElementsByTagName("pin").item(0).getTextContent());
                    
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); //conversion para fecha
                    Date date = dateFormat.parse(eElement.getElementsByTagName("fechaCreacion").item(0).getTextContent());
                    tempDtoClte.setFechaCreacion(date);
                    
                    tempDtoClte.setStatus(eElement.getElementsByTagName("status").item(0).getTextContent());                    
                    tempDtoClte.setMoneda(eElement.getElementsByTagName("moneda").item(0).getTextContent());
                    
                    double aDouble = Double.parseDouble(eElement.getElementsByTagName("saldo").item(0).getTextContent()); //conversion a double
                    tempDtoClte.setSaldo(aDouble);
                    
                    tempDtoClte.setCantTransaccionesCuenta(Integer.parseInt(eElement.getElementsByTagName("cantTransacciones").item(0).getTextContent()));
                    tempDtoClte.setIdCliente(Integer.parseInt(eElement.getElementsByTagName("cliente").item(0).getTextContent()));
                    
                    lista.add(tempDtoClte);
                }
            }            
        } catch (SAXException | ParserConfigurationException | IOException e1) {
        } catch (ParseException ex) {
            Logger.getLogger(XmlCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }         
        System.out.println("Registros CUENTA consultados");
        return lista;
        }

}