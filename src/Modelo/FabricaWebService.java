package Modelo;

import java.util.*;

/**
 * 
 */
public abstract class FabricaWebService implements IFabricaWebService {

    /**
     * Default constructor
     */
    public FabricaWebService() {
    }

    /**
     * @return
     */
    @Override
    public abstract IConexionWebService fabricarWebService();

}