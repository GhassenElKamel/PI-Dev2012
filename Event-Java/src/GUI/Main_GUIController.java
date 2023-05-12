/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.Afficher_CategorieController.getIt;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.Service_Categorie_Event;

/**
 * FXML Controller class
 *
 * @author kagha
 */
public class Main_GUIController implements Initializable {

    @FXML
    private Button Ajouter_id;
    @FXML
    private Button Traiter_id;
    @FXML
    private BorderPane border_main;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Ajouter(ActionEvent event) {
        FxmlLoader ob = new FxmlLoader();
        Pane view;
        view = ob.getPage("Version_3_Ajouter_Event");
        Stage stage = (Stage) Ajouter_id.getScene().getWindow();
        border_main.setCenter(view);
        //stage.setHeight(520);
        //stage.setWidth(800);
    }

    @FXML
    public void Traiter(ActionEvent event) {
        FxmlLoader ob = new FxmlLoader();
        Pane view;
        view = ob.getPage("Afficher_Event");
        border_main.setCenter(view);
    }

    
    
}
