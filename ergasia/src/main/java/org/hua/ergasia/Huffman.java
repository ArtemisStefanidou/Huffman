package org.hua.ergasia;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * A Huffman Tree Implementation
 *
 * @author Αντωνόπουλος Διογένης
 * @author Στεφανίδου Άρτεμις
 * @author Χύσκαϊ Βασίλης
 */
public class Huffman {

    public static final Character RIGHT = '1';
    public static final Character LEFT = '0';
    private Deque<Character> dequeCode;
    private StringBuffer stBuffer;
    private String[] array = new String[128];

    public Huffman() {

        this.dequeCode = new ArrayDeque<>();
        stBuffer = new StringBuffer();
    }

    /**
     * Get the root of the Huffman tree
     *
     * @param array of frequencies
     * @return the root of the tree
     */
    public Node makeTree(int[] array) {

        //Create an array of nodes.Each node represents a character
        Node[] treeNodes = new Node[128];
        for (char i = 0; i < array.length; i++) {
            treeNodes[i] = new Node(i, array[i], null, null);
        }

        //Create the MinHeap for our Nodes using the right constructor to use heapify
        MinHeap<Node> h = new ArrayMinHeap<>(treeNodes);

        //Create the Huffman Tree with the help of MinHeap
        while (h.size() > 1) {
            Node leftChild = h.deleteMin();
            Node rightChild = h.deleteMin();
            Node parent = new Node('\0', leftChild.getFrequency() + rightChild.getFrequency(), leftChild, rightChild);
            h.insert(parent);
        }
        return h.deleteMin();
    }

    /**
     * Get the root of the Huffman tree and do the representation of characters
     * in a sequence of aces and zeros
     *
     * @param curRoot the root of the tree
     * @return array of representation of characters in a sequence of aces and
     * zeros
     */
    public String[] printCode(Node curRoot) {

        //check if the node is leaf->has one of 128 ASCII characters
        if (!curRoot.isLeaf()) {

            dequeCode.push(LEFT);
            printCode(curRoot.getLeftChild());

            dequeCode.push(RIGHT);
            printCode(curRoot.getRightChild());

        } else {

            //the for of the objects that have iterator
            for (Character x : dequeCode) {
                //add in buffer the next Character  of representation 
                stBuffer.append(x);
            }

            //save the character representation in the corresponding position of the array with the correct way
            array[curRoot.getCharacter()] = stBuffer.reverse().toString();

            //delete the contents of the buffer so reused it in the next character representation
            stBuffer.delete(0, stBuffer.length());

        }

        //check if deque has characters to pop
        if (!dequeCode.isEmpty()) {

            dequeCode.pop();

        }

        //return the array of representation of characters to used in App class
        return array;

    }

}
