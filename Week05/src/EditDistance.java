import java.util.*;

class EditDistance {
  public static int EditDistance(String s, String t) {
    //write your code here
	char[] cs = s.toCharArray();
	char[] ct = t.toCharArray();
	
	int[][]  D = new int[cs.length+1][ct.length+1];
	for (int i = 0; i <= cs.length; i++)
	{
		D[i][0] = i;
	}
	
	for (int j = 0; j <= ct.length; j++)
	{
		D[0][j] = j;
	}
	
	for (int j = 1; j < ct.length+1; j++) {
		for (int i = 1; i < cs.length+1; i++) {
			int insertion = D[i][j-1]+1;
			int deletion = D[i-1][j]+1;
			int match = D[i-1][j-1];
			int mismatch = D[i-1][j-1]+1;
			if (cs[i-1] == ct[j-1])
				D[i][j] = Math.min(Math.min(insertion, deletion), match);
			else
				D[i][j] = Math.min(Math.min(insertion, deletion), mismatch);
		}
	}
    return D[cs.length][ct.length];
  }
  
  public static void main(String args[]) {
    Scanner scan = new Scanner(System.in);

    String s = scan.next();
    String t = scan.next();

    System.out.println(EditDistance(s, t));
  }

}
