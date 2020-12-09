/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hua.Huffman;

/**
 *
 * @author Αντωνόπουλος Διογένης
 * @author Στεφανίδου   Άρτεμις
 * @author  Χύσκαϊ      Βασίλης
 */

import java.io.Serializable;

/**
 *
 * @author luisd
 */
public class HuffmanTree implements Serializable {
    
    public Node makeTree(int[] array) {
        Node[] treeNodes = new Node[128];
        for(char i = 0; i<array.length; i++) {
            treeNodes[i] = new Node(i,array[i],null,null);
        }
        MinHeap<Node> h =new ArrayMinHeap<>(treeNodes);
        while(h.size() > 1) {
            Node leftChild = h.deleteMin();
            Node rightChild = h.deleteMin();
            Node parent = new Node('\0',leftChild.getFrequency() + rightChild.getFrequency(),leftChild,rightChild);
            h.insert(parent);
        }
        return h.deleteMin();
    }
    
}

