package net.spanbroek.parsing;

public class RemainingInput {

    private String input;
    private int offset;
    private Position position;

    public RemainingInput(String input) {
        this(input, 0, new Position(1,1));
    }

    private RemainingInput(String input, int offset, Position position) {
        this.input = input;
        this.offset = offset;
        this.position = position;
    }

    public boolean startsWith(String prefix) {
        int start = offset;
        int end = start + prefix.length();
        return end <= input.length() &&
                input.substring(start, end).equals(prefix);
    }

    public RemainingInput shift(int amountOfCharacters) {
        Position newPosition = shiftPosition(amountOfCharacters);
        return new RemainingInput(input, offset + amountOfCharacters, newPosition);
    }

    private Position shiftPosition(int amountOfCharacters) {
        int lineNumber = position.getLineNumber();
        int columnNumber = position.getColumnNumber();
        for (int i=0; i< amountOfCharacters; i++) {
            if (input.charAt(i) == '\n') {
                lineNumber++;
                columnNumber = 1;
            } else {
                columnNumber++;
            }
        }
        return new Position(lineNumber, columnNumber);
    }

    public int length() {
        return input.length() - offset;
    }

    public char firstCharacter() {
        return input.charAt(offset);
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object that) {
        return this == that ||
                that instanceof RemainingInput &&
                offset == ((RemainingInput) that).offset &&
                input.equals(((RemainingInput) that).input);
    }

    @Override
    public int hashCode() {
        return 31 * input.hashCode() + offset;
    }
}
