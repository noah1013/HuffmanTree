/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ntobi
 */
public class Node {
    int frequency; // The frequency of the character in the messagea
    String binary; // The binary string for the code table
    char data; // The character from the message
    Node left;
    Node right;

    public Node() {
    }

    public Node(int frequency, char data) {
        this.frequency = frequency;
        this.data = data;
    }

    public Node(String binary, char data) {
        this.binary = binary;
        this.data = data;
    }

}
