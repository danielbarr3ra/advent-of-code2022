import java.io.*;

enum Shape {
    ROCK(1),
    SCISSOR(3),
    PAPER(2);

    private int score;

    private Shape(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public Outcome canBeat(Shape opponentShape) {
        if ((this.ordinal() + 1) % 3 == opponentShape.ordinal())
            return Outcome.WIN;
        else if (this.ordinal() == opponentShape.ordinal())
            return Outcome.DRAW;
        else
            return Outcome.LOSS;
    }
}

enum Outcome {
    WIN(6),
    LOSS(0),
    DRAW(3);

    private int score;

    private Outcome(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}

class Solution {
    String path;

    public Solution(String input) {
        this.path = input;
    }

    public Shape decodeMove(String code) {
        if (code.equals("X") || code.equals("A"))
            return Shape.ROCK;
        else if (code.equals("Y") || code.equals("B"))
            return Shape.PAPER;
        else
            return Shape.SCISSOR;
    }

    public void readFile() {
        int total = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(this.path))) {
            String line = br.readLine();
            while (line != null) {
                String[] codes = line.split(" ");
                Shape theirMove = decodeMove(codes[0]);
                Shape yourMove = decodeMove(codes[1]);
                Outcome outcome = yourMove.canBeat(theirMove);
                total += yourMove.getScore() + outcome.getScore();
                line = br.readLine();
            }
            System.out.println("total is : " + total);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void enumTest() {
        Shape rock = Shape.ROCK;
        Shape paper = Shape.PAPER;
        Shape scissor = Shape.SCISSOR;

        Outcome t1 = rock.canBeat(paper);
        System.out.println("can rock beat papper: " + t1);

        Outcome t4 = paper.canBeat(paper);
        System.out.println("can paper beat papper: " + t4);

        Outcome t7 = scissor.canBeat(paper);
        System.out.println("can scissor beat papper: " + t7);
    }

    public static void main(String[] args) {
        System.out.println("Day 2");
        Solution test = new Solution("input.txt");
        test.readFile();
    }
}