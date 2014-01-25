package net.spanbroek.parsing;

import org.junit.Test;

import static net.spanbroek.parsing.Parsing.*;
import static net.spanbroek.parsing.util.Results.result;
import static org.junit.Assert.assertEquals;

public class RepetitionTests {

    @Test
    public void shouldParseRepetition() {
        Parser parser = repeat("a");
        assertEquals(result("a", "a", "a"), parser.parse("aaa"));
    }

    @Test
    public void shouldParseRepetitionAsPartOfALargerExpression() {
        Parser parser = concat(repeat("a"), "b");
        assertEquals(result("a", "a", "b"), parser.parse("aab"));
    }

    @Test
    public void shouldConcatenateItsArguments() {
        Parser parser = repeat("a", "b");
        assertEquals(result("a", "b", "a", "b"), parser.parse("abab"));
    }
}
