package com.jinyframework.core.factories;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadFactory;

/**
 * The type Server thread factory.
 */
@Slf4j
public final class ServerThreadFactory implements ThreadFactory {
    private final String name;
    private final List<String> stats;
    private int counter;
    private boolean isDebug = false;

    /**
     * Instantiates a new Server thread factory.
     *
     * @param name the name
     */
    public ServerThreadFactory(@NonNull String name) {
        counter = 1;
        this.name = name;
        stats = new ArrayList<>();
    }

    @Override
    public Thread newThread(final Runnable runnable) {
        val t = new Thread(runnable, name + "-thread-" + counter);
        counter++;
        val info = String.format("Created thread id %d with name %s on %d", t.getId(), t.getName(),
                System.currentTimeMillis());
        stats.add(info);
        if (isDebug) {
            log.info(info);
        }
        return t;
    }

    /**
     * Sets debug.
     *
     * @param isDebug the is debug
     */
    public void setDebug(final boolean isDebug) {
        this.isDebug = isDebug;
    }

    /**
     * Gets stats.
     *
     * @return the stats
     */
    public String getStats() {
        val buffer = new StringBuilder();
        for (String stat : stats) {
            buffer.append(stat).append("\n");
        }
        return buffer.toString();
    }
}