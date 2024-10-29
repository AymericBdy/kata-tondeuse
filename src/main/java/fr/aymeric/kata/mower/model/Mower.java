package fr.aymeric.kata.mower.model;

import fr.aymeric.kata.mower.util.EnumInstruction;
import fr.aymeric.kata.mower.util.EnumOrientation;

import java.util.Objects;

/**
 * Mower class represents a mower that can move on a lawn.
 */
public class Mower {
    /**
     * Lawn where the mower is moving.
     */
    private final Lawn lawn;
    /**
     * Current X position of the mower.
     */
    private int positionX;
    /**
     * Current Y position of the mower.
     */
    private int positionY;
    /**
     * Current orientation of the mower.
     */
    private EnumOrientation orientation;

    /**
     * Constructor of the mower.
     * @param lawn Lawn where the mower is moving.
     * @param positionX Initial X position of the mower.
     * @param positionY Initial Y position of the mower.
     * @param orientation Initial orientation of the mower.
     * @throws NullPointerException If the lawn or the orientation is null.
     * @throws IllegalArgumentException If the initial position is invalid (outside of the lawn or occupied).
     */
    public Mower(Lawn lawn, int positionX, int positionY, EnumOrientation orientation) {
        Objects.requireNonNull(lawn, "Lawn cannot be null");
        Objects.requireNonNull(orientation, "Orientation cannot be null");
        if (!lawn.isPositionValid(positionX, positionY)) {
            throw new IllegalArgumentException("Invalid initial position");
        }
        this.lawn = lawn;
        this.positionX = positionX;
        this.positionY = positionY;
        this.orientation = orientation;
    }

    /**
     * Get the current X position of the mower.
     * @return Current X position of the mower.
     */
    public int getPositionX() {
        return positionX;
    }

    /**
     * Get the current Y position of the mower.
     * @return Current Y position of the mower.
     */
    public int getPositionY() {
        return positionY;
    }

    /**
     * Get the current orientation of the mower.
     * @return Current orientation of the mower.
     */
    public EnumOrientation getOrientation() {
        return orientation;
    }

    /**
     * Set the orientation of the mower.
     * @param orientation New orientation of the mower.
     * @throws NullPointerException If the given orientation is null.
     */
    public void setOrientation(EnumOrientation orientation) {
        Objects.requireNonNull(orientation, "Orientation cannot be null");
        this.orientation = orientation;
    }

    /**
     * Move the mower to a new position if it is valid (unoccupied and inside the lawn).
     * @param x New X position.
     * @param y New Y position.
     */
    public void moveToPositionIfValid(int x, int y) {
        if(!lawn.isPositionValid(x, y)) {
            return;
        }
        positionX = x;
        positionY = y;
    }

    /**
     * Move the mower forward if the new position is valid (unoccupied and inside of the lawn).
     */
    public void moveForwardIfValid() {
        switch(orientation) {
            case NORTH:
                moveToPositionIfValid(positionX, positionY + 1);
                break;
            case EAST:
                moveToPositionIfValid(positionX + 1, positionY);
                break;
            case SOUTH:
                moveToPositionIfValid(positionX, positionY - 1);
                break;
            case WEST:
                moveToPositionIfValid(positionX - 1, positionY);
                break;
        }
    }

    /**
     * Rotate the mower to the left.
     */
    public void rotateLeft() {
        switch(orientation) {
            case NORTH:
                orientation = EnumOrientation.WEST;
                break;
            case EAST:
                orientation = EnumOrientation.NORTH;
                break;
            case SOUTH:
                orientation = EnumOrientation.EAST;
                break;
            case WEST:
                orientation = EnumOrientation.SOUTH;
                break;
        }
    }

    /**
     * Rotate the mower to the right.
     */
    public void rotateRight() {
        switch(orientation) {
            case NORTH:
                orientation = EnumOrientation.EAST;
                break;
            case EAST:
                orientation = EnumOrientation.SOUTH;
                break;
            case SOUTH:
                orientation = EnumOrientation.WEST;
                break;
            case WEST:
                orientation = EnumOrientation.NORTH;
                break;
        }
    }

    /**
     * Execute an instruction on the mower.
     * @param instruction Instruction to execute.
     * @throws IllegalArgumentException If the instruction is unknown.
     * @throws NullPointerException If the instruction is null.
     */
    public void executeInstruction(EnumInstruction instruction) {
        Objects.requireNonNull(instruction, "Instruction cannot be null");
        switch(instruction) {
            case FRONT:
                moveForwardIfValid();
                break;
            case LEFT:
                rotateLeft();
                break;
            case RIGHT:
                rotateRight();
                break;
            default:
                throw new IllegalArgumentException("Unknown instruction");
        }
    }

    /**
     * Get the current position of the mower as a string.
     * @return Current position of the mower
     */
    public String getCurrentPosition() {
        return positionX + " " + positionY + " " + orientation.getOrientationKey();
    }

    @Override
    public String toString() {
        return "Mower{" +
                "positionX=" + positionX +
                ", positionY=" + positionY +
                ", orientation=" + orientation +
                '}';
    }
}
