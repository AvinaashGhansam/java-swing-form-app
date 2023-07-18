package com.form;

import java.awt.BorderLayout;

import javax.swing.JFrame;

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

                textPanel.appendText(name + ": " + occupation + ": " + ageCategory + "\n");

            }
        });
        this.add(formsPanel, BorderLayout.WEST);
        this.add(toolBar, BorderLayout.NORTH);
        this.add(textPanel, BorderLayout.CENTER);

        this.setSize(600, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

}
