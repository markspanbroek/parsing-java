package net.spanbroek.parsing;

import org.junit.Test;

import static net.spanbroek.parsing.util.Results.result;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class ChartTests {

    private Chart chart = new Chart();
    private Result result = result("result");
    private RemainingInput input = new RemainingInput("input");
    private RemainingInput remainder = new RemainingInput("remainder");
    private ResultHandler handler = mock(ResultHandler.class);

    @Test
    public void shouldNotifyWhenAResultIsProvided() {
        chart.waitForResults(input, handler);
        chart.provideResult(input, result, remainder);

        verify(handler).handle(result, remainder);
    }

    @Test
    public void shouldNotNotifyAboutResultForADifferentInput() {
        chart.waitForResults(input, handler);
        chart.provideResult(new RemainingInput("different input"), result, remainder);

        verify(handler, never()).handle(result, remainder);
    }

    @Test
    public void shouldNotifyAboutMultipleResults() {
        chart.waitForResults(input, handler);
        chart.provideResult(input, result, new RemainingInput("remainder1"));
        chart.provideResult(input, result, new RemainingInput("remainder2"));

        verify(handler).handle(result, new RemainingInput("remainder1"));
        verify(handler).handle(result, new RemainingInput("remainder2"));
    }

    @Test
    public void shouldNotifyAboutResultsFromThePast() {
        chart.provideResult(input, result, new RemainingInput("remainder1"));
        chart.provideResult(input, result, new RemainingInput("remainder2"));
        chart.waitForResults(input, handler);

        verify(handler).handle(result, new RemainingInput("remainder1"));
        verify(handler).handle(result, new RemainingInput("remainder2"));
    }

    @Test
    public void shouldNotifyMultipleListeners() {
        ResultHandler handler1 = mock(ResultHandler.class);
        ResultHandler handler2 = mock(ResultHandler.class);

        chart.waitForResults(input, handler1);
        chart.waitForResults(input, handler2);
        chart.provideResult(input, result, remainder);

        verify(handler1).handle(result, remainder);
        verify(handler2).handle(result, remainder);
    }

    @Test
    public void shouldNotifyAboutAResultOnlyOnce() {
        chart.waitForResults(input, handler);
        chart.provideResult(input, result, remainder);
        chart.provideResult(input, result, remainder);

        verify(handler, times(1)).handle(result, remainder);
    }

    @Test
    public void shouldKnowWhenWaitingForResults() {
        assertFalse(chart.isWaitingForResults(input));
        chart.waitForResults(input, handler);
        assertTrue(chart.isWaitingForResults(input));
    }
}
