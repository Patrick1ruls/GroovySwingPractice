package gui

import javax.swing.BorderFactory
import javax.swing.JButton
import javax.swing.JPanel
import java.awt.FlowLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

class ToolBar extends JPanel implements ActionListener{ // extends JPanel ensures that this component is a JPanel. Action listener for mouse clicks
    private JButton helloButton
    private JButton goodbyeButton
    private StringListener textListener             // interface

    ToolBar() { // Constructor
        setBorder(BorderFactory.createEtchedBorder()) // Create a line border below the tool bar
        helloButton = new JButton("Hello")     // Button in tool bar that says Hello
        goodbyeButton = new JButton("Goodbye") // Button in tool bar that says Goodbye

        helloButton.addActionListener(this)
        goodbyeButton.addActionListener(this)

        setLayout(new FlowLayout(FlowLayout.LEFT))   // This flow layout will manage the component placement within the toolbar
        add(helloButton)
        add(goodbyeButton)
    }

    // Good set up (Tool bar is now loosely coupled with the text panel)
    void setStringListener(StringListener listener) { // Setter
        this.textListener = listener                  // Saves reference to listener here in textListener above in this class
    }

    @Override
    void actionPerformed(ActionEvent event1) {
        JButton clicked = (JButton)event1.getSource() // Retrieves which button is pressed

        if(clicked == helloButton) {
            if (textListener != null)
                textListener.textEmitted("Hello\n")
        }
        else if(clicked == goodbyeButton) {
            if(textListener != null)
                textListener.textEmitted("Goodbye\n")
        }
    }
}
