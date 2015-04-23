import java.rmi.*;
import java.io.*;

/**
 * Para su ejecución:
 * java -Djava.security.policy=cliente.permisos ServidorEco 54321
 */

public class TestClient
{

  final public static int BUF_SIZE = 1024 * 64;

  public static void copy(InputStream in, OutputStream out) throws IOException
  {
    System.out.println("using byte[] read/write");
    byte[] b = new byte[BUF_SIZE];
    int len;
    while ((len = in.read(b)) >=  0)
      {
        out.write(b, 0, len);
      }
    in.close();
      out.close();
  }

  public static void upload(HDDVirtual server, File src,
                            File dest) throws IOException
  {
    copy(new FileInputStream(src), server.getOutputStream(dest));
  }

  public static void download(HDDVirtual server, File src,
                              File dest) throws IOException
  {
    copy(server.getInputStream(src), new FileOutputStream(dest));
  }

  public static void main(String[]args) throws Exception
  {

 /*   if (args.length != 6)
      {
        System.err.println
          ("Uso: TestClient hostregistro numPuertoRegistro nomFichEntradaDownload nomFichSalidaDownload nomFichEntradaUpload nomFichSalidaUpload");
        return;
      }
*/
      if(args.length != 4){
          System.err.println("Uso: TestClient <host puerto nombreCliente idCliente>");
      }

    if (System.getSecurityManager() == null)
      System.setSecurityManager(new SecurityManager());

    try
    {

        // Obtenemos el servicio de HDDVirtual
      HDDVirtualImpl server =
        (HDDVirtualImpl) Naming.lookup("//" + args[0] + ":" + args[1] +
                                         "/HDDVirtual");

        // Creamos un nuevo cliente para el servidor
        Cliente client = new Cliente(args[2], args[3]);
        // Creamos un nuevo servicio de ficheros para el cliente
        ServicioFichero sf = server.crearServicio(client);


      File testFile = new File(args[2]);
      long len = testFile.length();

      long t;
      t = System.currentTimeMillis();
      download(server, testFile, new File(args[3]));
      t = (System.currentTimeMillis() - t) / 1000;
      System.out.println("download: " + (len / t / 1000000) + " MB/s");

      t = System.currentTimeMillis();
      upload(server, new File(args[4]), new File(args[5]));
      t = (System.currentTimeMillis() - t) / 1000;
      System.out.println("upload: " + (len / t / 1000000) + " MB/s");
    }
    catch(RemoteException e)
    {
      System.err.println("Error de comunicacion: " + e.toString());
    }
    catch(Exception e)
    {
      System.err.println("Excepcion en TestClient:");
      e.printStackTrace();
    }

  }

}

