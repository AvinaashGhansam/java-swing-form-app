package com.form.view;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrefsDialog extends JDialog {
    private JButton okButton;
    private JButton cancelButton;
    private JSpinner portSpinner;
    private SpinnerNumberModel spinnerModel;

    public PrefsDialog(JFrame parent) {

        super(parent, "Preferences", false);

        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");

        spinnerModel = new SpinnerNumberModel(3306, 0, 9999, 1);
        portSpinner = new JSpinner(spinnerModel);

        this.setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;

        gc.gridx = 0;
        this.add(new JLabel("Port"), gc);

        gc.gridx++;
        this.add(portSpinner, gc);

        //////////// Next Row ////////////
        gc.gridy++;
        gc.gridx = 0;
        this.add(okButton, gc);

        gc.gridx++;
        this.add(cancelButton, gc);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer value = (Integer) portSpinner.getValue();
                System.out.println(value);
                setVisible(false);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        this.setSize(300, 400);
        this.setLocationRelativeTo(parent);
    }

}
