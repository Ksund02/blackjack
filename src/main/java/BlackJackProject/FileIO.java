package blackjackproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileIO {

    private static final String filePath = System.getProperty("user.dir") + "/src/main/resources/blackjackproject/SavedGame.txt";

    /**
     * Writes each element in a list to a file. It overwrites existing content. 
     * It also catches IOException and prints out the stack trace. 
     * 
     * @param lines List of strings to write
    */
    public static void writeToFile(List<String> lines) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (String line : lines) {
                writer.write(line);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the contents of the file. It will always read the first four lines.
     * It also catches IOException and prints out the stack trace. 
     * 
     * @return Each line in the file in the form of a list of strings
     */
    public static List<String> readFromFile() {
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            for (int currentLine = 0; currentLine < 4; currentLine++) {
                String line = reader.readLine();
                lines.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    /**
     * Checks if the file is empty.
     * 
     * @return true if the file starts with "NA", otherwise false
     */
    public static boolean fileIsEmpty() {
        return readFromFile().get(0).equals("NA");
    }

    /**
     * Deletes the contents of the file and replaces it with "NA" (empty file).
     */
    public static void deleteFileContent() {
        writeToFile(new ArrayList<>(Arrays.asList("NA")));
    }

/* 
--FILESTRUCTURE--
D0,D1,D2,...
P0,P1,P2,...
balance,currentbet
hasPlacedBet,hasEndedRound 
*/

    public static void main(String[] args) {
        List<String> l = new ArrayList<>(
            Arrays.asList("C1,C3,C5", "\nS1,S2,S4", "\n200,20")
        );
        writeToFile(l);
        deleteFileContent();
        readFromFile();
        System.out.println(fileIsEmpty());
    }

}