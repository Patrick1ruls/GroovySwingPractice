package gui

import javax.swing.SwingUtilities

class SwingApp {                                    // Main program (Note written in java, put in groovy)
    static void main(String[] args) {
        SwingUtilities.invokeLater new Runnable() { // Set up JFrame window
            void run() {                            // Questionable but should make window more robust
                new MainFrame()                     // Uses magic to create window
            }
        }

    }
}

