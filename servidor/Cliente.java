import java.io.Serializable;

/**
 * Clase que representa un cliente, contiene datos básicos
 */
public class Cliente implements Serializable {

    /**
     * Nombre del cliente
     */
    private String nombre;

    /**
     * Identificador del cliente
     */
    private String iD;

    /**
     * Constructor principal
     * @param nom Nombre del cliente
     * @param id Identificador del cliente
     */
    Cliente(String nom, String id){
        nombre = nom;
        iD = id;
    }

    /**
     * Método que devuelve el nombre del cliente
     * @return nombre del cliente
     */
    public String getNombre(){
        return nombre;
    }

    /**
     * Método que devuelve el identificador del cliente
     * @return identificador del cliente
     */
    public String getID(){
        return iD;
    }

    /**
     * Método que devuelve una cadena con el nombre e identificador del cliente
     * @return nombre e identificador del cliente
     */
    public String toString(){
        return nombre + " | " + iD;
    }
}
