/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mikicrud.person;

import com.google.inject.Inject;
import com.mycompany.mikicrud.entities.Person;
import com.mycompany.mikicrud.registrations.RegistrationService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author lisa
 */
public class PersonsController implements Initializable {
    private Font clFont;
    @FXML
    private Label topTitle;
    @FXML
    private TableView<Person> tableView;
    @FXML
    private TableColumn firstName;
    @FXML
    private TableColumn lastName;
    @FXML
    private TableColumn id;
    @Inject
    RegistrationService rs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableView.TableViewSelectionModel<Person> selectionModel = tableView.getSelectionModel();
        
        selectionModel.selectedItemProperty().addListener(new ChangeListener<Person>() {
            @Override
            public void changed(ObservableValue<? extends Person> value, Person old, Person next) {
                String name= "" + next.getFirstName() + next.getId().toString();
                System.out.println("name = " + name);
            }
        });
        
        URL font_resource = this.getClass().getResource("/fonts/font_1_honokamin.ttf");
        try {
            clFont =  Font.loadFont(font_resource.openStream(), 60);
            topTitle.setFont(clFont);
            topTitle.setText("Person");
        } catch (IOException ex) {
            Logger.getLogger(PersonsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("PersonsController initialize");
        firstName.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        rs = new RegistrationService();
        rs.init();
        ObservableList<Person> p = FXCollections.observableArrayList(rs.all());
        System.out.println("p size " + p.size());
        tableView.setItems(p);
    }

}
