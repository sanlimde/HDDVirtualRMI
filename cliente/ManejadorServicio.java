import java.io.*;
import java.rmi.*;


/**
 * Created by jose on 25/4/15.
 */
public class ManejadorServicio {

    /**
     * Tamaño del búffer de copia
     */
    final public static int BUF_SIZE = 1024 * 64;

    /**
     * Objeto que representa el servicio a manejar
     */
    private ServicioFichero servicio;


    ManejadorServicio(ServicioFichero serv) {
        servicio = serv;
    }

    private static void copy(InputStream in, OutputStream out) throws IOException {
        byte[] b = new byte[BUF_SIZE];
        int len;
        while ((len = in.read(b)) >= 0) {
            out.write(b, 0, len);
        }
        in.close();
        out.close();
    }

    /**
     * Método que permite enviar un fichero
     *
     * @throws IOException
     */
    public void pushFichero(File f) throws IOException {

        OutputStream ficheroServidor = servicio.getOutputStream(f.getName());


        copy(new FileInputStream(f), ficheroServidor);

    }

    /**
     * Método que permite recibir un fichero
     *
     * @throws IOException
     */
    public void pullFichero(File f) throws IOException {

        InputStream ficheroServidor = servicio.getInputStream(f.getName());

        copy(ficheroServidor, new FileOutputStream(f));

    }
}