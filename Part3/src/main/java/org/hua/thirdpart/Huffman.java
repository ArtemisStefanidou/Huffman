package org.hua.thirdpart;

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;


/**
 * A Huffman Tree Implementation
 *
 * @author Αντωνόπουλος Διογένης
 * @author Στεφανίδου Άρτεμις
 * @author Χύσκαϊ Βασίλης
 */
public class Huffman {

    /**
     * Get the root of the Huffman tree
     *
     * @param array of frequencies
     * @return the root of the tree
     */
    public static final String RIGHT = "1";
    public static final String LEFT = "0";
    private final Deque<String> dequeCode;
    private final StringBuffer stBuffer;
    private final String[] array = new String[128];
    
    public Huffman() {
        
        this.dequeCode = new ArrayDeque<>();
        stBuffer=new StringBuffer();
    }

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

     public String[] printCode(Node curRoot){
        
        
        
        if(!curRoot.isLeaf()){
            
            dequeCode.push(LEFT);
            printCode(curRoot.getLeftChild());
            
            dequeCode.push(RIGHT);
            printCode(curRoot.getRightChild());
     
        }else{
            //εδώ θα παίρνω το char των παιδιών
           

           
            for(String x:dequeCode){
                stBuffer.append(x);
            }
           
            array[curRoot.getCharacter()]=stBuffer.reverse().toString();
           
        stBuffer.delete(0,stBuffer.length());
        
        }
        if(!dequeCode.isEmpty()){
            String curS = dequeCode.pop();
        }
        
        return array;
    }
     
    
}
