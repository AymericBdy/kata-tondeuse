package fr.aymeric.kata.mower;

import fr.aymeric.kata.mower.model.Lawn;
import fr.aymeric.kata.mower.model.Mower;
import fr.aymeric.kata.mower.util.EnumInstruction;
import fr.aymeric.kata.mower.util.EnumOrientation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * MowerProgramExecutor class is responsible for reading and executing mower instructions from a file.
 */
public class MowerProgramExecutor {
    /**
     * Entry point of the program.
     * Reads and executes the mower instructions from the given file.
     *
     * @param instructionFile File containing the instructions.
     * @return The lawn after executing the instructions.
     * @throws RuntimeException If the file format is invalid or the file is not found.
     */
    public static Lawn readAndExecuteInstructions(File instructionFile) {
        try (Scanner scanner = new Scanner(instructionFile)) {
            Lawn lawn = createLawn(scanner);
            int mowerIndex = 0;
            while(scanner.hasNext()) {
                Mower mower = createMower(scanner, lawn);
                String instructions = scanner.next();
                executeMowerInstructions(mower, instructions);
                String finalPosition = String.format("Mower %d position: %s", mowerIndex + 1, mower.getCurrentPosition());
                KataMowerMain.getLogger().info(finalPosition);
                mowerIndex++;
            }
            return lawn;
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Invalid instruction file format", e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Instructions file not found", e);
        }
    }

    /**
     * Creates a Lawn object from the scanner input. <br>
     * The input format must be: "sizeX sizeY".
     *
     * @param scanner Scanner to read the input.
     * @return The created Lawn object.
     * @throws NoSuchElementException If the input format is invalid.
     */
    private static Lawn createLawn(Scanner scanner) throws NoSuchElementException {
        int sizeX = scanner.nextInt();
        int sizeY = scanner.nextInt();
        return new Lawn(sizeX, sizeY);
    }

    /**
     * Creates a Mower object from the scanner input. <br>
     * The input format must be: "positionX positionY orientation".
     *
     * @param scanner Scanner to read the input.
     * @param lawn Lawn where the mower will move.
     * @return The created Mower object.
     * @throws NoSuchElementException If the input format is invalid.
     * @throws IllegalArgumentException If the orientation is invalid.
     */
    private static Mower createMower(Scanner scanner, Lawn lawn) throws NoSuchElementException, IllegalArgumentException {
        int positionX = scanner.nextInt();
        int positionY = scanner.nextInt();
        char orientationKey = scanner.next().charAt(0);
        EnumOrientation orientation = EnumOrientation.fromKey(orientationKey);
        Mower mower = new Mower(lawn, positionX, positionY, orientation);
        lawn.addMower(mower);
        return mower;
    }

    /**
     * Executes the given instructions on the given mower.
     *
     * @param mower Mower to execute the instructions on.
     * @param instructions Instructions to execute.
     * @throws IllegalArgumentException If an invalid instruction is found.
     */
    private static void executeMowerInstructions(Mower mower, String instructions) throws IllegalArgumentException{
        for(char instructionKey : instructions.toCharArray()) {
            EnumInstruction instruction = EnumInstruction.fromKey(instructionKey);
            switch(instruction) {
                case FRONT:
                    mower.moveForwardIfValid();
                    break;
                case LEFT:
                    mower.rotateLeft();
                    break;
                case RIGHT:
                    mower.rotateRight();
                    break;
                default:
                    throw new IllegalArgumentException("Unknown instruction " + instruction);
            }
        }
    }
}
