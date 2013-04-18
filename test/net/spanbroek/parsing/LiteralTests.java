package net.spanbroek.parsing;

import org.junit.Test;
import static net.spanbroek.parsing.Parsing.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LiteralTests {

    private Parser parser = literal("foo");

    @Test
    public void shouldSuccessfullyParseALiteral() {
        assertEquals("foo", parser.parse("foo"));
    }

    @Test
    public void shouldFailToParseALiteral() {
        assertNull(parser.parse("bar"));
    }
}
