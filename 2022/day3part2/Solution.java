import java.io.*;

class Solution {

	String path;

	public Solution(String path) {
		this.path = path;
	}

	public char getBadge(String line1, String line2, String line3) {
		int[] map = new int[127];
		for (int i = 0; i < line1.length(); i++) {
			if (map[line1.charAt(i)] == 0) {
				map[line1.charAt(i)] = 1;
			}
		}

		for (int i = 0; i < line2.length(); i++) {
			if (map[line2.charAt(i)] == 1) {
				map[line2.charAt(i)] = 2;
			}
		}

		for (int i = 0; i < line3.length(); i++) {
			if (map[line3.charAt(i)] == 2) {
				return line3.charAt(i);
			}
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
			String line2 = br.readLine();
			String line3 = br.readLine();
			while (line != null) {
				char duplicate = getBadge(line, line2, line3);
				total += getPriority(duplicate);
				System.out.println(line);
				System.out.println(duplicate);
				System.out.println(total);
				line = br.readLine();
				line2 = br.readLine();
				line3 = br.readLine();
			}
			System.out.println("total is : " + total);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void testDuplicateMethod() {
		String line1 = "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn";
		String line2 = "ttgJtRGJQctTZtZT";
		String line3 = "CrZsJsPPZsGzwwsLwLmpwMDw";

		char answer = getBadge(line1, line2, line3);

		System.out.println(answer);
	}

	public static void main(String[] args) {
		Solution solving = new Solution("input.txt");

		solving.readFile();
		// solving.readFile();
	}
}
