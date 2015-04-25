/**
 * Created by jose on 25/4/15.
 */
import java.rmi.*;
import java.io.*;

/**
 * Interfaz que definirá el manejo de los flujos de salida
 */
public interface RMIOutputStreamInterf extends Remote {

    /**
     * Método que permite escribir un número entero
     * @param b Número entero a escribir
     * @throws IOException
     * @throws RemoteException
     */
    public void write(int b) throws IOException, RemoteException;

    /**
     * Método que permite escribir un array de Bytes dados su offset y longitud
     * @param b Array de bytes a escribir
     * @param off Offset del comienzo de la escritura
     * @param len Longitud hasta el fin de la escritura
     * @throws IOException
     * @throws RemoteException
     */
    public void write(byte[] b, int off, int len) throws
            IOException, RemoteException;

    /**
     *
     * @throws IOException
     * @throws RemoteException
     */
    public void close() throws IOException, RemoteException;

}