import java.io.*;

public class Solution {
	String path;

	public Solution(String path) {
		this.path = path;
	}

	public void readImage() {
		try (BufferedReader br = new BufferedReader(new FileReader(this.path))) {
			String line = br.readLine();
			int totalStacks = (line.length() + 1) / 4;
			System.out.println("there are " + totalStacks + " total stacks");
			while (!line.startsWith(" 1")) {
				readImageLevel(line);
				line = br.readLine();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void readImageLevel(String line) {
		for (int i = 1, stack = 0; i < line.length(); i += 4, stack++) {
			if (line.charAt(i) != ' ') {
				System.out.println("adding " + line.charAt(i) + " to stack" + (stack + 1));
			}
		}
	}

	public void readFile() {
		try (BufferedReader br = new BufferedReader(new FileReader(this.path))) {
			String line = br.readLine();
			int totalStacks = (line.length() + 1) / 4;
			while (!line.startsWith(" 1")) { // read until the end of the image
				System.out.println("it has " + totalStacks);
				System.out.println(line);
				line = br.readLine();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void main(String[] args) {
		System.out.println("Day 5");
		Solution solving = new Solution("test.txt");
		solving.readImage();
	}
}