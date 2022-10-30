package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileEditor_v1 extends JDialog {
    private JPanel contentPane;
    private JButton buttonClose;
    private JButton buttonSave;
    private JButton buttonOpen;
    private JButton buttonGetSelectionBottom;

    private JButton buttonGetSelectionTop;
    private JTextArea textAreaTop;
    private JTextArea textAreaBottom;
    private JTextArea textAreaNew;
    private JButton buttonOpen1;

    String directory;
    String selection;
    String selection1;

    public FileEditor_v1() {
        setContentPane(contentPane);
        setModal(true);
        //    getRootPane().setDefaultButton(buttonOpen);

        buttonOpen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onButtonOpen();
            }
        });
        buttonOpen1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onButtonOpen1();
            }
        });

        buttonSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onButtonSave();
            }
        });

        buttonClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onButtonClose();
            }
        });
        buttonGetSelectionBottom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonGetSelectionBottom();
            }
        });
        buttonGetSelectionTop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonGetSelectionTop();
            }
        });


        // call onCancel() when cross is clicked
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onButtonClose();
            }
        });
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }


    public void saveFile(String directory, String filename) {
        if ((filename == null) || (filename.length() == 0))
            return;
        File file;
        FileWriter out = null;
        try {
            file = new File(directory, filename); // Create a file object
            out = new FileWriter(file); // And a char stream to write it
            textAreaNew.getLineCount(); // Get text from the text area
            String s = textAreaNew.getText();
            out.write(s);
        }
        // Display messages if something goes wrong
        catch (IOException e) {
            textAreaNew.setText(e.getClass().getName() + ": " + e.getMessage());
            this.setTitle("FileViewer: " + filename + ": I/O Exception");
        }
        // Always be sure to close the input stream!
        finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
            }
        }
    }

    public void loadAndDisplayFile(String directory, String filename) {
        if ((filename == null) || (filename.length() == 0))
            return;
        File file;
        FileReader in = null;
        // Read and display the file contents. Since we're reading text, we
        // use a FileReader instead of a FileInputStream.
        try {
            file = new File(directory, filename); // Create a file object
            in = new FileReader(file); // And a char stream to read it
            char[] buffer = new char[4096]; // Read 4K characters at a time
            int len; // How many chars read each time
            textAreaTop.setText(""); // Clear the text area
            while ((len = in.read(buffer)) != -1) { // Read a batch of chars
                String s = new String(buffer, 0, len); // Convert to a string
                textAreaTop.append(s); // And display them
            }
            this.setTitle("FileViewer: " + filename); // Set the window title
            textAreaTop.setCaretPosition(0); // Go to start of file
        }
        // Display messages if something goes wrong
        catch (IOException e) {
            textAreaTop.setText(e.getClass().getName() + ": " + e.getMessage());
            this.setTitle("FileViewer: " + filename + ": I/O Exception");
        }
        // Always be sure to close the input stream!
        finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
            }
        }
    }

    public void loadAndDisplayFile1(String directory, String filename) {
        if ((filename == null) || (filename.length() == 0))
            return;
        File file;
        FileReader in = null;
        // Read and display the file contents. Since we're reading text, we
        // use a FileReader instead of a FileInputStream.
        try {
            file = new File(directory, filename); // Create a file object
            in = new FileReader(file); // And a char stream to read it
            char[] buffer = new char[4096]; // Read 4K characters at a time
            int len; // How many chars read each time
            textAreaBottom.setText(""); // Clear the text area
            while ((len = in.read(buffer)) != -1) { // Read a batch of chars
                String s = new String(buffer, 0, len); // Convert to a string
                textAreaBottom.append(s); // And display them
            }
            this.setTitle("FileViewer: " + filename); // Set the window title
            textAreaBottom.setCaretPosition(0); // Go to start of file
        }
        // Display messages if something goes wrong
        catch (IOException e) {
            textAreaBottom.setText(e.getClass().getName() + ": " + e.getMessage());
            this.setTitle("FileViewer: " + filename + ": I/O Exception");
        }
        // Always be sure to close the input stream!
        finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
            }
        }
    }


    private void onButtonOpen() {
        FileDialog f = new FileDialog(this, "Otvori fajl", FileDialog.LOAD);
        f.setDirectory(directory);
        f.setVisible(true);
        directory = f.getDirectory();
        loadAndDisplayFile(directory, f.getFile());
        f.dispose();
    }

    private void onButtonOpen1() {
        FileDialog f = new FileDialog(this, "Otvori fajl", FileDialog.LOAD);
        f.setDirectory(directory);
        f.setVisible(true);
        directory = f.getDirectory();
        loadAndDisplayFile1(directory, f.getFile());
        f.dispose();
    }

    private void onButtonSave() {
        // Create a file dialog box to prompt for a new file to display
        FileDialog f = new FileDialog(this, "Otvori fajl", FileDialog.SAVE);
        f.setDirectory(directory); // Set the default directory
        // Display the dialog and wait for the user's response
        f.setVisible(true);
        directory = f.getDirectory(); // Remember new default directory
        saveFile(directory, f.getFile()); // Load and display selection
        f.dispose(); // Get rid of the dialog box
    }

    private void buttonGetSelectionBottom() {
        selection = textAreaBottom.getSelectedText();
        //textAreaNew.append(selection);
        String pom = textAreaNew.getText();
        textAreaNew.setText(pom + "\n" + selection);

    }

    private void buttonGetSelectionTop() {
        selection1 = textAreaTop.getSelectedText();
        //textAreaNew.append(selection1);
        String pom1 = textAreaNew.getText();
        textAreaNew.setText(pom1 + "\n" + selection1);

    }


    private void onButtonClose() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        FileEditor_v1 dialog = new FileEditor_v1();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }


}
