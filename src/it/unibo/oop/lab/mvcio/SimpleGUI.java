package it.unibo.oop.lab.mvcio;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame();

    /*
     * Once the Controller is done, implement this class in such a way that:
     * 
     * 1) It has a main method that starts the graphical application
     * 
     * 2) In its constructor, sets up the whole view
     * 
     * 3) The graphical interface consists of a JTextArea with a button "Save" right
     * below (see "ex02.png" for the expected result). SUGGESTION: Use a JPanel with
     * BorderLayout
     * 
     * 4) By default, if the graphical interface is closed the program must exit
     * (call setDefaultCloseOperation)
     * 
     * 5) The program asks the controller to save the file if the button "Save" gets
     * pressed.
     * 
     * Use "ex02.png" (in the res directory) to verify the expected aspect.
     */

    /**
     * builds a new {@link SimpleGUI}.
     * 
     * @param c
     *          The controller to use in the gui
     */
    public SimpleGUI(final Controller c) {                        // Ho spostato il controller qui dopo aver analizzato le soluzioni
        /*
         * Make the frame half the resolution of the screen. This very method is
         * enough for a single screen setup. In case of multiple monitors, the
         * primary is selected.
         * 
         * In order to deal coherently with multimonitor setups, other
         * facilities exist (see the Java documentation about this issue). It is
         * MUCH better than manually specify the size of a window in pixel: it
         * takes into account the current resolution.
         */
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);
        /*
         * Instead of appearing at (0,0), upper left corner of the screen, this
         * flag makes the OS window manager take care of the default positioning
         * on screen. Results may vary, but it is generally the best choice.
         */
        frame.setLocationByPlatform(true);

        final JPanel panel = new JPanel(new BorderLayout());
        final JTextArea stringArea = new JTextArea();
        panel.add(stringArea, BorderLayout.CENTER);
        final JButton saveButton = new JButton("Save");
        panel.add(saveButton, BorderLayout.SOUTH);

        // Handlers

        saveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {

                final JFrame optFrame = new JFrame("Confirm Save");
                optFrame.setLocationRelativeTo(frame);

                final int option = JOptionPane.showConfirmDialog(optFrame, 
                        "Do you really want to save the text?", "Confirm text save", JOptionPane.CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                        try {
                            c.saveString(stringArea.getText());
                            JOptionPane.showMessageDialog(null, "Text saved in " + c.getStringPath(),
                                    "Done", JOptionPane.CANCEL_OPTION);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                }

            }
        });

        frame.add(panel);

        frame.setVisible(true);
    }

    public static void main(final String... args) {

        new SimpleGUI(new Controller());
    }

}
