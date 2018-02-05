import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Change {
    private static int getChange(int m) {
    	
    	List<Integer> changers = new ArrayList<>();
    	changers.add(10);
    	changers.add(5);
    	changers.add(1);
    	int count = 0;
    	int n = m;
        while(changers.size() > 0)
        {
        	if (n - changers.get(0) >= 0)
        	{
        		n = n - changers.get(0);
        		count++;
        	}else
        		changers.remove(changers.get(0));
        	
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));
    }
}

