/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hua.secondpart;

/**
 *
 * @author Αντωνόπουλος Διογένης
 * @author Στεφανίδου   Άρτεμις
 * @author  Χύσκαϊ      Βασίλης
 */


import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {
        
        Scanner scanner = new Scanner(new File("frequencies.dat"));
        int[] array = new int[128];
        for(int i= 0; i < 128; i++) {
            array[i] = scanner.nextInt();
        }
        
        HuffmanTree tree = new HuffmanTree();
        ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream("tree.dat"));
        objectOut.writeObject(tree.makeTree(array));
        objectOut.close();
    }

}
