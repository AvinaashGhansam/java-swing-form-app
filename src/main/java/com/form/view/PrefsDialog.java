package com.form.view;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class PrefsDialog extends JDialog {

    public PrefsDialog(JFrame parent) {

        super(parent, "Preferences", false);

        this.setSize(300, 400);
    }

}
