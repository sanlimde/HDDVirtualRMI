import java.rmi.*;

/**
 * Para su ejecución:
 * java -Djava.security.policy=servidor.permisos TestServer 54321
 */
class TestServer  {

    static public void main (String args[]) {

        if (args.length!=1) {
            System.err.println("Uso: TestServer numPuertoRegistro");
            return;
        }

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }

        try {

            HDDVirtualImpl srv = new HDDVirtualImpl();
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
