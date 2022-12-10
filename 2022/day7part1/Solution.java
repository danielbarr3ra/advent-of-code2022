import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Directory {
    String name;
    int size;
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

    int getGlobalSize() {
        if (this.subDirectories.size() == 0) {
            return this.size;
        } else {
            int subTotal = 0;
            for (Directory directory : subDirectories) {
                subTotal += directory.getGlobalSize();
            }
            return subTotal + this.size;
        }
    }
}

public class Solution {
    String path;
    List<Integer> answer = new ArrayList<>();

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

    public Directory createDirectoryTree() {
        Directory root = new Directory("/", null);
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
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
        return root;
    }

    public void traverseTree(Directory root) {
        if (root == null) {
            return;
        }

        int subCount = root.subDirectories.size();
        for (int i = 0; i < subCount; i++) {
            traverseTree(root.subDirectories.get(i));
        }
        int total = root.getGlobalSize();
        answer.add(total);
        System.out.println("The node: " + root.name + " has size of " + total);
    }

    public static void main(String[] args) {
        System.out.println("Day7");
        Solution day7 = new Solution("input.txt");
        Directory tree = day7.createDirectoryTree();
        System.out.println(tree.getGlobalSize());
        day7.traverseTree(tree);
        int total = day7.answer.get(day7.answer.size() - 1);
        long available = 70000000 - total;
        System.out.println("total is " + total);
        Collections.sort(day7.answer);
        for (Integer file : day7.answer) {
            System.out.println(file);
            if (available + file > 30000000) {
                System.out.println("The answer is " + file);
                break;
            }
        }
    }
}