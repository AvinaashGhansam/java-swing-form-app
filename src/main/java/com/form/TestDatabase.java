package com.form;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.form.model.AgeCategory;
import com.form.model.Database;
import com.form.model.EmploymentCategory;
import com.form.model.Gender;
import com.form.model.Person;

public class TestDatabase {
    private static Logger logger = LoggerFactory.getLogger(TestDatabase.class);

    public static void main(String[] args) {
        try {
            Database.getDatabase().connect();
        } catch (Exception e) {
            logger.info(e.getMessage());

        }

        Database.getDatabase().addPerson(new Person("Avinaash", "SWENG", AgeCategory.adult, EmploymentCategory.employed,
                "1234", true, Gender.male));
        try {
            Database.getDatabase().save();

        } catch (SQLException se) {

        }
        Database.getDatabase().disconnect();
    }

}
