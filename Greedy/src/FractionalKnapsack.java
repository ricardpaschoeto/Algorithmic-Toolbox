import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class FractionalKnapsack {
	private static BigDecimal getOptimalValue(int capacity, int[] values, int[] weights) {
		Map<Integer, BigDecimal> ratios = new HashMap<>();
		BigDecimal bdNum;
		BigDecimal bdDen;
		BigDecimal value = new BigDecimal("0");
		int a = 0;

		for (int i = 0; i < values.length; i++) {
			bdNum = new BigDecimal(String.valueOf(values[i]));
			bdDen = new BigDecimal(String.valueOf(weights[i]));
			ratios.put(weights[i], bdNum.divide(bdDen,5,RoundingMode.HALF_DOWN));
		}
		// Sorting
		List<Entry<Integer, BigDecimal>> sorted = entriesSortedByValues(ratios);

		for (Entry<Integer, BigDecimal> entry : sorted) {

			if (capacity == 0)
				return value;
			a = Math.min(entry.getKey(), capacity);
			value = value.add(entry.getValue().multiply(new BigDecimal(String.valueOf(a))));
			capacity -= a;

		}

		return value;

	}

	public static <K, V extends Comparable<? super V>> List<Entry<K, V>> entriesSortedByValues(Map<K, V> map) {

		List<Entry<K, V>> sortedEntries = new ArrayList<Entry<K, V>>(map.entrySet());

		Collections.sort(sortedEntries, new Comparator<Entry<K, V>>() {
			@Override
			public int compare(Entry<K, V> e1, Entry<K, V> e2) {
				return e2.getValue().compareTo(e1.getValue());
			}
		});

		return sortedEntries;
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int capacity = scanner.nextInt();
		int[] values = new int[n];
		int[] weights = new int[n];
		for (int i = 0; i < n; i++) {
			values[i] = scanner.nextInt();
			weights[i] = scanner.nextInt();
		}
		System.out.println(getOptimalValue(capacity, values, weights));
	}

}
