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

public class Encode {

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

        //Call class HuffmanTree to create an object for that by the default constructor
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
            String[] arrayCode = tree.createCode(root);

              //print the result of representation of characters in the file codes.dat
            for (int i = 0; i < 128; i++) {

                output.write(arrayCode[i] + "\n");
                output.flush();
            }
            output.close();
            
        }

        //Check for correct input from the terminal
        if (args.length != 2) {
            System.out.println("Usage: program filename filename\nAnd the first file must already exist");
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
        
        //Store in encode,char by char,the corresponding huffman encoding
        try{
            File file = new File(args[0]);
        
            StringBuffer encode = new StringBuffer();
            try (BufferedReader myReader = new BufferedReader(new FileReader(file))) {
                int ch;
                while ((ch = myReader.read()) != -1) {
                    
                    if(ch < 128 && ch >= 0 ) {
                        
                        encode.append(code[ch]);
          
                    } else {
                        
                        System.out.println( (int)ch + "-> is not ASCII");
                    }
                }
               
            }
       

            //Find the useful bits of the last byte


            byte tmp = (byte) (encode.length() % 8);

            //Create and output the new bytes in the file
            String newLine=System.getProperty("line.separator");
            try (DataOutputStream dataOut = new DataOutputStream(new FileOutputStream(args[1]))) {

                //Print the useful bits
                dataOut.writeByte(tmp);

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
                    if (i % 8 == 0 || i == encode.length()) {
                        
                        dataOut.writeByte(currentByte);
                        j = 0;
                        currentByte = 0;
                    }
                }
            }
        }catch(FileNotFoundException e){
            System.out.println("Usage: program filename filename\nAnd the first file must already exist");
            System.exit(-1);
        }
    }

}

