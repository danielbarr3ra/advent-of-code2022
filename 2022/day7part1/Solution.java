import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Directory {
    String name;
    int size;
    int globalSize;
    Directory parent = null;
    List<Directory> subDirectories = new ArrayList<>();

    Directory(String name, Directory parent) {
        this.name = name;
        this.parent = parent;
    }

    void addFile(int fileSize) {
        this.size += fileSize;
    }

    void addSubDirectory(String name) {
        Directory sub = new Directory(name, this);
        subDirectories.add(sub);
    }

    Directory findSubDirectory(String name) {
        return this.subDirectories.stream().filter(subDir -> name.equals(subDir.name)).findFirst().orElse(null);
    }
}

public class Solution {
    String path;

    public Solution(String input) {
        this.path = input;
    }

    public static boolean isNumeric(String str) {
        String regex = "[0-9]+";
        Pattern p = Pattern.compile(regex);
        if (str == null)
            return false;
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public void readFile() {

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            Directory root = new Directory("/", null);
            Directory current = root;
            while (line != null) {
                String[] words = line.split(" ");
                if (words[1].equals("cd")) {
                    if (words[2].equals("..")) {
                        current = current.parent;
                    } else if (words[2].equals("/")) {
                        current = root;
                    } else {
                        current = current.findSubDirectory(words[2]);
                        System.out.println("moving into the root");
                    }
                } else if (words[1].equals("ls")) {
                    System.out.println("listing files");
                    line = br.readLine();
                    continue;
                }
                if (Solution.isNumeric(words[0])) {
                    current.addFile(Integer.parseInt(words[0]));
                } else if (words[0].equals("dir")) {
                    current.addSubDirectory(words[1]);
                }

                /******************* */
                line = br.readLine();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        System.out.println("Day7");
        Solution day7 = new Solution("test.txt");
        day7.readFile();
    }
}