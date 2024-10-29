package fr.aymeric.kata.mower.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Lawn class represents a bounded lawn where mowers can move.
 */
public class Lawn {
    /**
     * Size of the lawn on X axis.
     */
    private final int sizeX;
    /**
     * Size of the lawn on Y axis.
     */
    private final int sizeY;

    /**
     * List of mowers on the lawn.
     * @see Mower
     */
    private final List<Mower> mowers = new ArrayList<>();

    /**
     * Constructor of the lawn.
     * @param sizeX Size of the lawn on X axis.
     * @param sizeY Size of the lawn on Y axis.
     */
    public Lawn(int sizeX, int sizeY) {
        if (sizeX <= 0) {
            throw new IllegalArgumentException("Size X must be greater than 0");
        }
        if (sizeY <= 0) {
            throw new IllegalArgumentException("Size Y must be greater than 0");
        }
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    /**
     * Add a mower to the lawn.
     * @param mower Mower to add.
     */
    public void addMower(Mower mower) {
        mowers.add(mower);
    }

    /**
     * Get the list of mowers on the lawn.
     * @return Unmodifiable list of mowers.
     */
    public List<Mower> getMowers() {
        return Collections.unmodifiableList(mowers);
    }

    /**
     * Get the size of the lawn on X axis.
     * @return Size of the lawn on X axis.
     */
    public int getSizeX() {
        return sizeX;
    }

    /**
     * Get the size of the lawn on Y axis.
     * @return Size of the lawn on Y axis.
     */
    public int getSizeY() {
        return sizeY;
    }

    /**
     * Check if a position is free (not occupied by another mower) on the lawn.
     *
     * @param x X position to check.
     * @param y Y position to check.
     * @return True if the position is free, false otherwise.
     */
    public boolean isPositionFree(int x, int y) {
        return mowers.stream().noneMatch(mower -> mower.getPositionX() == x && mower.getPositionY() == y);
    }

    /**
     * Check if a position is inside the lawn. <br>
     * A position is considered inside the lawn if it is in the range [0, sizeX] on X axis and [0, sizeY] on Y axis.
     *
     * @param x X position to check.
     * @param y Y position to check.
     * @return True if the position is inside the lawn, false otherwise.
     */
    public boolean isPositionInside(int x, int y) {
        return x >= 0 && x <= sizeX && y >= 0 && y <= sizeY;
    }

    /**
     * Check if a position is valid on the lawn. <br>
     * A position is considered valid if it is inside the lawn and free (not occupied by another mower).
     *
     * @param x X position to check.
     * @param y Y position to check.
     * @return True if the position is valid, false otherwise.
     */
    public boolean isPositionValid(int x, int y) {
        return isPositionInside(x, y) && isPositionFree(x, y);
    }

    @Override
    public String toString() {
        return "Lawn{" +
                "sizeX=" + sizeX +
                ", sizeY=" + sizeY +
                ", mowers=" + mowers +
                '}';
    }
}
