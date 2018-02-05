import java.io.*;
import java.util.*;

public class Sorting {
	private static Random random = new Random();

	private static int[] partition3(int[] a, int low, int high) {
		// write your code here
		int index_i = 0;
		int index_j = 0;
		if (high - low <= 1) {
			if(a[high] < a[low])
				a = swap(a, low, high);
			index_i = low;
			index_j = high;
			return new int[] {index_i, index_j};
		}
		int mid = low;
		int pivot = a[low];
		while(mid <= high) {
			if (a[mid] < pivot)
				a = swap(a, low++, mid++);
			else if (a[mid] == pivot)
				mid++;
			else if (a[mid] > pivot)
				a = swap(a, mid, high--);
		}
		index_i = low-1;
		index_j = mid;
		return new int[]{ index_i, index_j };
	}

	private static int partition2(int[] a, int l, int r) {
		int x = a[l];
		int j = l;
		for (int i = l + 1; i <= r; i++) {
			if (a[i] <= x) {
				j++;
				a = swap(a, i, j);
			}
		}
		a = swap(a, l, j);
		return j;
	}

	private static int[] swap(int a[], int index1, int index2) {

		int t = a[index1];
		a[index1] = a[index2];
		a[index2] = t;
		return a;
	}

	private static void randomizedQuickSort(int[] a, int l, int r) {
		if (l >= r) {
			return;
		}
		int k = random.nextInt(r - l + 1) + l;
		a = swap(a, l, k);
		// use partition3
		//int m = partition2(a, l, r);
		int[] m = partition3(a, l, r);
		randomizedQuickSort(a, l, m[0]);
		randomizedQuickSort(a, m[1], r);
	}

	public static void main(String[] args) {
		FastScanner scanner = new FastScanner(System.in);
		int n = scanner.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = scanner.nextInt();
		}
		randomizedQuickSort(a, 0, n - 1);
		for (int i = 0; i < n; i++) {
			System.out.print(a[i] + " ");
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
