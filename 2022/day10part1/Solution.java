import java.io.*;

public class Solution {
	String path;
	int currentCycle = 0;
	int register = 1;
	int[] interSignals = new int[] { 40, 60, 100, 140, 180, 220 };
	int signalP = 0;
	int[] valuesAtSignal = new int[6];

	int[] cycles = new int[] { 40, 80, 120, 160, 200, 240 };
	int pointerCycles = 0;
	int crt = 0;

	char[][] imageCrt = new char[][] {
			".".repeat(40).toCharArray(),
			".".repeat(40).toCharArray(),
			".".repeat(40).toCharArray(),
			".".repeat(40).toCharArray(),
			".".repeat(40).toCharArray(),
			".".repeat(40).toCharArray() };

	public Solution(String path) {
		this.path = path;
	}

	void renderImage() {
		for (int cycle = 0; cycle < imageCrt.length; cycle++) {
			for (int c = 0; c < imageCrt[cycle].length; c++) {
				System.out.print(imageCrt[cycle][c]);
				System.out.print(" ");
			}
			System.out.println();
		}
	}

	void checkOverlpaing(int n) {
		for (int i = 0; i < n; i++) {
			if (crt <= register + 1 && crt >= register - 1) {
				imageCrt[pointerCycles][crt] = '#';
			}
			crt++;
			if (crt > 39) {
				crt = 0;
				pointerCycles++;
			}
		}
	}

	void moveSprite(String[] command) {
		if (command[0].equals("addx")) {
			currentCycle += 2;
			checkOverlpaing(2);
			register += Integer.parseInt(command[1]);
		} else {
			currentCycle += 1;
			checkOverlpaing(1);
		}
	}

	void incrementCycle(String[] command) {
		checkImportantSignal();
		if (command[0].equals("addx")) {
			currentCycle += 2;
			checkImportantSignal();
			register += Integer.parseInt(command[1]);
		} else {
			currentCycle += 1;
			checkImportantSignal();
		}
	}

	void checkImportantSignal() {
		if (currentCycle >= interSignals[signalP]) {
			System.out.println("Interesting signal crossed");
			valuesAtSignal[signalP++] = register;
		}
	}

	int calculateStrength() {
		int total = 0;
		for (int i = 0; i < interSignals.length; i++) {
			total += interSignals[i] * valuesAtSignal[i];
		}
		return total;
	}

	public void readFile() {
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();
			while (line != null) {
				System.out.println(line);
				String[] commands = line.split(" ");
				// incrementCycle(commands);
				moveSprite(commands);
				line = br.readLine();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void main(String[] args) {
		System.out.println("Hello day10");
		Solution day10 = new Solution("input.txt");
		day10.readFile();
		for (int i = 0; i < day10.valuesAtSignal.length; i++) {
			System.out.println(day10.interSignals[i] + ": " + day10.valuesAtSignal[i]);
		}
		System.out.println(day10.calculateStrength());

		day10.renderImage();
	}
}
