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

public class Decode {

    public static void main(String[] args) throws MalformedURLException, IOException, ClassNotFoundException {

        //Check for correct input from the terminal
        if (args.length != 2) {
            System.out.println("Usage: program filename filename\nAnd the first file must already exist");
            System.exit(-1);
        }

        Huffman tree = new Huffman();

        // open file given from arguments
        File f = new File(args[0]);

        // create a byte array to store all bytes from file
        byte[] byteArray = new byte[(int)f.length()];

        //creating a stream to read the file given from arguments
        FileInputStream inputStream = new FileInputStream(f);

        // read the file and store all bytes to byteArray
        inputStream.read(byteArray);

        inputStream.close();

        //create a stream to read file tree.dat
        ObjectInputStream inputTree = new ObjectInputStream(new BufferedInputStream(new FileInputStream("tree.dat")));
        Node rootTree = (Node) inputTree.readObject();


        //Create a BufferWriter to write in file
        try ( BufferedWriter outFile = new BufferedWriter(new FileWriter(args[1]))) {

            //print the decoded characters in the file
            outFile.write(tree.decode(byteArray, rootTree));
            outFile.flush();

        }

    }

}

