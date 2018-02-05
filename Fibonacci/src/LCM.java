import java.math.BigDecimal;
import java.util.*;

public class LCM {
  private static long lcm_naive(int a, int b) {
    for (long l = 1; l <= (long) a * b; ++l)
      if (l % a == 0 && l % b == 0)
        return l;

    return (long) a * b;
  }
  
  private static BigDecimal lcm_fast(int a, int b)
  {
	  int gcd = euclid_gcd(a, b);
	  BigDecimal bGCD = new BigDecimal(String.valueOf(gcd));
	  BigDecimal bda = new BigDecimal(String.valueOf(a));
	  BigDecimal bdb = new BigDecimal(String.valueOf(b));
	  	  
	  return bda.divide(bGCD).multiply(bdb);
  }
  
	public static int euclid_gcd(int a, int b) {
		if (b == 0)
			return a;
		return euclid_gcd(b, a % b);
	}

  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    int a = scanner.nextInt();
    int b = scanner.nextInt();

    //System.out.println(lcm_naive(a, b));
/*	Random gerador = new Random();
	while(true)
	{
		int a = gerador.nextInt(2000000001);
		int b = gerador.nextInt(2000000001);
		System.out.println("for a and b: " + a + " " + b);
		System.out.println(lcm_fast(a, b));
	}*/
    System.out.println(lcm_fast(a, b));
    scanner.close();
  }
}
