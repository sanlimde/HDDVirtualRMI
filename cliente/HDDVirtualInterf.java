import java.rmi.*;
import java.io.*;


interface HDDVirtualInterf extends Remote {
public InputStream getInputStream(File f) throws IOException;
public OutputStream getOutputStream(File f) throws IOException;
}

