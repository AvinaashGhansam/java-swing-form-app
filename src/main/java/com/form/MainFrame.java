package com.form;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;

public class MainFrame extends JFrame {
    private TextPanel textPanel;
    private ToolBar toolBar;

    public MainFrame() {
        super("Hello World");
        // Setting a border layout and add components to it.
        this.setLayout(new BorderLayout());

        toolBar = new ToolBar();

        textPanel = new TextPanel();

        this.add(toolBar, BorderLayout.NORTH);
        this.add(textPanel, BorderLayout.CENTER);

        this.setSize(600, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

}
