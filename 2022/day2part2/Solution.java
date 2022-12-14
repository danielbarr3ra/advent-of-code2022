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

    public Shape complement(Shape opponentShape) {
        if (this == DRAW)
            return opponentShape;
        else if (this == WIN) {
            return Shape.values()[(opponentShape.ordinal() + 2) % 3];
        } else {
            return Shape.values()[(opponentShape.ordinal() + 4) % 3];
        }
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

    public Outcome decodeOutcome(String code) {
        if (code.equals("X")) {
            return Outcome.LOSS;
        } else if (code.equals("Y"))
            return Outcome.DRAW;
        else
            return Outcome.WIN;
    }

    public void readFile() {
        int total = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(this.path))) {
            String line = br.readLine();
            while (line != null) {
                String[] codes = line.split(" ");
                Shape theirMove = decodeMove(codes[0]);
                Outcome outcome = decodeOutcome(codes[1]);
                Shape neededMove = outcome.complement(theirMove);
                total += neededMove.getScore() + outcome.getScore();
                line = br.readLine();
            }
            System.out.println("total is : " + total);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void enumTest() {
        // opponents move
        Shape rock = Shape.ROCK;
        Shape paper = Shape.PAPER;
        Shape scissor = Shape.SCISSOR;

        // for winning
        Outcome t1 = Outcome.WIN;
        Shape m1 = t1.complement(rock);
        System.out.println(t1 + "and opponent uses" + rock + "I should use " + m1);

        Outcome t2 = Outcome.DRAW;
        Shape m2 = t2.complement(paper);
        System.out.println(t2 + "and opponent uses" + paper + "I should use " + m2);

        Outcome t3 = Outcome.LOSS;
        Shape m3 = t3.complement(scissor);
        System.out.println(t3 + "and opponent uses" + scissor + "I should use " + m3);

    }

    public static void main(String[] args) {
        System.out.println("Day 2");
        Solution test = new Solution("input.txt");
        test.readFile();
    }
}