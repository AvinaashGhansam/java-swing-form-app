package com.form.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
    private JComboBox<String> empComboBox;
    private JCheckBox citizenCheck;
    JLabel isCitizen;
    private JTextField taxField;
    JLabel taxLabel;

    private JRadioButton maleRadio;
    private JRadioButton femaleRadio;
    private ButtonGroup genderGroup;

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
        empComboBox = new JComboBox<>();
        citizenCheck = new JCheckBox();
        isCitizen = new JLabel("U.S. Citizen: ");
        taxField = new JTextField(10);
        taxLabel = new JLabel("Tax ID: ");
        okBtn = new JButton("OK");

        // Set up mnemomics
        okBtn.setMnemonic(KeyEvent.VK_O);

        maleRadio = new JRadioButton("male");
        femaleRadio = new JRadioButton("female");
        maleRadio.setActionCommand("male"); // for internal purposed to be retrieved
        femaleRadio.setActionCommand("female");
        genderGroup = new ButtonGroup();

        maleRadio.setSelected(true);

        // Set up gender radios
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);

        // Set up tax id
        taxLabel.setEnabled(false);
        taxField.setEnabled(false);

        citizenCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isTicked = citizenCheck.isSelected();
                taxLabel.setEnabled(isTicked);
                taxField.setEnabled(isTicked);

            }
        });

        DefaultListModel<AgeCategory> ageModel = new DefaultListModel<>();
        ageModel.addElement(new AgeCategory(0, "Under 18"));
        ageModel.addElement(new AgeCategory(1, "18 to 65"));
        ageModel.addElement(new AgeCategory(2, "65 or Over"));
        ageList.setModel(ageModel);
        ageList.setSelectedIndex(0); // set a default selection

        // make ageList prettier
        ageList.setPreferredSize(new Dimension(95, 66));
        ageList.setBorder(BorderFactory.createEtchedBorder());

        // Setting up combo box
        DefaultComboBoxModel<String> empModel = new DefaultComboBoxModel<>();
        empModel.addElement("Employed");
        empModel.addElement("Self-Employed");
        empModel.addElement("Unemployed");
        empComboBox.setModel(empModel);
        empComboBox.setSelectedIndex(0); // set a default selection
        empComboBox.setEditable(true);

        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String occupation = occupationField.getText();
                AgeCategory ageCategory = ageList.getSelectedValue();
                String employmentCat = empComboBox.getSelectedItem().toString();
                boolean isCitizen = citizenCheck.isSelected();
                String taxId = taxField.toString();
                String genderCommand = genderGroup.getSelection().getActionCommand(); // get the button model on the
                                                                                      // selection and return male or
                                                                                      // female
                System.out.println("Name: " + name + "\n" + "Occupation: " + occupation + "\n" + "Age Category: "
                        + ageCategory + "\n" + "Employment Category: " + employmentCat + "\n" + "U.S. Citizen: "
                        + isCitizen + "\n" + "Tax Id: " + taxId + "\n" + "Gender: " + genderCommand);

                FormEvent ev = new FormEvent(this, name, occupation, ageCategory.getId(), employmentCat,
                        citizenCheck.isSelected(), taxField.getText(), genderCommand);

                if (formListener != null) {
                    formListener.formEventOcurred(ev);
                }

            }

        });

        Border innerBorder = BorderFactory.createTitledBorder("Add Person");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        this.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        layoutComponents();
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

    public void layoutComponents() {
        // Set the layout manager
        this.setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        // x --> left to right
        // y --> top to bottom
        /////// First Row //////////
        gc.gridy = 0;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END; // side of the cell that the control sticks to
        gc.insets = new Insets(0, 0, 0, 5); // this will be used to put some ident on the label

        this.add(namLabel, gc); // if this is the only component then it will be center relative to panel

        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        this.add(nameField, gc);

        /////// Next Row //////////
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5); // this will be used to put some ident on the label
        gc.anchor = GridBagConstraints.LINE_END;

        this.add(occupationLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        this.add(occupationField, gc);

        /////// Next Row //////////
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        this.add(new JLabel("Age: "), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        this.add(ageList, gc);

        /////// Next Row //////////
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        this.add(new JLabel("Employment: "), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        this.add(empComboBox, gc);

        /////// Next Row //////////
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        this.add(isCitizen, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        this.add(citizenCheck, gc);

        /////// Next Row //////////
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        this.add(taxLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        this.add(taxField, gc);

        /////// Next Row //////////
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.05;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        this.add(new JLabel("Gender: "), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        this.add(maleRadio, gc);

        /////// Next Row //////////
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        // this.add(new JLabel("Female: "), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        this.add(femaleRadio, gc);

        /////// Next Row //////////
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        this.add(okBtn, gc);

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
