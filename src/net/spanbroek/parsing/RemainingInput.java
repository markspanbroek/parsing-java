package net.spanbroek.parsing;

public class RemainingInput {

    private String input;
    private int offset;

    public RemainingInput(String input) {
        this(input, 0);
    }

    public RemainingInput(String input, int offset) {
        this.input = input;
        this.offset = offset;
    }

    public boolean startsWith(String prefix) {
        int start = offset;
        int end = start + prefix.length();
        return end <= input.length() &&
                input.substring(start, end).equals(prefix);
    }

    public RemainingInput shift(int amountOfCharacters) {
        return new RemainingInput(input, offset + amountOfCharacters);
    }

    public int length() {
        return input.length() - offset;
    }

    public char firstCharacter() {
        return input.charAt(offset);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RemainingInput that = (RemainingInput) o;
        return offset == that.offset && input.equals(that.input);
    }

    @Override
    public int hashCode() {
        return 31 * input.hashCode() + offset;
    }
}