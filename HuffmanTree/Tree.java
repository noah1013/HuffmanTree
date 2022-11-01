/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ntobi
 */
public class Tree {
    private Node root;

    public Tree() {
    }

    public Tree(Node root) {
        this.root = root;
    }

    // Creates the Huffman code table
    public void codeTable(PriorityQueue queue, Node curr, String code) {
        if (root.left == null && root.right == null) { // Insert "00" to the code table if the string is one character
                                                       // and return
            queue.insert(new Node("00", root.data));
            return;
        }

        if (curr == null)
            return;

        if (curr.left == null && curr.right == null) { // Navigates the Huffman tree and creates the code for each
            queue.insert(new Node(code, curr.data));
        }
        codeTable(queue, curr.left, code + '0');
        codeTable(queue, curr.right, code + '1');
    }

    // Encodes the message
    public String encode(String message, PriorityQueue codeTable) {
        String encoded = "";
        for (int i = 0; i < message.length(); i++) {
            for (int j = 0; j < codeTable.getQueueArray().length; j++) {
                if (message.charAt(i) == codeTable.getQueueArray()[j].data)
                    encoded += (codeTable.getQueueArray()[j].binary);
            }

        }
        return encoded;
    }

    // Decodes the binary (encoded) messaage back into a readable String message
    public String decode(String encoded, Node curr) {
        String decoded = "";
        if (root.left == null && root.right == null) { // Returns the root if there is only one character
            return root.data + "";
        }

        while (true) {
            if (encoded.length() > 1) { // Keep condensing length of the "encoded" string until its length is 1
                if (curr.left == null && curr.right == null) { // Add to string if leaf node is found
                    decoded += curr.data;
                    curr = root;
                }
                curr = moveNode(curr, encoded.charAt(0));
                encoded = encoded.substring(1);
            } else { // After the length of the "encoded" string is 1, move to a tail Node and add
                     // the character to the decoded message
                curr = moveNode(curr, encoded.charAt(0));
                decoded += curr.data;
                break;
            }
        }

        return decoded;
    }

    public Node moveNode(Node curr, char bit) {
        // Moves left in tree if 0, right if 1
        return bit == '0' ? curr.left : curr.right;
    }

}
