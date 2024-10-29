package fr.aymeric.kata.mower.model;


import fr.aymeric.kata.mower.util.EnumInstruction;
import fr.aymeric.kata.mower.util.EnumOrientation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit-level testing for {@link Mower} object.
 */
class MowerTest {
    /**
     * Test the creation of a land mower.
     */
    @Test
    void createMower() {
        Lawn lawn = new Lawn(5, 5);
        Mower mower = new Mower(lawn, 1, 2, EnumOrientation.NORTH);
        assertNotNull(mower);
        assertEquals(1, mower.getPositionX());
        assertEquals(2, mower.getPositionY());
        assertEquals(EnumOrientation.NORTH, mower.getOrientation());
    }

    /**
     * Test the creation of an invalid land mower.
     * IllegalArgumentException should be thrown if the land mower is out of the lawn.
     * NullPointerException should be thrown if the orientation is null.
     */
    @Test
    void createInvalidMower() {
        Lawn lawn = new Lawn(5, 5);
        assertThrows(IllegalArgumentException.class, () -> new Mower(lawn, 6, 5, EnumOrientation.NORTH));
        assertThrows(IllegalArgumentException.class, () -> new Mower(lawn, 5, 6, EnumOrientation.NORTH));
        assertThrows(IllegalArgumentException.class, () -> new Mower(lawn, -1, 5, EnumOrientation.NORTH));
        assertThrows(IllegalArgumentException.class, () -> new Mower(lawn, 5, -1, EnumOrientation.NORTH));
        assertThrows(NullPointerException.class, () -> new Mower(lawn, 3, 3, null));
    }

    /**
     * Test the movement of a land mower.
     */
    @Test
    void moveMower() {
        // Test the movement and rotation in all orientations
        Lawn lawn = new Lawn(5, 5);
        Mower mower = new Mower(lawn, 1, 2, EnumOrientation.NORTH);
        lawn.addMower(mower);

        mower.moveForwardIfValid();
        assertEquals(1, mower.getPositionX());
        assertEquals(3, mower.getPositionY());
        assertEquals(EnumOrientation.NORTH, mower.getOrientation());

        mower.rotateRight();
        mower.moveForwardIfValid();
        assertEquals(2, mower.getPositionX());
        assertEquals(3, mower.getPositionY());
        assertEquals(EnumOrientation.EAST, mower.getOrientation());

        mower.rotateRight();
        mower.moveForwardIfValid();
        assertEquals(2, mower.getPositionX());
        assertEquals(2, mower.getPositionY());
        assertEquals(EnumOrientation.SOUTH, mower.getOrientation());

        mower.rotateRight();
        mower.moveForwardIfValid();
        assertEquals(1, mower.getPositionX());
        assertEquals(2, mower.getPositionY());
        assertEquals(EnumOrientation.WEST, mower.getOrientation());

        mower.rotateLeft();
        assertEquals(EnumOrientation.SOUTH, mower.getOrientation());
    }

    /**
     * Test the getCurrentPosition method output.
     */
    @Test
    void getMowerPosition() {
        Lawn lawn = new Lawn(5, 5);
        Mower mower = new Mower(lawn, 3, 1, EnumOrientation.WEST);
        lawn.addMower(mower);
        assertEquals("3 1 W", mower.getCurrentPosition());
    }

    /**
     * Test the execution of instructions on a land mower.
     */
    @Test
    void executeMowerInstructions() {
        // Test the movement and rotation in all orientations
        Lawn lawn = new Lawn(5, 5);
        Mower mower = new Mower(lawn, 1, 2, EnumOrientation.NORTH);
        lawn.addMower(mower);

        mower.executeInstruction(EnumInstruction.LEFT);
        assertEquals("1 2 W", mower.getCurrentPosition());
        mower.executeInstruction(EnumInstruction.FRONT);
        assertEquals("0 2 W", mower.getCurrentPosition());

        mower.executeInstruction(EnumInstruction.LEFT);
        assertEquals("0 2 S", mower.getCurrentPosition());
        mower.executeInstruction(EnumInstruction.FRONT);
        assertEquals("0 1 S", mower.getCurrentPosition());

        mower.executeInstruction(EnumInstruction.LEFT);
        assertEquals("0 1 E", mower.getCurrentPosition());
        mower.executeInstruction(EnumInstruction.FRONT);
        assertEquals("1 1 E", mower.getCurrentPosition());

        mower.executeInstruction(EnumInstruction.LEFT);
        assertEquals("1 1 N", mower.getCurrentPosition());
        mower.executeInstruction(EnumInstruction.FRONT);
        assertEquals("1 2 N", mower.getCurrentPosition());

        mower.executeInstruction(EnumInstruction.RIGHT);
        assertEquals("1 2 E", mower.getCurrentPosition());
    }

    /**
     * Test the movement of a land mower out of the lawn.
     * The land mower should not move if the movement is invalid.
     */
    @Test
    void executeInvalidMovement() {
        Lawn lawn = new Lawn(5, 5);
        Mower mower = new Mower(lawn, 5, 5, EnumOrientation.NORTH);
        lawn.addMower(mower);

        // Try to move out of the lawn, the land mower should not move
        mower.executeInstruction(EnumInstruction.FRONT);
        assertEquals("5 5 N", mower.getCurrentPosition());
        mower.executeInstruction(EnumInstruction.RIGHT);
        assertEquals("5 5 E", mower.getCurrentPosition());
        mower.executeInstruction(EnumInstruction.FRONT);
        assertEquals("5 5 E", mower.getCurrentPosition());

        // Create an obstacle in the way, and try to move over it. The land mower should not move
        Mower obstacle = new Mower(lawn, 5, 4, EnumOrientation.NORTH);
        lawn.addMower(obstacle);
        mower.executeInstruction(EnumInstruction.RIGHT);
        mower.executeInstruction(EnumInstruction.FRONT);
        assertEquals("5 5 S", mower.getCurrentPosition());
    }

    /**
     * Test the execution of invalid instructions.
     * NullPointerException should be thrown if the instruction is null.
     */
    @Test
    void testInvalidInstructions() {
        Lawn lawn = new Lawn(5, 5);
        Mower mower = new Mower(lawn, 1, 1, EnumOrientation.NORTH);
        lawn.addMower(mower);
        assertThrows(NullPointerException.class, () -> mower.executeInstruction(null));
    }

    /**
     * Test the setting of the orientation of a land mower.
     */
    @Test
    void testSetOrientation() {
        Lawn lawn = new Lawn(5, 5);
        Mower mower = new Mower(lawn, 1, 1, EnumOrientation.NORTH);
        lawn.addMower(mower);

        // Test setting a valid orientation
        mower.setOrientation(EnumOrientation.EAST);
        assertEquals(EnumOrientation.EAST, mower.getOrientation());
        mower.setOrientation(EnumOrientation.SOUTH);
        assertEquals(EnumOrientation.SOUTH, mower.getOrientation());
        mower.setOrientation(EnumOrientation.WEST);
        assertEquals(EnumOrientation.WEST, mower.getOrientation());
        mower.setOrientation(EnumOrientation.NORTH);
        assertEquals(EnumOrientation.NORTH, mower.getOrientation());

        // Test setting a null orientation
        assertThrows(NullPointerException.class, () -> mower.setOrientation(null));
    }
}
