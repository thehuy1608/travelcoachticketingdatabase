/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import application.ApplicationConfiguration;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.api.loading_anchor_pane.LoadingAnchorPane;
import model.api.security.Encryption;
import model.database.dao.LoginInfoDAO;

/**
 * FXML Controller class
 *
 * @author User
 */
public class Controller_login_stage implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXButton btnLogin;
    @FXML
    private JFXPasswordField txtPassword;

    private boolean is_match_password = false;
    private LoadingAnchorPane loading_anchor_pane;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Add loading anchor pane and bring it to back
        loading_anchor_pane = new LoadingAnchorPane();
        try {
            loading_anchor_pane.configure_pane(1200, 800, "#000000", 0.8);
        } catch (FileNotFoundException ex) {

        }
        rootPane.getChildren().add(loading_anchor_pane);
        loading_anchor_pane.setOpacity(0);
        loading_anchor_pane.setCursor(Cursor.WAIT);
        loading_anchor_pane.toBack();
    }

    @FXML
    private void login_button_action(ActionEvent event) {
        loading_anchor_pane.toFront();
        animate_loading_anchor_pane(loading_anchor_pane, 1);
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();
        if (username == null || username.equals("") || password == null || password.equals("")) {
            Alert null_textfield_alert = new Alert(AlertType.ERROR);
            null_textfield_alert.setTitle("Lỗi đăng nhập");
            null_textfield_alert.setHeaderText("Tên đăng nhập và mật khẩu không được phép để trống.");
            null_textfield_alert.setContentText(null);
            null_textfield_alert.show();
        } else {
            Task<Void> check_login_task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    byte[] hash_password = Encryption.encrypt_AES(password);
                    is_match_password = LoginInfoDAO.check_login(username, hash_password);
                    return null;
                }
            };
            check_login_task.setOnSucceeded(event1 -> {
                if (is_match_password) {
                    animate_loading_anchor_pane(loading_anchor_pane, 0);
                    loading_anchor_pane.toBack();
                    Stage current_stage = (Stage) btnLogin.getScene().getWindow();
                    ApplicationConfiguration app_config = new ApplicationConfiguration();
                    try {
                        app_config.configure_stage(current_stage, "/view/fxml/staff/staff_home.fxml", "Minh Nhut Corporation", 1200, 800);
                    } catch (IOException ex) {
                        Logger.getLogger(Controller_login_stage.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    current_stage.show();
                } else {
                    Alert wrong_login_info = new Alert(AlertType.ERROR);
                    wrong_login_info.setTitle("Lỗi đăng nhập");
                    wrong_login_info.setHeaderText("Tên đăng nhập hoặc mật khẩu không đúng.");
                    wrong_login_info.setContentText(null);
                    wrong_login_info.show();
                    animate_loading_anchor_pane(loading_anchor_pane, 0);
                    loading_anchor_pane.toBack();
                }
            });
            Thread thread = new Thread(check_login_task);
            thread.setDaemon(true);
            thread.start();
        }

    }

    private void animate_loading_anchor_pane(final LoadingAnchorPane pane, double opacity) {
        Duration transition_duration = Duration.millis(300);
        FadeTransition ft = new FadeTransition(transition_duration, pane);
        ft.setFromValue(pane.getOpacity());
        ft.setToValue(opacity);
        ft.play();
    }

}
