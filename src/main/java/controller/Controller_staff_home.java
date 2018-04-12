/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FillTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author User
 */
public class Controller_staff_home implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private JFXHamburger btnMenu;
    @FXML
    private JFXButton btnCustomerInfo;
    @FXML
    private JFXButton btnTripInfo;
    @FXML
    private JFXButton btnTicketing;
    @FXML
    private JFXButton btnCheckOut;
    @FXML
    private JFXButton btnLockScreen;
    @FXML
    private JFXButton btnLogOut;
    @FXML
    private JFXButton btnClose;
    @FXML
    private JFXButton btnMinimize;
    @FXML
    private JFXButton btnCustomerInfo_Side;
    @FXML
    private JFXButton btnTripInfo_Side;
    @FXML
    private JFXButton btnTicketing_Side;
    @FXML
    private JFXButton btnCheckOut_Side;
    @FXML
    private JFXButton btnLockScreen_Side;
    @FXML
    private JFXButton btnLogOut_Side;
    @FXML
    private JFXHamburger btnMenu_Side;
    @FXML
    private AnchorPane side_menu_bar;
    @FXML
    private AnchorPane side_menu_expand_container;
    @FXML
    private Rectangle btnCustomerInfo_Side_Background;
    @FXML
    private Rectangle btnTripInfo_Side_Background;
    @FXML
    private Rectangle btnTicketing_Side_Background;
    @FXML
    private Rectangle btnCheckOut_Side_Background;
    @FXML
    private Rectangle btnLockScreen_Side_Background;
    @FXML
    private Rectangle btnLogOut_Side_Background;
    @FXML
    private Rectangle btnCustomerInfo_Background;
    @FXML
    private Rectangle btnTripInfo_Background;
    @FXML
    private Rectangle btnTicketing_Background;
    @FXML
    private Rectangle btnCheckOut_Background;
    @FXML
    private Rectangle btnLockScreen_Background;
    @FXML
    private Rectangle btnLogOut_Background;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        //Set default rate for btnMenu and btnMenu_Side
        HamburgerBackArrowBasicTransition burger_btnMenu_transition = new HamburgerBackArrowBasicTransition(btnMenu);
        burger_btnMenu_transition.setRate(-1);
        HamburgerBackArrowBasicTransition burger_btnMenu_Side_transition = new HamburgerBackArrowBasicTransition(btnMenu_Side);
        burger_btnMenu_Side_transition.setRate(-1);

        //Add transition for btnMenu_Side, when clicked on the btnMenu_Side, expand the sidemenu
        btnMenu_Side.addEventHandler(MouseEvent.MOUSE_PRESSED, (Event event) -> {
            burger_btnMenu_Side_transition.setRate(burger_btnMenu_Side_transition.getRate() * -1);
            burger_btnMenu_Side_transition.play();

            //Expand the Sidemenu
            burger_btnMenu_transition.setRate(burger_btnMenu_transition.getRate() * -1);
            burger_btnMenu_transition.play();
            animate_sidemenu(side_menu_expand_container, 0);
        });

        //Add transition for btnMenu, when clicked on the btnMenu, collapse the sidemenu
        btnMenu.addEventHandler(MouseEvent.MOUSE_PRESSED, (Event event) -> {
            burger_btnMenu_transition.setRate(burger_btnMenu_transition.getRate() * -1);
            burger_btnMenu_transition.play();

            //Collapse the Sidemenu
            burger_btnMenu_Side_transition.setRate(burger_btnMenu_Side_transition.getRate() * -1);
            burger_btnMenu_Side_transition.play();
            animate_sidemenu(side_menu_expand_container, -305);
        });

        //Set effect on hover for buttons on Sidemenu panel
        btnCustomerInfo.setOnMouseEntered(event -> {
            animate_sidemenu_button_when_hover(btnCustomerInfo_Background);
        });

        btnCustomerInfo.setOnMouseExited(event -> {
            animate_sidemenu_button_when_lose_hover(btnCustomerInfo_Background);
        });

        btnTripInfo.setOnMouseEntered(event -> {
            animate_sidemenu_button_when_hover(btnTripInfo_Background);
        });

        btnTripInfo.setOnMouseExited(event -> {
            animate_sidemenu_button_when_lose_hover(btnTripInfo_Background);
        });

        btnTicketing.setOnMouseEntered(event -> {
            animate_sidemenu_button_when_hover(btnTicketing_Background);
        });

        btnTicketing.setOnMouseExited(event -> {
            animate_sidemenu_button_when_lose_hover(btnTicketing_Background);
        });

        btnCheckOut.setOnMouseEntered(event -> {
            animate_sidemenu_button_when_hover(btnCheckOut_Background);
        });

        btnCheckOut.setOnMouseExited(event -> {
            animate_sidemenu_button_when_lose_hover(btnCheckOut_Background);
        });

        btnLockScreen.setOnMouseEntered(event -> {
            animate_sidemenu_button_when_hover(btnLockScreen_Background);
        });

        btnLockScreen.setOnMouseExited(event -> {
            animate_sidemenu_button_when_lose_hover(btnLockScreen_Background);
        });

        btnLogOut.setOnMouseEntered(event -> {
            animate_sidemenu_button_when_hover(btnLogOut_Background);
        });

        btnLogOut.setOnMouseExited(event -> {
            animate_sidemenu_button_when_lose_hover(btnLogOut_Background);
        });

        //Set effect on hover for buttons on Sidemenu bar
        btnCustomerInfo_Side.setOnMouseEntered(event -> {
            animate_sidemenu_button_when_hover(btnCustomerInfo_Side_Background);
        });

        btnCustomerInfo_Side.setOnMouseExited(event -> {
            animate_sidemenu_button_when_lose_hover(btnCustomerInfo_Side_Background);
        });

        btnTripInfo_Side.setOnMouseEntered(event -> {
            animate_sidemenu_button_when_hover(btnTripInfo_Side_Background);
        });

        btnTripInfo_Side.setOnMouseExited(event -> {
            animate_sidemenu_button_when_lose_hover(btnTripInfo_Side_Background);
        });

        btnTicketing_Side.setOnMouseEntered(event -> {
            animate_sidemenu_button_when_hover(btnTicketing_Side_Background);
        });

        btnTicketing_Side.setOnMouseExited(event -> {
            animate_sidemenu_button_when_lose_hover(btnTicketing_Side_Background);
        });

        btnCheckOut_Side.setOnMouseEntered(event -> {
            animate_sidemenu_button_when_hover(btnCheckOut_Side_Background);
        });

        btnCheckOut_Side.setOnMouseExited(event -> {
            animate_sidemenu_button_when_lose_hover(btnCheckOut_Side_Background);
        });

        btnLockScreen_Side.setOnMouseEntered(event -> {
            animate_sidemenu_button_when_hover(btnLockScreen_Side_Background);
        });

        btnLockScreen_Side.setOnMouseExited(event -> {
            animate_sidemenu_button_when_lose_hover(btnLockScreen_Side_Background);
        });

        btnLogOut_Side.setOnMouseEntered(event -> {
            animate_sidemenu_button_when_hover(btnLogOut_Side_Background);
        });

        btnLogOut_Side.setOnMouseExited(event -> {
            animate_sidemenu_button_when_lose_hover(btnLogOut_Side_Background);
        });        
    }

    private void animate_sidemenu(final Pane pane, double layoutX) {
        Duration transition_duration = Duration.millis(500);
        Timeline time_line = new Timeline(new KeyFrame(transition_duration, new KeyValue(pane.layoutXProperty(), layoutX, Interpolator.EASE_BOTH)));
        time_line.play();
    }

    private void animate_sidemenu_button_when_hover(final Rectangle button_background) {
        Duration transition_duration = Duration.millis(300);
        FillTransition ft = new FillTransition(transition_duration, button_background, Color.web("#ffffff"), Color.web("#fcf5a1"));
        ft.play();
    }

    private void animate_sidemenu_button_when_lose_hover(final Rectangle button_background) {
        Duration transition_duration = Duration.millis(300);
        FillTransition ft = new FillTransition(transition_duration, button_background, Color.web("#fcf5a1"), Color.web("#ffffff"));
        ft.play();
    }
    
}
