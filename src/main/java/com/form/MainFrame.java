package com.form;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainFrame extends JFrame {
    private TextPanel textPanel;
    private ToolBar toolBar;
    private FormsPanel formsPanel;

    public MainFrame() {
        super("Hello World");
        // Setting a border layout and add components to it.
        this.setLayout(new BorderLayout());

        toolBar = new ToolBar();
        textPanel = new TextPanel();
        formsPanel = new FormsPanel();

        this.setJMenuBar(createMenuBar());

        toolBar.setStringListener(new StringListener() {

            @Override
            public void textEmitted(String text) {
                textPanel.appendText(text);

            }

        });

        formsPanel.setFormListener(new FormListener() {
            public void formEventOcurred(FormEvent e) {
                String name = e.getName();
                String occupation = e.getOccupation();
                int ageCategory = e.getAgeCategory();
                String employmentCat = e.getEmploymentCat();
                boolean usCitizen = e.isUsCiziten();
                String taxId = e.getTaxId();
                String gender = e.getGenderCommand();

                textPanel
                        .appendText(name + ": " + occupation + ": " + ageCategory + ": " + employmentCat
                                + ": " + usCitizen + ": " + taxId + ": " + gender + "\n");

            }
        });
        this.add(formsPanel, BorderLayout.WEST);
        this.add(toolBar, BorderLayout.NORTH);
        this.add(textPanel, BorderLayout.CENTER);

        this.setSize(600, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem exportDataItem = new JMenuItem("Export Data...");
        JMenuItem importDataItem = new JMenuItem("Import Data...");
        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(exportDataItem);
        fileMenu.add(importDataItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        JMenu windowMenu = new JMenu("Window");
        JMenu showMenu = new JMenu("Show");
        JMenuItem showFormItem = new JMenuItem("Person Form");
        showMenu.add(showFormItem);
        windowMenu.add(showMenu);

        menuBar.add(fileMenu);
        menuBar.add(windowMenu);
        return menuBar;
        // TODO: Lesson 20

    }

}
