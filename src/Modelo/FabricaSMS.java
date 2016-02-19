package Modelo;

/**
 * 
 */
public class FabricaSMS extends FabricaMensaje {

    /**
     * Default constructor
     */
    public FabricaSMS() {
    }

    /**
     * @param dto
     * @return 
     */
    @Override
    public IMensaje CrearMensaje(Object dto) {
        return new SMS();
    }

}