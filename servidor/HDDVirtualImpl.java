import java.io.*;
import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

class HDDVirtualImpl extends UnicastRemoteObject implements HDDVirtual
{

    /**
     * Constante para definir el nombre del directorio donde se almacenará el fichero
     */
    private final String carpetaHdd = "HDD";

    /**
     * Fichero que contiene el directorio HDD donde se buscará a los clientes
     */
    private File directorioHdd;

    /**
     * Objeto que nos servirá para almacenar los servicios abiertos por los clientes
     */
    private List<ServicioFichero> listadoServicios;

    /**
     * Constructor principal que inicializa el sistema
     * @throws RemoteException
     */
  HDDVirtualImpl() throws RemoteException
  {
    super();
      // Iniciamos características
      init();

  }

    /**
     * Método que inicializa algunas funciones del servidor
     */
    private void init(){
        // Comprobamos el directorio actual
        File directoriActual = new File(".");

        try {
            // Abrimos un nuevo fichero que será nuestro disco virtual
            directorioHdd = new File(directoriActual.getCanonicalPath()+"/"+carpetaHdd);

            // Si no existe, crea el directorio, en caso contrario no se hace nada
            if(directorioHdd.mkdirs())
                System.out.println("Se ha creado el directorio " + carpetaHdd + " con éxito");
            else
                System.out.println("No se ha creado el directorio " + carpetaHdd + ". Ya existe en el sistema");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

  public OutputStream getOutputStream(File f) throws IOException
  {
    return new RMIOutputStream(new RMIOutputStreamImpl(new
                                                       FileOutputStream(f)));
  }

    /**
     * Método que sirve para crea un nuevo servicio para un cliente determinado
     *
     * @param c Cliente que crea el servicio
     * @return Servicio para el envío y recepción de ficheros
     * @throws java.rmi.RemoteException
     */
    @Override
    public ServicioFichero crearServicio(Cliente c) throws RemoteException {

        File directorioCliente = null;
        ServicioFichero sf = null;

        if((directorioCliente = verificarDirectorioCliente(c)) != null){
            // Creamos un nuevo servicio para gestionar los ficheros, pasándole como argumento el cliente
            sf = new ServicioFicheroImpl(c, directorioCliente);
            // Añadimos dicho servicio al listado
            listadoServicios.add(sf);
        }else{
            System.err.println("No se ha podido abrir el directorio de cliente");
        }

        return sf;
    }

    /**
     * Método que devuelve el listado con los servicios inicializados
     *
     * @return Listado con los servicios de los distintos clientes
     * @throws java.rmi.RemoteException
     */
    @Override
    public List<ServicioFichero> getServicios() throws RemoteException {
        return listadoServicios;
    }

    /**
     * Método que comprueba y crea un nuevo directorio para el cliente si este no existiera
     *
     * @return True si el directorio se ha creado, False en caso contrario
     * @throws java.rmi.RemoteException
     */
    @Override
    public  File  verificarDirectorioCliente(Cliente c) throws RemoteException {

        File directorioCliente = null;

        try {

            directorioCliente = new File(directorioHdd.getCanonicalPath() + "/" + c.getNombre());

            if(directorioCliente.mkdirs())
                System.out.println("Se ha creado un nuevo directorio para el cliente");
            else
                System.out.println("Se ha abierto el directorio para el cliente");



        } catch (IOException e) {
            e.printStackTrace();
        }

        return directorioCliente;
    }

    public InputStream getInputStream(File f) throws IOException
  {
    return new RMIInputStream(new RMIInputStreamImpl(new FileInputStream(f)));
  }

}

