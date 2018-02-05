import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class FibonacciHuge {
	private static long getFibonacciHugeNaive(long n, long m) {
		if (n <= 1)
			return n;

		long previous = 0;
		long current = 1;

		for (long i = 0; i < n - 1; ++i) {
			long tmp_previous = previous;
			previous = current;
			current = tmp_previous + current;
		}

		return current % m;
	}

	private static long getFibonacciHugeFast(long n, long m) {

		return 0;
	}

	public static long periodicPisano(long m) {
		long p = 0;
		List<Integer> setD = new ArrayList<>();
		if (m == 1)
			return 1;
		if (isPrime(m)) {
			p = m;
			if (p == 2)
				return 3;
			if (p == 5)
				return 20;
			if ((p % 10) == 1 || (p % 10) == 9) {
				setD = setOfDivisors(p - 1);
				if (periodic(setD, p) == 0)
					return p - 1;
				else
					return periodic(setD, p);
			} else {
				setD = setCompOfDivisors(p);
				if (periodic(setD, p) == 0)
					return 2 * (p + 1);
				else
					return periodic(setD, p);
			}
		} else {
			List<Integer> factors = primeFactors(m);
			Map<Integer, Integer> coefss = coefs(factors);
			return notPrimePeridoc(coefss);
		}
	}

	// Non-Prime numbers
	private static long notPrimePeridoc(Map<Integer, Integer> coefss) {

		Iterator<Integer> it = coefss.keySet().iterator();
		int a = 0;
		int factora = 0;
		int b = 0;
		int factorb = 0;
		if (coefss.size() == 1) {
			a = it.next();
			factora = (int) (powerN(a, coefss.get(a)) * periodicPisano(a));
			return lcm_fast(factora, 1).longValue();
		} else {
			a = it.next();
			b = it.next();
			factora = (int) (powerN(a, coefss.get(a)) * periodicPisano(a));
			factorb = (int) (powerN(b, coefss.get(b)) * periodicPisano(b));
			BigDecimal lcm = lcm_fast(factora, factorb);
			while (it.hasNext()) {
				b = it.next();
				factorb = (int) (powerN(b, coefss.get(b)) * periodicPisano(b));
				lcm = lcm_fast(lcm.intValue(), factorb);
			}
			return lcm.longValue();
		}
	}

	private static BigDecimal lcm_fast(int a, int b) {
		int gcd = GCD.euclid_gcd(a, b);
		BigDecimal bGCD = new BigDecimal(String.valueOf(gcd));
		BigDecimal bda = new BigDecimal(String.valueOf(a));
		BigDecimal bdb = new BigDecimal(String.valueOf(b));

		return bda.divide(bGCD).multiply(bdb);
	}

	private static int powerN(int number, int power) {
		if (power == 0)
			return 1;
		int result = number;

		while (power > 1) {
			result *= number;
			power--;
		}

		return result;
	}

	private static Map<Integer, Integer> coefs(List<Integer> factors) {
		Map<Integer, Integer> coef = new HashMap<>();
		Integer repeatFactor = 0;
		for (Integer factor : factors) {
			int count = 0;
			if (repeatFactor != factor) {
				for (int i = 0; i < factors.size(); i++) {
					if (factor == factors.get(i))
						count++;
				}
				if (count != 0) {
					coef.put(factor, count - 1);
				}
			}
			repeatFactor = factor;
		}
		return coef;
	}

	private static List<Integer> primeFactors(long number) {
		long n = number;
		List<Integer> factors = new ArrayList<Integer>();
		for (int i = 2; i <= n; i++) {
			while (n % i == 0) {
				factors.add(i);
				n /= i;
			}
		}
		return factors;
	}

	// Prime numbers
	private static long periodic(List<Integer> setD, long p) {
		long[][] matrixU = new long[2][2];
		matrixU[0][0] = 0;
		matrixU[0][1] = 1;
		matrixU[1][0] = 1;
		matrixU[1][1] = 1;
		for (Integer d : setD) {
			long[][] result = matrixU;
			for (int i = 1; i < d; i++)
				result = matrixMultiply(result, matrixU);
			if ((result[0][0] % p == 1) && (result[0][1] % p == 0) && (result[1][0] % p == 0)
					&& (result[1][1] % p == 1))
				return d;
		}

		return 0;
	}

	private static long[][] matrixMultiply(long[][] matrixA, long[][] matrixB) {
		long[][] result = new long[2][2];

		result[0][0] = (matrixA[0][0] * matrixB[0][0] + matrixA[0][1] * matrixB[1][0]);
		result[0][1] = (matrixA[0][0] * matrixB[0][1] + matrixA[0][1] * matrixB[1][1]);
		result[1][0] = (matrixA[1][0] * matrixB[0][0] + matrixA[1][1] * matrixB[1][0]);
		result[1][1] = (matrixA[1][0] * matrixB[0][1] + matrixA[1][1] * matrixB[1][1]);

		return result;
	}

	private static boolean isPrime(long m) {
		for (int i = 2; i < m; i++) {
			if (m % i == 0)
				return false;
		}
		return true;
	}

	private static List<Integer> setOfDivisors(long p) {
		List<Integer> setD = new ArrayList<>();
		setD.add(1);
		for (int i = 2; i < p; i++) {
			if (p % i == 0)
				setD.add(i);
		}
		return setD;
	}

	private static List<Integer> setCompOfDivisors(long p) {
		List<Integer> setD = new ArrayList<>();
		for (int i = 2; i < 2 * (p + 1); i++) {
			if ((2 * (p + 1)) % i == 0 && (p + 1) % i != 0)
				setD.add(i);
		}
		return setD;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		// long n = scanner.nextLong();

		
/*		 Scanner scanner = new Scanner(System.in); long m =
		 scanner.nextLong(); System.out.println(periodicPisano(m));
		 scanner.close();*/
		 

		List<Long> fromFile = new ArrayList<>();
		List<Long> fromFunction = new ArrayList<>();
		BufferedReader br = new BufferedReader(
				new FileReader("H:/Paschoeto/COURSERA/ALGORITHMS/semana 02/task/dados.txt"));
		while (br.ready()) {
			String linha = br.readLine().trim();
			fromFile.add(Long.parseLong(linha));
		}
		br.close();

		for (long m = 2; m <= 2001; m++)
			fromFunction.add(periodicPisano(m));
		int count = 0;
		for (int i = 0; i < fromFunction.size(); i++) {
			if (fromFile.get(i).compareTo(fromFunction.get(i)) != 0) {
				System.out.println(i + 2 + " --> " + "From File: " + fromFile.get(i) + " , " + "From Function: "
						+ fromFunction.get(i));
				count++;
			}
		}
		System.out.println("Total: " + count);
	}
}
