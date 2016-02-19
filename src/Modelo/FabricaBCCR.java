package Modelo;

/**
 * 
 */
public class FabricaBCCR extends FabricaWebService {

    /**
     * Default constructor
     */
    public FabricaBCCR() {
    }

    /**
     * @return
     */
    @Override
    public IConexionWebService fabricarWebService() {
        // TODO implement here
        return new TipoCambioBCCR();
    }
}