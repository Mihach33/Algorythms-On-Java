import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class s20511 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File(args[0]);
        Scanner sr = new Scanner(file);

        BinaryTree root = new BinaryTree();
        root.root = new Node(null, null);

        while(sr.hasNextLine()){

            String line = sr.nextLine();
            String cells[] = line.split("\\s");
            if (cells.length == 1) {
                root.root.value = cells[0];
            } else {
                root.add(root.root, cells[0], cells[1]);
            }
        }
        sr.close();

        root.getCells(root.root);
        int cellCount = root.getHowManyLines();

        String lines [] = new String[cellCount];
        root.getLines(root.root, lines, "");

        String l = lines[0];
        for (int i = 1; i < lines.length; i++) {
            if (l.compareTo(lines[i]) < 0)
                l = lines[i];
        }
        System.out.println(l);
    }
}

class Node {

    String value;
    Node left;
    Node right;
    Node parent;
    boolean isLast;

    Node(String value, Node parent) {
        this.value = value;
        this.parent = parent;
        right = null;
        left = null;
        isLast = true;

    }

    void ifLast() {
        if (left != null || right != null) {
            isLast = false;
        }
    }
}

class BinaryTree {

    Node root;
    int linesCount = 0;
    int howManyLines = 0;

    public Node emptyNode() {
        return new Node(null, null);
    }

    public void add(Node node, String value, String path) {
        Node parentNode = null;

        for (int i = 0; i < path.length(); i ++) {
            parentNode = node;


            if (path.valueOf(path.charAt(i)).equals("L")) {
                if (node.left == null) {
                    node.left = emptyNode();
                }
                node = node.left;
            } else {
                if (node.right == null) {
                    node.right = emptyNode();
                }
                node = node.right;
            }
        }

        node.value = value;
        node.parent = parentNode;
        if (node.parent != null){
            node.parent.ifLast();
        }
    }

    public void getCells(Node node) {
        if (node != null) {
            getCells(node.left);
            getCells(node.right);
            if (node.isLast) {
                howManyLines++;
            }
        }
    }

    public void getLines(Node node, String[] lines, String word) {
        if (node != null) {
            word = node.value + word;
            getLines(node.left, lines, word);
            getLines(node.right, lines, word);
            if (node.isLast) {
                lines[linesCount] = word;
                linesCount++;
            }
        }
    }

    public int getHowManyLines() {
        return howManyLines;
    }
}