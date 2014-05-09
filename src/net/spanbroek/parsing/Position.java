package net.spanbroek.parsing;

public class Position {

    private int lineNumber;
    private int columnNumber;

    public Position(int lineNumber, int columnNumber) {
        this.lineNumber = lineNumber;
        this.columnNumber = columnNumber;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    @Override
    public String toString() {
        return "[" + lineNumber + ", " + columnNumber + ']';
    }

    @Override
    public boolean equals(Object that) {
        return this == that ||
                that instanceof Position &&
                lineNumber == ((Position) that).lineNumber &&
                columnNumber == ((Position) that).columnNumber;
    }

    @Override
    public int hashCode() {
        return 31 * lineNumber + columnNumber;
    }
}
