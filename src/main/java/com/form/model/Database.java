package com.form.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Database {
    private static final Database database = new Database();
    private List<Person> people;

    private Connection con;

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

    public void connect() throws Exception {
        if (con != null)
            return;
        // dynamically load class
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException cnf) {
            throw new Exception("Driver not found");
        }

        String url = "jdbc:mysql://localhost:3306/swingtest";

        con = DriverManager.getConnection(url, "root", "4SU78S");
    }

    public void disconnect() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException se) {
            System.out.println(se.getStackTrace());

        }

    }

    public void save() throws SQLException {
        // SQL Statement
        String checkSql = "SELECT COUNT(*) as count FROM people WHERE id=?";
        PreparedStatement checkStmt = con.prepareStatement(checkSql);
        for (Person person : people) {
            int id = person.getId();
            // Look for the first wildcard and replace it with the id
            checkStmt.setInt(1, id);
            ResultSet checkResult = checkStmt.executeQuery();

            // move to next row
            int count = checkResult.getInt(1);
            System.out.println("Count for person with ID " + id + " is " + count);
        }
        checkStmt.close();
    }
}
