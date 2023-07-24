package com.form.view;

import com.form.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class MainFrame extends JFrame {
    private TextPanel textPanel;
    private ToolBar toolBar;
    private FormsPanel formsPanel;
    private JFileChooser fileChooser;
    private TablePanel tablePanel;
    Controller controller;
    public MainFrame() {
        super("Hello World");
        // Setting a border layout and add components to it.
        this.setLayout(new BorderLayout());

        toolBar = new ToolBar();
        textPanel = new TextPanel();
        formsPanel = new FormsPanel();
        tablePanel = new TablePanel();

        controller = new Controller();

        tablePanel.setData(controller.getPeople());

        fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new PersonFileFilter());

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

                controller.addPerson(e);
                // refresh everytime something is added to the table
                tablePanel.refresh();

            }
        });
        this.add(formsPanel, BorderLayout.WEST);
        this.add(toolBar, BorderLayout.NORTH);
//        this.add(textPanel, BorderLayout.CENTER);
        this.add(tablePanel, BorderLayout.CENTER);

        this.setMinimumSize(new Dimension(500, 400));
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

        JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Person Form");
        showFormItem.setSelected(true);

        showMenu.add(showFormItem);
        windowMenu.add(showMenu);

        menuBar.add(fileMenu);
        menuBar.add(windowMenu);

        showFormItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) e.getSource();
                // show / hide the person form
                formsPanel.setVisible(menuItem.isSelected());

            }

        });

        // Setting mnemonic
        fileMenu.setMnemonic(KeyEvent.VK_F);
        exitItem.setMnemonic(KeyEvent.VK_X);
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));

        importDataItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Pass the parent window in
               if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
                   // open the file
                   try {
                       controller.loadFromFile(fileChooser.getSelectedFile());
                       tablePanel.refresh();

                   } catch (Exception ex) {
                       JOptionPane.showMessageDialog(MainFrame.this, "Could not load data from file",
                               "Error", JOptionPane.ERROR_MESSAGE);

                   }

               }

            }
        });

        exportDataItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Pass the parent window in
                if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
                    try {
                        controller.saveToFile(fileChooser.getSelectedFile());

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(MainFrame.this, "Could not save data to file",
                                "Error", JOptionPane.ERROR_MESSAGE);

                    }

                }

            }
        });
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*String text = JOptionPane.showInputDialog(MainFrame.this,
                        "Enter Your Username",
                        "Username", JOptionPane.INFORMATION_MESSAGE);*/

                int action = JOptionPane.showConfirmDialog(MainFrame.this,
                        "Do You Really Want To Exit?",
                        "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);

                if (action == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }
        });
        return menuBar;

    }

}
