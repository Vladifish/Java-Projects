package File_Handling;

import java.io.*;
import java.util.Scanner;

public class FileTesting {
    public static void main(String[] args) throws IOException {
        File f0 = new File("File_Handling/Editables/Epic.txt");
        try {
            if (f0.createNewFile()) {
                System.out.println("File " + f0.getName() + " Created Successfully"); // returns file name
            } else {
                System.out.println("File already in directory");
            }
            System.out.print(f0.getName() + " ");
            System.out.printf("Writable: %B :: Readable: %B\n", f0.canWrite(), f0.canRead());

            Scanner console = new Scanner(System.in);
            FileWriter fileWriter = new FileWriter(f0);

            System.out.println("Input a word to be added to file: ");
            fileWriter.write(console.next());
            System.out.println("File Writing Successful");
            fileWriter.close();

        } catch (IOException e) {
            System.out.println("An unexpected error has occured");
            e.printStackTrace();
        }

    }
}