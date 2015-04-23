import java.rmi.*;
import java.io.*;
import java.util.List;

/**
 * Interfaz que definirá el servicio de archivos
 */
interface HDDVirtual extends Remote {

    /**
     * Método que sirve para crea un nuevo servicio para un cliente determinado
     * @param c Cliente que crea el servicio
     * @return Servicio para el envío y recepción de ficheros
     * @throws RemoteException
     */
    public ServicioFichero crearServicio(Cliente c) throws RemoteException;

    /**
     * Método que devuelve el listado con los servicios inicializados
     * @return Listado con los servicios de los distintos clientes
     * @throws RemoteException
     */
    public List<ServicioFichero> getServicios() throws RemoteException;

    /**
     * Método que comprueba y crea un nuevo directorio para el cliente si este no existiera
     * @return True si el directorio se ha creado, False en caso contrario
     * @throws RemoteException
     */
     File verificarDirectorioCliente(Cliente c) throws RemoteException;

}

