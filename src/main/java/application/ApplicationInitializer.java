/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class ApplicationInitializer extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ApplicationConfiguration app_config = new ApplicationConfiguration();
        app_config.configure_stage(primaryStage, "/view/fxml/staff/staff_home.fxml", "Minh Nhut Corporation", 1200, 800);
        
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
