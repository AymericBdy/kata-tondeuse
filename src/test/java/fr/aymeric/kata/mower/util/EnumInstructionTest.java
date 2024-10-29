package fr.aymeric.kata.mower.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for EnumInstruction class.
 */
class EnumInstructionTest {
    /**
     * Test the fromKey method with valid keys.
     * Ensure the correct EnumInstruction is returned for each key.
     */
    @Test
    void testFromKeyValid() {
        assertEquals(EnumInstruction.FRONT, EnumInstruction.fromKey('A'));
        assertEquals(EnumInstruction.LEFT, EnumInstruction.fromKey('G'));
        assertEquals(EnumInstruction.RIGHT, EnumInstruction.fromKey('D'));
    }

    /**
     * Test the fromKey method with an invalid key.
     * Ensure an IllegalArgumentException is thrown.
     */
    @Test
    void testFromKeyInvalid() {
        assertThrows(IllegalArgumentException.class, () -> EnumInstruction.fromKey('X'));
    }
}
