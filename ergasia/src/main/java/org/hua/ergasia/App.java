/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hua.ergasia;

/**
 *
 * @author Αντωνόπουλος Διογένης
 * @author Στεφανίδου Άρτεμις
 * @author Χύσκαϊ Βασίλης
 */
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class App {    

    public static void main(String[] args) throws MalformedURLException, IOException, ClassNotFoundException {

  
        URL[] url = new URL[3];

        //URL given from user
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
                outputStream.write( chars[i] + "\n");
                outputStream.flush();
            }
        }

        //Read the frequencies
        Scanner scanner = new Scanner(new File("frequencies.dat"));
        int[] array = new int[128];
        for(int i= 0; i < 128; i++) {
            array[i] = scanner.nextInt();
        }

        //Call class HuffmanTree to create an object for that by the dafault costructor
        Huffman tree = new Huffman();

        //Create the huffman tree by calling makeTree and write it in tree.dat
        try (ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream("tree.dat"))) {
            objectOut.writeObject(tree.makeTree(array));
        }
        
        //create a stream to read file tree.dat
        ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(new FileInputStream("tree.dat")));
        Node root = (Node) input.readObject();
        
        //Create a BufferWriter to write in file codes.dat
        try ( BufferedWriter output = new BufferedWriter(new FileWriter("codes.dat"))) {
            
            //save the array that return from class Huffman method printCode
            String[] arrayCode = tree.printCode(root);

            //print the result of representation of characters in the file codes.dat
            for (int i = 0; i < 128; i++) {

                output.write(i + "->" + arrayCode[i] + "\n");
                output.flush();
            }
            output.close();
            System.out.println("A file created.Check codes.dat");
        }
        

    }

}