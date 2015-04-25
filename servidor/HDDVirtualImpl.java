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
  private List < ServicioFichero > listadoServicios;

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
  private void init()
  {
    // Comprobamos el directorio actual
    File directoriActual = new File(".");
    listadoServicios = new LinkedList<ServicioFichero>();

      try
    {
      // Abrimos un nuevo fichero que será nuestro disco virtual
      directorioHdd =
        new File(directoriActual.getCanonicalPath() + "/" + carpetaHdd);

      // Si no existe, crea el directorio, en caso contrario no se hace nada
      if (directorioHdd.mkdirs())
        System.out.println("Se ha creado el directorio " + carpetaHdd +
                           " con éxito");
      else
        System.out.println("No se ha creado el directorio " + carpetaHdd +
                           ". Ya existe en el sistema");

    }
    catch(IOException e)
    {
      e.printStackTrace();
    }
  }

    /**
     * Método que sirve para crear un nuevo servicio para un cliente determinado
     *
     * @param c Cliente que crea el servicio
     * @return Servicio para el envío y recepción de ficheros
     * @throws java.rmi.RemoteException
     */
  @Override
    public ServicioFichero crearServicio(Cliente c) throws RemoteException
  {

    File directorioCliente = null;
    ServicioFichero sf = null;

    if ((directorioCliente = verificarDirectorioCliente(c)) != null)
      {
        // Creamos un nuevo servicio para gestionar los ficheros, pasándole como argumento el cliente
        sf = new ServicioFicheroImpl(c, directorioCliente);
        // Añadimos dicho servicio al listado
        listadoServicios.add(sf);
      }
    else
      {
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
    public List < ServicioFichero > getServicios()throws RemoteException
  {
    return listadoServicios;
  }


        /**
     * Método que devuelve el Servicio del cliente en cuestion
     * @return Servicio del cliente en cuestion o null si no lo encuentra
     * @throws RemoteException
     */
  public ServicioFichero getServicio(Cliente c) throws RemoteException
  {
    for (ServicioFichero i:listadoServicios)
      {
        if (i.getCliente().getNombre() == c.getNombre())
          {
            return i;
          }
      }
    return null;
  }

    /**
     * Método que comprueba y crea un nuevo directorio para el cliente si este no existiera
     *
     * @return True si el directorio se ha creado, False en caso contrario
     * @throws java.rmi.RemoteException
     */
  @Override
    public File verificarDirectorioCliente(Cliente c) throws RemoteException
  {

    File directorioCliente = null;

      try
    {

      directorioCliente =
        new File(directorioHdd.getCanonicalPath() + "/" + c.getNombre());

      if (directorioCliente.mkdirs())
        System.out.
          println("Se ha creado un nuevo directorio para el cliente");
      else
        System.out.println("Se ha abierto el directorio para el cliente");



    }
    catch(IOException e)
    {
      e.printStackTrace();
    }
    catch(SecurityException e){
      System.out.println("No se tienen permisos");

    }

    return directorioCliente;
  }



}

