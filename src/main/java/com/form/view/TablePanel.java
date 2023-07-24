package com.form.view;

import com.form.model.Person;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class TablePanel extends JPanel {
    private JTable table;
    private PersonTableModel tableModel;

    public TablePanel() {
        tableModel = new PersonTableModel();
        table = new JTable(tableModel);

        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(table), BorderLayout.CENTER);

    }

    public void setData(List<Person> db) {
        tableModel.setData(db);

    }

    public void refresh() {
        tableModel.fireTableDataChanged();
    }
}
