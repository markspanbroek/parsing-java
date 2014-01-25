package net.spanbroek.parsing;

import org.junit.Test;

import static net.spanbroek.parsing.Parsing.literal;
import static net.spanbroek.parsing.Parsing.optional;
import static net.spanbroek.parsing.util.Results.result;
import static org.junit.Assert.assertEquals;

public class OptionalTests {

    Parser parser = optional(literal("foo"));

    @Test
    public void shouldParseOptional() {
        assertEquals("foo", parser.parse("foo"));
    }

    @Test
    public void shouldParseEmpty() {
        assertEquals(result(), parser.parse(""));
    }
}
