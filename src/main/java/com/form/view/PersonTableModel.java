package com.form.view;

import com.form.model.Person;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PersonTableModel  extends AbstractTableModel {
    private List<Person>db;
    private String[] colName = {"ID", "Name", "Occupation", "Age Category", "Employment", "US Citizen", "Tax Id" };

    public PersonTableModel() {}

    @Override
    public String getColumnName(int column) {
        return colName[column];
    }

    @Override
    public int getRowCount() {
        return db.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Person person = db.get(rowIndex);

        switch(columnIndex) {
            case 0 -> {
                return person.getId();
            }
            case 1 -> {
                return person.getName();
            }
            case 2 -> {
                return person.getOccupation();
            }
            case 3 -> {
                return person.getAgeCategory();
            }
            case 4 -> {
                return person.getEmpCat();
            }
            case 5 -> {
                return person.getUsCitizen();
            }
            case 6 -> {
                return person.getTaxId();
            }

        }
        return null;
    }

    public void setData(List<Person> db) {
        this.db = db;

    }
}
