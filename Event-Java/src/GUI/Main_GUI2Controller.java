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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kagha
 */
public class Main_GUI2Controller implements Initializable {

    @FXML
    private Button Ajouter_id;
    @FXML
    private Button Traiter_id;
    @FXML
    private BorderPane border_main;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Ajouter(ActionEvent event) {
        FxmlLoader ob = new FxmlLoader();
        Pane view;
        view = ob.getPage("Ajouter_Categorie");
        Stage stage = (Stage) Ajouter_id.getScene().getWindow();
        border_main.setCenter(view);
        //stage.setHeight(520);
        //stage.setWidth(800);
    }

    @FXML
    private void Traiter(ActionEvent event) {
        FxmlLoader ob = new FxmlLoader();
        Pane view;
        view = ob.getPage("Afficher_Categorie");
        border_main.setCenter(view);
    }
    
    
}
