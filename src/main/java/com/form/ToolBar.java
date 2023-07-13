package com.form;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.tools.Tool;

public class ToolBar extends JPanel {
    private JButton helloButton;
    private JButton goodByButton;

    public ToolBar() {
        helloButton = new JButton("Hello");
        goodByButton = new JButton("Goodbye");

        // using flow layout left-to-right
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.add(helloButton);
        this.add(goodByButton);

        // TODO: Lesson Listener
    }

}
