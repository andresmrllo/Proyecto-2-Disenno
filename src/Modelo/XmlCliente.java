package Modelo;

import Utilidades.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 
 */
public class XmlCliente implements IXml {

    DTO_Cuenta dtoCuenta;
    DTO_Cliente dtoCliente;
    DTO_Sistema dtoSistema; 
    /**
     * Default constructor
     * @param dto
     */
    public XmlCliente(ArrayList<Object> dto) {
        //dtoCliente = (DTO_Cliente) dto;
        dtoCliente = (DTO_Cliente)dto.get(0);
        dtoSistema = (DTO_Sistema)dto.get(1);
        dtoCuenta = (DTO_Cuenta)dto.get(2);
    }

    /**
     */

    /**
     * 
     */
    @Override
    public void agregarRegistro() {
        //verificar que el cliente existe
            //si no existe, agregarlo al archivo xml
            //si existe, devolver error
        String filePath = "C:\\Users\\Alexander\\Documents\\"
                + "NetBeansProjects\\TI5501_Proyecto2\\clientes.xml";
        File xmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            
            agregarCliente(doc, dtoCliente, dtoSistema, dtoCuenta); 
            
            
            //write the updated document to file or console
            doc.getDocumentElement().normalize();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));            
            
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2"); // si require mas identacion, incrementar ese "2"
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");            
            transformer.transform(source, result);
            System.out.println("XML file updated successfully");
            
        } catch (SAXException | ParserConfigurationException | IOException e1) {
        } catch (TransformerException ex) {
            Logger.getLogger(XmlCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
    
    private void agregarCliente(Document doc, DTO_Cliente dto_cliente, DTO_Sistema dto_sis, DTO_Cuenta dto_cta) {
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
                //Print each employee's detail
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
    /**
     * @return
     */
    @Override
    public ArrayList<Object> consultarRegistros() {
        ArrayList<Object> lista = new ArrayList<Object>();
        
        String filePath = "C:\\Users\\Alexander\\Documents\\"
                + "NetBeansProjects\\TI5501_Proyecto2\\clientes.xml";
        File xmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            
            Element root = doc.getDocumentElement();
            NodeList nList = doc.getElementsByTagName("cliente");
            for (int temp = 0; temp < nList.getLength(); temp++){
                Node node = nList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE){
                    Element eElement = (Element) node;               
                    
                    System.out.println("usuario id : "    + eElement.getAttribute("id"));
                    System.out.println("Nombre : "  + eElement.getElementsByTagName("nombre").item(0).getTextContent());
                    System.out.println("cuenta : "   + eElement.getElementsByTagName("cuenta").item(0).getTextContent());
                    
                    DTO_Cliente tempDtoClte = new DTO_Cliente();
                    
                    tempDtoClte.setId(Integer.parseInt(eElement.getAttribute("id")));                    
                    tempDtoClte.setNombre(eElement.getAttribute("nombre"));
                    tempDtoClte.setEmail(eElement.getElementsByTagName("email").item(0).getTextContent());
                    tempDtoClte.setTelefono(Integer.parseInt(eElement.getElementsByTagName("telefono").item(0).getTextContent()));
                    tempDtoClte.setCuenta(Integer.parseInt(eElement.getElementsByTagName("cuenta").item(0).getTextContent()));
                    
                    lista.add(tempDtoClte);
                }
                

            }
            
        } catch (SAXException | ParserConfigurationException | IOException e1) {
        } 
        
        System.out.println("Registros consultados");
        return lista;
    }

}