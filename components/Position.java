package components;

import interfaces.IPosition;

/**
 * Stores the position in the source file when a token is generated
 */
public class Position implements IPosition {

    private int lineCounter;
    private int columnCounter;

    /**
     * parametrized constructor
     * @param lineCounter
     * @param columnCounter
     */
    public Position(int lineCounter, int columnCounter) {
        this.lineCounter = lineCounter;
        this.columnCounter = columnCounter;
    }

    public int getLineCounter() {
        return lineCounter;
    }

    public int getColumnCounter() {
        return columnCounter;
    }

    public String toString() {
        return "(" + lineCounter + ", " + columnCounter + ")";
    }

}
