package Blackmail;

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

class kappa {

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

        //quickSort(0,q.getHeapSize(),q.getHeap());
       // mergeSort(q.getHeap(),q.getHeapSize());

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
            //quickSort(1,q.getHeapSize()-1,q.getHeap());
            //mergeSort(q.getHeap(),q.getHeapSize());
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

    /*public static void quickSort(int low, int high, Blackmail.Node[]nodes)
    {
        int i, j;  // для хранения границ подмассива
        int p;      // для хранения опорного элемента
        int temp;

        i = low;    // делаем копию нижней границы
        j = high;   // делаем копию верхней границы

        p = nodes[(low+high)/2].value;    // выбираем опорный элемент

        // цикл будет работать, пока границы не пересекутся в массиве
        do
        {
            // если элемент нижней границы меньше опорного элемента,
            // передвигаем указатель i на один элемент вправо
            while (nodes[i].value < p) i++;
            // если элемент верхней границы меньше опорного элемента,
            // передвигаем указатель j на один элемент влево
            while (nodes[j].value > p) j--;

            // если границы не пересеклись по позициям в массиве,
            // меняем местами элементы i и j
            if (i <= j)
            {
                temp = nodes[i].value;
                nodes[i].value = nodes[j].value;
                nodes[j].value = temp;

                i++;
                j--;
            }
        } while (i <= j);

        // если в выделенном подмассиве [Low, j] больше одного элемента,
        // запускаем рекурсивно функцию QuickSort(long, long)
        if (j > low) quickSort(low, j, nodes);

        // аналогично
        if (high > i) quickSort(i, high, nodes);
    }

    public static void merge(
            Blackmail.Node[] a, Blackmail.Node[] l, Blackmail.Node[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i].value <= r[j].value) {
                a[k++] = l[i++];
            }
            else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }
    public static void mergeSort(Blackmail.Node[] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        Blackmail.Node[] l = new Blackmail.Node[mid];
        Blackmail.Node[] r = new Blackmail.Node[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(a, l, r, mid, n - mid);
    }*/
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

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public int size() {
        return heapSize;
    }

    public void insert(Node node) {

        Node newJob = node;

        /*heap[heapSize++] = newJob;
        int pos = heapSize;
        while (pos != 1 && newJob.value > heap[pos/2].value) {
            heap[pos] = heap[pos/2];
            pos /=2;
        }
        heap[pos] = newJob;*/
        int i = heapSize;
        siftUp(i, node);
        heapSize = i + 1;
    }

    public int getHeapSize() {
        return heapSize;
    }

    public Node remove() {

//        int parent, child;
//        Blackmail.Node item, temp;
//
//        if (isEmpty()) {
//            System.out.println("Heap is empty");
//            return null;
//        }
//
//        item = heap[1];
//        temp = heap[heapSize--];
//
//        parent = 1;
//        child = 2;
//        while (child <= heapSize) {
//            if (child < heapSize && heap[child].value < heap[child + 1].value) {
//                child++;
//            }
//
//            if (temp.value >= heap[child].value) {
//                break;
//            }
//
//            heap[parent] = heap[child];
//            parent = child;
//            child *= 2;
//        }
//
//        heap[parent] = temp;
//        return item;
        final Node[] nodes;
        final Node result;

        if ((result = (Node) ((nodes = heap)[0])) != null) {
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

    public Node[] getHeap() {
        return heap;
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