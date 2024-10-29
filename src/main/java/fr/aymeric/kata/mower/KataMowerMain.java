package fr.aymeric.kata.mower;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Logger;

/**
 * Main class for the Mower Program.
 * <p>
 * This class serves as the entry point for the application. It reads the input file
 * containing the instructions for the mowers and executes them.
 * </p>
 * <p>
 * Usage:
 * <pre>
 * java -jar MowerProgram.jar [inputFile]
 * </pre>
 * If no input file is provided, it defaults to "input_instructions.txt".
 * </p>
 */
public class KataMowerMain {
    /**
     * Logger for this project.
     */
    private static final Logger logger = Logger.getLogger(KataMowerMain.class.getName());

    /**
     * Main method to start the Mower Program. <br>
     * The first argument is optional and can be the path to the input file. It defaults to "input_instructions.txt".
     *
     * @param args Command line arguments.
     *             args[0]: Optional path to the input file.
     * @throws FileNotFoundException If the input file is not found.
     */
    public static void main(String[] args) throws FileNotFoundException {
        String inputFile = "input_instructions.txt";
        if (args.length > 0) {
            inputFile = args[0];
        }
        File instructionsFile = new File(inputFile);
        if (!instructionsFile.exists()) {
            throw new FileNotFoundException("Instructions file not found: " + inputFile);
        }
        MowerProgramExecutor.readAndExecuteInstructions(instructionsFile);
    }

    /**
     * Gets the logger for this project.
     *
     * @return Logger instance for this project.
     */
    public static Logger getLogger() {
        return logger;
    }
}
