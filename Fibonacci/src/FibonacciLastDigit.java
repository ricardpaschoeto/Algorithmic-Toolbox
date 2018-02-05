import java.util.*;

public class FibonacciLastDigit {
    private static int getFibonacciLastDigitFast(int n) {
        if (n <= 1)
            return n;

        int[] vector = new int[n+1];
        vector[0] = 0;
        vector[1] = 1;

        for (int i = 2; i <= n; i++) {
        	vector[i] = (vector[i-1] + vector[i-2])%10; 
        }

        return vector[n];
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int c = getFibonacciLastDigitFast(n);
        System.out.println(c);
        scanner.close();
    }
}

