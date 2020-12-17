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


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {
        
        //Read the frequencies
        Scanner scanner = new Scanner(new File("frequencies.dat"));
        int[] array = new int[128];
        for(int i= 0; i < 128; i++) {
            array[i] = scanner.nextInt();
        }
        
        //Call class HuffmanTree to create an object for that
        Huffman tree = new Huffman();
        
        //Cretae the huffman tree by calling makeTree and write it in tree.dat 
        try (ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream("tree.dat"))) {
            objectOut.writeObject(tree.makeTree(array));
        }
    }
