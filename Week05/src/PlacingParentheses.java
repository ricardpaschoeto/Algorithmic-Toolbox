import java.util.Scanner;

public class PlacingParentheses {
	
	private static char[] expArray;
	private static long[][] M;
	private static long[][] m;
	private static char[] op;
	
	private static long getMaximValue(String exp) {
		// write your code here
		int j = 0;
		expArray = exp.toCharArray();
		op = new char[(expArray.length-1)/2];
		
		for(int l = 0; l < op.length;l++)
			op[l] = expArray[2*l+1];
		
		M = new long[(expArray.length+1)/ 2][(expArray.length+1)/ 2];
		m = new long[(expArray.length+1)/ 2][(expArray.length+1)/ 2];

		for (int i = 0; i < M.length; i++) {
			m[i][i] = Character.getNumericValue(expArray[2 * i]);
			M[i][i] = Character.getNumericValue(expArray[2 * i]);
		}

		for (int s = 1; s <= M.length - 1; s++) {
			for (int i = 0; i < M.length - s; i++) {
				j = i + s;
				long[] res = minAndMax(i, j);
				m[i][j] = res[0];
				M[i][j] = res[1];
			}
		}

		return M[0][M.length-1];
	}
	
	private static long[] minAndMax(int i, int j){
		long min = Long.MAX_VALUE;
		long max = Long.MIN_VALUE;
		long a = 0;
		long b = 0;
		long c = 0;
		long d = 0;
		
		for (int k = i; k <= j-1; k++){
			a = eval(M[i][k], M[k+1][j], op[k]);
			b = eval(M[i][k], m[k+1][j], op[k]);
			c = eval(m[i][k], M[k+1][j], op[k]);
			d = eval(m[i][k], m[k+1][j], op[k]);
			min = Math.min(Math.min(Math.min(Math.min(min, a), b), c), d);
			max = Math.max(Math.max(Math.max(Math.max(max, a), b), c), d);
		}
		
		return new long[]{min,max};
	}

	private static long eval(long a, long b, char op) {
		if (op == '+') {
			return a + b;
		} else if (op == '-') {
			return a - b;
		} else if (op == '*') {
			return a * b;
		} else {
			assert false;
			return 0;
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String exp = scanner.next();
		System.out.println(getMaximValue(exp));
	}
}
