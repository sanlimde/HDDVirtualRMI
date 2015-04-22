import java.rmi.*;
import java.rmi.server.*;
import java.io.*;
import java.util.*;


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

  public static void upload(HDDVirtualInterf server, File src,
                            File dest) throws IOException
  {
    copy(new FileInputStream(src), server.getOutputStream(dest));
  }

  public static void download(HDDVirtualInterf server, File src,
                              File dest) throws IOException
  {
    copy(server.getInputStream(src), new FileOutputStream(dest));
  }

  public static void main(String[]args) throws Exception
  {

    if (args.length != 6)
      {
        System.err.println
          ("Uso: TestClient hostregistro numPuertoRegistro nomFichEntradaDownload nomFichSalidaDownload nomFichEntradaUpload nomFichSalidaUpload");
        return;
      }


    if (System.getSecurityManager() == null)
      System.setSecurityManager(new SecurityManager());

    try
    {

      HDDVirtualInterf server =
        (HDDVirtualInterf) Naming.lookup("//" + args[0] + ":" + args[1] +
                                         "/HDDVirtual");


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

