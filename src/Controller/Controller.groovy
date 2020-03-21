package Controller

import model.AgeCategory
import gui.FormEvent
import model.Database
import model.EmploymentCategory
import model.Gender
import model.Person

// Interfaces between the backend model and front end gui
class Controller {
    Database db = new Database()

    List<Person> getPeople() { // Wut...?
        db.getPeople()
    }

    void removePerson(int index) {
        db.removePerson(index)
    }

    void addPerson(FormEvent event) {
        String name = event.getName()
        String occupation = event.getOccupation()
        int ageCatId = event.getAgeCat()
        String empCat = event.getEmpCat()
        String taxId = event.getTaxId()
        boolean usCitizen = event.isUsCitizen()
        String gender = event.getGender()

        AgeCategory ageCategory = null

        switch(ageCatId) {
            case 0:
                ageCategory = AgeCategory.child
                break
            case 1:
                ageCategory = AgeCategory.adult
                break
            case 2:
                ageCategory = AgeCategory.senior
                break
        }

        EmploymentCategory employmentCategory

        if (empCat == "employed") {
            employmentCategory = EmploymentCategory.employed
        }
        else if (empCat == "self-employed") {
            employmentCategory = EmploymentCategory.selfEmployed
        }
        else if (empCat == "unemployed") {
            employmentCategory = EmploymentCategory.unemployed
        }
        else  {
            employmentCategory = EmploymentCategory.other
            System.err.println(empCat)
        }

        Gender genderCat

        if (gender == "Male") {
            genderCat = Gender.Male
        }
        else {
            genderCat = Gender.Female
        }

        Person person = new Person(
                name,
                occupation,
                ageCategory,
                employmentCategory,
                taxId,
                usCitizen,
                genderCat
        )

        db.addPerson(person)
    }

    void saveToFile(File file) throws IOException{ // Wrapper method that just calls another method
        db.saveToFile(file)
    }

    void loadFromFile(File file) throws IOException{ // Wrapper method that just calls another method
        db.loadFromFile(file)
    }


}
