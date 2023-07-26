package com.form.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Database {
    private static final Database database = new Database();
    private List<Person> people;

    private Database() {
        people = new LinkedList<>();

    }

    public static Database getDatabase() {
        return database;
    }

    public void addPerson(Person person) {
        people.add(person);
    }

    public List<Person> getPerson() {
        return Collections.unmodifiableList((this.people));
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

    public void removePerson(int row) {
        people.remove(row);
    }
}
