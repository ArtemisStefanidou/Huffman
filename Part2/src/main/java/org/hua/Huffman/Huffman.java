package org.hua.Huffman;

import java.io.Serializable;

/**
 * A Huffman Tree Implementation
 * 
 * @author Αντωνόπουλος Διογένης
 * @author Στεφανίδου   Άρτεμις
 * @author  Χύσκαϊ      Βασίλης
 */
public class Huffman implements Serializable {

    
    /**
     * Get the root of the Huffman tree
     * 
     * @param array of frequencies
     * @return the root of the tree
     */
    public Node makeTree(int[] array) {
        
        //Create an array of nodes.Each node represents a character
        Node[] treeNodes = new Node[128];
        for(char i = 0; i<array.length; i++) {
            treeNodes[i] = new Node(i,array[i],null,null);
        }
        
        //Create the MinHeap for our Nodes using the right constructor to use heapify
        MinHeap<Node> h =new ArrayMinHeap<>(treeNodes);
        
        //Create the Huffman Tree with the help of MinHeap
        while(h.size() > 1) {
            Node leftChild = h.deleteMin();
            Node rightChild = h.deleteMin();
            Node parent = new Node('\0',leftChild.getFrequency() + rightChild.getFrequency(),leftChild,rightChild);
            h.insert(parent);
        }
        return h.deleteMin();
    }
}

