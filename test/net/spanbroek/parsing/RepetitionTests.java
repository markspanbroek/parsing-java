package net.spanbroek.parsing;

import org.junit.Test;

import static net.spanbroek.parsing.Parsing.concat;
import static net.spanbroek.parsing.Parsing.literal;
import static net.spanbroek.parsing.Parsing.repeat;
import static net.spanbroek.parsing.util.Results.result;
import static org.junit.Assert.assertEquals;

public class RepetitionTests {

    @Test
    public void shouldParseRepetition() {
        Parser parser = repeat(literal("a"));
        assertEquals(result("a", "a", "a"), parser.parse("aaa"));
    }

    @Test
    public void shouldParseRepetitionAsPartOfALargerExpression() {
        Parser parser = concat(repeat(literal("a")), literal("b"));
        assertEquals(result("a", "a", "b"), parser.parse("aab"));
    }
}
