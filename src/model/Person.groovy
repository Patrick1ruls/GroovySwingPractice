package model

class Person implements Serializable { // Data store for person data object
    private static int count = 0 // Used to create a "unique" id for each generated person (1 then 2 then 3 etc)

    private static final long serialVersionUID = - 7155063816080506018L

    private int id            // UUID
    private String name
    private String occupation
    private AgeCategory ageCat
    private EmploymentCategory empCat
    private String taxId
    private boolean usCitizen
    private Gender gender      // Type is replaced with enum Gender

    // Person constructor
    Person( String name,
            String occupation,
            AgeCategory ageCat,
            EmploymentCategory empCat,
            String taxId,
            boolean usCitizen,
            Gender gender) {
        this.name = name
        this.occupation = occupation
        this.ageCat = ageCat
        this.empCat = empCat
        this.taxId = taxId
        this.usCitizen = usCitizen
        this.gender = gender

        this.id = count
        count++
    }

    int getId() {
        return id
    }

    void setId(int id) {
        this.id = id
    }

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

    AgeCategory getAgeCat() {
        return ageCat
    }

    void setAgeCat(AgeCategory ageCat) {
        this.ageCat = ageCat
    }

    EmploymentCategory getEmpCat() {
        return empCat
    }

    void setEmpCat(EmploymentCategory empCat) {
        this.empCat = empCat
    }

    String getTaxId() {
        return taxId
    }

    void setTaxId(String taxId) {
        this.taxId = taxId
    }

    boolean getUsCitizen() {
        return usCitizen
    }

    void setUsCitizen(boolean usCitizen) {
        this.usCitizen = usCitizen
    }

    Gender getGender() {
        return gender
    }

    void setGender(Gender gender) {
        this.gender = gender
    }
}
