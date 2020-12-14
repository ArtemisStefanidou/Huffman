/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hua.Huffman;

import java.io.Serializable;

/**
 *
 * @author Αντωνόπουλος Διογένης
 * @author Στεφανίδου   Άρτεμις
 * @author  Χύσκαϊ      Βασίλης
 */

public class HuffmanTree implements Serializable {
    
    //Create our Huffman Tree
    public Node makeTree(int[] array) {
        
        //For each character we created a tree (of a node)
        Node[] treeNodes = new Node[128];
        for(char i = 0; i<array.length; i++) {
            treeNodes[i] = new Node(i,array[i],null,null);
        }
        
        //Create the MinHeap for our Nodes
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

