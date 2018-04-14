/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FillTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import model.api.validate.ValidateInput;
import model.database.dao.InvoiceDAO;
import model.database.dao.InvoicelineitemDAO;
import model.database.pojo.Invoice;

/**
 * FXML Controller class
 *
 * @author User
 */
public class Controller_staff_home implements Initializable {
    
     // <editor-fold defaultstate="collapsed" desc="FXML Components">
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
    @FXML
    private AnchorPane paneCustomerInfo;
    @FXML
    private Label lblSearchCustomerInfo;
    @FXML
    private JFXTextField txtSearchCustomerInfo;
    @FXML
    private JFXButton btnSearchCustomerInfo;
    @FXML
    private JFXTreeTableView<TableSearchCustomerInfoDataModel> tblSearchCustomerInfoResult;
    @FXML
    private TreeTableColumn<TableSearchCustomerInfoDataModel, Number> tblSearchCustomerInfoResult_index;
    @FXML
    private TreeTableColumn<TableSearchCustomerInfoDataModel, String> tblSearchCustomerInfoResult_name;
    @FXML
    private TreeTableColumn<TableSearchCustomerInfoDataModel, String> tblSearchCustomerInfoResult_phone_number;
    @FXML
    private TreeTableColumn<TableSearchCustomerInfoDataModel, String> tblSearchCustomerInfoResult_trip;
    @FXML
    private TreeTableColumn<TableSearchCustomerInfoDataModel, Number> tblSearchCustomerInfoResult_tickets;
    @FXML
    private TreeTableColumn<TableSearchCustomerInfoDataModel, String> tblSearchCustomerInfoResult_payment_status;
    // </editor-fold>
    private boolean is_active_customer_scene = false;
    private final ValidateInput validate_input = new ValidateInput();
    private final ObservableList<TableSearchCustomerInfoDataModel> table_customer_info_list = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        // <editor-fold defaultstate="collapsed" desc="Initialize Sidemenu Button and effect">
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

        //Display scene when click on corresponding button
        btnCustomerInfo.setOnMousePressed(event -> {
            if (is_active_customer_scene) {
                animate_pane_when_click_on_menu_button(paneCustomerInfo, -800);
            } else {
                animate_pane_when_click_on_menu_button(paneCustomerInfo, 0);
            }
        });

        btnCustomerInfo_Side.setOnMousePressed(event -> {
            if (is_active_customer_scene) {
                animate_pane_when_click_on_menu_button(paneCustomerInfo, -800);
            } else {
                animate_pane_when_click_on_menu_button(paneCustomerInfo, 0);
            }
        });
        // </editor-fold>

        //Add table column for table search customer info results
        
        // <editor-fold defaultstate="collapsed" desc="TreTableColumn Initialization">
        tblSearchCustomerInfoResult_index.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableSearchCustomerInfoDataModel, Number> param) -> param.getValue().getValue().index);
        tblSearchCustomerInfoResult_name.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableSearchCustomerInfoDataModel, String> param) -> param.getValue().getValue().name);
        tblSearchCustomerInfoResult_phone_number.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableSearchCustomerInfoDataModel, String> param) -> param.getValue().getValue().phone_number);
        tblSearchCustomerInfoResult_trip.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableSearchCustomerInfoDataModel, String> param) -> param.getValue().getValue().trip_line_name);
        tblSearchCustomerInfoResult_payment_status.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableSearchCustomerInfoDataModel, String> param) -> param.getValue().getValue().payment_status);
        tblSearchCustomerInfoResult_tickets.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableSearchCustomerInfoDataModel, Number> param) -> param.getValue().getValue().number_of_tickets);
        TreeItem<TableSearchCustomerInfoDataModel> table_customer_info_root = new RecursiveTreeItem<>(table_customer_info_list, (recursiveTreeObject) -> recursiveTreeObject.getChildren());
        tblSearchCustomerInfoResult.setRoot(table_customer_info_root);
        tblSearchCustomerInfoResult.setShowRoot(false);
        // </editor-fold>
    }

    //btnSearchCustomerInfo button action
    @FXML
    private void search_customer_info_button_action() {
        String search_input = txtSearchCustomerInfo.getText().trim();
        boolean is_phone_number = validate_input.check_phone(search_input);
        Task<List> get_customer_invoice_task = new Task<List>() {
            List<Invoice> invoice_list;

            @Override
            protected List call() throws Exception {
                if (is_phone_number) {
                    //TO-DO when search invoices by phone number
                    invoice_list = InvoiceDAO.get_10_latest_invoice_by_phone_number(search_input);
                } else {
                    //TO-DO when search invoices by name
                    invoice_list = InvoiceDAO.get_invoice_list_by_name(search_input);
                }
                if (!invoice_list.isEmpty()) {
                    if (!table_customer_info_list.isEmpty()) {
                        table_customer_info_list.clear();
                    }
                    for (int i = 0; i < invoice_list.size(); i++) {
                            String name = invoice_list.get(i).getCustomer().getCustomerName();
                            String phone_number = invoice_list.get(i).getCustomer().getCustomerPhoneNumber();
                            String trip_line_name = invoice_list.get(i).getTrip().getLine().getLineName();
                            int number_of_tickets = InvoicelineitemDAO.count_invoice_line_items(invoice_list.get(i));
                            String payment_status = invoice_list.get(i).getInvoiceStatus();
                            TableSearchCustomerInfoDataModel row = new TableSearchCustomerInfoDataModel(i + 1, name, phone_number, trip_line_name, number_of_tickets, payment_status);
                            table_customer_info_list.add(row);
                    }
                }
                return table_customer_info_list;
            }

        };

        Thread thread = new Thread(get_customer_invoice_task);
        thread.setDaemon(true);
        thread.start();

    }

    // <editor-fold defaultstate="collapsed" desc="Effects APIs">
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

    private void animate_pane_when_click_on_menu_button(final Pane pane, double layoutY) {
        Duration transition_duration = Duration.millis(500);
        Timeline time_line = new Timeline(new KeyFrame(transition_duration, new KeyValue(pane.layoutYProperty(), layoutY, Interpolator.EASE_BOTH)));
        time_line.play();
        is_active_customer_scene = !is_active_customer_scene;
    }
    // </editor-fold>
    
    
    //Class for access table customer info search result
    private class TableSearchCustomerInfoDataModel extends RecursiveTreeObject<TableSearchCustomerInfoDataModel> {

        IntegerProperty index;
        StringProperty name;
        StringProperty phone_number;
        StringProperty trip_line_name;
        IntegerProperty number_of_tickets;
        StringProperty payment_status;

        TableSearchCustomerInfoDataModel(int index, String name, String phone_number, String trip_line_name, int number_of_tickets, String payment_status) {
            this.index = new SimpleIntegerProperty(index);
            this.name = new SimpleStringProperty(name);
            this.phone_number = new SimpleStringProperty(phone_number);
            this.trip_line_name = new SimpleStringProperty(trip_line_name);
            this.number_of_tickets = new SimpleIntegerProperty(number_of_tickets);
            this.payment_status = new SimpleStringProperty(payment_status);
        }
    }
}
