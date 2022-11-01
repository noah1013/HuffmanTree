/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ntobi
 */

import java.util.Scanner;

public class HuffmanTester {
    public static void main(String[] args) {
        String message = UserInput(); // Get the message to be encoded

        PriorityQueue huff = PriorityQueue.makeQueue(message);

        huff.huffman(huff.getQueueArray());
        Node root = huff.getQueueArray()[0];

        Tree huffmanTree = new Tree(root); // Creates the Huffman Tree

        PriorityQueue codeTable = new PriorityQueue(); // Make the huffman code table
        huffmanTree.codeTable(codeTable, root, "");

        String encoded = huffmanTree.encode(message, codeTable); // Encode the message
        System.out.println("\nEncoded message:\n" + encoded + "\n");

        String decoded = huffmanTree.decode(encoded, root); // Decode the encoded message
        System.out.println("Decoded message:\n" + decoded);
    }

    public static String UserInput() {
        Scanner input = new Scanner(System.in);
        System.out.println("Type in a message:");
        String message = input.nextLine();
        input.close();
        return message + "\n"; // Returns the message with a line feed because it is not included with th
    }

}
