import java.rmi.*;
import java.io.*;
import java.util.Objects;

/**
 * Para su ejecución:
 * java -Djava.security.policy=cliente.permisos ServidorEco 54321
 */

public class TestClient
{



    public static void main(String[]args) throws Exception
    {

 /*   if (args.length != 6)
      {
        System.err.println
          ("Uso: TestClient hostregistro numPuertoRegistro nomFichEntradaDownload nomFichSalidaDownload nomFichEntradaUpload nomFichSalidaUpload");
        return;
      }
*/
        if(args.length != 5){
            System.err.println("Uso: TestClient <host puerto nombreCliente [-d (para descargar) / -s (para subir)] nombreFichero>");
        }

        if (System.getSecurityManager() == null)
            System.setSecurityManager(new SecurityManager());

        try
        {

            // Obtenemos el servicio de HDDVirtual
            HDDVirtual server =
                    (HDDVirtual) Naming.lookup("//" + args[0] + ":" + args[1] +
                            "/HDDVirtual");

            // Creamos un nuevo cliente para el servidor
            Cliente client = new Cliente(args[2]);

            // Creamos un nuevo servicio de ficheros para el cliente
            ServicioFichero sf = server.crearServicio(client);

            long len;
            long t;

            // Creamos un nuevo manejador de servicio
            ManejadorServicio manejador = new ManejadorServicio(sf);
            File testFile;

            // Comprobamos si es una descarga
            if("-d".compareTo(args[3]) == 0){
                System.out.println("Se va a realizar una descarga");
                testFile = new File(args[4]);
                len = testFile.length();
                t = System.currentTimeMillis();
                manejador.pullFichero(testFile);
                t = (System.currentTimeMillis() - t) / 1000;
                System.out.println("Descarga de " + args[4] + " : " + (len / t / 1000000) + " MB/s");

            }// En caso contrario comprobamos si es una subida
            else if ("-s".compareTo(args[3]) == 0){
                System.out.println("Se va a realizar una subida");
                testFile = new File(args[4]);
                len = testFile.length();
                t = System.currentTimeMillis();
                manejador.pushFichero(testFile);
                t = (System.currentTimeMillis() - t) / 1000;
                System.out.println("Subida de " + args[4] + " : " + (len / t / 1000000) + " MB/s");


            }
            else if("-l".compareTo(args[3]) == 0){


            }
            else{ // En caso contrario mostramos un error
                System.err.println("No se ha introducido el parámetro [-d (para descargar) / -s (para subir)] correctamente");
            }

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

