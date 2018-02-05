import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.io.*;

public class MajorityElement {

	private static Map<Integer, Integer> data = new HashMap<>();

	private static int getMajorityElement(int[] a, int left, int right) {
		if (left == right) {
			return -1;
		}

		if (left + 1 == right) {
			return a[left];
		}

		// write your code here
		for (int i = 0; i < a.length; i++) {
			if (!data.containsKey(a[i]))
				data.put(a[i], 1);
			else
				data.put(a[i], data.get(a[i]) + 1);
		}

		LinkedHashMap<Integer, Integer> dataLinked = (LinkedHashMap<Integer, Integer>) sortByValue(data);
		List<Entry<Integer, Integer>> entryList = new ArrayList<Map.Entry<Integer, Integer>>(dataLinked.entrySet());
		Entry<Integer, Integer> lastEntry = entryList.get(entryList.size() - 1);

		if (lastEntry.getValue() > a.length / 2)
			return 1;
		else
			return -1;

	}

	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		return map.entrySet().stream().sorted(Map.Entry.comparingByValue())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}

	public static void main(String[] args) {

		FastScanner scanner = new FastScanner(System.in);
		int n = scanner.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = scanner.nextInt();
		}
		if (getMajorityElement(a, 0, a.length) != -1) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}
	}

	static class FastScanner {
		BufferedReader br;
		StringTokenizer st;

		FastScanner(InputStream stream) {
			try {
				br = new BufferedReader(new InputStreamReader(stream));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		String next() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}
	}
}
