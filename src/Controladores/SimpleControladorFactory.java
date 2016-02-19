package Controladores;

/**
 * 
 */
public class SimpleControladorFactory implements ISimpleControladorFactory {

    /**
     * Default constructor
     */
    public SimpleControladorFactory() {
    }

    /**
     * @return
     */
    @Override
    public Controlador crearControlador() {
        // TODO implement here
        return new Controlador();
    }


}