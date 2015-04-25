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

    private RMIOutputStreamInterf out;

    public RMIOutputStream(RMIOutputStreamImpl out) {
        this.out = out;
    }

    public void write(int b) throws IOException {
        out.write(b);
    }

    public void write(byte[] b, int off, int len) throws
            IOException {
        out.write(b, off, len);
    }

    public void close() throws IOException {
        out.close();
    }

}