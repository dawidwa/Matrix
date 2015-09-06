import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.net.*;

public class Server {
	public static void main(String args[]) {
		try {
			Matrix mnoz = new Matrix();
			Registry registry = LocateRegistry
					.createRegistry(Constant.RMI_PORT);
			registry.bind(Constant.RMI_ID, mnoz);
			// Naming.rebind("MatrixOP",mnoz);

			System.out.println("Server is waiting");

		} catch (RemoteException e) {
		} catch (Exception a) {
		}
	}
}
