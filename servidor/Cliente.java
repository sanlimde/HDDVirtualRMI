import java.io.Serializable;
import java.rmi.*;


/**
 * Clase que representa un cliente, contiene datos básicos
 */
public class Cliente implements Serializable {


    /**
     * Nombre del cliente
     */
    private String nombre;


    /**
     * Constructor principal
     * @param nom Nombre del cliente
     */
    Cliente(String nom)
    {
        nombre = nom;
        System.out.println("Se ha creado un nuevo cliente con nombre : " + nom);
    }

    /**
     * Método que devuelve el nombre del cliente
     * @return nombre del cliente
     */
    public String getNombre(){
        return nombre;
    }


    /**
     * Método que devuelve una cadena con el nombre e identificador del cliente
     * @return nombre del cliente
     */
    public String toString(){
        return nombre;
    }
}
