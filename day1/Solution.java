import java.io.*;

public class Solution {
	String file;
	long sumMax = 0;

	long firstPlace = 0;
	long secondPlace = 0;
	long thirdPlace = 0;

	public Solution(String input) {
		this.file = input;
	}

	public void readFile() {
		try (BufferedReader br = new BufferedReader(new FileReader(this.file))) {
			String line = br.readLine();

			int currentSum = 0;
			while (line != null) {
				if (line.isEmpty()) {
					if (currentSum >= firstPlace) {
						long holder = firstPlace;
						long holder2 = secondPlace;
						firstPlace = currentSum;
						secondPlace = holder;
						thirdPlace = holder2;
					} else if (currentSum >= secondPlace && currentSum < firstPlace) {
						long holder = secondPlace;
						secondPlace = currentSum;
						thirdPlace = holder;
					} else if (currentSum >= thirdPlace && currentSum < secondPlace) {
						thirdPlace = currentSum;
					}
					System.out.println("empty line");
					System.out.println("total sum is: " + currentSum);
					currentSum = 0;

				} else {
					currentSum += Integer.parseInt(line);
				}
				System.out.println(line);
				line = br.readLine();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void main(String[] args) {
		Solution solving = new Solution("input.txt");
		solving.readFile();
		System.out.println("first place: " + solving.firstPlace + " second place: " + solving.secondPlace
				+ " third place:" + solving.thirdPlace);

		System.out.println("total " + (solving.firstPlace + solving.secondPlace + solving.thirdPlace));
		System.out.println(System.getProperty("user.dir"));
	}
}
