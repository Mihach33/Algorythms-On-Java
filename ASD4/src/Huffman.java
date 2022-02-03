import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Scanner;

class HuffmanNode {
    int item;
    char c;
    HuffmanNode left;
    HuffmanNode right;
}

// For comparing the nodes
class ImplementComparator implements Comparator<HuffmanNode> {
    public int compare(HuffmanNode x, HuffmanNode y) {
        return x.item - y.item;
    }
}

// IMplementing the huffman algorithm
public class Huffman {
    public static void printCode(HuffmanNode root, String s) {
        if (root.left == null && root.right == null && Character.isLetter(root.c)) {

            System.out.println(root.c + " " + s);

            return;
        }
        printCode(root.left, s + "0");
        printCode(root.right, s + "1");
    }

    public static void main(String[] args) throws FileNotFoundException {

        int size = 0;
        Scanner sc1 = new Scanner(new File(args[0]));
        while(sc1.hasNextLine()) {

            sc1.nextLine();
            size++;
        }
        sc1.close();

        Scanner sc2 = new Scanner(new File(args[0]));


        PriorityQueue<HuffmanNode> q = new PriorityQueue<HuffmanNode>(size, new ImplementComparator());

        while (sc2.hasNext()) {
            HuffmanNode hn = new HuffmanNode();
            String st = sc2.next();
            hn.c = st.charAt(0);

            hn.item = Integer.parseInt(sc2.next());

            hn.left = null;
            hn.right = null;

            q.add(hn);
        }

        HuffmanNode root = null;

        while (q.size() > 1) {

            HuffmanNode x = q.peek();
            q.poll();

            HuffmanNode y = q.peek();
            q.poll();

            HuffmanNode f = new HuffmanNode();

            f.item = x.item + y.item;
            f.c = '-';
            f.left = x;
            f.right = y;
            root = f;

            q.add(f);
        }
        printCode(root, "");
    }
}