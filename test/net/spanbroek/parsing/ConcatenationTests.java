package net.spanbroek.parsing;

import org.junit.Test;

import static net.spanbroek.parsing.Parsing.*;

import static net.spanbroek.parsing.util.Results.result;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ConcatenationTests {

    private Parser parser = concat("foo", "bar");

    @Test
    public void shouldSuccessfullyParseAConcatenation() {
        assertEquals(result("foo", "bar"), parser.parse("foobar"));
    }

    @Test
    public void shouldFailParsingAConcatenation() {
        assertNull(parser.parse("foobaz"));
    }

    @Test
    public void shouldReturnListOfResults() {
        parser = concat("a", "b", "c");
        assertEquals(result("a","b","c"), parser.parse("abc"));
    }

    @Test
    public void shouldHandleConcatenationOfOne() {
        parser = concat("foo");
        assertEquals("foo", parser.parse("foo"));
    }

    @Test
    public void shouldHandleEmptyConcatenation() {
        parser = concat();
        assertEquals(result(), parser.parse(""));
    }
}
