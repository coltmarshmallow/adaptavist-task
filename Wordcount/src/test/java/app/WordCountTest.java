package app.wordcount;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordCountTest {

    @Test
    public void testWordCount() {
        // Redirect System.out to capture output
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        // Call the main method with the test file path
        String[] args = {"src/test/resources/test.txt"};
        WordCount.main(args);

        // Get the output
        String output = outputStreamCaptor.toString().trim();

        // Check the output against expected values
        String expectedOutput = "in: 5\n" +
                "id: 4\n" +        
                "tempor: 3\n" +
                "semper: 2\n" +
                "aenean: 1";
        assertEquals(expectedOutput, output);
    }
}

