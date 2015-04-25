import java.io.*;
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

    OutputStream getOutputStream(String nombreFichero) throws IOException;

    InputStream getInputStream(String nombreFichero) throws IOException;


    }
