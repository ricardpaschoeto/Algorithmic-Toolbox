import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class fibonacci {
/*  private static long calc_fib(int n) {
    if (n <= 1)
      return n;

    return calc_fib(n - 1) + calc_fib(n - 2);
  }*/
  
/*  public static long calc_fib_fast(int n)
  {
	  if (n == 0)
		  return 0;
	  long[] vector = new long[n + 1];
	  vector[0] = 0;
	  vector[1] = 1;
	  for(int i = 2; i <= n; i++)
	  {
		  vector[i] = vector[i - 1] + vector[i - 2];
	  }
	  return vector[n];
  }*/

  public static long calc_fib_fast(long n)
  {
	  if (n == 0)
		  return 0;
	  List<Long> vector = new ArrayList<Long>();
	  vector.add((long) 0);
	  vector.add((long) 1);
	  for(int i = 2; i <= n; i++)
	  {
		  vector.add(i, vector.get(i-1) + vector.get(i-2));
	  }
	  return vector.get(vector.size()-1);
  }
  public static void main(String args[]) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();

    //System.out.println(calc_fib(n));
    System.out.println(calc_fib_fast(n));
    in.close();
  }
}
