import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;

class Coordinate {
    int x = 0;
    int y = 0;

    Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int distance(Coordinate C) {
        int dX = Math.abs(x - C.x);
        int dY = Math.abs(y - C.y);
        return dX + dY;
    }

    boolean isDiagonal(Coordinate C) {
        if (C.x == x || C.y == y)
            return false;
        return true;
    }

    boolean touching(Coordinate C) {
        if (isDiagonal(C)) {
            return (distance(C) == 2);
        }
        return (distance(C) <= 1);
    }

    void chase(Coordinate C) {
        int diffX = C.x - x;
        int diffY = C.y - y;

        x = x + Integer.signum(diffX);
        y = y + Integer.signum(diffY);
    }

    void move(int x, int y) {
        this.x = this.x + x;
        this.y = this.y + y;
    }

    @Override
    public String toString() {
        return new String("X: " + x + " Y: " + y);
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 589 * result + y;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (x == ((Coordinate) obj).x && y == ((Coordinate) obj).y)
            return true;
        return false;
    }
}

public class Solution {
    String path;
    int sizeOfRope = 10;
    ArrayList<Coordinate> rope = new ArrayList<>();
    HashSet<Coordinate> visited = new HashSet<>();
    Coordinate head = new Coordinate(0, 0);
    Coordinate tail = new Coordinate(0, 0);

    Solution(String p) {
        this.path = p;
    }

    void moveDirection(String command) {
        if (command.equals("U")) {
            head.move(0, 1);
        } else if (command.equals("D")) {
            head.move(0, -1);
        } else if (command.equals("L")) {
            head.move(-1, 0);
        } else if (command.equals("R")) {
            head.move(1, 0);
        } else {
            head.move(0, 0);
        }
        if (!tail.touching(head)) {
            tail.chase(head);
            visited.add(tail);
        }
    }

    void moveMultiple(String command, int times) {
        for (int i = 0; i < times; i++) {
            moveDirection(command);
        }
    }

    void readFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            while (line != null) {
                System.out.println(line);
                String[] opps = line.split(" ");
                System.out.println(opps[0] == "U");
                moveMultiple(opps[0], Integer.parseInt(opps[1]));
                line = br.readLine();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void testing() {
        System.out.println(tail.distance(head));
        System.out.println("Are they equal " + head.equals(tail));
        System.out.println(tail.touching(head));
        tail.chase(head);
        System.out.println(tail);
        System.out.println(tail.touching(head));
        visited.add(head);
        visited.add(tail);
        System.out.println(visited.size());
    }

    public static void main(String[] args) {
        Solution day9 = new Solution("input.txt");
        day9.readFile();
        System.out.println(day9.visited.size());
    }
}