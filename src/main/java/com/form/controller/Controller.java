package com.form.controller;

import com.form.model.*;
import com.form.view.FormEvent;

import java.io.File;
import java.util.List;

public class Controller {
    public List<Person> getPeople() {
        return Database.getDatabase().getPerson();
    }

    /**
     * See the formPanel event listener
     * reference {@link com.form.view.MainFrame}
     * 
     * @param e
     */
    public void addPerson(FormEvent e) {
        String name = e.getName();
        String occupation = e.getOccupation();
        int ageCategoryId = e.getAgeCategory();
        String employmentCat = e.getEmploymentCat();
        boolean usCitizen = e.isUsCiziten();
        String taxId = e.getTaxId();
        String gender = e.getGenderCommand();

        AgeCategory category = null;

        switch (ageCategoryId) {
            case 0 -> category = AgeCategory.child;
            case 1 -> category = AgeCategory.adult;
            case 2 -> category = AgeCategory.senior;
        }

        EmploymentCategory employmentCategory = switch (employmentCat) {
            case "Employed" -> EmploymentCategory.employed;
            case "Unemployed" -> EmploymentCategory.unemployed;
            case "Self-Employed" -> EmploymentCategory.selfEmployed;
            default -> EmploymentCategory.other;
        };

        Gender genderCategory = null;
        if (gender.equals("male")) {
            genderCategory = Gender.male;
        } else {
            genderCategory = Gender.female;
        }

        Database.getDatabase().addPerson(new Person(name, occupation, category,
                employmentCategory, taxId, usCitizen, genderCategory));

    }

    public void saveToFile(File file) throws Exception {
        Database.getDatabase().saveToFile(file);
    }

    public void loadFromFile(File file) throws Exception {
        Database.getDatabase().loadFromFile(file);
    }

    public void removePerson(int row) {
        Database.getDatabase().removePerson(row);
    }

    public void getAuthentication(String user, String password, int port) {
        System.out.println("user[controller] " + user + " password " + password + " port " + port);

    }

}
