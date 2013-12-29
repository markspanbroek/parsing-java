package net.spanbroek.parsing;

import java.util.LinkedList;
import java.util.List;

public class Trampoline {
    private List<Runnable> scheduled = new LinkedList<Runnable>();

    public void schedule(Runnable item) {
        scheduled.add(item);
    }

    public void run() {
        while (!scheduled.isEmpty()) {
            scheduled.remove(0).run();
        }
    }
}
