import java.rmi.*;
import java.rmi.server.*;

public class Matrix extends UnicastRemoteObject implements Matrix_mnoz {
	/**
	 * 
	 */
	static double eps = 1e-12;

	public boolean licz(int n, double[][] A) {
		WThread[] Threads = new WThread[n];
		int i, j, k;
		int num_threads = n;
		int rows = n / num_threads;
		int start = 0;
		int end = rows;
		for (int t = 0; t < num_threads; t++) {

			Threads[t] = new WThread(start, end, n, A);
			Threads[t].start();
			start = end;
			end = start + rows;

			try {
				Threads[t].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return true;
	}

	// Funkcja sprawdza wyznacznik
	// ------------------------------------------------
	public boolean oblicz(int k, int n, double[][] A, double[][] X) {
		int i, j;
		double s = 0;

		for (i = 1; i < n; i++) {
			for (j = 0; j < i; j++)
				s = A[i][j] * X[j][k];

			X[i][k] -= s;
		}

		if (Math.abs(A[n - 1][n - 1]) < eps)
			return false;

		for (i = n - 2; i >= 0; i--) {
			for (j = i + 1; j < n; j++)
				s = A[i][j] * X[j][k];

			if (Math.abs(A[i][i]) < eps)
				return false;

			X[i][k] = (X[i][k] - s) / A[i][i];
		}

		return true;
	}

	int i, j, k;
	int c[][] = new int[2][2];

	public Matrix() throws RemoteException {
		super();
	}

	public double[][] transpozycja(double A[][], int n) throws RemoteException {
		double[][] X = new double[n][n];
		System.out.println("Macierz Odwrocona:");
		boolean ok;
		if (licz(n, A)) {

			// tworzymy macierz jednostkową w X

			for (i = 0; i < n; i++) {
				for (j = 0; j < n; j++)
					X[i][j] = 0;
				X[i][i] = 1;
			}

			// Wyznaczamy kolejne kolumny macierzy odwrotnej w X

			ok = true;
			for (i = 0; i < n; i++)
				if (!oblicz(i, n, A, X)) {
					ok = false;
					break;
				}
		} else
			ok = false;
		// jeśli obliczenia się powiodły, wyświetlamy X

		if (ok) {
			for (i = 0; i < n; i++) {
				for (j = 0; j < n; j++)
					System.out.print(X[i][j] + " ");
				System.out.println();
			}
			return X;
		} else
			System.out.println("Dzielnik Zero");
		return null;
	}
}
