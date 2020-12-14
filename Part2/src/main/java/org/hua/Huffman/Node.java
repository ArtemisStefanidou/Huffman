/*
 * A class that represendes its node for a tree
 * 
 * 
 */
package org.hua.Huffman;

import java.io.Serializable;

/**
 * @author Αντωνόπουλος Διογένης
 * @author Στεφανίδου Άρτεμις
 * @author Χύσκαϊ Βασίλης
 */
public class Node implements Comparable<Node>, Serializable {

    //Node Characteristics 
    private final char character;
    private final int frequency;
    private final Node left;
    private final Node right;

    //Constractor
    public Node(char character, int frequency, Node left, Node right) {
        this.character = character;
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    //Compare the frequencys from two nodes
    @Override
    public int compareTo(Node n) {
        return this.frequency - n.frequency;
    }

    //Check if node is leaf:Has character 
    public boolean isLeaf() {
        return right == null && left == null;
    }
    
    //geters
    public char getCharacter() {
        return character;
    }

    public int getFrequency() {
        return frequency;
    }

    public Node getLeftChild() {
        return left;
    }

    public Node getRightChild() {
        return right;
    }

}
