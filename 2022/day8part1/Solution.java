import java.util.*;
import java.io.*;

public class Solution {
    String path;
    List<ArrayList<Integer>> grid = new ArrayList<>();
    int columns = 0;
    int rows = 0;

    int scenciScore = 0;

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

    public int getTopScore(int r, int c, int h, int calls) {
        int next = grid.get(r - 1).get(c);
        if (h > next) {
            if (r - 1 == 0) {
                return calls;
            } else
                return getTopScore(r - 1, c, h, calls + 1);
        } else {
            return calls;
        }
    }

    public int getRightScore(int r, int c, int h, int calls) {
        int next = grid.get(r).get(c + 1);
        if (h > next) {
            if (c + 1 == columns - 1) {
                return calls;
            } else
                return getRightScore(r, c + 1, h, calls + 1);
        } else {
            return calls;
        }
    }

    public int getLeftScore(int r, int c, int h, int calls) {
        int next = grid.get(r).get(c - 1);
        if (h > next) {
            if (c - 1 == 0) {
                return calls;
            } else
                return getLeftScore(r, c - 1, h, calls + 1);
        } else {
            return calls;
        }
    }

    public int getBottomScore(int r, int c, int h, int calls) {
        int next = grid.get(r + 1).get(c);
        if (h > next) {
            if (r + 1 == rows - 1) {
                return calls;
            } else
                return getBottomScore(r + 1, c, h, calls + 1);
        } else {
            return calls;
        }
    }

    public int currentScenicScore(int r, int c) {
        if (r == 0 || c == 0 || r == rows - 1 || c == columns - 1) {
            return 0;
        } else {
            int current = grid.get(r).get(c);
            int left = getLeftScore(r, c, current, 1);
            int right = getRightScore(r, c, current, 1);
            int bottom = getBottomScore(r, c, current, 1);
            int up = getTopScore(r, c, current, 1);

            int total = up * right * bottom * left;

            return total;
        }
    }

    public int findBiggestScore() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                int curr = currentScenicScore(r, c);
                scenciScore = Math.max(scenciScore, curr);
            }
        }
        System.out.println("SCENIC SCORE " + scenciScore);
        return scenciScore;
    }

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

    public void testing(int r, int c) {
        // int curr = grid.get(r).get(c);
        // System.out.println("CURRENT : " + curr);
        // System.out.println("TOP SCORE OF " + getTopScore(r, c, curr, 1));
        // System.out.println("LEFT SCORE OF " + getLeftScore(r, c, curr, 1));
        // System.out.println("BOTT SCORE OF " + getBottomScore(r, c, curr, 1));
        // System.out.println("RIGHT SCORE OF " + getRightScore(r, c, curr, 1));
        System.out.println(currentScenicScore(r, c));
    }

    public static void main(String[] args) {
        Solution day8 = new Solution("input.txt");
        day8.readFile();

        // // testing
        // int total = day8.countTotal();
        // System.out.println("TOTAL: " + total);

        // get score right
        day8.testing(1, 2);
        System.out.println(day8.findBiggestScore());

    }
}