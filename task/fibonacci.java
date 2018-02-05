import java.util.Scanner;

public class fibonacci {
  private static long calc_fib(int n) {
    if (n <= 1)
      return n;

    return calc_fib(n - 1) + calc_fib(n - 2);
  }
  
  private static long calc_fib_fast(int n)
  {
	  if (n == 0)
		  return 0;
	  if (n < 0 || n > 45)
	  {
		  System.out.println("Exceed Bounds");
		  return -1;
	  }
	  int[] vector = new int[n + 1];
	  vector[0] = 0;
	  vector[1] = 1;
	  for(int i = 2; i <= n; i++)
	  {
		  vector[i] = vector[i - 1] + vector[i - 2];
	  }
	  return (long)vector[n];
  }

  public static void main(String args[]) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();

    System.out.println(calc_fib_fast(n));
    in.close();
  }
}
