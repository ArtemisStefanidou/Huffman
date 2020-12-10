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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class App {    

    public static void main(String[] args) throws MalformedURLException, IOException {

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
                outputStream.write(i + " -> " + chars[i] + "\n");
                outputStream.flush();
            }
        }
    }

}