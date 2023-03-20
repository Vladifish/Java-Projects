// Vladimir Gray Velazco 1CSC
package File_Handling;

import java.io.File;
import java.io.IOException;

public class FileFindWord {
    public static void main(String[] args) throws IOException {
        File f0 = new File("File_Handling/Epic.txt");
        try {
            if (f0.createNewFile()) {
                System.out.println("File " + f0.getName() + " Created Successfully"); // returns file name
            } else {
                System.out.println("File already in directory");
            }
        } catch (IOException e) {
            System.out.println("An unexpected error has occured");
            e.printStackTrace();
        }

    }
}
