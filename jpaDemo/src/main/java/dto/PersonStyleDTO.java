/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author Tha-Y
 */
public class PersonStyleDTO {
    
    private String name;
    private int year;
    private String StyleName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getStyleName() {
        return StyleName;
    }

    public void setStyleName(String StyleName) {
        this.StyleName = StyleName;
    }
    

    public PersonStyleDTO(String name, int year, String StyleName) {
        this.name = name;
        this.year = year;
        this.StyleName = StyleName;
    }
    
}
