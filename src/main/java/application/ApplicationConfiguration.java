/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class ApplicationConfiguration {

    /**
     * Create a new Stage with the attributes specified in the parameters
     *
     * @param primary_stage
     * @param fxml_url
     * @param title
     * @param scene_width
     * @param scene_height
     * @throws IOException
     */
    public void configure_stage(Stage primary_stage, String fxml_url, String title, double scene_width, double scene_height) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml_url));
        Scene scene = new Scene(root, scene_width, scene_height);
        primary_stage.setTitle(title);
        primary_stage.setResizable(false);
        primary_stage.setScene(scene);
        primary_stage.sizeToScene();
    }

    public <T> T get_stage_controller(String fxml_url) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml_url));
        return loader.getController();
    }
}
