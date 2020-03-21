package gui

import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTextArea
import java.awt.BorderLayout

class TextPanel extends JPanel { // JPanel is an object for containing components to be placed on the JFrame window
    private  JTextArea textArea

    TextPanel() { // Constructor gets ran when class first used
        textArea = new JTextArea()
        setLayout(new BorderLayout())
        add(new JScrollPane(textArea), BorderLayout.CENTER) // JScrollPane adds scroll bars to text area if it gets filled up
    }

    void appendText(String text) { // TextPanel method that adds provided text
        textArea.append(text)      // Not sure why I can't use this directly in the MainFrame class?
    }

}
