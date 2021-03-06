/**
 * Created by jose on 25/4/15.
 */
import java.rmi.*;
import java.rmi.server.*;
import java.io.*;


/* Esta clase serializa el flujo de entrada
* de forma que podamos transmitirlo
*
*/
public class RMIInputStream extends InputStream implements
        Serializable {
    /**
     * Flujo de entrada
     */
    RMIInputStreamInterf in;

    /**
     * Constructor principal
     * @param in Flujo de entrada utilizado, pasado como argumento
     */
    public RMIInputStream(RMIInputStreamInterf in) {
        this.in = in;
    }

    /**
     *
     * @return
     * @throws IOException
     */
    public int read() throws IOException {
        return in.read();
    }

    public int read(byte[] b, int off, int len) throws IOException {
        byte[] b2 = in.readBytes(len);
        if (b2 == null)
            return -1;
        int i = b2.length;
        System.arraycopy(b2, 0, b, off, i);
        return i;
    }

    public void close() throws IOException {
        super.close();
    }

}