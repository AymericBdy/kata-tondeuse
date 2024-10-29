package fr.aymeric.kata.mower.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the EnumInstruction enum.
 */
class EnumOrientationTest {
    /**
     * Tests the fromKey method with valid keys.
     * Ensures that the correct EnumInstruction is returned for each valid key.
     */
    @Test
    void testFromKeyValid() {
        assertEquals(EnumOrientation.NORTH, EnumOrientation.fromKey('N'));
        assertEquals(EnumOrientation.WEST, EnumOrientation.fromKey('W'));
        assertEquals(EnumOrientation.SOUTH, EnumOrientation.fromKey('S'));
        assertEquals(EnumOrientation.EAST, EnumOrientation.fromKey('E'));
    }

    /**
     * Tests the fromKey method with an invalid key.
     * Ensures that an IllegalArgumentException is thrown for an invalid key.
     */
    @Test
    void testFromKeyInvalid() {
        assertThrows(IllegalArgumentException.class, () -> EnumOrientation.fromKey('X'));
    }
}
