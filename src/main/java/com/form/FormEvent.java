package com.form;

import java.util.EventObject;

/**
 * This class will be used to take the name and occupation
 * from the form even and transmit to the MainFrame
 */
public class FormEvent extends EventObject {
    private String name;
    private String occupation;
    private int ageCategory;

    public FormEvent(Object source) {
        super(source);
    }

    public FormEvent(Object source, String name, String occupation, int ageCategory) {
        super(source);
        this.name = name;
        this.occupation = occupation;
        this.ageCategory = ageCategory;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the occupation
     */
    public String getOccupation() {
        return occupation;
    }

    /**
     * @param occupation the occupation to set
     */
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    /**
     * @return the ageCategory
     */
    public int getAgeCategory() {
        return ageCategory;
    }

    /**
     * @param ageCategory the ageCategory to set
     */
    public void setAgeCategory(int ageCategory) {
        this.ageCategory = ageCategory;
    }

}
