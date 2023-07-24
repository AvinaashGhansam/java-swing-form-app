package com.form.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Database {
    private static final Database database = new Database();
    private ArrayList<Person> people;

    private Database() {
        people = new ArrayList<>();

    }

    public static Database getDatabase() {
        return database;
    }

    public void addPerson(Person person) {
        people.add(person);
    }

    public List<Person> getPerson() {
        return this.people;
    }

    public void saveToFile(File file) throws IOException {

        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        // make an array from the arraylist
        Person[] persons = people.toArray(new Person[people.size()]);
        oos.writeObject(persons);

        fos.close();


    }

    public void loadFromFile(File file) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        try {
            Person[] persons = (Person[]) ois.readObject();

            people.clear();
            people.addAll(Arrays.asList(persons));

        } catch (ClassNotFoundException cnf) {
            System.out.println(cnf.getMessage());
        }

        ois.close();

    }
}
