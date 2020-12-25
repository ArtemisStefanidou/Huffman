/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hua.thirdpart;

/**
 *
 * @author artemis
 */
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

/**
 *
 * @author artemis
 */
public class ReadTree {

    public static final String RIGHT = "1";
    public static final String LEFT = "0";
    //private static String[] array = new String[128];
    //private Iterator printCodePath;
  
    
    
    private final  Deque<String> dequeCode;

    
    public ReadTree()  {
        
        this.dequeCode = new ArrayDeque<>();
        //this.printCodePath= dequeCode.descendingIterator();
        //printCode(root,output);
    }

   
    
        
    public void printCode(Node curRoot,PrintWriter output){
        
        
        
        if(curRoot.getLeftChild()!=null){
            dequeCode.push(LEFT);
            printCode(curRoot.getLeftChild(),output);
        }
        //String curS = dequeCode.pop();
        if(curRoot.getRightChild()!=null){
            dequeCode.push(RIGHT);
            printCode(curRoot.getRightChild(),output);
        }
        if(curRoot.isLeaf()){
            //εδώ θα παίρνω το char των παιδιών
            System.out.print(curRoot.getCharacter()+"->");//freq

            Iterator printCodePath=dequeCode.descendingIterator();

            // prints the elements using an iterator 
            while (printCodePath.hasNext()) {
               
                //output.printf( "%c -> %s",curRoot.getCharacter(),(String)printCodePath.next());
                //output.println("");
                //output.flush();
                //array[(int)curRoot.getCharacter()]=(String) printCodePath.next();
                System.out.print(printCodePath.next());
            }
            //output.println("");
            //output.flush();
            System.out.println("");
            
        }
        String curS = dequeCode.pop();
        //System.out.println(array[0]);
        
    }
    
    /*
    public boolean hasNext() {
        
        return !dequeNode.isEmpty();
        
    }
    private void childLeft(Node curRoot) {
        while (curRoot.getLeftChild()!= null) {
            dequeNode.push(curRoot);

            dequeCode.push(LEFT);
            curRoot = curRoot.getLeftChild();

        }
        
        if(curRoot.isLeaf())
        {
            //εδώ θα παίρνω το char των αριστερών παιδιών
            System.out.print(curRoot.getCharacter()+"->");//freq

            Iterator printCodePathL = dequeCode.descendingIterator();

            // prints the elements using an iterator 
            while (printCodePathL.hasNext()) {
                System.out.print(printCodePathL.next());
            }
            System.out.println("\n");
        }
        
        //rightChild();
    }

    //θα κάνω next κάθε φορά που κάνω και pushAllLeft
    //@Override
    public void rightChild() {

        Node cur = dequeNode.pop();
        //cur.next();
        String curS = dequeCode.pop();
        dequeCode.push(RIGHT);
        
        if (!cur.getRightChild().isLeaf()) {
            childLeft(cur.getRightChild());
            
            
        }
        if(cur.isLeaf())
        {
            //εδώ των δεξιών παιδιών το char
            System.out.print(cur.getRightChild().getCharacter()+"->");//freq

            Iterator printCodePathR = dequeCode.descendingIterator();

            // prints the elements using an iterator 

                while (printCodePathR.hasNext()) {
                    System.out.print(printCodePathR.next());
                }
                System.out.println("\n");
                String curc=dequeCode.pop();
                //String curp=dequeCode.pop();


           //rightChild();
        }
       
       
    }
    
    public void printCodeTree(){
        while(hasNext()){
            rightChild();
        }
    }
    public static void printHuffmanTree(Node root, String s) {
        if (root.getLeftChild() == null && root.getRightChild() == null) {
            System.out.println(root.getFrequency() + ":" +s);
            return;
        }

        printHuffmanTree(root.getLeftChild(), s + "0");
        printHuffmanTree(root.getRightChild(), s + "1");
    }
*/

}
