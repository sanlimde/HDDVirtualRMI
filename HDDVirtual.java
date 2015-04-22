import java.io.*;
import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

class HDDVirtual extends UnicastRemoteObject implements HDDVirtualInterf
{

public OutputStream getOutputStream(File f) throws IOException {
    return new RMIOutputStream(new RMIOutputStreamImpl(new 
    FileOutputStream(f)));
}

public InputStream getInputStream(File f) throws IOException {
    return new RMIInputStream(new RMIInputStreamImpl(new 
    FileInputStream(f)));
}

}
