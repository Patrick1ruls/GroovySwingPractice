package gui

import model.Person

import javax.swing.table.AbstractTableModel

class PersonTableModel extends AbstractTableModel{
    private List<Person> db
    private String[] colNames = [
            "ID",
            "Name",
            "Occupation",
            "Age Category",
            "Employmet Category",
            "US Citizen",
            "Tax ID",
            "Gender"
    ]

    PersonTableModel() {

    }

    @Override
    String getColumnName(int column) {
        return colNames[column]
    }

    void setData(List<Person> db) {
        this.db = db // Passing a reference to the database to the table
    }

    @Override
    int getRowCount() {
        return db.size() // Can use size mehtod since db is a list of people
    }

    @Override
    int getColumnCount() {
        return colNames.length
    }

    @Override
    Object getValueAt(int row, int col) { // row/col indexes
        Person person = db.get(row)

        switch (col) {
            case 0:
                return person.getId()
            case 1:
                return person.getName()
            case 2:
                return person.getOccupation()
            case 3:
                return person.getAgeCat()
            case 4:
                return person.getEmpCat()
            case 5:
                return person.getUsCitizen()
            case 6:
                return person.getTaxId()
            case 7:
                return person.getGender()
        }

        return null
    }
}
