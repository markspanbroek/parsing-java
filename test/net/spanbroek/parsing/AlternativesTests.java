package net.spanbroek.parsing;

import org.junit.Test;

import static net.spanbroek.parsing.Parsing.choice;
import static net.spanbroek.parsing.Parsing.literal;
import static net.spanbroek.parsing.util.Results.result;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AlternativesTests {

    private Parser parser = choice(literal("foo"), literal("bar"), literal("baz"));

    @Test
    public void shouldParseAlternatives() {
        assertEquals(result("foo"), parser.parse("foo"));
        assertEquals(result("bar"), parser.parse("bar"));
        assertEquals(result("baz"), parser.parse("baz"));
    }

    @Test
    public void shouldFailToParseAlternatives() {
        assertNull(parser.parse("qux"));
    }
}
