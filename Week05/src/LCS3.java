import java.util.*;

public class LCS3 {

    private static int lcs3(int[] a, int[] b, int[] c) {
        //Write your code here
    	List<int[]> inputs = new ArrayList<>();
    	List<Integer> results = new ArrayList<>();
    	inputs.add(a);
    	inputs.add(b);
    	inputs.add(c);

    	for (int i = 0; i < inputs.size()-1;i++)
    		for(int j = i+1; j < inputs.size();j++) {
    			results.add(editDistance(inputs.get(i), inputs.get(j)));
    		}
    	Collections.sort(results);
    	return results.get(0);

    	
        //return Math.min(Math.min(a.length, b.length), c.length);
    }
    
    private static int editDistance(int[] a, int[] b) {
    	int[][]  D = new int[a.length+1][b.length+1];
    	for (int i = 0; i <= a.length; i++)
    	{
    		D[i][0] = i;
    	}
    	
    	for (int j = 0; j <= b.length; j++)
    	{
    		D[0][j] = j;
    	}
    	
    	for (int j = 1; j < b.length+1; j++) {
    		for (int i = 1; i < a.length+1; i++) {
    			int insertion = D[i][j-1]+1;
    			int deletion = D[i-1][j]+1;
    			int match = D[i-1][j-1];
    			int mismatch = D[i-1][j-1]+1;
    			if (a[i-1] == b[j-1])
    				D[i][j] = Math.min(Math.min(insertion, deletion), match);
    			else
    				D[i][j] = Math.min(Math.min(insertion, deletion), mismatch);
    		}
    	}
    	return D[a.length][b.length];
    }
    

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int an = scanner.nextInt();
        int[] a = new int[an];
        for (int i = 0; i < an; i++) {
            a[i] = scanner.nextInt();
        }
        int bn = scanner.nextInt();
        int[] b = new int[bn];
        for (int i = 0; i < bn; i++) {
            b[i] = scanner.nextInt();
        }
        int cn = scanner.nextInt();
        int[] c = new int[cn];
        for (int i = 0; i < cn; i++) {
            c[i] = scanner.nextInt();
        }
        System.out.println(lcs3(a, b, c));
    }
}

