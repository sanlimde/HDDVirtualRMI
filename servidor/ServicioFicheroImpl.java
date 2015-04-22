import java.io.File;
import java.rmi.RemoteException;

/**
 * Created by Chorro on 22/04/15.
 */
public class ServicioFicheroImpl implements  ServicioFichero {

    /**
     * Objeto que representa a un cliente en el servicio
     */
    private Cliente client;

    /**
     * Fichero que se utilizará en el servicio
     * Sólo se manipulará un fichero por vez
     */
    private File fichero;

    ServicioFicheroImpl(Cliente c) throws RemoteException{
        client = c;
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
     * Método que permite enviar un fichero
     *
     * @return Si el fichero se ha transmitido correctamente (true) o no (false)
     * @throws java.rmi.RemoteException
     */
    @Override
    public boolean pushFichero(File f) throws RemoteException {
        return false;
    }

    /**
     * Método que permite recibir un fichero
     *
     * @return Si el fichero se ha recibido correctamente (true) o no (false)
     * @throws java.rmi.RemoteException
     */
    @Override
    public boolean pullFichero(File f) throws RemoteException {
        return false;
    }
}
