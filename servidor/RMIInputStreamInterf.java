/**
 * Created by jose on 25/4/15.
 */
import java.rmi.*;
import java.io.*;


public interface RMIInputStreamInterf extends Remote {

    public byte[] readBytes(int len) throws IOException,
            RemoteException;
    public int read() throws IOException;
    public void close() throws IOException;

}