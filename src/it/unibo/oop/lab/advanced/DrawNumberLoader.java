package it.unibo.oop.lab.advanced;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class DrawNumberLoader {

    private final List<String> configLines = new ArrayList<>();

    public DrawNumberLoader() {
        final InputStream in = ClassLoader.getSystemResourceAsStream("config.yml");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            while (br.ready()) {
                configLines.add(br.readLine());
            }
        } catch (IOException e) {
            System.out.println("Errore nella lettura I/O");
        }
    }

    public final int getMinimum() {
        final int min = this.searchSource("minimum");
        if (min == Integer.MAX_VALUE) {
            return Integer.MIN_VALUE;
        }
        return min;
    }

    public final int getMaximum() {
        return this.searchSource("maximum");
    }

    public final int getAttempts() {
        return this.searchSource("attempts");
    }

    private int searchSource(final String toSearch) {
        int source = Integer.MAX_VALUE;
        for (final String line : this.configLines) {
            if (line.contains(toSearch)) {
                source = Integer.parseInt(line.split(": ")[1]);
                break;
            }
        }
        return source;
    }



}
