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

    public List<String> readFromFile() {
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            for (int currentLine = 1; currentLine < 4; currentLine++) {
                String line = reader.readLine();
                lines.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static void main(String[] args) {
        FileIO fileIO = new FileIO("SavedGame.txt");
        List<String> l = new ArrayList<>(
            Arrays.asList("C1,C3,C5", "\nS1,S2,S4", "\n200,20")
        );
        fileIO.writeToFile(l);
    }

}
