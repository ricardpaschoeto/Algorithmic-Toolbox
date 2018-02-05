import java.util.*;

public class CoveringSegments {

	private static List<Long> optimalPoints(Segment[] segments) {
		// write your code here
		long[] outermost = new long[segments.length];
		List<Long> points = new ArrayList<Long>();
		int count1 = 0;
		int count2 = 0;
		for (int i = 0; i < segments.length; i++) {
			outermost[i] = segments[i].end;
		}

		Arrays.sort(outermost);

		for (Segment segment : segments) {
			if (outermost[0] <= segment.end && outermost[0] >= segment.start)
				count1++;
		}
		
		points.add(outermost[outermost.length - 1]);
		if (count1 > 1 && count1 != segments.length) {
			points.add(outermost[0]);
			for (int i = outermost.length - 1; i > 0; i--) {
				int count3 = 0;
				for (Segment segment : segments) {
					if (outermost[outermost.length - 1] <= segment.end
							&& outermost[outermost.length - 1] >= segment.start) {
						count2 = Math.max(count2, count3++);
						points.remove(outermost[i]);
						points.add(outermost[i]);
					}
				}
			}
		}

		return points;
	}

	private static class Segment {
		long start, end;

		Segment(long start, long end) {
			this.start = start;
			this.end = end;
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		Segment[] segments = new Segment[n];
		for (int i = 0; i < n; i++) {
			long start, end;
			start = scanner.nextInt();
			end = scanner.nextInt();
			segments[i] = new Segment(start, end);
		}
		List<Long> points = optimalPoints(segments);
		System.out.println(points.size());
		for (long point : points) {
			System.out.print(point + " ");
		}
		scanner.close();
	}
}
