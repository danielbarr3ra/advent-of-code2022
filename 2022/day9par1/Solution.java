import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void update(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isDiagonal(Coordinates secondCord) {
        if (x == secondCord.getX() || y == secondCord.getY())
            return false;
        return true;
    }

    public boolean isTouching(Coordinates secondCord) {
        int diffX = Math.abs(x - secondCord.getX());
        int diffY = Math.abs(y - secondCord.getY());
        if (isDiagonal(secondCord)) {
            return (diffX + diffY <= 2);
        }
        return (diffX + diffY <= 1);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Coordinates other = (Coordinates) obj;
        return this.getX() == other.getX() && this.getY() == other.getY();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;

        return result;
    }

    @Override
    public String toString() {
        return "Coordinates X: " + this.x + "  Y: " + this.y;
    }
}

public class Solution {
    String path;
    Coordinates head = new Coordinates(0, 0);
    Coordinates tail = new Coordinates(0, 0);

    HashSet<Coordinates> traversed = new HashSet<>();

    public Solution(String path) {
        this.path = path;
    };

    public void moveDirection(String dir) {
        switch (dir) {
            case "U":
                System.out.println("GOING UP");
                head.update(head.getX(), head.getY() + 1);
                if (!head.isTouching(tail)) {
                    if (head.isDiagonal(tail)) {
                        if (head.getX() > tail.getX()) {
                            tail.update(tail.getX() + 1, tail.getY() + 1);
                        } else {
                            tail.update(tail.getX() - 1, tail.getY() + 1);
                        }
                    } else {
                        tail.update(tail.getX(), tail.getY() + 1);
                    }
                    // add the tail to coordinates if not in it.
                }
                break;
            case "D":
                System.out.println("GOIGN DOWN");
                head.update(head.getX(), head.getY() - 1);
                if (!head.isTouching(tail)) {
                    if (head.isDiagonal(tail)) {
                        if (head.getX() > tail.getX()) {
                            tail.update(tail.getX() + 1, tail.getY() - 1);
                        } else {
                            tail.update(tail.getX() - 1, tail.getY() - 1);
                        }
                    } else {
                        tail.update(tail.getX(), tail.getY() - 1);
                    }
                    // add the tail to coordinates if not in it.
                }
                break;

            case "R":
                System.out.println("GOIGN RIGHT");
                head.update(head.getX() + 1, head.getY());
                if (!head.isTouching(tail)) {
                    if (head.isDiagonal(tail)) {
                        if (head.getY() > tail.getY()) {
                            tail.update(tail.getX() + 1, tail.getY() + 1);
                        } else {
                            tail.update(tail.getX() + 1, tail.getY() - 1);
                        }
                    } else {
                        tail.update(tail.getX() + 1, tail.getY());
                    }
                    // add the tail to coordinates if not in it.
                }
                break;
            case "L":
                System.out.println("GOING LEFT");
                head.update(head.getX() - 1, head.getY());
                if (!head.isTouching(tail)) {
                    if (head.isDiagonal(tail)) {
                        if (head.getY() > tail.getY()) {
                            tail.update(tail.getX() - 1, tail.getY() + 1);
                        } else {
                            tail.update(tail.getX() - 1, tail.getY() - 1);
                        }
                    } else {
                        tail.update(tail.getX() - 1, tail.getY());
                    }
                    // add the tail to coordinates if not in it.
                }
                break;
        }
    }

    public void readFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            traversed.add(tail);
            String line = br.readLine();
            while (line != null) {
                String[] command = line.split(" ");
                int amount = Integer.parseInt(command[1]);
                String direction = command[0];
                System.out.println(line);
                for (int i = 0; i < amount; i++) {
                    moveDirection(direction);

                    traversed.add(tail);

                }
                line = br.readLine();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void testDirections() {
        System.out.println("Is it diagonal? " + head.isDiagonal(tail));
        System.out.println("Are they touching: " + head.isTouching(tail));
    }

    public static void main(String[] args) {
        System.out.println("hey");
        Solution day9 = new Solution("input.txt");
        day9.testDirections();
        day9.readFile();
        System.out.println("TRAVERSED FOR TAIL " + day9.traversed.size());
    }
}