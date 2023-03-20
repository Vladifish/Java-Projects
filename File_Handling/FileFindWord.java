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
        String foundLine = "";
        int foundLineNum = 0;
        createLine();
        boolean found = false;
        try {
            Scanner lyricSearcher = new Scanner(lyrics);
            int lineNum = 0;
            while (lyricSearcher.hasNextLine()) {
                String line = lyricSearcher.nextLine();
                String[] splitLine = line.split(" ");

                // Line Count
                lineNum++;
                System.out.printf("%d: ", lineNum);

                // The Piece de Resistance
                for (String el : splitLine) {
                    System.out.print(el + " ");
                    if (el.equalsIgnoreCase(word)) {
                        found = true;
                        foundLine = line;
                        foundLineNum = lineNum;
                        break;
                    }
                }
                System.out.println();

            }
            createLine();

            if (found) {
                System.out.printf("Given Word: %s :: Last Found in Line #: %d\n", word, foundLineNum);
                System.out.println("Line: " + foundLine);
            } else {
                System.out.println("Word not in the song");
            }
            lyricSearcher.close();
        } catch (IOException e) {
            System.out.println("Something went wrong");
        } finally {
            console.close();
            createLine();
        }

    }

    static void createLine() {
        System.out.println("-------------------------------------------");
    }
}
