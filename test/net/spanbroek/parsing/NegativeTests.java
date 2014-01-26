package net.spanbroek.parsing;

import org.junit.Test;

import static net.spanbroek.parsing.Parsing.no;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class NegativeTests {

    @Test
    public void shouldNotParseSpecifiedCharacters() {
        Parser parser = no('b', 'c');
        assertNull(parser.parse("b"));
        assertNull(parser.parse("c"));
    }

    @Test
    public void shouldParseAllOtherCharacters() {
        Parser parser = no('b');
        assertEquals("a", parser.parse("a"));
        assertEquals("c", parser.parse("c"));
    }
}
