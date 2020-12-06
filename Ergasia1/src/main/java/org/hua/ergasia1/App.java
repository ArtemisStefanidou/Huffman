package org.hua.ergasia1;

/**
 *
 * @author Αντωνόπουλος Διογένης
 * @author Στεφανίδου   Άρτεμις
 * @author  Χύσκαϊ      Βασίλης
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;

public class App {

    public static void main(String[] args) throws Exception {

        //Array for the url
        String[] urlName = new String[3];

        //URL given fron user
        urlName[0] = "https://www.gutenberg.org/files/1342/1342-0.txt";
        urlName[1] = "https://www.gutenberg.org/files/11/11-0.txt";
        urlName[2] = "https://www.gutenberg.org/files/2701/2701-0.txt";
        
        try {
            URL[] url = new URL[3];
            BufferedReader[] reader = new BufferedReader[3];

            //each position of the array corresponds to the counter of an ASCII character
            int[] chars = new int[128];
            int charValue = 0;
            
            //For the three url
            for (int i = 0; i < 3; i++) {

                url[i] = new URL(urlName[i]);

                reader[i] = new BufferedReader(new InputStreamReader(url[i].openStream()));

                while ((charValue = reader[i].read()) != -1) {
                    if (charValue < 128) {
                        chars[charValue]++;
                    }
                }

                reader[i].close();
            }
            //Create a file and print the results there
            BufferedWriter outputStream = new BufferedWriter(new FileWriter("frequencies.dat"));

            for (int i = 0; i < 128; i++) {
                outputStream.write(chars[i] + "\n");
                outputStream.flush();
            }

            outputStream.close();
        }
        // Check for exceptions
        catch (IndexOutOfBoundsException e ) {
            System.out.println("Your index is out of bounds" );
        }catch (NullPointerException e){
            System.out.println("Some of the arrays are null");
        }catch (Exception e){
            System.out.println("Something went wrong");
        }
        
    }

}


