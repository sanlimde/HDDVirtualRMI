import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaz que definirá el servicio de ficheros
 */
interface ServicioFichero extends Remote{

    /**
     * Método que permite obtener el cliente asociado
     * @return Cliente asociado al servicio
     * @throws RemoteException
     */
    Cliente getCliente() throws RemoteException;

    /**
     * Método que permite enviar un fichero
     * @return Si el fichero se ha transmitido correctamente (true) o no (false)
     * @throws RemoteException
     */
    boolean pushFichero(File f) throws RemoteException;

    /**
     * Método que permite recibir un fichero
     * @return Si el fichero se ha recibido correctamente (true) o no (false)
     * @throws RemoteException
     */
    boolean pullFichero(File f) throws RemoteException;

}
