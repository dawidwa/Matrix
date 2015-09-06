import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.*;
import java.net.*;
import java.io.*;
import java.util.*;

public class Client {
	public static void main(String args[]) throws RemoteException,
			NotBoundException {

		int i, j, n;
		Registry registry = LocateRegistry.getRegistry("192.168.0.19",
				Constant.RMI_PORT);

		try {
			Matrix_mnoz rob = (Matrix_mnoz) registry.lookup(Constant.RMI_ID);
			Scanner rozmiar = new Scanner(System.in);
			System.out.println("Rozmiar macierzy");
			n = rozmiar.nextInt();
			double a[][] = new double[n][n];
			double x[][] = new double[n][n];
			System.out.println("Wprowadz macierz:");
			for (i = 0; i < n; i++) {
				for (j = 0; j < n; j++) {
					a[i][j] = rozmiar.nextInt();
				}
			}
			x = rob.transpozycja(a, n);
			if (x != null) {
				System.out.println(" wynik=");
				for (i = 0; i < n; i++) {
					for (j = 0; j < n; j++)
						System.out.print(x[i][j] + " ");
					System.out.println();
				}
			} else
				System.out.println("Dzielnik Zero");

		} catch (RemoteException e) {
			System.out.println("error");
		} catch (Exception p) {
		}

	}
}
