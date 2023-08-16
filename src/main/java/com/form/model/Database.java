package com.form.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.*;
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

    public void load() throws SQLException {
        people.clear();
        String sql = "SELECT id, name, age, employment_status, tax_id, us_citizen, gender, occupation FROM people ORDER BY name";
        Statement selectStatement = con.createStatement();
        ResultSet resultSet = selectStatement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String age = resultSet.getString("age");
            String employmentStatus = resultSet.getString("employment_status");
            String tax_id = resultSet.getString("tax_id");
            boolean usCitizen = resultSet.getBoolean("us_citizen");
            String gender = resultSet.getString("gender");
            String occupation = resultSet.getString("occupation");

            Person person = new Person(id,
                    name,
                    occupation,
                    AgeCategory.valueOf(age),
                    EmploymentCategory.valueOf(employmentStatus),
                    tax_id,
                    usCitizen,
                    Gender.valueOf(gender));
            people.add(person);
            System.out.println(person);
        }
        resultSet.close();
        selectStatement.close();

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
            System.out.println(Arrays.toString(se.getStackTrace()));

        }

    }

    public void save() throws SQLException {
        System.out.println("saving...");
        // SQL Statement
        String checkSql = "SELECT COUNT(*) as count FROM people WHERE id=?";
        PreparedStatement checkStmt = con.prepareStatement(checkSql);

        String insertSql = "INSERT INTO people (id, name, age, employment_status, tax_id, us_citizen, gender, occupation)"
                +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement insertStatement = con.prepareStatement(insertSql);

        String updateSql = "UPDATE people SET name=?, age=?, employment_status=?, tax_id=?, us_citizen=?, gender=?, occupation=?"
                +
                " WHERE id=?";

        PreparedStatement updateStatement = con.prepareStatement(updateSql);

        for (Person person : people) {
            System.out.println("in loop");
            int id = person.getId();
            String name = person.getName();
            String occupation = person.getOccupation();
            AgeCategory ageCategory = person.getAgeCategory();
            EmploymentCategory employmentCategory = person.getEmpCat();
            String taxId = person.getTaxId();
            boolean isCitizen = person.getUsCitizen();
            Gender gender = person.getGender();

            // Look for the first wildcard and replace it with the id
            checkStmt.setInt(1, id);
            ResultSet checkResult = checkStmt.executeQuery();
            checkResult.next();

            // move to next row
            int count = checkResult.getInt(1);
            System.out.println("count: " + count);

            if (count == 0) {
                System.out.println("adding column...");
                System.out.println("Inserting person with ID " + id);

                int col = 1;
                insertStatement.setInt(col++, id);
                insertStatement.setString(col++, name);
                insertStatement.setString(col++, ageCategory.name());
                insertStatement.setString(col++, employmentCategory.name());
                insertStatement.setString(col++, taxId);
                insertStatement.setBoolean(col++, isCitizen);
                insertStatement.setString(col++, gender.name());
                insertStatement.setString(col++, occupation);

                insertStatement.executeUpdate();

            } else {
                System.out.println("Updating person with ID " + id);
                int col = 1;

                updateStatement.setString(col++, name);
                updateStatement.setString(col++, ageCategory.name());
                updateStatement.setString(col++, employmentCategory.name());
                updateStatement.setString(col++, taxId);
                updateStatement.setBoolean(col++, isCitizen);
                updateStatement.setString(col++, gender.name());
                updateStatement.setString(col++, occupation);
                updateStatement.setInt(col++, id);

                updateStatement.executeUpdate();

            }
        }

        checkStmt.close();
        updateStatement.close();
        insertStatement.close();
    }
}
