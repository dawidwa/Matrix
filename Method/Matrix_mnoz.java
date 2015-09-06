import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Matrix_mnoz extends Remote {
	public double[][] transpozycja(double a[][], int m) throws RemoteException;
}