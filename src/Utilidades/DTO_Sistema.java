package Utilidades;

/**
 * 
 */
public class DTO_Sistema {

    private static int cantIntentosPin = 0;
    private String palabraClaveSistema;
    private String palabraClaveUsuario;
    private int cantClientes;
    private int cantCuentas;
    private String claseLlamar;

    
    /**
     * Default constructor
     */
    public DTO_Sistema() {
    }

    /**
     * @param n
     */
    public void setCantIntentosPin(int n) {
        // TODO implement here
    }

    /**
     * @return
     */
    public int getCantIntentosPin() {
        // TODO implement here
        return 0;
    }

    /**
     * @param pc
     */
    public void setPalabraClaveSistema(String pc) {
        // TODO implement here
    }

    /**
     * @return
     */
    public String getPalabraClaveSistema() {
        // TODO implement here
        return "";
    }

    /**
     * @param p
     */
    public void setPalabraClaveUsuario(String p) {
        // TODO implement here
    }

    /**
     * @return
     */
    public String getPalabraClaveUsuario() {
        // TODO implement here
        return "";
    }

    /**
     * @param c
     */
    public void setCantClientes(int c) {
        // TODO implement here
    }

    /**
     * @return
     */
    public int getCantClientes() {
        // TODO implement here
        return 0;
    }

    /**
     * @param c
     */
    public void setCantCuentas(int c) {
        // TODO implement here
    }

    /**
     * @return
     */
    public int getCantCuentas() {
        // TODO implement here
        return 0;
    }

    public String getNombreClase() {
        return claseLlamar;
    }
    
    public void setNombreClase(String nombreClase) {
        claseLlamar =nombreClase;
    }
    

}