package model

class Database { // Now I can store people in a database!
    private List<Person> people

    Database() { // Constructor
        people = new LinkedList<Person>() // Linked list are optimized for adding/removing items from middle of a list
    }

    void addPerson(Person person) { // Not sure what the point of this is
        people.add(person)
    }

    void removePerson(int index) {
        people.remove(index)
    }

    List<Person> getPeople() {
        return Collections.unmodifiableList(people) // No other components can edit this data
    }

    void saveToFile(File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file)
        ObjectOutputStream oos = new ObjectOutputStream(fos)
        Person[] persons = people.toArray(new Person[people.size()])
        oos.writeObject(persons)
        oos.close()
    }

    void loadFromFile(File file) throws IOException{
        FileInputStream fis = new FileInputStream(file)
        ObjectInputStream ois = new ObjectInputStream(fis)
        try {
            Person[] persons = (Person[])ois.readObject()
            people.clear()
            people.addAll(Arrays.asList(persons))

        } catch (ClassNotFoundException e) {
            e.printStackTrace()
        }

        ois.close()
    }
}
