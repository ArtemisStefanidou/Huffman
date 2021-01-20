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
    public String[] createCode(Node curRoot) {

        //check if the node is leaf->has one of 128 ASCII characters
        if (!curRoot.isLeaf()) {

            dequeCode.push(LEFT);
            createCode(curRoot.getLeftChild());

            dequeCode.push(RIGHT);
            createCode(curRoot.getRightChild());

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


    /**
     * Get the root of Huffman tree and the encoded text as an array of bytes.
     * Read every bit in byte array and follow the path in Huffman tree (zero
     * go left ace go right) until find a leaf then save the character. When
     * have read all byte array return saved characters as a string
     *
     * @param byteArray the array of encoded bytes
     * @param root the root of the Huffman tree
     * @return String of the decoded text
     */
    public String decode (byte[] byteArray, Node root){

        StringBuffer decodedCharacters = new StringBuffer();
        Node tmp = root;

        // all bytes have 8 useful bits except the last one
        int usefulBits = 8;

        // for loop to access byte array except first and last byte
        for(int i = 1; i < byteArray.length ; i++)
        {
            // a mask 0x80 is 1000 0000 in binary
            int mask = 0x80;

            //if we are on the last byte
            if(i == byteArray.length - 1 ) {

                //the first byte of array represents the useful bits of the last byte
                usefulBits = byteArray[0];
            }

            // we need a loop to access all useful bits of a byte
            for(int j = 0; j < usefulBits; j++) {

                // if the selected bit is zero or ace go left or right on the tree
                if( ( byteArray[i] & mask ) == 0 ) {

                    tmp = tmp.getLeftChild();

                } else {

                    tmp = tmp.getRightChild();

                }

                // mask shift lef by one for the next bit
                mask >>= 1 ;

                // if is leaf then add character to StringBuffer
                if(tmp.isLeaf()) {
                    decodedCharacters.append(tmp.getCharacter());
                    tmp = root;
                }


            }

        }


        // return all characters as a string
        return decodedCharacters.toString();
    }

}
