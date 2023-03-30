// Vladimir Gray P. Velazco 1CSC
package File_Handling;

import java.io.*;

import javax.swing.text.AbstractDocument.Content;

// Write a program that will perform the following:
// a. read a text file (filename: test.txt)
// b. find all 3-letter words in the text file and store it in “word3.txt”
// c. find all 4-letter words in the text file and store it in “word4.txt”
// d. find all 5-letter words in the text file and store it in “word5.txt”
// e. display the contents of word3.txt, word4.txt, and word5.txt
public class FileWord345 {
    public static void main(String[] args) {
        File file = new File("File_Handling/Handleables/test.txt");
        File three_letters = new File("File_Handling/Handleables/word3.txt");
        File four_letters = new File("File_Handling/Handleables/word4.txt");
        File five_letters = new File("File_Handling/Handleables/word5.txt");
        try {
            FileReader fr = new FileReader(file);
            BufferedReader fileReader = new BufferedReader(fr);

            // Writing to the three files
            FileWriter writer3Letters = new FileWriter(three_letters);
            FileWriter writer4Letters = new FileWriter(four_letters);
            FileWriter writer5Letters = new FileWriter(five_letters);
            while (fileReader.readLine() != null) {
                String line = fileReader.readLine();
                String[] words = line.split(" ");
                for (String word : words) {
                    if (word.length() == 3)
                        writer3Letters.append(word + " ");
                    else if (word.length() == 4)
                        writer4Letters.append(word + " ");
                    else if (word.length() == 5)
                        writer5Letters.append(word + " ");
                }
            }
            writer3Letters.close();
            writer4Letters.close();
            writer5Letters.close();
            fileReader.close();
            fr.close();
            // Reading the contents of the three files
            createLine();
            System.out.println("Three Letter Words:");
            printFileContents(new FileReader(three_letters));
            createLine();
            System.out.println("Four Letter Words:");
            printFileContents(new FileReader(four_letters));
            createLine();
            System.out.println("Five Letter Words:");
            printFileContents(new FileReader(five_letters));
            createLine();

        } catch (IOException e) {
            System.out.println("Error 404: File Not Found");
        }
    }

    static void printFileContents(FileReader fr) throws IOException {
        BufferedReader fileReader = new BufferedReader(fr);
        String line = fileReader.readLine();
        String[] words = line.split(" ");
        int count = 0;

        for (String word : words) {
            System.out.print(word + " ");
            count = (count + 1) % 5; // breaks after 5 words
            if (count == 0) {
                System.out.println();
            }
        }
        System.out.println();
        fr.close();
    }

    static void createLine() {
        System.out.println("------------------------------");
    }
}
