import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaz que definirá el servicio de ficheros
 */
interface ServicioFichero extends Remote{

    /**
     * Método que permite establecer un nuevo fichero al servicio
     * @param f Fichero único para el cliente del servicio
     * @return True si fue correcta la operación, False en caso contrario
     * @throws RemoteException
     */
    boolean setFile(File f) throws RemoteException;

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
    public OutputStream getOutputStream(File f) throws IOException;

    /**
     * Método que permite recibir un fichero
     * @return Devuelve el flujo de salida para escribir el fichero
     * @throws RemoteException
     */
    public InputStream getInputStream(File f) throws IOException;

}
