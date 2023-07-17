package com.form;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class FormsPanel extends JPanel {
    private JLabel namLabel;
    private JLabel occupationLabel;
    private JTextField nameField;
    private JTextField occupationField;
    private JButton okBtn;
    private FormListener formListener;
    private JList<AgeCategory> ageList;

    public FormsPanel() {
        // Using the layout manager to determine the size
        Dimension dim = getPreferredSize();
        dim.width = 250;
        this.setPreferredSize(dim);

        namLabel = new JLabel("Name: ");
        occupationLabel = new JLabel("Occupation: ");
        nameField = new JTextField(10);
        occupationField = new JTextField(10);
        ageList = new JList<>();

        DefaultListModel<AgeCategory> ageModel = new DefaultListModel<>();
        ageModel.addElement(new AgeCategory(0, "Under 18"));
        ageModel.addElement(new AgeCategory(1, "18 to 65"));
        ageModel.addElement(new AgeCategory(2, "65 or Over"));
        ageList.setModel(ageModel);
        ageList.setSelectedIndex(0); // set a default selection

        // make ageList prettier
        ageList.setPreferredSize(new Dimension(95, 66));
        ageList.setBorder(BorderFactory.createEtchedBorder());

        okBtn = new JButton("OK");
        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String occupation = occupationField.getText();
                AgeCategory ageCategory = ageList.getSelectedValue();
                System.out.println(ageCategory);

                FormEvent ev = new FormEvent(this, name, occupation, ageCategory.getId());

                if (formListener != null) {
                    formListener.formEventOcurred(ev);
                }

            }

        });

        Border innerBorder = BorderFactory.createTitledBorder("Add Person");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        this.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        // Set the layout manager
        this.setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        // x --> left to right
        // y --> top to bottom
        /////// First Row //////////
        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END; // side of the cell that the control sticks to
        gc.insets = new Insets(0, 0, 0, 5); // this will be used to put some ident on the label

        this.add(namLabel, gc); // if this is the only component then it will be center relative to panel

        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        this.add(nameField, gc);

        /////// Second Row //////////
        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridy = 1;
        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5); // this will be used to put some ident on the label
        gc.anchor = GridBagConstraints.LINE_END;

        this.add(occupationLabel, gc);

        gc.gridy = 1;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        this.add(occupationField, gc);

        /////// Third Row //////////
        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridy = 2;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        this.add(ageList, gc);

        /////// Fourth Row //////////
        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridy = 3;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        this.add(okBtn, gc);

    }

    /**
     * @return the formListener
     */
    public FormListener getFormListener() {
        return formListener;
    }

    /**
     * @param formListener the formListener to set
     */
    public void setFormListener(FormListener formListener) {
        this.formListener = formListener;
    }

}

class AgeCategory {
    private String text;
    private int id;

    public AgeCategory(int id, String text) {
        this.text = text;
        this.id = id;

    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return text;
    }

}
