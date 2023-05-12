/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Tests.MainGUICategorie2;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
//import sun.tracing.MultiplexProviderFactory;

/**
 *
 * @author kagha
 */
public class FxmlLoader {

    private Pane view;

    public Pane getPage(String fileName) {
        try {

            URL fileUrl = MainGUICategorie2.class.getResource("../GUI/" + fileName + ".fxml");
            if (fileUrl == null) {
                throw new java.io.FileNotFoundException("Fxml FILE NOT FOUND");
            }
            view = new FXMLLoader().load(fileUrl);

        } catch (Exception e) {
            System.out.println("No page " + fileName + "CHECK FXMLoader");
        }
        return view;
    }

}
