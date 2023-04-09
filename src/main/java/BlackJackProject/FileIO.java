package blackjackproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileIO {
    
    private String file;

    public FileIO(String file) {
        this.file = file;
    }

    /**
     * Writes each element in a list to a file. It overwrites existing content. 
     * It also catches IOException and prints out the stack trace. 
     * 
     * @param lines List of lines to write
    */
    public void writeToFile(List<String> lines) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(file)));
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
     * @return Each line in the file in the form of a list
     */
    public List<String> readFromFile() {
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
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
    public boolean fileEmpty() {
        return readFromFile().get(0).equals("NA");
    }

    /**
     * Deletes the contents of the file and replaces it with "NA" (empty file).
     */
    public void deleteFileContent() {
        writeToFile(new ArrayList<>(Arrays.asList("NA")));
    }

/* 
--FILSTRUKTUR--
D0,D1,D2,...
P0,P1,P2,...
balance,currentbet
hasPlacedBet,hasEndedRound 
*/

    public static void main(String[] args) {
        FileIO fileIO = new FileIO("SavedGame.txt");
        List<String> l = new ArrayList<>(
            Arrays.asList("C1,C3,C5", "\nS1,S2,S4", "\n200,20")
        );
        fileIO.writeToFile(l);
        fileIO.deleteFileContent();
        fileIO.readFromFile();
        System.out.println(fileIO.fileEmpty());
    }

}

