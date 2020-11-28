package it.unibo.oop.lab.advanced;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class PrintLog implements DrawNumberView {

    private final PrintStream printStream;

    public PrintLog(final PrintStream printStream) {
        super();
        this.printStream = printStream;
    }

    public PrintLog(final String file) throws FileNotFoundException {
        this(new PrintStream(new FileOutputStream(new File(file))));
    }

    @Override
    public final void setObserver(final DrawNumberViewObserver observer) {
        // TODO Auto-generated method stub
    }

    @Override
    public void start() {
        // TODO Auto-generated method stub

    }

    @Override
    public final void numberIncorrect() {
        this.printStream.println("Incorrect number");
    }

    @Override
    public final void result(final DrawResult res) {
        this.printStream.println(res.getDescription());

    }

    @Override
    public final void limitsReached() {
        this.printStream.println("Limits reached");

    }

    @Override
    public final void displayError(final String message) {
        this.printStream.println(message);
    }

}
