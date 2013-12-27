package net.spanbroek.parsing;

import org.junit.Test;

import static net.spanbroek.parsing.Parsing.range;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RangeTests {

    Parser parser = range('b', 'd');

    @Test
    public void shouldParseCharactersWithinRange() {
        assertEquals("b", parser.parse("b"));
        assertEquals("c", parser.parse("c"));
        assertEquals("d", parser.parse("d"));
    }

    @Test
    public void shouldNotParseCharactersOutsideRange() {
        assertNull(parser.parse("a"));
        assertNull(parser.parse("e"));
    }

    @Test
    public void shouldNotParseEmptyString() {
        assertNull(parser.parse(""));
    }
}
