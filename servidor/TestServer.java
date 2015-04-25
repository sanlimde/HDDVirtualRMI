import java.rmi.*;

/**
 * Para su ejecución:
 * java -Djava.security.policy=servidor.permisos ServidorEco 54321
 */
class TestServer  {

    static public void main (String args[]) {

        // Comprobamos el número de argumentos
        if (args.length!=1) {
            // En caso de error mostramos una sugerencia
            System.err.println("Uso: TestServer numPuertoRegistro");
            return;
        }

        // Obtenemos el gestor de seguridad, en caso de que sea nulo
        if (System.getSecurityManager() == null) {
            // Establecemos uno nuevo
            System.setSecurityManager(new RMISecurityManager());
        }

        try {
            // Creamos el servicio HDDVirtualImpl
            HDDVirtualImpl srv = new HDDVirtualImpl();
            // Y lo registramos en el puerto especificado con el nombre HDDVirtual
            Naming.rebind("rmi://localhost:" + args[0] + "/HDDVirtual", srv);
        }
        catch (RemoteException e) {
            System.err.println("Error de comunicacion: " + e.toString());
            System.exit(1);
        }
        catch (Exception e) {
            System.err.println("Excepcion en TestServer:");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
