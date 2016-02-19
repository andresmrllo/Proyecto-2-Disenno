package Modelo;

import Utilidades.*;
import Utilidades.Directorio;
import com.chilkatsoft.CkHttp;
import com.chilkatsoft.CkHttpRequest;
import com.chilkatsoft.CkHttpResponse;
import com.chilkatsoft.CkXml;

/**
 * 
 */
public class TipoCambioBCCR implements IConexionWebService {

    
    private final int port = 80;
    private final String domain = "indicadoreseconomicos.bccr.fi.cr";
    private CkHttpRequest req ;
    private CkHttp http = new CkHttp();
    private CkXml solicitudSOAP , respuestaXML ,respuestaSOAP;
    private CkHttpResponse respuesta;
    private final  boolean ssl = false;
    private boolean acceso;
    private String response ;
    
    /**
     * Default constructor
     */
    public TipoCambioBCCR() 
    {
    }
    static {
        try {
            System.load(Directorio.obtenerDirectorio()+"\\librerias\\chilkat-9.5.0-jdk8-x64\\chilkat.dll");
        } catch (UnsatisfiedLinkError e) {
          System.err.println("Native code library failed to load.\n" + e);
          System.exit(1);
        }
      }

    private String comprobarAcceso()
    {
        String resultado = " ";
        acceso = http.UnlockComponent("Trial");
        if (!acceso)
        {
            resultado += http.lastErrorText();
            resultado += "EL sistema no pudo obtener el tipo de cambio, por favor verifique su conexión";
        }
        return resultado;
    }
    
     private String comprobarRespuesta()
    {
        String resultado = " ";
        respuesta = http.SynchronousRequest(domain,port,ssl,req);
        if (respuesta == null)
        {
            resultado += "EL sistema no pudo obtener el tipo de cambio, por favor verifique su conexión";
        }
        return resultado;
    }
    
    private void construirSoap(String fechaInicio, String fechaFinal, String indicador)
    {
        solicitudSOAP = new CkXml();
        solicitudSOAP.put_Encoding("utf-8");
        solicitudSOAP.put_Tag("soap12:Envelope");
        solicitudSOAP.AddAttribute("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
        solicitudSOAP.AddAttribute("xmlns:xsd","http://www.w3.org/2001/XMLSchema");
        solicitudSOAP.AddAttribute("xmlns:soap12","http://www.w3.org/2003/05/soap-envelope");
        solicitudSOAP.NewChild2("soap12:Body","");
        solicitudSOAP.FirstChild2();
        solicitudSOAP.NewChild2("ObtenerIndicadoresEconomicosXML","");
        solicitudSOAP.FirstChild2();
        solicitudSOAP.AddAttribute("xmlns","http://ws.sdde.bccr.fi.cr");
        solicitudSOAP.NewChild2("tcIndicador",indicador);
        solicitudSOAP.NewChild2("tcFechaInicio", fechaInicio);
        solicitudSOAP.NewChild2("tcFechaFinal", fechaFinal);
        solicitudSOAP.NewChild2("tcNombre","Usuario");
        solicitudSOAP.NewChild2("tnSubNiveles","N");
        solicitudSOAP.GetRoot2();
    }
    
    /**
     * @param dto
     * @return
     */
    private String ConsultaTipoCambio(DTO_Transaccion dto) 
    {
        response = comprobarAcceso();
        if (!" ".equals(response))
        {
            return response;
        }  
        
        acceso = http.UnlockComponent("Trial");
        /*if (!acceso){
                System.out.println(http.lastErrorText());
                System.out.println("EL sistema no pudo obtener el tipo de cambio, por favor verifique su conexión");
                return -1;
        }*/
       construirSoap(dto.getFechaInicio(),dto.getFechaFinal(),dto.getIndicadorEconomico());
        
        req = new CkHttpRequest();
        req.UseXmlHttp(solicitudSOAP.getXml());
        req.put_Path("/indicadoreseconomicos/WebServices/wsIndicadoresEconomicos.asmx");
        req.AddHeader("SOAPAction","http://ws.sdde.bccr.fi.cr/ObtenerIndicadoresEconomicosXML");
        
        response =comprobarRespuesta();
        if (!" ".equals(response))
        {
            return response;
        }
        respuesta = http.SynchronousRequest(domain,port,ssl,req);
        if (respuesta == null){
             System.out.println("EL sistema no pudo obtener el tipo de cambio, por favor verifique su conexi�n");
        }
        else{
            respuestaSOAP = new CkXml();
            respuestaSOAP.LoadXml(respuesta.bodyStr());
            respuestaSOAP.FirstChild2();
            respuestaSOAP.FirstChild2();
            respuestaSOAP.FirstChild2();
            respuestaXML = new CkXml();
            respuestaXML.LoadXml(respuestaSOAP.content());
        }
        
        //Se adjuntan los datos de la respuesta a la variable webService
        response = (respuestaXML.getXml());
        String strDolar = response.substring( response.indexOf("<NUM_VALOR>")+11, response.lastIndexOf("</NUM_VALOR"));
        return strDolar;
    }

    /**
     * @param obj
     */
    @Override
    public Object Conectar(Object obj)
    {
        // TODO implement here
        DTO_Transaccion dto = (DTO_Transaccion)obj;
        double resultado = Double.parseDouble(ConsultaTipoCambio((DTO_Transaccion) dto));
        if ("318".equals(dto.getIndicadorEconomico()))
        {
            dto.setVentaDolar(resultado);
        }
        else
            dto.setCompraDolar(resultado);
        return dto;
    }

    
}