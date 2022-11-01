
public class PriorityQueue {
    private Node[] queueArray;
    private int nItems;

    public PriorityQueue() {
    }

    public PriorityQueue(int size) {
        queueArray = new Node[size];
        nItems = 0;
    }

    public void insert(Node item) // insert item
    {
        incrementArraySize();
        int i;
        for (i = nItems - 1; i >= 0; i--) {
            if ((item.frequency < queueArray[i].frequency)) {
                queueArray[i + 1] = queueArray[i];
            } else {
                break;
            }
        }

        queueArray[i + 1] = item;
        nItems++;
    } // end insert()

    public Node remove() // remove minimum item
    {
        Node temp = queueArray[0];
        decrementArraySize();
        nItems--;
        return temp;
    }

    public boolean isEmpty() // true if queue is empty
    {
        return nItems == 0;
    }

    public Node[] getQueueArray() {
        return queueArray;
    }

    public int getNItems() {
        return nItems;
    }

    // Increments the queueArray's size so that its length is non-constant
    public void incrementArraySize() {
        Node[] newArray = new Node[nItems + 1];
        if (!isEmpty()) {
            for (int i = 0; i < nItems; i++)
                newArray[i] = queueArray[i];
        }
        queueArray = newArray;
    }

    // Decrements the queueArray's size so that its length is non-constant
    public void decrementArraySize() {
        Node[] newArray = new Node[nItems - 1];
        if (!isEmpty()) {
            for (int i = 1; i < nItems; i++)
                newArray[i - 1] = queueArray[i];
        }

        queueArray = newArray;
    }

    public void printQueue() {
        for (int i = 0; i < queueArray.length; i++) {
            if (queueArray[i] != null)
                System.out.print(queueArray[i].data + " ");
        }
        System.out.println();

    }

    public static PriorityQueue makeQueue(String message) {
        PriorityQueue queue = new PriorityQueue(0);
        for (int j = 0; j < message.length(); j++) {
            // Validates all ascii characters
            if (message.charAt(j) <= 255) {
                if (!queue.find(message.charAt(j))) {
                    int count = countChar(message, message.charAt(j));
                    queue.insert(new Node(count, message.charAt(j)));
                }
            }
        }
        return queue;
    }

    public static int countChar(String message, char c) {
        int count = 0;
        for (int i = 0; i < message.length(); i++) {
            if (message.charAt(i) == c)
                count++;
        }
        return count;
    }

    // Creates the root node for the huffman tree
    public Node huffman(Node[] queueArray) {
        if (nItems == 1) {
            return queueArray[0];
        }
        Node temp = new Node();

        // Takes the two characters with the minimum frequency and attaches them to a
        // parent node
        temp.left = remove();
        temp.right = remove();
        temp.frequency = temp.left.frequency + temp.right.frequency;
        temp.data = ' ';
        insert(temp);
        return huffman(queueArray);

    }

    public boolean find(char c) {
        for (Node i : queueArray) {
            if (i.data == c) {
                return true;
            }
        }
        return false;
    }

}
