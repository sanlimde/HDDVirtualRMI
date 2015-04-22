import java.rmi.*;
import java.rmi.server.*;

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
            HDDVirtual srv = new HDDVirtual();
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
