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

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;

        return result;
    }
}

public class Solution {
    Coordinates head = new Coordinates(0, 0);
    Coordinates tail = new Coordinates(1, 2);

    HashSet<Coordinates> traversed = new HashSet<>();

    public Solution() {
    };

    void testDirections() {
        System.out.println("IS it diagonal? " + head.isDiagonal(tail));
        System.out.println("Are they touching: " + head.isTouching(tail));
    }

    public static void main(String[] args) {
        System.out.println("hey");
        Solution day9 = new Solution();
        day9.testDirections();
    }
}