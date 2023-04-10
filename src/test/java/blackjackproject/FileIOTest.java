package blackjackproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FileIOTest {
    
    private CardGame cg1;
   
    @BeforeEach
    public void setUp() {
        cg1 = new CardGame(200, 1);
        cg1.getPlayer().increaseBet();
        cg1.getPlayer().setHasPlacedBet(true);
        cg1.getPlayer().drawCard(new Card('S', 1), cg1.getCardDeck());
        cg1.getPlayer().drawCard(new Card('C', 7), cg1.getCardDeck());
        cg1.getDealer().drawCard(new Card('C', 12), cg1.getCardDeck());
        cg1.getDealer().drawCard(new Card('D', 3), cg1.getCardDeck());
    }

    @Test
    @DisplayName("Test writing to file")
    public void testWriteToFile() throws IOException {
        FileIO.writeToFile(cg1.getCurrentCardGame());
        List<String> actualLines = FileIO.readFromFile();
        List<String> expectedLines = new ArrayList<>(Arrays.asList(
            "C12,D3",
            "S1,C7",
            "200,10",
            "true,false"
        ));

        assertEquals(expectedLines.size(), actualLines.size(), "Wrong amount of lines!");
        for (int i = 0; i < actualLines.size(); i++) {
            assertEquals(expectedLines.get(i), actualLines.get(i));
        }
        FileIO.deleteFileContent();
    }

    @Test
    @DisplayName("Test reading from file")
    public void testReadFromFile() throws IOException {

        List<String> expectedLines = new ArrayList<>(Arrays.asList(
            "H13,D12",
            "\nS3,C5,C9",
            "\n240,0",
            "\ntrue,true"
        ));
        FileIO.writeToFile(expectedLines);
        List<String> actualLines = FileIO.readFromFile();

        assertEquals(expectedLines.size(), actualLines.size(), "Wrong amount of lines!");
        for (int i = 0; i < actualLines.size(); i++) {
            assertEquals(expectedLines.get(i).strip(), actualLines.get(i));
        }
        FileIO.deleteFileContent();
    }

    @Test
    @DisplayName("Test that file is empty")
    public void testFileIsEmpty() throws IOException {
        FileIO.writeToFile(cg1.getCurrentCardGame());
        assertFalse(FileIO.fileIsEmpty());
        FileIO.deleteFileContent();
        assertTrue(FileIO.fileIsEmpty());
    }

}
