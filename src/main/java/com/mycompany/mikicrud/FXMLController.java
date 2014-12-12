package com.mycompany.mikicrud;

import com.google.inject.Inject;
import com.mycompany.mikicrud.entities.Person;
import com.mycompany.mikicrud.registrations.RegistrationService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class FXMLController implements Initializable {
    
    @FXML
    private Label label;
    @Inject
    RegistrationService rs;
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
        Person p = new Person( "aiai","dawa");
        rs.save(p);
        List<Person> all = rs.all();
        System.out.println("all size = " + all.size());
        all.stream().forEach((a) -> {
            System.out.println("" + a.getFirstName() + " " + a.getId());
        });
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rs = new RegistrationService();
        rs.init();
    }    
}
