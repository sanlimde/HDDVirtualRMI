import java.io.*;
import java.rmi.*;

/**
 * Created by Chorro on 22/04/15.
 */
public class ServicioFicheroImpl implements  ServicioFichero, Serializable {

    /**
     * Objeto que representa a un cliente en el servicio
     */
    private Cliente client;

    /**
     * Fichero que se utilizará en el servicio
     * Sólo se manipulará un fichero por vez
     */
    private File directorioCliente;

    /**
     * Constructor principal
     * @param c Cliente que obtiene el servicio
     * @throws RemoteException
     */
    ServicioFicheroImpl(Cliente c, File dc) throws RemoteException, IOException{
        client = c;
        directorioCliente = dc;

        System.out.println("Se ha creado un nuevo servicio:\n" +
                "\tCliente : " + c.getNombre() +
                "\n\tDirectorio : " + dc.getCanonicalPath());
    }


    /**
     * Método que permite obtener el cliente asociado
     *
     * @return Cliente asociado al servicio
     * @throws java.rmi.RemoteException
     */
    @Override
    public Cliente getCliente() throws RemoteException {
        return client;
    }



    /**
     * Método que devuelve un flujo de salida remoto que nos permite recibir un fichero desde él
     * @param nombreFichero nombre del fichero que queremos leer
     * @throws IOException
     */
    public OutputStream getOutputStream(String nombreFichero) throws IOException{
        File f = new File(nombreFichero);
        return new RMIOutputStream(new RMIOutputStreamImpl(new
                FileOutputStream(f)));
    }

    /**
     * Método que devuelve un flujo de entrada remoto que nos permite escribir un fichero en él
     * @param nombreFichero nombre del fichero en el que queremos escribir
     * @throws IOException
     */
    public InputStream getInputStream(String nombreFichero) throws IOException{
        File f = new File(nombreFichero);
        return new RMIInputStream(new RMIInputStreamImpl(new FileInputStream(f)));
    }
}
