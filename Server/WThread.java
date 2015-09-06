public class WThread extends Thread {

	private double[][] A;
	private int end, start, n;
	static double eps = 0.0001;

	public WThread(int start, int end, int n, double[][] A) {
		this.start = start;
		this.end = end;
		this.n = n;
		this.A = A;

	}

	public void run() {
		for (int k = start; k < end; k++) {
			if (Math.abs(A[k][k]) < eps)
				break;

			for (int i = k + 1; i < n; i++)
				A[i][k] /= A[k][k];
		}
		for (int k = start; k < end; k++) {
			for (int i = k + 1; i < n; i++)
				for (int j = k + 1; j < n; j++)
					A[i][j] -= A[i][k] * A[k][j];
		}

	}
}