package com.form.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ToolBar extends JPanel implements ActionListener {
    private JButton helloButton;
    private JButton goodByButton;

    private StringListener textListener;

    public ToolBar() {
        this.setBorder(BorderFactory.createEtchedBorder());
        helloButton = new JButton("Hello");
        goodByButton = new JButton("Goodbye");

        // Add the action listener to the button
        helloButton.addActionListener(this);
        goodByButton.addActionListener(this);

        // using flow layout left-to-right
        this.setLayout(new FlowLayout(FlowLayout.LEFT));

        this.add(helloButton);
        this.add(goodByButton);
    }

    /**
     * @return the textListener
     */
    public StringListener getStringListener() {
        return textListener;
    }

    /**
     * @param textListener the textListener to set
     */
    public void setStringListener(StringListener textListener) {
        this.textListener = textListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        System.out.println("Clicked");
        if (clicked == helloButton) {
            System.out.println("Click Hello");
            if (textListener != null) {
                textListener.textEmitted("Hello\n");

            }
        } else if (clicked == goodByButton) {
            System.out.println("Clicked GoodBye");
            if (textListener != null) {
                textListener.textEmitted("Goodbye\n");
            }
        }

    }

}
