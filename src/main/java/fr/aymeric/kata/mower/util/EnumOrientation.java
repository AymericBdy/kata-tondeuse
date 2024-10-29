package fr.aymeric.kata.mower.util;

/**
 * EnumOrientation represents the orientation of a mower.
 */
public enum EnumOrientation {
    NORTH('N'),
    EAST('E'),
    SOUTH('S'),
    WEST('W');

    /**
     * The key representing the orientation.
     * It is used to parse the orientation from a character.
     */
    private final char orientationKey;

    /**
     * EnumOrientation constructor.
     *
     * @param orientationKey the character representing the orientation
     */
    EnumOrientation(char orientationKey) {
        this.orientationKey = orientationKey;
    }

    /**
     * Parses an orientation from a character.
     *
     * @param orientationKey the character representing the orientation
     * @return the orientation corresponding to the character
     * @throws IllegalArgumentException if the character does not correspond to any orientation
     */
    public static EnumOrientation fromKey(char orientationKey) throws IllegalArgumentException {
        for(EnumOrientation orientation : values()) {
            if(orientation.getOrientationKey() == orientationKey) {
                return orientation;
            }
        }
        throw new IllegalArgumentException("Invalid orientation key: " + orientationKey);
    }

    /**
     * @return the character representing the orientation
     */
    public char getOrientationKey() {
        return orientationKey;
    }
}
