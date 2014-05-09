package net.spanbroek.parsing;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RemainingInputTests {

    @Test
    public void canBeCheckedForPrefix() {
        assertTrue(new RemainingInput("foobar").startsWith("foo"));
        assertFalse(new RemainingInput("foobar").startsWith("bar"));
        assertFalse(new RemainingInput("fo").startsWith("foo"));
    }

    @Test
    public void canBeShifted() {
        RemainingInput foobar = new RemainingInput("foobar");
        assertTrue(foobar.shift(0).startsWith("foo"));
        assertTrue(foobar.shift(3).startsWith("bar"));
    }

    @Test
    public void canBeCompared() {
        RemainingInput foo = new RemainingInput("foo");
        assertEquals(foo, new RemainingInput("foo"));
        assertFalse(foo.equals(new RemainingInput("bar")));
        assertFalse(foo.equals(foo.shift(1)));
    }

    @Test
    public void hasLength() {
        assertEquals(3, new RemainingInput("foo").length());
        assertEquals(6, new RemainingInput("foobar").length());
        assertEquals(3, new RemainingInput("foobar").shift(3).length());
    }

    @Test
    public void hasFirstCharacter() {
        assertEquals('f', new RemainingInput("foo").firstCharacter());
        assertEquals('a', new RemainingInput("bar").shift(1).firstCharacter());
    }

    @Test
    public void knowsItsPosition() {
        RemainingInput input = new RemainingInput("foo\nbar");
        assertEquals(new Position(1, 1), input.getPosition());
        assertEquals(new Position(1, 2), input.shift(1).getPosition());
        assertEquals(new Position(2, 1), input.shift(4).getPosition());
    }
}
