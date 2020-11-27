package it.unibo.oop.lab.mvcio2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import it.unibo.oop.lab.mvcio.Controller;
/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    /*
     * TODO: Starting from the application in mvcio:
     * 
     * 1) Add a JTextField and a button "Browse..." on the upper part of the
     * graphical interface.
     * Suggestion: use a second JPanel with a second BorderLayout, put the panel
     * in the North of the main panel, put the text field in the center of the
     * new panel and put the button in the line_end of the new panel.
     * 
     * 2) The JTextField should be non modifiable. And, should display the
     * current selected file.
     * 
     * 3) On press, the button should open a JFileChooser. The program should
     * use the method showSaveDialog() to display the file chooser, and if the
     * result is equal to JFileChooser.APPROVE_OPTION the program should set as
     * new file in the Controller the file chosen. If CANCEL_OPTION is returned,
     * then the program should do nothing. Otherwise, a message dialog should be
     * shown telling the user that an error has occurred (use
     * JOptionPane.showMessageDialog()).
     * 
     * 4) When in the controller a new File is set, also the graphical interface
     * must reflect such change. Suggestion: do not force the controller to
     * update the UI: in this example the UI knows when should be updated, so
     * try to keep things separated.
     */

    private final JFrame frame = new JFrame();

    public SimpleGUIWithFileChooser(final Controller c) {

        final Dimension screen = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);
        frame.setLocationByPlatform(true);

        final JPanel panel1 = new JPanel(new BorderLayout());
        final JPanel panel2 = new JPanel(new BorderLayout());
        final JTextArea stringArea = new JTextArea();
        final JTextField currentSelected = new JTextField();
        final JButton saveButton = new JButton("Save");
        final JButton browseButton = new JButton("Browse...");

        panel1.add(panel2, BorderLayout.NORTH);
        panel1.add(stringArea, BorderLayout.CENTER);
        panel1.add(saveButton, BorderLayout.SOUTH);

        panel2.add(currentSelected, BorderLayout.CENTER);
        panel2.add(browseButton, BorderLayout.LINE_END);

        currentSelected.setText(c.getStringPath());

        // Handlers

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent es) {
                final JFrame optFrame = new JFrame();
                optFrame.setLocationRelativeTo(frame);

                final int option = JOptionPane.showConfirmDialog(optFrame, "Do you really want to save the text?",
                        "Confirm text save", JOptionPane.CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    try {
                        c.saveString(stringArea.getText());
                        JOptionPane.showMessageDialog(null, "Text saved in " + c.getStringPath());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        browseButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {

                final JFileChooser fileChooser = new JFileChooser();
                final int option = fileChooser.showSaveDialog(frame);
                if (option == JFileChooser.APPROVE_OPTION) {
                    c.setCurrentFile(fileChooser.getSelectedFile());
                    currentSelected.setText(c.getStringPath());
                } else if (option == JFileChooser.CANCEL_OPTION) {
                    JOptionPane.showMessageDialog(frame, "An error as occurred");
                }
            }
        });


        frame.add(panel1);

        frame.setVisible(true);

    }

    public static void main(final String... args) {
        new SimpleGUIWithFileChooser(new Controller());

    }
}
