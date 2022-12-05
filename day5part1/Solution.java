import java.io.*;
import java.util.*;

public class Solution {
	String path;
	List<Stack<Character>> elfStacks = new ArrayList<Stack<Character>>();

	public Solution(String path) {
		this.path = path;
	}

	public void initializeStacks(int total) {
		while (total > 0) {
			elfStacks.add(new Stack<Character>());
			total--;
		}
	}

	public void readImage() {
		try (BufferedReader br = new BufferedReader(new FileReader(this.path))) {
			String line = br.readLine();
			int totalStacks = (line.length() + 1) / 4;
			initializeStacks(totalStacks);
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
				elfStacks.get(stack).push(line.charAt(i));
				System.out.println("adding " + line.charAt(i) + " to stack" + (stack + 1));
			}
		}
	}

	public void readFile() {
		try (BufferedReader br = new BufferedReader(new FileReader(this.path))) {
			String line = br.readLine();
			while (!line.startsWith(" 1")) { // read until the end of the image
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
		// checking if the elf stacks were added

		for (Stack<Character> elfStack : solving.elfStacks) {
			System.out.println(Arrays.toString(elfStack.toArray()));
		}
	}
}
