package net.spanbroek.parsing;

import java.util.HashMap;

public class Session {
    private final Trampoline trampoline = new Trampoline();
    private final Charts charts = new Charts();

    public Trampoline getTrampoline() {
        return trampoline;
    }

    public Chart getChartForRule(Rule rule) {
        if(!charts.containsKey(rule)) {
            charts.put(rule, new Chart());
        }
        return charts.get(rule);
    }

    private static class Charts extends HashMap<Rule, Chart>{}
}
