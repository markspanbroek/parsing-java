package net.spanbroek.parsing;

import org.junit.Test;

import static net.spanbroek.parsing.Parsing.choice;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AlternativesTests {

    private Parser parser = choice("foo", "bar", "baz");

    @Test
    public void shouldParseAlternatives() {
        assertEquals("foo", parser.parse("foo"));
        assertEquals("bar", parser.parse("bar"));
        assertEquals("baz", parser.parse("baz"));
    }

    @Test
    public void shouldFailToParseAlternatives() {
        assertNull(parser.parse("qux"));
    }

    @Test
    public void shouldHandleChoiceOfOne() {
        parser = choice("foo");
        assertEquals("foo", parser.parse("foo"));
    }

    @Test
    public void shouldHandleEmptyChoice() {
        parser = choice();
        assertNull(parser.parse(""));
    }

}
