package gui

import Controller.Controller

import javax.swing.JCheckBoxMenuItem
import javax.swing.JFileChooser
import javax.swing.JFrame
import javax.swing.JMenu
import javax.swing.JMenuBar
import javax.swing.JMenuItem
import javax.swing.JOptionPane
import javax.swing.KeyStroke
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.KeyEvent

// Model View Controller architecture
class MainFrame extends JFrame {      // Hooks together and manages (controls) components (Highly specialized batch of hardware components)
    private TextPanel textPanel
    private ToolBar toolBar
    private FormPanel formPanel // Making static allows me to use with setWindowMenu() method
    private JFileChooser fileChooser
    private Controller controller
    private TablePanel tablePanel

    MainFrame() { // Constructor
        super("Patrick's Window") // Magical equivalent to JFrame frame = new JFrame("Patrick's Window") which creates a JFrame window
        setLayout(new BorderLayout())  // Manages where objects go on window page

        // Initialize Components
        toolBar = new ToolBar()
        textPanel = new TextPanel()    // Region that contains text
        formPanel = new FormPanel()
        setJMenuBar(createMenuBar())
        fileChooser = new JFileChooser()
        controller = new Controller()
        tablePanel = new TablePanel()

        // Connecting the people database to the table panel
        tablePanel.setData(controller.getPeople())

        tablePanel.setPersonTableListener(new PersonTableListener() {
             void rowDeleted(int row) {
                controller.removePerson(row)
            }
        })

        // Set file filter
        fileChooser.addChoosableFileFilter(new PersonFileFilter()) // Filters what files can be imported/exported in program (PersonFileFilter is class I have to make)

        /**
         * Good loosely coupled implementation. The toolbar can now indirectly communicate with the text panel via the string listener
         * ___________                                         __________________                                                        _____________
         * |         |                                         |                |                                                        |           |
         * | toolBar |--(sends string when button is clicked)->| StringListener |>--(string is read by text panel and printed to screen->| textPanel |
         * |_________|                                         |________________|                                                        |___________|
         *
         * Now if the text panel is down, the toolBar just won't send any strings
         */
        toolBar.setStringListener(new StringListener() { // Anonymous class that listens for the string listener that comes from the toolbar
            void textEmitted(String text) {
                textPanel.appendText(text)
            }
        })

        /**
         * Good loosely coupled implementation. The form panel can now indirectly communicate with the text panel via the event listener (Event carries multiple variables)
         * _____________                                                                     _________________                                                                              _____________
         * |           |                                                                     |               |                                                                              |           |
         * | formPanel |--(sends event [containing name/occupation] when button is clicked)->| EventListener |>--(Event's name and occupation are read by text panel and printed to screen->| textPanel |
         * |___________|                                                                     |_______________|                                                                              |___________|
         *
         * Now if the text panel is down, the toolBar just won't send any strings
         */
        formPanel.setFormListener(new FormListener() { // Anonymous class (exists in created interface below) that listens for the form event that comes from the form panel
            void formEventOccurred(FormEvent event) { // When an event is detected by the event listener in the form panel. That event will get piped up to the main frame here
                /*
                String name = event.getName()
                String occupation = event.getOccupation()
                int ageCat = event.getAgeCat()
                String empCat = event.getEmpCat()
                String taxId = event.getTaxId()
                boolean usCitizen = event.isUsCitizen()
                String gender = event.getGender()

                FormToTextPanel(name, occupation, ageCat, empCat, taxId, usCitizen, gender) // Handles logic for what to print to text panel based on what's present in the form
                */
                controller.addPerson(event)
                tablePanel.refresh() // Refresh the table panel when it receives new info
            }
        })

        // Place Components in JFrame (Window)
        add(formPanel,BorderLayout.WEST)
        add(toolBar, BorderLayout.NORTH)        // Place tool bar at top of window (Note: The other components resize to fit around each other)
        add(tablePanel, BorderLayout.CENTER)


        // Set up the JFrame (Window) variables (size, how to close, etc)
        setMinimumSize(new Dimension(500, 400)) // Set's minimum size so program doesn't go wacky
        setSize(800, 800)          // Set the JFrame window size
        setDefaultCloseOperation(EXIT_ON_CLOSE) // WARNING: the application will keep running unless this is put in
        setVisible(true)                        // Makes the JFrame window visible
    }

    // Handles logic for what to print to text panel based on what's present in the form panel
/*    void FormToTextPanel(String name, String occupation, int ageCat, String empCat, String taxId, boolean usCitizen, String gender) {
        name = name.trim()
        occupation = occupation.trim()
        empCat = empCat.trim()
        taxId = taxId.trim()
        gender = gender.trim()

        if(name == "" && occupation == "" && taxId == "") {
            print "" // Do nothing if name and occupation are empty
        }
        else if(name == "") { // Can also input logic to handle if one field is empty
            textPanel.appendText("Please enter a name\n")
        }
        else if(occupation == "") {
            textPanel.appendText("Please enter an occupation\n")
        }
        else if(empCat == "") {
            textPanel.appendText("Please enter an employment category\n")
        }
        else if(taxId == "" && usCitizen) {
            textPanel.appendText("Please enter a Tax ID\n")
        }
        else if(!usCitizen) { // If not US Citizen
            textPanel.appendText("$name: $occupation | Age Category: $ageCat | Gender: $gender | $empCat | Is US Citizen: $usCitizen\n")
        }
        else {
            textPanel.appendText("$name: $occupation | Age Category: $ageCat | Gender: $gender | $empCat | tax ID: $taxId | Is US Citizen: $usCitizen\n") // Once form event is received, take name and occupation and print them on the text panel
        }
    }
*/
    private createMenuBar() {
        JMenuBar menuBar = new JMenuBar()

        menuBar.add(setFileMenu())
        menuBar.add(setWindowMenu())

        return menuBar
    }

    private JMenu setFileMenu() {
        JMenu fileMenu = new JMenu("File")
        JMenuItem exportDataItem = new JMenuItem("Export Data...")
        JMenuItem importDataItem = new JMenuItem("Import Data...")
        JMenuItem exitItem = new JMenuItem("Exit")

        fileMenu.add(exportDataItem)
        fileMenu.add(importDataItem)
        fileMenu.addSeparator() // Thin lines that separate menu groups
        fileMenu.add(exitItem)

        // Set Exit Option
        exitItem.addActionListener(new ActionListener() {
            void actionPerformed(ActionEvent event) {
                int checkExitAction = JOptionPane.showConfirmDialog( // Creates dialog box checking if user really wants to exit
                        MainFrame.this, /* Need to reference the Main Frame */
                        "Are you sure you want to exit the application?",
                        "Confirm Exit",
                        JOptionPane.OK_CANCEL_OPTION
                )
                if (checkExitAction == JOptionPane.OK_OPTION) { // Only exits when OK is clicked
                    System.exit(0) // Quit program when exit menu item is clicked
                }
            }
        })

        // Set Mnemonics
        fileMenu.setMnemonic(KeyEvent.VK_F) // Mnemonic is similar to keyboard shortcut using alt+KEY VK_F = Virtual Key F
        exitItem.setMnemonic(KeyEvent.VK_X) // TODO: Figure out why Mnemonic isn't getting the window to pop up

        // Set Accelerators
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK)) // CTRL + X is now a shortcut to close the program!!
        importDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK)) // CTRL + X is now a shortcut to close the program!!

        // Set File Import (Load file)
        importDataItem.addActionListener(new ActionListener() { // When clicking "Import Data..." open the file chooser
            void actionPerformed(ActionEvent event) { // Set parent window to Main Frame
                fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES) // Let's me select folders
                if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) { // Check if user confirms choice rather than cancels
                    try {
                        controller.loadFromFile(fileChooser.getSelectedFile())
                        tablePanel.refresh()
                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(
                                MainFrame.this,
                                "Could not load data from file.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        )
                    }
                }
            }
        })

        // Set File Export (Save file)
        exportDataItem.addActionListener(new ActionListener() { // When clicking "Export Data..." open the file chooser
            void actionPerformed(ActionEvent event) { // Set parent window to Main Frame
                fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES) // Let's me select folders
                if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) { // Check if user confirms choice rather than cancels
                    try {
                        controller.saveToFile(fileChooser.getSelectedFile())
                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(
                                MainFrame.this,
                                "Could not save data to file.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        )
                    }
                }
            }
        })

        return fileMenu
    }

    private JMenu setWindowMenu() {
        // Show Menu
        JMenu showMenu = new JMenu("Show...")
        JMenuItem showPeresonFormItem = new JCheckBoxMenuItem("Person Form") // Can check and uncheck that menu item now
        showPeresonFormItem.setSelected(true)
        showMenu.add(showPeresonFormItem)
        showPeresonFormItem.addActionListener(new ActionListener() {
            void actionPerformed(ActionEvent event) {
                JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem)event.getSource() // Returns JCheckBoxMenuItem from showPeresonFormItem

                formPanel.setVisible(menuItem.isSelected()) // Form panel will only be visible when the show form menu is checked
            }
        })

        // Window Menu
        JMenu windowMenu = new JMenu("Window")
        windowMenu.add(showMenu)

        // Add accelerator to show person form
        showPeresonFormItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK))

        return windowMenu
    }
}
