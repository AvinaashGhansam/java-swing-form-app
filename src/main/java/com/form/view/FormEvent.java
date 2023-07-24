package com.form.view;

import java.util.EventObject;

/**
 * This class will be used to take the name and occupation
 * from the form even and transmit to the MainFrame
 */
public class FormEvent extends EventObject {
    private String name;
    private String occupation;
    private int ageCategory;
    private String employmentCat;
    private boolean usCiziten;
    private String taxId;
    private String genderCommand;

    public FormEvent(Object source) {
        super(source);
    }

    public FormEvent(Object source, String name, String occupation, int ageCategory, String employmentCat,
            boolean usCitizen, String taxId, String genderCommand) {
        super(source);
        this.name = name;
        this.occupation = occupation;
        this.ageCategory = ageCategory;
        this.employmentCat = employmentCat;
        this.usCiziten = usCitizen;
        this.taxId = taxId;
        this.genderCommand = genderCommand;
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

    /**
     * @return the employmentCat
     */
    public String getEmploymentCat() {
        return employmentCat;
    }

    /**
     * @param employmentCat the employmentCat to set
     */
    public void setEmploymentCat(String employmentCat) {
        this.employmentCat = employmentCat;
    }

    /**
     * @return the usCiziten
     */
    public boolean isUsCiziten() {
        return usCiziten;
    }

    /**
     * @param usCiziten the usCiziten to set
     */
    public void setUsCiziten(boolean usCiziten) {
        this.usCiziten = usCiziten;
    }

    /**
     * @return the taxId
     */
    public String getTaxId() {
        return taxId;
    }

    /**
     * @param taxId the taxId to set
     */
    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    /**
     * @return the genderCommand
     */
    public String getGenderCommand() {
        return genderCommand;
    }

    /**
     * @param genderCommand the genderCommand to set
     */
    public void setGenderCommand(String genderCommand) {
        this.genderCommand = genderCommand;
    }

}
