// Vladimir Gray Velazco 1CSC
package File_Handling;

import java.io.*;
import java.util.Scanner;

public class FileFindWord {
    public static void main(String[] args) throws IOException {
        File lyrics = new File("File_Handling/test.txt");
        Scanner console = new Scanner(System.in);

        System.out.print("Enter a word to be searched: ");
        String word = console.next();

        boolean found = false;
        try {
            Scanner lyricSearcher = new Scanner(lyrics);
            int lineNum = 0;
            String line = "";
            while (lyricSearcher.hasNextLine() && !found) {
                line = lyricSearcher.nextLine();
                String[] splitLine = line.split(" ");

                for (String el : splitLine) {
                    System.out.print(el + " ");
                    if (el == word) {
                        found = true;
                        break;
                    }
                }
                System.out.println();
                lineNum++;
            }
            if (found) {
                System.out.printf("Word: %s, Found in Line #: %d\n", word, lineNum);
                System.out.println("Line: " + line);
            } else {
                System.out.println("Word not in the song");
            }

            lyricSearcher.close();
            console.close();
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }

    }
}
