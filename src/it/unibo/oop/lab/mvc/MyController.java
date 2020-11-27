package it.unibo.oop.lab.mvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyController implements Controller {

    private String stringToPrint;
    private final List<String> history;

    public MyController(final String stringToPrint) {
        super();
        this.stringToPrint = stringToPrint;
        this.history = new ArrayList<>(Arrays.asList(stringToPrint));
    }

    public MyController() {
        super();
        this.stringToPrint = "No string setted";
        this.history = new ArrayList<>();
    }

    @Override
    public final void setStringToPrint(final String stringToPrint) {
        if (stringToPrint != null) {
            this.stringToPrint = stringToPrint;
        }
    }

    @Override
    public final String getStringToPrint() {
        return this.stringToPrint;
    }

    @Override
    public final List<String> getHistory() {
        return this.history;
    }

    @Override
    public final void printCurrentString() {
        if (getStringToPrint() == null) {
            throw new IllegalStateException("There's nothing to print");
        }
        System.out.println(this.getStringToPrint());
        this.history.add(stringToPrint);
    }

}
