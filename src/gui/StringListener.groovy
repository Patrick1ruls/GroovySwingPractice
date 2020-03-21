package gui

interface StringListener {        // Creating an interface to decouple the tool bar and text panel (Or force a convention I guess)
    void textEmitted(String text) // Any class that implements this interface must have the interface's methods
}
