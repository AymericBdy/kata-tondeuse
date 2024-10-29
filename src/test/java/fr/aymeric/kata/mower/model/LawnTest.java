package fr.aymeric.kata.mower.model;

import fr.aymeric.kata.mower.util.EnumOrientation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit-level testing for {@link Lawn} object.
 */
class LawnTest {
    /**
     * Test the creation of a lawn.
     */
    @Test
    void createLawn() {
        Lawn lawn = new Lawn(5, 5);
        assertNotNull(lawn);
        assertEquals(5, lawn.getSizeX());
        assertEquals(5, lawn.getSizeY());
    }

    /**
     * Test the creation of an invalid lawn.
     * IllegalArgumentException should be thrown if the size is invalid.
     */
    @Test
    void testInvalidSizes() {
        assertThrows(IllegalArgumentException.class, () -> new Lawn(0, 5));
        assertThrows(IllegalArgumentException.class, () -> new Lawn(5, 0));
    }

    /**
     * Test the isPositionInside method.
     * The method should return true if the position is inside the lawn, false otherwise.
     */
    @Test
    void isPositionInside() {
        Lawn lawn = new Lawn(5, 5);
        // Test inside positions
        assertTrue(lawn.isPositionInside(0, 0));
        assertTrue(lawn.isPositionInside(5, 5));
        // Test out-of-bounds positions
        assertFalse(lawn.isPositionInside(6, 5));
        assertFalse(lawn.isPositionInside(5, 6));
        assertFalse(lawn.isPositionInside(-1, 0));
        assertFalse(lawn.isPositionInside(0, -1));
    }

    /**
     * Test the isPositionFree method.
     * The method should return true if the position is free (not occupied by a land mower), false otherwise.
     */
    @Test
    void isPositionFree() {
        Lawn lawn = new Lawn(5, 5);
        Mower mower1 = new Mower(lawn, 1, 2, EnumOrientation.SOUTH);
        Mower mower2 = new Mower(lawn, 2, 3, EnumOrientation.NORTH);
        lawn.addMower(mower1);
        lawn.addMower(mower2);
        // Verify the land mowers are in the lawn
        assertEquals(2, lawn.getMowers().size());
        // Test occupied positions
        assertFalse(lawn.isPositionFree(1, 2));
        assertFalse(lawn.isPositionFree(2, 3));
        // Test free positions
        assertTrue(lawn.isPositionFree(1, 3));
        assertTrue(lawn.isPositionFree(5, 4));
    }

    /**
     * Test the isPositionValid method.
     * The method should return true if the position is valid (inside the lawn and free), false otherwise.
     */
    @Test
    void isPositionValid() {
        Lawn lawn = new Lawn(5, 5);
        Mower mower1 = new Mower(lawn, 1, 2, EnumOrientation.SOUTH);
        Mower mower2 = new Mower(lawn, 2, 3, EnumOrientation.NORTH);
        lawn.addMower(mower1);
        lawn.addMower(mower2);
        // Test occupied positions
        assertFalse(lawn.isPositionValid(1, 2));
        assertFalse(lawn.isPositionValid(2, 3));
        // Test valid positions
        assertTrue(lawn.isPositionValid(1, 3));
        assertTrue(lawn.isPositionValid(1, 5));
        assertTrue(lawn.isPositionValid(5, 2));
        // Test out-of-bounds positions
        assertFalse(lawn.isPositionValid(6, 6));
        assertFalse(lawn.isPositionValid(-1, 0));
    }
}
