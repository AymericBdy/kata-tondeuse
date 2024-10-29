package fr.aymeric.kata.mower.util;

/**
 * EnumInstruction represents the instructions that can be given to a mower.
 */
public enum EnumInstruction {
    FRONT('A'),
    LEFT('G'),
    RIGHT('D');

    /**
     * The key representing the instruction.
     * It is used to parse the instruction from a character.
     */
    private final char instructionKey;

    /**
     * EnumInstruction constructor.
     *
     * @param instructionKey the character representing the instruction
     */
    EnumInstruction(char instructionKey) {
        this.instructionKey = instructionKey;
    }

    /**
     * Parses an instruction from a character.
     *
     * @param instructionKey the character representing the instruction
     * @return the instruction corresponding to the character
     * @throws IllegalArgumentException if the character does not correspond to any instruction
     */
    public static EnumInstruction fromKey(char instructionKey) throws IllegalArgumentException {
        for(EnumInstruction instruction : values()) {
            if(instruction.getInstructionKey() == instructionKey) {
                return instruction;
            }
        }
        throw new IllegalArgumentException("Invalid instruction key: " + instructionKey);
    }

    /**
     * @return the character representing the instruction
     */
    public char getInstructionKey() {
        return instructionKey;
    }
}
