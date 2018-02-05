import java.util.*;

public class PrimitiveCalculator {
	private static List<Integer> optimal_sequence(int n) {
		int[] nop = numMinOp(n);
		List<Integer> sequence = new ArrayList<>();
		sequence.add(n);
		while (n > 1) {
			if (nop[n - 2] == nop[n - 1] - 1) {
				n = n - 1;
				sequence.add(n);
			}
			if (n % 2 == 0 && nop[(n-2) / 2] == nop[n - 1] - 1) {
				n = n / 2;
				sequence.add(n);
			}
			if (n % 3 == 0 && nop[(n-2) / 3] == nop[n - 1] - 1) {
				n = n / 3;
				sequence.add(n);
			}

		}
		Collections.reverse(sequence);
		return sequence;

	}

	private static int[] numMinOp(int n) {
		int[] MinNumOps = new int[n];
		int NumOp = 0;
		for (int m = 2; m <= n; m++) {
			MinNumOps[m - 1] = Integer.MAX_VALUE;
			List<Integer> quocients = new ArrayList<Integer>();
			if (m % 3 == 0)
				quocients.add(0, m / 3);
			else
				quocients.add(0, -1);
			if (m % 2 == 0)
				quocients.add(1, m / 2);
			else
				quocients.add(1, -1);
			quocients.add(2, m - 1);

			for (Integer q : quocients) {
				if (q != -1) {
					if (m >= q) {
						NumOp = MinNumOps[q - 1] + 1;
						if (NumOp < MinNumOps[m - 1]) {
							MinNumOps[m - 1] = NumOp;
						}
					}
				}
			}
			quocients = null;
		}

		return MinNumOps;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		List<Integer> sequence = optimal_sequence(n);
		System.out.println(sequence.size() - 1);
		for (Integer x : sequence) {
			System.out.print(x + " ");
		}
	}
}
