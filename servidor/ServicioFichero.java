import java.io.*;
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

    OutputStream getOutputStream(String nombreFichero) throws IOException;

    InputStream getInputStream(String nombreFichero) throws IOException;


    }
