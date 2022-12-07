import java.io.*;

class Solution {

	String path;

	public Solution(String path) {
		this.path = path;
	}

	public char getDuplicateChar(String line) {
		int stockLen = line.length() / 2;
		int[] map1 = new int[127];
		int[] map2 = new int[127];
		for (int start = 0; start < stockLen; start++) {
			char stock1 = line.charAt(start);
			char stock2 = line.charAt(start + stockLen);

			map1[stock1]++;
			map2[stock2]++;

			if (map1[stock2] > 0)
				return stock2;
			if (map2[stock1] > 0)
				return stock1;
		}

		return '.';
	}

	public int getPriority(char letter) {
		if (letter > 96) {
			return letter - 96;
		} else if (letter < 91 && letter > 47) {
			return letter - 38;
		}
		return 0;
	}

	public void readFile() {
		int total = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(this.path))) {
			String line = br.readLine();
			while (line != null) {
				char duplicate = getDuplicateChar(line);
				total += getPriority(duplicate);
				System.out.println(line);
				System.out.println(duplicate);
				System.out.println(total);
				line = br.readLine();
			}
			System.out.println("total is : " + total);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void testDuplicateMethod() {
		String line = "ttgJtRGJQctTZtZT";
		String line2 = "aa";
		char answer = getDuplicateChar(line);
		char answer2 = getDuplicateChar(line2);
		System.out.println(answer);
		System.out.println(getPriority(answer));
		System.out.println(answer2);
		System.out.println(getPriority(answer2));
	}

	public static void main(String[] args) {
		Solution solving = new Solution("input.txt");

		solving.testDuplicateMethod();
		solving.readFile();
	}
}
