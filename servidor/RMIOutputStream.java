/**
 * Created by jose on 25/4/15.
 */
import java.rmi.*;
import java.rmi.server.*;
import java.io.*;

/* Esta clase serializa el flujo de salida
* de forma que podamos transmitirlo
*
*/
public class RMIOutputStream extends OutputStream implements
        Serializable {

    /**
     * Flujo de salida
     */
    private RMIOutputStreamInterf out;

    /**
     * Constructor principal, recibe como parámetro un flujo de salida RMI
     * @param out Flujo de salida RMI
     */
    public RMIOutputStream(RMIOutputStreamImpl out) {
        // Asignamos el flujo de salida
        this.out = out;
    }

    /**
     * Método que sirve para escribir en el flujo de salida
     * @param b Número que desea escribir?
     * @throws IOException
     */
    public void write(int b) throws IOException {
        // Escribimos en el flujo de salida
        out.write(b);
    }

    /**
     * Método que permite escribir un número de bytes dando su offset y su longitud
     * @param b Array de bytes que se van a enviar
     * @param off Offset del comienzo de escritura
     * @param len Longitud a escribir
     * @throws IOException
     */
    public void write(byte[] b, int off, int len) throws
            IOException {

        out.write(b, off, len);
    }

    /**
     * Método que sirve para cerrar el flujo de salida
     * @throws IOException
     */
    public void close() throws IOException {
        // Cerramos el flujo de salida
        out.close();
    }

}