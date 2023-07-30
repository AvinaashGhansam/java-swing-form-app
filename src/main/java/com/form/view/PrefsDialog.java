package com.form.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrefsDialog extends JDialog {
    private JButton okButton;
    private JButton cancelButton;
    private JSpinner portSpinner;
    private SpinnerNumberModel spinnerModel;
    private JTextField userField;
    private JPasswordField passwordField;
    private PrefListener prefListener;

    public PrefsDialog(JFrame parent) {

        super(parent, "Preferences", false);

        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");

        spinnerModel = new SpinnerNumberModel(3306, 0, 9999, 1);
        portSpinner = new JSpinner(spinnerModel);

        this.userField = new JTextField(10);
        passwordField = new JPasswordField(10);

        passwordField.setEchoChar('*');

       layoutControl();

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer value = (Integer) portSpinner.getValue();

                String user = userField.getText();
                char[]  password = passwordField.getPassword();

                if (prefListener != null) {
                    prefListener.preferenceSet(user, new String(password), value);

                }


                setVisible(false);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        this.setSize(350, 250);
        this.setLocationRelativeTo(parent);
    }
    private void layoutControl() {
        JPanel controlPanel = new JPanel();
        JPanel buttonPanel = new JPanel();

        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createTitledBorder("Database Authentication");

        controlPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));


        controlPanel.setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        gc.gridy = 0;
        Insets rightPadding = new Insets(0,0,0,15);
        Insets noPadding = new Insets(0,0,0,0);

        //////////// 1st Row ////////////
        gc.weightx = 1;
        gc.weighty = 1;


        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST; // Anchor to right hand side
        gc.insets = rightPadding;

        gc.gridx = 0;
        controlPanel.add(new JLabel("User: "), gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST; // Anchor to right hand side
        gc.insets = noPadding;
        controlPanel.add(userField, gc);

        //////////// Next Row ////////////
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;

        gc.gridx = 0;
        gc.insets = rightPadding;
        controlPanel.add(new JLabel("Password: "), gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST; // Anchor to right hand side
        gc.insets = noPadding;
        controlPanel.add(passwordField, gc);

        //////////// Next Row ////////////
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.EAST;

        gc.gridx = 0;
        gc.insets = rightPadding;
        controlPanel.add(new JLabel("Port: "), gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST; // Anchor to right hand side
        gc.insets = noPadding;
        controlPanel.add(portSpinner, gc);

        //////////// Button Panel  ////////////
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        Dimension btnSize = cancelButton.getPreferredSize();
        okButton.setPreferredSize(btnSize);


        // Add Sub-panel to dialog
        this.setLayout(new BorderLayout());
        this.add(controlPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

    }

    public void setDefault(final String user, final String password, final int value) {
        userField.setText(user);
        passwordField.setText(password);
        portSpinner.setValue(value);
    }

    public void setPrefListener(PrefListener listener) {
        this.prefListener = listener;
    }

}
