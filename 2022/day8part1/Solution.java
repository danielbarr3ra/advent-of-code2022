import java.util.*;
import java.io.*;

public class Solution {
    String path;
    List<ArrayList<Integer>> grid = new ArrayList<>();
    int columns = 0;
    int rows = 0;

    public Solution(String path) {
        this.path = path;
    }

    public void readFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(this.path))) {
            String line = br.readLine();
            while (line != null) {
                ArrayList<Integer> row = new ArrayList<>();
                for (int i = 0; i < line.length(); i++) {
                    Integer x = Character.getNumericValue(line.charAt(i));
                    row.add(x);
                }
                grid.add(row);
                line = br.readLine();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        this.rows = grid.size();
        this.columns = grid.get(0).size();
    }

    public boolean checkRight(int r, int c, int h) {
        int curr = grid.get(r).get(c);
        if (c == columns - 1) {
            return h > curr;
        } else if (h > curr) {
            return checkRight(r, c + 1, h);
        }
        return false;
    }

    public boolean checkLeft(int r, int c, int h) {
        int curr = grid.get(r).get(c);
        if (c == 0) {
            return h > curr;
        } else if (h > curr) {
            return checkLeft(r, c - 1, h);
        }
        return false;
    }

    public boolean checkBottom(int r, int c, int h) {
        int curr = grid.get(r).get(c);

        if (r == rows - 1) {
            return h > curr;
        } else if (h > curr) {
            return checkBottom(r + 1, c, h);
        }
        return false;
    }

    public boolean checkTop(int r, int c, int h) {
        int curr = grid.get(r).get(c);

        if (r == 0) {
            return h > curr;
        } else if (h > curr) {
            return checkTop(r - 1, c, h);
        }
        return false;
    }

    public boolean isVisible(int r, int c) {
        int curr = grid.get(r).get(c);
        if (r == 0 || c == 0 || r == rows - 1 || c == columns - 1) {

            return true;
        }
        if (checkTop(r - 1, c, curr) || checkBottom(r + 1, c, curr) || checkLeft(r, c - 1, curr)
                || checkRight(r, c + 1, curr)) {

            return true;
        }
        return false;
    };

    public int countTotal() {
        int total = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {

                if (isVisible(r, c))
                    total += 1;
            }
        }
        return total;
    }

    public void testing() {
        System.out.println(isVisible(4, 3));
    }

    public static void main(String[] args) {
        Solution day8 = new Solution("input.txt");
        day8.readFile();

        // // testing
        int total = day8.countTotal();
        System.out.println("TOTAL: " + total);

    }
}