import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;

class Node{
    int value;
    char letter;

    Node left;
    Node right;
}

class NodeComparator implements Comparator<Node> {
    public int compare(Node x, Node y) {
        return x.value - y.value;
    }
}

class s20511 {

    public static void main(String[] args) throws FileNotFoundException {

        int size = 0;
        Scanner sc1 = new Scanner(new File(args[0]));
        while(sc1.hasNextLine()) {
            sc1.nextLine();
            size++;
        }
        sc1.close();

        Scanner sc2 = new Scanner(new File(args[0]));

        MyPriorityQueue q = new MyPriorityQueue(size, new NodeComparator());

        while (sc2.hasNext()) {

            Node node = new Node();

            String st = sc2.next();
            node.letter = st.charAt(0);
            node.value = Integer.parseInt(sc2.next());

            node.left = null;
            node.right = null;

            q.insert(node);
        }
        sc2.close();

        Node root = null;

        while (q.size() > 1) {
            Node x = q.remove();
            Node y = q.remove();

            Node f = new Node();

            f.value = x.value + y.value;
            f.letter = '-';

            f.left = x;
            f.right = y;

            root = f;

            q.insert(f);
        }

        printCode(root, "");
    }

    public static void printCode(Node root, String s) {
        if (root.left == null && root.right == null && Character.isLetter(root.letter)) {
            System.out.println(root.letter + " " + s);
            return;
        }
        printCode(root.left, s + "0");
        printCode(root.right, s + "1");
    }
}

class MyPriorityQueue
{
    private Node[] heap;
    private int heapSize, capacity;
    private Comparator comparator;

    public MyPriorityQueue(int capacity, Comparator comparator) {
        this.capacity = capacity + 1;
        heap = new Node[this.capacity];
        this.comparator = comparator;
        heapSize = 0;
    }

    public int size() {
        return heapSize;
    }

    public void insert(Node node) {
        int i = heapSize;
        siftUp(i, node);
        heapSize = i + 1;
    }

    public Node remove() {
        final Node[] nodes;
        final Node result;

        if ((result = ((nodes = heap)[0])) != null) {
            final int n;
            final Node x = nodes[(n = --heapSize)];
            nodes[n] = null;
            if (n > 0) {
                final Comparator<Node> cmp;
                if ((cmp = comparator) == null)
                    siftDownComparable(0, x, nodes, n);
                else
                    siftDownUsingComparator(0, x, nodes, n, cmp);
            }
        }
        return result;
    }

    private void siftUp(int k, Node node) {
        if (comparator != null)
            siftUpUsingComparator(k, node, heap, comparator);
        else
            siftUpComparable(k, node, heap);
    }

    private static void siftUpComparable(int k, Node node, Node[] nodes) {
        Comparable<Node> key = (Comparable<Node>) node;
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            Node n = nodes[parent];
            if (key.compareTo(n) >= 0)
                break;
            nodes[k] = n;
            k = parent;
        }
        nodes[k] = (Node) key;
    }

    private static void siftUpUsingComparator(
            int k, Node node, Node[] nodes, Comparator<Node> cmp) {
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            Node n = nodes[parent];
            if (cmp.compare(node, n) >= 0)
                break;
            nodes[k] = n;
            k = parent;
        }
        nodes[k] = node;
    }

    private static void siftDownComparable(int k, Node node, Node[] nodes, int n) {
        Comparable<Node> key = (Comparable<Node>)node;
        int half = n >>> 1;
        while (k < half) {
            int child = (k << 1) + 1;
            Node c = nodes[child];
            int right = child + 1;
            if (right < n &&
                    ((Comparable<Node>) c).compareTo( nodes[right]) > 0)
                c = nodes[child = right];
            if (key.compareTo(c) <= 0)
                break;
            nodes[k] = c;
            k = child;
        }
        nodes[k] = (Node) key;
    }

    private static void siftDownUsingComparator(
            int k, Node node, Node[] nodes, int n, Comparator cmp) {
        int half = n >>> 1;
        while (k < half) {
            int child = (k << 1) + 1;
            Node c = nodes[child];
            int right = child + 1;
            if (right < n && cmp.compare( c, nodes[right]) > 0)
                c = nodes[child = right];
            if (cmp.compare(node, c) <= 0)
                break;
            nodes[k] = c;
            k = child;
        }
        nodes[k] = node;
    }
}