import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class s20511 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(args[0]));
        int k = sc.nextInt();
        LinkedList linkedList = new LinkedList();
        int size = 1;

        linkedList.head = new LinkedList.Node(sc.nextInt());
        LinkedList.Node lastNode = linkedList.head;
        
        while(sc.hasNextInt()) {
            lastNode = linkedList.insertNode(lastNode, sc.nextInt());
            size++;
        }
        sc.close();

        int ifRemoved = 0;
        int p = 0;

        for (int i = 0; i < k; i++) {
            if (linkedList.getValue(p) % 2 == 0) {
                if (p == size - 1) {
                    ifRemoved = linkedList.getValue(0);
                    linkedList.deleteAtPosition(linkedList,0);
                } else {
                    ifRemoved = linkedList.getValue(p + 1);
                    linkedList.deleteAtPosition(linkedList,p + 1);
                }

                size--;

                if (ifRemoved > size - p - 1) {
                    int l = size - p - 1;
                    if (l == -1)
                        l = 0;
                    ifRemoved = ifRemoved - l;

                    if(size == 0) {
                        break;
                    }

                    p = ifRemoved % size - 1;
                    if (p == -1)
                        p = size - 1;;
                } else {
                    if (ifRemoved == 0 && p == size)
                        p--;
                    else
                        p += ifRemoved;
                }

            } else {
                linkedList.insertAfter(linkedList.getNode(p), linkedList.getValue(p) - 1);
                size++;
                if (linkedList.getValue(p) > size - p - 1) {
                    p = linkedList.getValue(p) - (size - p - 1);
                    p = p % size - 1;
                    if (p == -1)
                        p = size - 1;
                } else {
                    p += linkedList.getValue(p);
                }
            }

        }
        if (size == 0) {
            System.out.println();
        } else {
            System.out.print(linkedList.getValue(p));
            for (int i = p + 1; i < size; i++) {
                System.out.print(" " + linkedList.getValue(i));
            }
            for (int i = 0; i < p; i++) {
                System.out.print(" " + linkedList.getValue(i));
            }
        }
    }
}

class LinkedList {

    Node head;

    static class Node {

        int value;
        Node next;

        Node(int value) {
            this.value = value;
            next = null;
        }
    }

    public Node insertNode(Node prevNode, int data) {
        Node newNode = new Node(data);
        newNode.next = prevNode.next;
        prevNode.next = newNode;
        return  newNode;
    }

    public int getValue(int index) {
        Node current = head;
        int count = 0;
        while (current != null) {
            if (count == index)
                return current.value;
            count++;
            current = current.next;
        }
        assert (false);
        return 0;
    }

    public Node getNode(int index) {
        Node current = head;
        int count = 0;
        while (current != null) {
            if (count == index)
                return current;
            count++;
            current = current.next;
        }
        assert (false);
        return null;
    }

    public static LinkedList deleteAtPosition(LinkedList list, int index) {
        Node current = list.head;
        Node prev = null;

        if (index == 0 && current != null) {
            list.head = current.next;
            return list;
        }

        int counter = 0;

        while (current != null) {
            if (counter == index) {
                prev.next = current.next;
                break;
            }
            else {
                prev = current;
                current = current.next;
                counter++;
            }
        }

        return list;
    }

    public void insertAfter(Node prevNode, int data) {
        Node newNode = new Node(data);
        newNode.next = prevNode.next;
        prevNode.next = newNode;
    }




}