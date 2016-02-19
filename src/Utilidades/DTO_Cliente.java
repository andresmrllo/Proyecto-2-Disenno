package Utilidades;

/**
 * 
 */
public class DTO_Cliente {

    private String claseLlamar;
    private String nombre;
    private String email;
    private int telefono;
    private int idCliente;
       private int cuenta;
    
    /**
     * Default constructor
     */
    public DTO_Cliente() {
    }


    public void setNombre(String n) {
        
    }

    /**
     * @return
     */
    public String getNombre() {
        // TODO implement here
        return "";
    }

    /**
     * @param e
     */
    public void setEmail(String e) {
        email = e;
    }

    /**
     * @return
     */
    public String getEmail() {
        // TODO implement here
        return email;
    }

    /**
     * @param t
     */
    public void setTelefono(int t) {
        telefono = t;
    }

    /**
     * @return
     */
    public int getTelefono() {
        // TODO implement here
        return telefono;
    }

    public String getNombreClase() {
        return claseLlamar;
    }

    public void setNombreClase(String nombreClase)
    {
        claseLlamar = nombreClase;
    }

        public void setId(int id)
    {
        this.idCliente = id;
    }
    public int getId()
    {
        return this.idCliente;
    }
    
    public void setCuenta(int cta)
    {
        this.cuenta = cta;
    }
    public int getCuenta()
    {
        return this.cuenta;
    }
}