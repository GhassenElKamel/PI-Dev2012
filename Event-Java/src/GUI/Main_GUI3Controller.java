/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author kagha
 */
public class Main_GUI3Controller implements Initializable {

    @FXML
    private BorderPane border_main;
    @FXML
    private Button Traiter_id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    private void Traiter(ActionEvent event) {
        FxmlLoader ob = new FxmlLoader();
        Pane view;
        view = ob.getPage("Afficher_Event_Client");
        border_main.setCenter(view);
    }

}
