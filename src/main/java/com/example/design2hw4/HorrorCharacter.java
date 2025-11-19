package com.example.design2hw4;
import java.time.LocalDate;
import javafx.beans.property.SimpleStringProperty;

/**
 * Horror character POJO, contains all info for making a horror character
 */

public class HorrorCharacter
{
    /**
     * Variables for names, subtypes, and dates or rebirth
     */

    private SimpleStringProperty name;
    private SimpleStringProperty subtype;
    private LocalDate dateOfRebirth;

    /**
     * Constructor for class
     * @param name
     * @param subtype
     * @param dateOfRebirth
     */

    public HorrorCharacter(String name, String subtype, LocalDate dateOfRebirth)
    {
        this.name = new SimpleStringProperty(name);
        this.subtype = new SimpleStringProperty(subtype);
        this.dateOfRebirth = dateOfRebirth;
    }

    /**
     * Getters and setters
     */

    public String getName()
    {
        return name.get();
    }

    public void setName(String name)
    {
        this.name.set(name);
    }

    public SimpleStringProperty nameProperty()
    {
        return name;
    }

    public String getSubtype()
    {
        return subtype.get();
    }

    public void setSubtype(String subtype)
    {
        this.subtype.set(subtype);
    }

    public SimpleStringProperty subtypeProperty()
    {
        return subtype;
    }

    public LocalDate getDateOfRebirth()
    {
        return dateOfRebirth;
    }

    public void setDateOfRebirth(LocalDate dateOfRebirth)
    {
        this.dateOfRebirth = dateOfRebirth;
    }

    /**
     * Method to calculate years since monster rebirthed (just in case)
     * @return
     */

    public int yearsSinceRebirth()
    {
        return LocalDate.now().getYear() - dateOfRebirth.getYear();
    }

    @Override
    public String toString()
    {
        String s = "";
        s += "Name: " + this.getName() + " Subtype: " + this.getSubtype() + " Date of Rebirth: " + this.getDateOfRebirth();
        return s;
    }

}
