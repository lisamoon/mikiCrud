/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mikicrud.entities;

import java.io.Serializable;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author lisa
 */
@Entity
@Table(name = "PERSON")
@NamedQueries({
    @NamedQuery(name = "Person.findall", query = "SELECT p from Person p")
})
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private LongProperty idProperty;
    private StringProperty firstNameProperty;   //(1)
    private StringProperty lastNameProperty;

    public Person() {
        this.idProperty = new SimpleLongProperty();
        this.firstNameProperty = new SimpleStringProperty();    //(2)
        this.lastNameProperty = new SimpleStringProperty();
    }

    public Person(String firstName, String lastName) {
        this();
        this.firstNameProperty.set(firstName);  //(3)
        this.lastNameProperty.set(lastName);
    }
    
    //private Long id;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return idProperty.get();
    }

    public void setId(Long id) {
        this.idProperty.set(id);
    }
    @Column(name = "FIRSTNAME")
    public String getFirstName(){
        return firstNameProperty.get();     // (4)
    }
    public void setFirstName(String firstName){
        this.firstNameProperty.set(firstName);  //(5)
    }
    @Column(name = "LASTNAME")
    public String getLastName(){
        return lastNameProperty.get();
    }
    public void setLastName(String lastName){
        this.lastNameProperty.set(lastName);
    }

    

    public StringProperty firstNmeProperty() {  
        return firstNameProperty;           // (6)
    }

     

    public StringProperty lastNameProperty() {
        return lastNameProperty;
    }

}
