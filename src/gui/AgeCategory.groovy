package gui

class AgeCategory { // Utility class used for managing the values in the age category table as their own entities
    private int id;  // Each age category will have it's own id that they can be identified by
    private String text;

    AgeCategory(int id, String text) { // Constructor
        this.id = id;
        this.text = text;
    }

    String toString() { // Enables converting the AgeCategory object to a string
        return text;
    }

    int getId() { // Enables pulling the AgeCategories id value
        return id;
    }
}
