package gui

class FormEvent extends EventObject { // The event object handles the transfer of private information through managing getters and setters
    private String name       // Temporarily stores the name and occupation pulled from form panel during the ok button click
    private String occupation // event and stores it here in the form event class so that it can be pulled by the main frame X.X
    private int ageCat
    private String empCat
    private String taxId
    private boolean usCitizen
    private String gender

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    FormEvent(Object source) {
        super(source) // NO idea what this is for
    }

    FormEvent(Object source, String name, String occupation, int ageCat,
              String empCat, String taxId, boolean usCitizen, String gender) { // This method relays info from the form panel to the mainframe
        super(source) // NO idea what this is for

        this.name = name
        this.occupation = occupation
        this.ageCat = ageCat
        this.empCat = empCat
        this.taxId = taxId
        this.usCitizen = usCitizen
        this.gender = gender
    }

    // Auto getter and setter generation is awesome!! (Required for pulling encapsulated info from one object to another
    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    String getOccupation() {
        return occupation
    }

    void setOccupation(String occupation) {
        this.occupation = occupation
    }

    int getAgeCat() {
        return ageCat
    }

    String getEmpCat() {
        return empCat
    }

    String getTaxId() {
        return taxId
    }

    boolean isUsCitizen() {
        return usCitizen
    }

    String getGender() {
        return gender
    }
}
