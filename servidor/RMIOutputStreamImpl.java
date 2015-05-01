import java.rmi.server.*;
import java.rmi.*;
import java.io.*;

/**
 * Implementación de la interfaz RMIOutputStreamInterf
 */
public class RMIOutputStreamImpl implements RMIOutputStreamInterf {

    /**
     * Flujo de salida
     */
    private OutputStream out;

    /**
     * Constructor principal
     * @param out Flujo de salida al cual escribir
     * @throws IOException
     */
    public RMIOutputStreamImpl(OutputStream out) throws
            IOException {
        // Asignamos el flujo de salida
        this.out = out;
        // Exportamos la clase para que esté disponible en el puerto 1099
        // todo comprobar que el puerto no se solapa con el de entrada
        UnicastRemoteObject.exportObject(this, 1099);
    }

    /**
     * Método que permite escribir un número entero
     * @param b Número a escribir
     * @throws IOException
     */
    public void write(int b) throws IOException {
        // Escrbimos el número entero
        out.write(b);
    }

    /**
     * Método que permite escribir un número de bytes dando su offset y longitud
     * @param b Array de bytes a escribir
     * @param off Offset desde el cual comenzar la escritura
     * @param len Longitud hasta finalizar la escritura
     * @throws IOException
     */
    public void write(byte[] b, int off, int len) throws
            IOException {
        out.write(b, off, len);
    }

    /**
     * Método que permite cerrar el flujo de salida
     * @throws IOException
     */
    public void close() throws IOException {
        out.close();
    }

}

