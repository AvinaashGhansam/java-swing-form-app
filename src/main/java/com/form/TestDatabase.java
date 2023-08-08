package com.form;

import java.sql.SQLException;

import com.form.model.AgeCategory;
import com.form.model.Database;
import com.form.model.EmploymentCategory;
import com.form.model.Gender;
import com.form.model.Person;

public class TestDatabase {
    public static void main(String[] args) {
        System.out.println("Running database test");
        try {
            Database.getDatabase().connect();
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        Database.getDatabase().addPerson(new Person("Ghansam", "Engr", AgeCategory.adult,
                EmploymentCategory.employed, "1234", true, Gender.male ));
        try {
            System.out.println("saving db");
            Database.getDatabase().save();

        } catch (SQLException se) {

        }
        Database.getDatabase().disconnect();
    }
    // TODO: Lesson 41 Loading Data from db

}
