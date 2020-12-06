/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hua.ergasia1;

import java.io.Serializable;

/**
 *
 * @author luisd
 */
public class Huffman implements Serializable {
    
    private static class Node implements Comparable<Node>,Serializable {
        
        private char character;
        private int frequency;
        private Node left, right;
        
        public Node(char character,int frequency, Node left,Node right) {
            this.character = character;
            this.frequency = frequency;
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Node n) {
            return this.frequency - n.frequency;
        }
        
        public boolean isLeaf() {
            return right == null && left == null;
        }
    }
    
    public Node makeTree(int[] array) {
        Node[] treeNodes = new Node[128];
        for(char i = 0; i<array.length; i++) {
            treeNodes[i] = new Node(i,array[i],null,null);
        }
        MinHeap<Node> h =new ArrayMinHeap<>(treeNodes);
        while(h.size() > 1) {
            Node leftChild = h.deleteMin();
            Node rightChild = h.deleteMin();
            Node parent = new Node('\0',leftChild.frequency + rightChild.frequency,leftChild,rightChild);
            h.insert(parent);
        }
        return h.deleteMin();
    }
    
}
