/**
 * Created by jose on 25/4/15.
 */
import java.rmi.*;
import java.rmi.server.*;
import java.io.*;

/**
 * Clase que implementa los flujos de entradas RMI
 */
public class RMIInputStreamImpl implements RMIInputStreamInterf {

    /**
     * Flujo de entrada
     */
    private InputStream in;

    /**
     * Array de bytes
     */
    private byte[] b;

    /**
     * Constructor principal. Inicializa el flujo de entrada
     * @param in Flujo de entrada que se va a utilizar
     * @throws IOException
     */
    public RMIInputStreamImpl(InputStream in) throws IOException {
        // Asginamos el flujo de entrada pasado como parámetro
        this.in = in;

        /* TODO Comentario aclaratorio, eliminar antes de la entrega
           Método estático que permite exportar de forma remota un objeto para darle la disponibilidad de recibir
           llamadas entrantes, usando para ello un puerto específico
        */

        // Exportamos esta clase para que esté disponible en un puerto 1099
        UnicastRemoteObject.exportObject(this, 1099);
    }

    /**
     * Método que permite cerra el flujo de entrada
     * @throws IOException
     * @throws RemoteException
     */
    public void close() throws IOException, RemoteException {
        // Cerramos flujo de entrada
        in.close();
    }

    /**
     * Método que permite leer sin una longitud dada
     * @return Número de bytes leídos
     * @throws IOException
     * @throws RemoteException
     */
    public int read() throws IOException, RemoteException {
        return in.read();
    }

    /**
     * Método que permite leer una serie de bytes, dados por una longitud
     * @param len longitud de bytes a leer
     * @return Array de bytes leídos
     * @throws IOException
     * @throws RemoteException
     */
    public byte[] readBytes(int len) throws IOException, RemoteException {
        // Si b no ha sido instanciado o la longitud es distinta de la adecuada
        if (b == null || b.length != len)
            // Creamos un nuevo vector con dicha longitud
            b = new byte[len];

        // Comenzamos a leer bytes y obtenemos la longitud total leída
        int len2 = in.read(b);
        // Si la longitud es negativa (error) o 0 significa que no se ha leído correctamente
        if (len2 <= 0)
            return null; // EOF reached

        // Comprobamos si ambas longitudes coincide, en caso de que sean distintas
        if (len2 != len) {
            // copy bytes to byte[] of correct length and return it
            // Copia los bytes a byte[] con la longitud correcta y lo devuelve
            byte[] b2 = new byte[len2];
            System.arraycopy(b, 0, b2, 0, len2);
            return b2;
        }
        else // En caso contrario es correcto y procedemos a devolverlo
            return b;
    }

}