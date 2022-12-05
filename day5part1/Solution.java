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

	public String getTopStacks() {
		StringBuilder answer = new StringBuilder();
		for (int i = 0; i < elfStacks.size(); i++) {
			char curr = elfStacks.get(i).peek();
			answer.append(curr);
		}
		return answer.toString();
	}

	public void makeMove(int fromStack, int toStack) {
		char crate = elfStacks.get(fromStack).pop();
		elfStacks.get(toStack).push(crate);
	}

	public void makeMultipeMoves(int fromStack, int toStack, int manyTimes) {
		while (manyTimes > 0) {
			makeMove(fromStack, toStack);
			manyTimes--;
		}
	}

	public void makeMultipeMovesIn9001(int fromStack, int toStack, int manyTimes) {
		Stack<Character> holder = new Stack<>();
		while (manyTimes > 0) {
			char crate = elfStacks.get(fromStack).pop();
			holder.push(crate);
			manyTimes--;
		}

		while (!holder.isEmpty()) {
			char crate = holder.pop();
			elfStacks.get(toStack).push(crate);
		}
	}

	public int[] parseInstruction(String line) {
		int[] instructions = new int[3];
		String[] parts = line.split(" ");
		instructions[0] = Integer.parseInt(parts[1]);
		instructions[1] = Integer.parseInt(parts[3]) - 1;
		instructions[2] = Integer.parseInt(parts[5]) - 1;
		return instructions;
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

	public void readInstructions() {
		try (BufferedReader br = new BufferedReader(new FileReader(this.path))) {
			String line = br.readLine();
			while (!line.startsWith(" 1")) {
				line = br.readLine();
			}
			line = br.readLine();
			line = br.readLine();
			while (line != null) {
				int[] instructions = parseInstruction(line);
				makeMultipeMovesIn9001(instructions[1], instructions[2], instructions[0]);
				System.out.println(line);
				line = br.readLine();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void readImageLevel(String line) {
		for (int i = 1, stack = 0; i < line.length(); i += 4, stack++) {
			if (line.charAt(i) != ' ') {
				elfStacks.get(stack).add(0, line.charAt(i));
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
		Solution solving = new Solution("input.txt");
		solving.readImage();
		System.out.println("BEFORE");
		for (Stack<Character> elfStack : solving.elfStacks) {
			System.out.println(Arrays.toString(elfStack.toArray()));
		}
		solving.readInstructions();
		System.out.println("AFTER");
		// checking if the elf stacks were added

		for (Stack<Character> elfStack : solving.elfStacks) {
			System.out.println(Arrays.toString(elfStack.toArray()));
		}
		String answer = solving.getTopStacks();
		System.out.println(answer);
	}
}
