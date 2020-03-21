package gui

import javax.swing.BorderFactory
import javax.swing.ButtonGroup
import javax.swing.DefaultComboBoxModel
import javax.swing.DefaultListModel
import javax.swing.JButton
import javax.swing.JCheckBox
import javax.swing.JComboBox
import javax.swing.JLabel
import javax.swing.JList
import javax.swing.JPanel
import javax.swing.JRadioButton
import javax.swing.JTextField
import javax.swing.border.Border
import java.awt.Dimension
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Insets
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.KeyEvent

class FormPanel extends JPanel {
    private JLabel nameLabel
    private JLabel occupationLabel
    private JTextField nameField // Field for inputting text info
    private JTextField occupationField
    private JButton okButton
    private FormListener formListener
    private JList ageList
    private JComboBox empCombo
    private JCheckBox citizenCheck // If US citizen then have to fill in taxField
    private JTextField taxField
    private JLabel taxLabel
    private JRadioButton maleRadio // Lets you choose between mutually exclusive options
    private JRadioButton femaleRadio
    private ButtonGroup genderGroup // Allows you to group buttons together

    FormPanel() { // Constructor
        initFormPanel()
        setFormButtonAction()
        setTaxCitizenController()
        setAgeListController()
        setEmpComboboxController()
        setGenderController()
        layoutGridComponents() // Set where everything is in the form panel

    }

    void initFormPanel() {
        // Set the size for the form panel
        Dimension dim = getPreferredSize()
        dim.width = 300
        setPreferredSize(dim)

        // Create borders for an Add Person section
        Border innerBorder = BorderFactory.createTitledBorder("Add Person") // Create a boarder within the panel titled Add Person
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5) // Create an invisible border that provides 5px spacing within the panel
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder)) // Mesh together multiple boarders

        // Create objects (labels, buttons)
        nameLabel = new JLabel("Name: ")
        occupationLabel = new JLabel("Occupation: ")
        nameField = new JTextField(10 /* Number of char's */)
        occupationField = new JTextField(10)
        okButton = new JButton("OK")
        ageList = new JList()
        empCombo = new JComboBox()
        citizenCheck = new JCheckBox()
        taxField = new JTextField(10) // Has 10 characters
        taxLabel = new JLabel("Tax ID: ")
        maleRadio = new JRadioButton("Male")
        femaleRadio = new JRadioButton("Female")
        genderGroup = new ButtonGroup() // Comes from jwt

        // Set Mnemonics
        okButton.setMnemonic(KeyEvent.VK_O) // Alt+O SHOULD now hit OK button for me
        nameLabel.setDisplayedMnemonic(KeyEvent.VK_N)
        nameLabel.setLabelFor(nameField)    // Sets association between name label and name field so mnemonic will work for name field now

    }

    void setFormButtonAction() {
        // Add listener to OK button. So that when it's pressed, the text field's info gets sent to the mainframe to be sent to the text panel
        okButton.addActionListener(new ActionListener() { // When button is clicked, perform the following below
            void actionPerformed(ActionEvent event1) {
                String name = nameField.getText() // grab text from name field and assign it to name variable
                String occupation = occupationField.getText()
                // Converting the age categories to their own object gives the advantage of accessing both a string and id
                AgeCategory ageCat = (AgeCategory)ageList.getSelectedValue() // (AgeCategory) coverts this object to an AgeCategory object
                String empCat = (String)empCombo.getSelectedItem() // Grab employee category and assign it to a variable
                String taxId = taxField.getText()
                boolean usCitizen = citizenCheck.isSelected()
                String gender = genderGroup.getSelection().getActionCommand() // Returns radio button (resulting in Male or Female)

                FormEvent ev = new FormEvent(this /* What's this for? (No pun intended) */, name, occupation, ageCat.getId(), empCat,
                        taxId, usCitizen, gender) // Create a form event to carry the name/occupation info from the form panel to the main frame

                if(formListener != null) { // If an event occurs, it will be detected by the form listener and trigger the following code
                    formListener.formEventOccurred(ev)
                }
            }
        })
    }

    void setFormListener(FormListener listener) {
        this.formListener = listener // Method that checks whether an event occurred
    }

    void layoutGridComponents() { // Sets the label locations within the form panel
        setLayout(new GridBagLayout()) // Will help control border layout
        GridBagConstraints gc = new GridBagConstraints() // Going to set the grid constraints below
        gc.fill = GridBagConstraints.NONE
        int y = 0

        /******************** FIRST ROW ********************/
        setGridComponent(
                gc,
                0,
                y,
                1,
                0.1,
                GridBagConstraints.LINE_END,
                new Insets(0, 0, 0, 5),
                nameLabel
        )

        setGridComponent(
                gc,
                1,
                y,
                1,
                0.1,
                GridBagConstraints.LINE_START,
                new Insets(0, 0, 0, 0),
                nameField
        )
        y++ // Increment row after all elements added

        /******************** SECOND ROW ********************/
        setGridComponent(
                gc,
                0,
                y,
                1,
                0.1,
                GridBagConstraints.LINE_END,
                new Insets(0, 0, 0, 5),
                occupationLabel
        )

        setGridComponent(
                gc,
                1,
                y,
                1,
                0.1,
                GridBagConstraints.LINE_START,
                new Insets(0, 0, 0, 0),
                occupationField
        )
        y++

        /******************** THIRD ROW ********************/
        setGridComponent(
                gc,
                0,
                y,
                1,
                0.1,
                GridBagConstraints.FIRST_LINE_END,
                new Insets(0, 0, 0, 5 ),
                new JLabel("Age: ")
        )

        setGridComponent(
                gc,
                1,
                y,
                1,
                0.1,
                GridBagConstraints.FIRST_LINE_START,
                new Insets(0, 4, 0, 0 ),
                ageList
        )
        y++

        /******************** FOURTH ROW ********************/
        setGridComponent(
                gc,
                0,
                y,
                1,
                0.1,
                GridBagConstraints.FIRST_LINE_END,
                new Insets(4, 0, 0, 0 ),
                new JLabel("Employment: ")
        )

        setGridComponent(
                gc,
                1,
                y,
                1,
                0.1,
                GridBagConstraints.FIRST_LINE_START,
                new Insets(0, 0, 0, 0 ),
                empCombo
        )
        y++

        /******************** FIFTH ROW ********************/
        setGridComponent(
                gc,
                0,
                y,
                1,
                0.1,
                GridBagConstraints.FIRST_LINE_END,
                new Insets(4, 0, 0, 0 ),
                new JLabel("US Citizen: ")
        )

        setGridComponent(
                gc,
                1,
                y,
                1,
                0.1,
                GridBagConstraints.FIRST_LINE_START,
                new Insets(0, 0, 0, 0 ),
                citizenCheck
        )
        y++

        /******************** SIXTH ROW ********************/
        setGridComponent(
                gc,
                0,
                y,
                1,
                0.1,
                GridBagConstraints.FIRST_LINE_END,
                new Insets(4, 0, 0, 0 ),
                taxLabel
        )

        setGridComponent(
                gc,
                1,
                y,
                1,
                0.1,
                GridBagConstraints.FIRST_LINE_START,
                new Insets(0, 0, 0, 0 ),
                taxField
        )
        y++

        /******************** SEVENTH ROW ********************/
        setGridComponent(
                gc,
                0,
                y,
                1,
                0.05,
                GridBagConstraints.FIRST_LINE_END,
                new Insets(4, 0, 0, 0 ),
                new JLabel("Gender: ")
        )

        setGridComponent(
                gc,
                1,
                y,
                1,
                0.05,
                GridBagConstraints.FIRST_LINE_START,
                new Insets(0, 0, 0, 0 ),
                maleRadio
        )
        y++

        /******************** EIGHTH ROW ********************/
        setGridComponent(
                gc,
                1,
                y,
                1,
                0.1,
                GridBagConstraints.FIRST_LINE_START,
                new Insets(0, 0, 0, 0 ),
                femaleRadio
        )
        y++

        /******************** NINTH ROW ********************/
        setGridComponent(
                gc,
                1,
                y,
                1,
                2.0,
                GridBagConstraints.FIRST_LINE_START,
                new Insets(0, 0, 0, 0 ),
                okButton
        )

    }

    // Sterilized
    void setGridComponent(GridBagConstraints gc, int gridx, int gridy, float weightx, float weighty, Anchor, Insets, object) {
        gc.gridy = gridy // Starts at 0 and increments by 1 for each row we go down

        gc.weightx = weightx // Controls how much space it takes up relative to other cell weights
        gc.weighty = weighty

        gc.gridx = gridx       // (0, 0) is top left hand corner | (1, 1) is bottom right hand corner
        gc.anchor = Anchor // Shifts text objects to the right
        gc.insets = Insets // Add 5px padding to right of name label
        add(object, gc) // Add the name label in the top left grid corner
    }

    void setTaxCitizenController() {
        taxLabel.setEnabled(false) // Looks greyed out
        taxField.setEnabled(false) // Looks greyed out
        // Tax field responds to whether citizen check box is ticked
        citizenCheck.addActionListener(new ActionListener() {
            void actionPerformed(ActionEvent event) { // Send an event when checkbox is ticked
                boolean isTicked = citizenCheck.isSelected()
                taxLabel.setEnabled(isTicked) // Tax field is no longer greyed out when citizen checkbox is ticked
                taxField.setEnabled(isTicked)
            }
        })

    }

    void setAgeListController() { // Set up a controller for the age list
        DefaultListModel ageModel = new DefaultListModel()
        ageModel.addElement(new AgeCategory(0, "Under 18"))
        ageModel.addElement(new AgeCategory(1, "18 to 65"))
        ageModel.addElement(new AgeCategory(2, "Over 65"))
        ageList.setModel(ageModel)

        ageList.setPreferredSize(new Dimension(110, 70))
        ageList.setBorder(BorderFactory.createEtchedBorder())
        ageList.setSelectedIndex(1) // Sets the default value hovered over in the list to the second option
    }

    void setEmpComboboxController() { // Set up a controller for the employee combo box
        DefaultComboBoxModel empModel = new DefaultComboBoxModel()
        empModel.addElement("employed")
        empModel.addElement("self-employed")
        empModel.addElement("unemployed")
        empCombo.setModel(empModel)
        empCombo.setSelectedIndex(0)
        empCombo.setEditable(true) // Allows me to type in a value into the employee combo box (Need to check what user sends, dangerous)
    }

    void setGenderController() {
        genderGroup.add(maleRadio)
        genderGroup.add(femaleRadio)
        maleRadio.setActionCommand("Male") // Male radio button responds with "Male" when clicked
        femaleRadio.setActionCommand("Female")
        maleRadio.setSelected(true)
    }

}


