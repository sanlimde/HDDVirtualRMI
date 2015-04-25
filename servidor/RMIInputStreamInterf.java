import java.rmi.*;
import java.io.*;

/**
 * Clase que se encargada de los flujos de entrada RMI
 */
public interface RMIInputStreamInterf extends Remote {

    /**
     * Método que permite leer una longitud de bytes determinada
     * @param len longitud de bytes a leer
     * @return Un array con los bytes leídos
     * @throws IOException
     * @throws RemoteException
     */
    public byte[] readBytes(int len) throws IOException,
            RemoteException;

    /**
     * Método que permite leer sin conocer la longitud en bytes
     * @return Número de bytes leídos
     * @throws IOException
     */
    public int read() throws IOException;

    /**
     * Método que permite cerrar el flujo de entrada
     * @throws IOException
     */
    public void close() throws IOException;

}