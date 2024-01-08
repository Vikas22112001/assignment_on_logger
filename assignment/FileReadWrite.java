import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

class CustomFileException extends Exception {
    public CustomFileException(String errorMessage) {
        super(errorMessage);
    }
}

public class FileReadWrite {
    private static final Logger logger = Logger.getLogger(FileReadWrite.class.getName());

    public static void main(String[] args) {
        String inputFile = "input.txt"; // Replace with your input file name
        String outputFile = "output.txt"; // Replace with your output file name

        try {
            copyFileContent(inputFile, outputFile);
            logger.info("File content copied successfully from " + inputFile + " to " + outputFile);
        } catch (CustomFileException e) {
            logger.severe("CustomFileException: " + e.getMessage());
        }
    }

    public static void copyFileContent(String inputFile, String outputFile) throws CustomFileException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                bufferedWriter.write(line);
                bufferedWriter.newLine(); // Add a new line after each line read
            }
        } catch (IOException e) {
            throw new CustomFileException("Error reading/writing file: " + e.getMessage());
        }
    }
}
