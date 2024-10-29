package fr.aymeric.kata.mower;

import fr.aymeric.kata.mower.model.Mower;
import fr.aymeric.kata.mower.model.Lawn;
import fr.aymeric.kata.mower.util.EnumOrientation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link MowerProgramExecutor} class.
 */
class MowerProgramExecutorTest {
    /**
     * Temporary directory for the test instructions file.
     */
    @TempDir
    Path tempDir;

    /**
     * Test the {@link MowerProgramExecutor#readAndExecuteInstructions(File)} method. <br>
     * This test also tests the internal private methods of the class.
     *
     * @throws IOException if an I/O error occurs while writing the instructions file
     */
    @Test
    void testReadAndExecuteInstructions() throws IOException {
        // Create a temporary file with test instructions
        final File instructionFile = getTestFile();
        // Verify the lawn size is correct
        Lawn lawn = MowerProgramExecutor.readAndExecuteInstructions(instructionFile);
        assertLawnSize(lawn, 10, 10);
        // Verify if the mower position and orientation are correct
        List<Mower> mowers = lawn.getMowers();
        assertEquals(4, mowers.size());
        checkMowerPosition(mowers.get(0), 1, 2, EnumOrientation.SOUTH);
        checkMowerPosition(mowers.get(1), 5, 6, EnumOrientation.EAST);
        checkMowerPosition(mowers.get(2), 7, 8, EnumOrientation.WEST);
        checkMowerPosition(mowers.get(3), 3, 3, EnumOrientation.NORTH);
    }

    /**
     * Helper method to create a temporary file with test instructions.
     *
     * @return the created temporary file
     * @throws IOException if an I/O error occurs while writing the instructions file
     */
    private File getTestFile() throws IOException {
        File instructionFile = tempDir.resolve("instructions1.txt").toFile();
        try (FileWriter writer = new FileWriter(instructionFile)) {
            // Create a lawn of size 10x10
            writer.write("10 10\n");
            // Create 4 mowers with different positions and orientations, verify they are correctly read
            writer.write("1 2 S\nGD\n");
            writer.write("5 6 E\nGD\n");
            writer.write("7 8 W\nGD\n");
            // Test forward instructions are correctly read and executed
            writer.write("2 2 N\nDAGA\n");
        }
        return instructionFile;
    }

    /**
     * Helper method to check the size of a {@link Lawn}.
     *
     * @param lawn      the {@link Lawn} to check
     * @param expectedX the expected X size
     * @param expectedY the expected Y size
     */
    private void assertLawnSize(Lawn lawn, int expectedX, int expectedY) {
        assertNotNull(lawn);
        assertEquals(expectedX, lawn.getSizeX());
        assertEquals(expectedY, lawn.getSizeY());
    }

    /**
     * Helper method to check the position and orientation of a {@link Mower}.
     *
     * @param mower   the {@link Mower} to check
     * @param positionX   the expected X position
     * @param positionY   the expected Y position
     * @param orientation the expected orientation
     */
    private void checkMowerPosition(Mower mower, int positionX, int positionY, EnumOrientation orientation) {
        assertNotNull(mower);
        assertEquals(positionX, mower.getPositionX());
        assertEquals(positionY, mower.getPositionY());
        assertEquals(orientation, mower.getOrientation());
    }

    /**
     * Test the {@link MowerProgramExecutor#readAndExecuteInstructions(File)} method with invalid orientation instruction.
     * Ensure an {@link IllegalArgumentException} is thrown.
     *
     * @throws IOException if an I/O error occurs while writing the instructions file
     */
    @Test
    void testMowerWithInvalidOrientation() throws IOException {
        // Create a temporary file with test instructions
        File instructionFile = tempDir.resolve("instructions3.txt").toFile();
        try (FileWriter writer = new FileWriter(instructionFile)) {
            // Create a lawn of size 5x5
            writer.write("5 5\n");
            // Create a mower with invalid instructions
            writer.write("1 2 X\n");
            writer.write("AGD\n");
        }
        assertThrows(IllegalArgumentException.class, () -> MowerProgramExecutor.readAndExecuteInstructions(instructionFile));
    }

    /**
     * Test the {@link MowerProgramExecutor#readAndExecuteInstructions(File)} method with invalid mower movement instruction.
     * Ensure an {@link IllegalArgumentException} is thrown.
     *
     * @throws IOException if an I/O error occurs while writing the instructions file
     */
    @Test
    void testExecuteInvalidMowerInstructions() throws IOException {
        // Create a temporary file with test instructions
        File instructionFile = tempDir.resolve("instructions2.txt").toFile();
        try (FileWriter writer = new FileWriter(instructionFile)) {
            // Create a lawn of size 5x5
            writer.write("5 5\n");
            // Create a mower with invalid instructions
            writer.write("1 2 W\n");
            writer.write("AGDF\n");
        }
        assertThrows(IllegalArgumentException.class, () -> MowerProgramExecutor.readAndExecuteInstructions(instructionFile));
    }
}
