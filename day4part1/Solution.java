import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class Solution {
	String path;

	public Solution(String path) {
		this.path = path;
	}

	public boolean isSubset(int[] range1, int[] range2) {
		if ((range1[0] >= range2[0] && range1[1] <= range2[1]) ||
				(range2[0] >= range1[0] && range2[1] <= range1[1]))
			return true;
		return false;
	}

	public boolean dontOverlap(int[] range1, int[] range2) {
		if ((range1[1] < range2[0] && range1[0] < range2[0]) ||
				(range2[1] < range1[0] && range2[0] < range1[0]))
			return true;
		return false;
	}

	public int[][] parseRanges(String line) {

		String[] splitRanges = line.split(",");
		String[] ran1 = splitRanges[0].split("-");
		String[] ran2 = splitRanges[1].split("-");
		int[] range1 = { Integer.parseInt(ran1[0]), Integer.parseInt(ran1[1]) };

		int[] range2 = { Integer.parseInt(ran2[0]), Integer.parseInt(ran2[1]) };
		return new int[][] { range1, range2 };
	}

	public void readFile() {
		int total = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(this.path))) {
			String line = br.readLine();
			while (line != null) {
				int[][] ranges = parseRanges(line);
				if (!dontOverlap(ranges[0], ranges[1]))
					total++;
				line = br.readLine();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("subset total lines " + total);
	}

	boolean overlapTest() {
		int[] r1 = { 2, 8 };
		int[] r2 = { 0, 3 };

		boolean test1 = dontOverlap(r1, r2);
		boolean test2 = dontOverlap(r2, r1);

		return test1 && test2;
	}

	public boolean testIsSubset() {
		int[] r1 = { 2, 4 };
		int[] r2 = { 3, 6 };

		boolean test1 = isSubset(r1, r2);
		boolean test2 = isSubset(r2, r1);

		return test1 && test2;
	}

	public void testParseRanges() {
		String testLine = "6-6,4-6";

		int[][] ranges = parseRanges(testLine);

		System.out.println(Arrays.toString(ranges[0]));

		System.out.println(Arrays.toString(ranges[1]));

		System.out.println(isSubset(ranges[0], ranges[1]));
	}

	public static void main(String[] args) {
		System.out.println("Day4");

		Solution solving = new Solution("input.txt");
		solving.readFile();
	}
}
