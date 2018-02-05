import java.util.*;

public class Knapsack {
    static int optimalWeight(int W, int[] w) {
        //write you code here
    	long[][]  value = new long[W+1][w.length+1];

    	for(int i = 1; i < w.length+1; i++ ) {
    		for(int wm = 1; wm < W+1; wm++ ) {
    			value[wm][i] = value[wm][i-1];
    			if(w[i-1] <= wm) {
    				long val = value[wm - w[i-1]][i-1] + w[i-1];
    				if(value[wm][i] < val)
    					value[wm][i] = val;
    			}
    		}
    	}
    	
        return (int) value[W][w.length];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        System.out.println(optimalWeight(W, w));
    }
}

