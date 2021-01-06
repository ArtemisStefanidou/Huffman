/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hua.fourthpart;

/**
 *
 * @author artemis
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        //Array for the url
        URL[] url = new URL[3];

        //URL given fron user
        url[0] = new URL("https://www.gutenberg.org/files/1342/1342-0.txt");
        url[1] = new URL("https://www.gutenberg.org/files/11/11-0.txt");
        url[2] = new URL("https://www.gutenberg.org/files/2701/2701-0.txt");

        BufferedReader[] reader = new BufferedReader[3];

        //each position of the array corresponds to the counter of an ASCII character
        int[] chars = new int[128];
        int charValue = 0;

        //For the three url
        for (int i = 0; i < 3; i++) {

            reader[i] = new BufferedReader(new InputStreamReader(url[i].openStream()));

            while ((charValue = reader[i].read()) != -1) {
                if (charValue < 128) {
                    chars[charValue]++;
                }
            }

            reader[i].close();
        }
        //Create a file and print the results there
        try (BufferedWriter outputStream = new BufferedWriter(new FileWriter("frequencies.dat"))) {
            for (int i = 0; i < 128; i++) {
                outputStream.write(chars[i] + "\n");
                outputStream.flush();
            }
        }

        //Read the frequencies
        Scanner scanner = new Scanner(new File("frequencies.dat"));
        int[] array = new int[128];
        for (int i = 0; i < 128; i++) {
            array[i] = scanner.nextInt();
        }

        //Call class HuffmanTree to create an object for that
        Huffman tree = new Huffman();

        //Cretae the huffman tree by calling makeTree and write it in tree.dat 
        try (ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream("tree.dat"))) {
            objectOut.writeObject(tree.makeTree(array));
        }
        
        //create a stream to read file tree.dat
        ObjectInputStream input = new ObjectInputStream(new FileInputStream("tree.dat"));
        Node root = (Node) input.readObject();
        
        try (BufferedWriter output = new BufferedWriter(new FileWriter("codes.dat"))) {

            //save the array that return from class Huffman method printCode
            String[] arrayCode = tree.createCode(root);

            //print the result of representation of characters in the file codes.dat
            for (int i = 0; i < 128; i++) {

                output.write(arrayCode[i] + "\n");
                output.flush();
            }
            output.close();
            System.out.println("A file created.Check codes.dat");
        }

        //Check for correct input from the terminal
        if (args.length < 2) {
            System.out.println("Usage: program filename filename");
            System.exit(-1);
	    }
        
        
        //Read the Huffman coding from the file codes.dat
        String[] code = new String[128];
        try (Scanner myScanner = new Scanner(new File("codes.dat"))) {
            int i = 0;
            while (myScanner.hasNextLine()) {
                code[i++] = myScanner.nextLine();
            }
        }
        
        //Store in encode,char by char,the coresponding huffman encoding
        File file = new File(args[0]);
        StringBuffer encode = new StringBuffer();
        try (BufferedReader myReader = new BufferedReader(new FileReader(file))) {
            int ch;
            while ((ch = myReader.read()) != -1) {
                encode.append(code[ch]);
            }
        }

        //Find the useful bits of the last byte
        Integer tmp;
        tmp = encode.length() % 8;
        
        //Create and output the new bytes in the file
        try (DataOutputStream dataOut = new DataOutputStream(new FileOutputStream(args[1]))) {
            //Print the usesful bits and a newline
            dataOut.writeBytes(tmp.toString());
            dataOut.writeChar(10);
            
            byte currentByte = 0;
            int i = 0, j = 0;
            while (i < encode.length()) {
                //Do the required bitwise operations 
                if (encode.charAt(i) == '1') {
                    currentByte |= 1 << (7 - j);
                }
                i++;
                j++;
                //Print to the file if you have 8 bits or at your last bits
                if (i % 8 == 0 || i == encode.length() - 1) {
                    System.out.println(currentByte);
                    dataOut.writeByte(currentByte);
                    j = 0;
                    currentByte = 0;
                }
            }
        }
    }
}
