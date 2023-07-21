package com.form;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.*;

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
