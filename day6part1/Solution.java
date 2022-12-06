import java.io.*;
import java.util.*;

public class Solution {
    String path;
    int windowSize;

    public Solution(String path, int size) {
        this.path = path;
        this.windowSize = size;
    }

    public boolean isLocked(Queue<Integer> queue) {
        if (queue.size() == this.windowSize && queue.stream().distinct().count() == this.windowSize)
            return true;
        return false;
    }

    public void readingFile() {
        FileReader inputStream = null;
        Queue<Integer> window = new LinkedList<>();
        boolean signalFound = isLocked(window);
        int location = 0;
        try {
            inputStream = new FileReader(this.path);
            int c;
            while ((c = inputStream.read()) != -1 && !signalFound) {
                window.add(c);
                if (window.size() > this.windowSize) {
                    window.remove();
                }
                signalFound = isLocked(window);
                location++;
            }
            System.out.println("location : " + location);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        System.out.println("Day 6");
        Solution solving = new Solution("input.txt", 14);
        solving.readingFile();
    }

}