package it.unibo.oop.lab.advanced;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {

    private final DrawNumber model;
    private final List<DrawNumberView> views;

    /**
     * @param views
     *          The views to show at the same time.
     */
    public DrawNumberApp(final DrawNumberView... views) {
        final DrawNumberLoader loader = new DrawNumberLoader();
        this.model = new DrawNumberImpl(loader.getMinimum(), loader.getMaximum(),
                loader.getAttempts());
        this.views = Arrays.asList(Arrays.copyOf(views, views.length));
        for (final var view : this.views) {
            view.setObserver(this);
            view.start();
        }
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            for (final var view : this.views) {
                view.result(result);
            }
        } catch (IllegalArgumentException e) {
            for (final var view : this.views) {
                view.numberIncorrect();
            }
        } catch (AttemptsLimitReachedException e) {
            for (final var view : this.views) {
                view.limitsReached();
            }
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        System.exit(0);
    }

    /**
     * @param args
     *            ignored
     * @throws FileNotFoundException 
     */
    public static void main(final String... args) throws FileNotFoundException {
        new DrawNumberApp(new DrawNumberViewImpl(), new DrawNumberViewImpl(), new PrintLog("log.txt"), new PrintLog(System.out));
    }

}
