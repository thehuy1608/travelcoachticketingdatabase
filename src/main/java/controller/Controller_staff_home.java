/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.util.StringConverter;
import javax.imageio.ImageIO;
import model.api.date.DateToLocalDate;
import model.api.date.DateToString;
import model.api.date.GetCurrentDateTimeString;
import model.api.date.LocalTimeToString;
import model.api.date.TimeToLocalTime;
import model.api.loading_anchor_pane.LoadingAnchorPane;
import model.api.validate.ValidateInput;
import model.database.dao.InvoiceDAO;
import model.database.dao.InvoicelineitemDAO;
import model.database.pojo.Invoice;
import model.database.pojo.Invoicelineitem;

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
    @FXML
    private TreeTableColumn<TableSearchCustomerInfoDataModel, String> tblSearchCustomerInfoResult_start_date;
    @FXML
    private ScrollPane editInvoiceScrollPane;
    @FXML
    private AnchorPane editInvoiceAnchorPane;
    @FXML
    private Label lblInvoiceName;
    @FXML
    private JFXTextField txtInvoiceName;
    @FXML
    private JFXTextField txtInvoicePhoneNumber;
    @FXML
    private JFXTextField txtInvoiceTrip;
    @FXML
    private Label lblInvoiceNameMessage;
    @FXML
    private Label lblInvoicePhoneNumberMessage;
    @FXML
    private Label lblInvoiceTripMessage;
    @FXML
    private Label lblInvoiceDepartureMessage;
    @FXML
    private JFXTextField txtInvoiceDeparture;
    @FXML
    private JFXTextField txtInvoiceDestination;
    @FXML
    private Label lblInvoiceDestinationMessage;
    @FXML
    private JFXTimePicker txtInvoiceStartTime;
    @FXML
    private Label lblInvoiceStartTimeMessage;
    @FXML
    private JFXTimePicker txtInvoiceEndTime;
    @FXML
    private JFXDatePicker txtInvoiceStartDate;
    @FXML
    private JFXDatePicker txtInvoiceEndDate;
    @FXML
    private Label lblInvoiceEndTimeMessage;
    @FXML
    private JFXTextField txtInvoiceNumberOfTickets;
    @FXML
    private Label lblNumberOfTicketsMessage;
    @FXML
    private JFXTextField txtInvoicePricePerSeat;
    @FXML
    private Label lblPricePerSeatMessage;
    @FXML
    private JFXTextField txtInvoiceTotalPrice;
    @FXML
    private Label lblTotalPriceMessage;
    @FXML
    private JFXTextField txtInvoicePaymentStatus;
    @FXML
    private Label lblPaymentStatusMessage;
    @FXML
    private JFXButton btnInvoiceUpdate;
    @FXML
    private JFXButton btnInvoiceClose;
    @FXML
    private JFXButton btnInvoiceCheckOut;
    @FXML
    private AnchorPane paneFinalInvoice;
    @FXML
    private Label lblFinalInvoiceName;
    @FXML
    private Label lblFinalInvoiceID;
    @FXML
    private Label lblFinalInvoiceDueDate;
    @FXML
    private Label lblFinalInvoiceLine;
    @FXML
    private Label lblFinalInvoicePhoneNumber;
    @FXML
    private Label lblFinalInvoiceDeparture;
    @FXML
    private Label lblFinalInvoiceDestination;
    @FXML
    private Label lblFinalInvoiceStartDateTime;
    @FXML
    private Label lblFinalInvoiceEndDateTime;
    @FXML
    private Label lblFinalInvoiceCoach;
    @FXML
    private Label lblFinalInvoiceTotalPrice;
    @FXML
    private JFXTreeTableView<TableFinalInvoiceModel> tblFinalInvoice;
    @FXML
    private TreeTableColumn<TableFinalInvoiceModel, Number> tblFinalInvoice_Index;
    @FXML
    private TreeTableColumn<TableFinalInvoiceModel, String> tblFinalInvoice_TicketName;
    @FXML
    private TreeTableColumn<TableFinalInvoiceModel, Number> tblFinalInvoice_Quantity;
    @FXML
    private TreeTableColumn<TableFinalInvoiceModel, Number> tblFinalInvoice_SeatNumber;
    @FXML
    private TreeTableColumn<TableFinalInvoiceModel, Number> tblFinalInvoice_Price;
    // </editor-fold>
    private boolean is_active_customer_scene = false;
    private boolean is_active_final_invoice = false;
    private boolean is_active_invoice_scene = false;
    private final ValidateInput validate_input = new ValidateInput();
    private final ObservableList<TableSearchCustomerInfoDataModel> table_customer_info_list = FXCollections.observableArrayList();
    private final ObservableList<TableFinalInvoiceModel> table_final_invoice_list = FXCollections.observableArrayList();
    private List<Invoice> invoice_list;
    private LoadingAnchorPane loading_anchor_pane = new LoadingAnchorPane();
    private int current_selected_invoice_id = 0;
    private Invoice current_invoice = null;
    private FinalInvoice final_invoice = null;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

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
            clean_current_scenes();
            animate_customer_pane_when_click_on_menu_button(paneCustomerInfo, 0);
        });

        btnCustomerInfo_Side.setOnMousePressed(event -> {
            clean_current_scenes();
            animate_customer_pane_when_click_on_menu_button(paneCustomerInfo, 0);
        });
        // </editor-fold>

        //Add table column for table search customer info results
        // <editor-fold defaultstate="collapsed" desc="Search Customer Info Table TreeTableColumn Initialization">
        tblSearchCustomerInfoResult_index.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableSearchCustomerInfoDataModel, Number> param) -> param.getValue().getValue().index);
        tblSearchCustomerInfoResult_name.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableSearchCustomerInfoDataModel, String> param) -> param.getValue().getValue().name);
        tblSearchCustomerInfoResult_phone_number.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableSearchCustomerInfoDataModel, String> param) -> param.getValue().getValue().phone_number);
        tblSearchCustomerInfoResult_trip.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableSearchCustomerInfoDataModel, String> param) -> param.getValue().getValue().trip_line_name);
        tblSearchCustomerInfoResult_payment_status.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableSearchCustomerInfoDataModel, String> param) -> param.getValue().getValue().payment_status);
        tblSearchCustomerInfoResult_tickets.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableSearchCustomerInfoDataModel, Number> param) -> param.getValue().getValue().number_of_tickets);
        tblSearchCustomerInfoResult_start_date.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableSearchCustomerInfoDataModel, String> param) -> param.getValue().getValue().start_date);
        TreeItem<TableSearchCustomerInfoDataModel> table_customer_info_root = new RecursiveTreeItem<>(table_customer_info_list, (recursiveTreeObject) -> recursiveTreeObject.getChildren());
        tblSearchCustomerInfoResult.setRoot(table_customer_info_root);
        tblSearchCustomerInfoResult.setShowRoot(false);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Final Invoice Table TreeTableColumn Initialization">
        tblFinalInvoice_Index.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableFinalInvoiceModel, Number> param) -> param.getValue().getValue().index);
        tblFinalInvoice_TicketName.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableFinalInvoiceModel, String> param) -> param.getValue().getValue().ticket_name);
        tblFinalInvoice_Quantity.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableFinalInvoiceModel, Number> param) -> param.getValue().getValue().quantity);
        tblFinalInvoice_SeatNumber.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableFinalInvoiceModel, Number> param) -> param.getValue().getValue().seat_number);
        tblFinalInvoice_Price.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableFinalInvoiceModel, Number> param) -> param.getValue().getValue().price);
        TreeItem<TableFinalInvoiceModel> table_final_invoice_root = new RecursiveTreeItem<>(table_final_invoice_list, (recursiveTreeObject) -> recursiveTreeObject.getChildren());
        tblFinalInvoice.setRoot(table_final_invoice_root);
        tblFinalInvoice.setShowRoot(false);
        // </editor-fold>

        //Set DateConverter for txtInvoiceStartDate and txtInvoiceEndDate
        txtInvoiceStartDate.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter date_formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return date_formatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, date_formatter);
                } else {
                    return null;
                }
            }
        });
        txtInvoiceEndDate.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter date_formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return date_formatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, date_formatter);
                } else {
                    return null;
                }
            }
        });

        //Smooth Scrolling for editInvoiceScrollPane
        JFXScrollPane.smoothScrolling(editInvoiceScrollPane);

        //Show invoice form with corresponding data of row clicked
        tblSearchCustomerInfoResult.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                TreeItem<TableSearchCustomerInfoDataModel> selected_item = (TreeItem<TableSearchCustomerInfoDataModel>) newValue;
                int index = selected_item.getValue().index.getValue() - 1;
                current_selected_invoice_id = selected_item.getValue().invoice_id.getValue();
                //Set data of selected row in table to invoice form
                lblInvoiceName.setText(selected_item.getValue().name.getValueSafe());
                txtInvoiceName.setText(selected_item.getValue().name.getValueSafe());
                txtInvoicePhoneNumber.setText(selected_item.getValue().phone_number.getValueSafe());
                txtInvoiceTrip.setText(invoice_list.get(index).getTrip().getTripName());
                txtInvoiceDeparture.setText(invoice_list.get(index).getTrip().getLine().getStationByDepartureStationId().getStationName() + " : " + invoice_list.get(index).getTrip().getLine().getStationByDepartureStationId().getStationAddress());
                txtInvoiceDestination.setText(invoice_list.get(index).getTrip().getLine().getStationByDestinationStationId().getStationName() + " : " + invoice_list.get(index).getTrip().getLine().getStationByDestinationStationId().getStationAddress());
                txtInvoiceStartTime.setValue(TimeToLocalTime.convert_date_to_local_time(invoice_list.get(index).getTrip().getSchedule().getStartTime()));
                txtInvoiceStartDate.setValue(DateToLocalDate.convert_date_to_local_date(invoice_list.get(index).getTrip().getSchedule().getStartDate()));
                txtInvoiceEndTime.setValue(TimeToLocalTime.convert_date_to_local_time(invoice_list.get(index).getTrip().getSchedule().getEndTime()));
                txtInvoiceEndDate.setValue(DateToLocalDate.convert_date_to_local_date(invoice_list.get(index).getTrip().getSchedule().getEndDate()));
                txtInvoiceNumberOfTickets.setText(selected_item.getValue().number_of_tickets.getValue().toString());
                txtInvoicePricePerSeat.setText("100,000");
                txtInvoiceTotalPrice.setText("500,000");
                txtInvoicePaymentStatus.setText(selected_item.getValue().payment_status.getValueSafe());

                if (txtInvoicePaymentStatus.getText().equals("Chưa thanh toán")) {
                    txtInvoicePaymentStatus.setStyle("-fx-text-fill: red;");
                } else if (txtInvoicePaymentStatus.getText().equals("Đã thanh toán")) {
                    txtInvoicePaymentStatus.setStyle("-fx-text-fill: green;");
                }

                clean_current_scenes();
                if (!is_active_invoice_scene) {
                    animate_invoice_form(editInvoiceScrollPane, 0);
                }
            }
        });
    }

    //btnSearchCustomerInfo button action
    @FXML
    private void search_customer_info_button_action() {
        String search_input = txtSearchCustomerInfo.getText();
        boolean is_phone_number = validate_input.check_phone(search_input);
        loading_anchor_pane.toFront();
        animate_loading_anchor_pane(loading_anchor_pane, 1);
        Task<List> get_customer_invoice_task = new Task<List>() {
            @Override
            protected List call() throws Exception {
                if (is_phone_number) {
                    //TO-DO when search invoices by phone number
                    invoice_list = InvoiceDAO.get_latest_invoice_by_phone_number(search_input);
                } else {
                    //TO-DO when search invoices by name
                    invoice_list = InvoiceDAO.get_invoice_list_by_name(search_input);
                }
                if (!invoice_list.isEmpty()) {
                    if (!table_customer_info_list.isEmpty()) {
                        table_customer_info_list.clear();
                    }
                    for (int i = 0; i < invoice_list.size(); i++) {
                        int invoice_id = invoice_list.get(i).getInvoiceId();
                        String name = invoice_list.get(i).getCustomer().getCustomerName();
                        String phone_number = invoice_list.get(i).getCustomer().getCustomerPhoneNumber();
                        String trip_line_name = invoice_list.get(i).getTrip().getLine().getLineName();
                        int number_of_tickets = InvoicelineitemDAO.count_invoice_line_items(invoice_list.get(i));
                        String payment_status = invoice_list.get(i).getInvoiceStatus();
                        String start_date = DateToString.convert_date_to_string(invoice_list.get(i).getTrip().getSchedule().getStartDate());
                        TableSearchCustomerInfoDataModel row = new TableSearchCustomerInfoDataModel(invoice_id, i + 1, name, phone_number, trip_line_name, number_of_tickets, payment_status, start_date);
                        table_customer_info_list.add(row);
                    }
                }

                return table_customer_info_list;
            }

        };
        get_customer_invoice_task.setOnSucceeded(event -> {
            animate_loading_anchor_pane(loading_anchor_pane, 0);
            loading_anchor_pane.toBack();
        });
        Thread thread = new Thread(get_customer_invoice_task);
        thread.setDaemon(true);
        thread.start();
    }

    @FXML
    private void invoice_close_button_action(ActionEvent event) {
        animate_invoice_form(editInvoiceScrollPane, 800);
        animate_customer_pane_when_click_on_menu_button(paneCustomerInfo, 0);
        is_active_customer_scene = true;
        is_active_invoice_scene = false;
    }

    @FXML
    private void invoice_check_out_button_action(ActionEvent event) {
        //Add a confirm dialog will shown up each time the staff clicked on the check out button to confirm data input
        Alert invoice_check_out_alert = new Alert(AlertType.CONFIRMATION);
        invoice_check_out_alert.setTitle("XÁC NHẬN THANH TOÁN");
        invoice_check_out_alert.setHeaderText(null);
        invoice_check_out_alert.setContentText("Bạn có chắc chắn muốn thanh toán?");
        Optional<ButtonType> invoice_check_out_alert_action = invoice_check_out_alert.showAndWait();
        if (invoice_check_out_alert_action.get() == ButtonType.OK) {
            loading_anchor_pane.toFront();
            animate_loading_anchor_pane(loading_anchor_pane, 1);
            Task<Void> check_out_task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    current_invoice = InvoiceDAO.get_invoice_by_id(current_selected_invoice_id);
                    List<Invoicelineitem> line_items = InvoicelineitemDAO.get_invoice_line_items_by_invoice(current_invoice);

                    //Reset table_final_invoice_list     
                    table_final_invoice_list.clear();
                    for (int i = 0; i < line_items.size(); i++) {
                        int index = i + 1;
                        String ticket_name = line_items.get(i).getTicket().getTicketName();
                        int quantity = 1;
                        int seat_number = line_items.get(i).getTicket().getTicketSeatNumber();
                        double price = line_items.get(i).getPrice();
                        TableFinalInvoiceModel row = new TableFinalInvoiceModel(index, ticket_name, quantity, seat_number, price);
                        table_final_invoice_list.add(row);
                    }

                    //Update invoice status to "Đã thanh toán"
                    current_invoice.setInvoiceStatus("Đã thanh toán");
                    InvoiceDAO.update_invoice(current_invoice);

                    //Get the total price of invoice
                    double total_price = 0;
                    for (Invoicelineitem item : line_items) {
                        total_price += item.getPrice();
                    }

                    //Add necessary data and create new FinalInvoice Object
                    String invoice_name = current_invoice.getCustomer().getCustomerName();
                    String invoice_number = current_invoice.getInvoiceId().toString();
                    String invoice_phone_number = current_invoice.getCustomer().getCustomerPhoneNumber();
                    String invoice_date = GetCurrentDateTimeString.get_current_datetime_string();
                    String trip_name = current_invoice.getTrip().getTripName();
                    String departure = current_invoice.getTrip().getLine().getStationByDepartureStationId().getStationName() + " : " + current_invoice.getTrip().getLine().getStationByDepartureStationId().getStationAddress();
                    String destination = current_invoice.getTrip().getLine().getStationByDestinationStationId().getStationName() + " : " + current_invoice.getTrip().getLine().getStationByDestinationStationId().getStationAddress();
                    String start_date_time = LocalTimeToString.time_to_string(TimeToLocalTime.convert_date_to_local_time(current_invoice.getTrip().getSchedule().getStartTime())) + " - " + DateToString.convert_date_to_string(current_invoice.getTrip().getSchedule().getStartDate());
                    String end_date_time = LocalTimeToString.time_to_string(TimeToLocalTime.convert_date_to_local_time(current_invoice.getTrip().getSchedule().getEndTime())) + " - " + DateToString.convert_date_to_string(current_invoice.getTrip().getSchedule().getEndDate());
                    String coach_number_plate = "59XA-000001";
                    String total_price_string = Double.toString(total_price);

                    final_invoice = new FinalInvoice(invoice_name, invoice_number, invoice_phone_number, invoice_date, trip_name, departure, destination, start_date_time, end_date_time, coach_number_plate, total_price_string);
                    return null;
                }
            };
            check_out_task.setOnSucceeded(event1 -> {
                //Set data to final invoice form
                if (final_invoice != null) {
                    lblFinalInvoiceName.setText(final_invoice.invoice_name);
                    lblFinalInvoiceID.setText(final_invoice.invoice_number);
                    lblFinalInvoicePhoneNumber.setText(final_invoice.invoice_phone_number);
                    lblFinalInvoiceDueDate.setText(final_invoice.invoice_date);
                    lblFinalInvoiceLine.setText(final_invoice.trip_name);
                    lblFinalInvoiceDeparture.setText(final_invoice.departure);
                    lblFinalInvoiceDestination.setText(final_invoice.destination);
                    lblFinalInvoiceStartDateTime.setText(final_invoice.start_date_time);
                    lblFinalInvoiceEndDateTime.setText(final_invoice.end_date_time);
                    lblFinalInvoiceCoach.setText(final_invoice.coach_number_plate);
                    lblFinalInvoiceTotalPrice.setText(final_invoice.total_price + " đồng.");
                }

                animate_loading_anchor_pane(loading_anchor_pane, 0);
                loading_anchor_pane.toBack();
                clean_current_scenes();
                animate_final_invoice_pane_when_click_on_menu_button(paneFinalInvoice, 16);

                //Display a dialog to confirm export Invoice to a printable Image
                Alert export_image_alert = new Alert(AlertType.INFORMATION);
                export_image_alert.setTitle("XUẤT HÓA ĐƠN");
                export_image_alert.setHeaderText("XUẤT HÓA ĐƠN");
                export_image_alert.setContentText("Vui lòng đóng dấu hóa đơn và cung cấp thông tin cần thiết về chuyến đi cho khách hàng. \nXin cảm ơn!");
                export_image_alert.initStyle(StageStyle.UNDECORATED);
                Optional<ButtonType> export_image_alert_action = export_image_alert.showAndWait();
                if (export_image_alert_action.get() == ButtonType.OK) {
                    try {
                        WritableImage invoice_printable_image = paneFinalInvoice.snapshot(new SnapshotParameters(), null);
                        String path = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "TravelBusTicketing" + File.separator + "Invoice.png";
                        File invoice_printable_image_file = new File(path);
                        Files.deleteIfExists(invoice_printable_image_file.toPath());
                        if (invoice_printable_image_file.createNewFile()) {
                            ImageIO.write(SwingFXUtils.fromFXImage(invoice_printable_image, null), "png", invoice_printable_image_file);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(Controller_staff_home.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            Thread thread = new Thread(check_out_task);
            thread.setDaemon(true);
            thread.start();
        }
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

    private void animate_customer_pane_when_click_on_menu_button(final Pane pane, double layoutY) {
        Duration transition_duration = Duration.millis(500);
        Timeline time_line = new Timeline(new KeyFrame(transition_duration, new KeyValue(pane.layoutYProperty(), layoutY, Interpolator.EASE_BOTH)));
        time_line.play();
        is_active_customer_scene = !is_active_customer_scene;
    }

    private void animate_final_invoice_pane_when_click_on_menu_button(final Pane pane, double layoutY) {
        Duration transition_duration = Duration.millis(500);
        Timeline time_line = new Timeline(new KeyFrame(transition_duration, new KeyValue(pane.layoutYProperty(), layoutY, Interpolator.EASE_BOTH)));
        time_line.play();
        is_active_final_invoice = !is_active_final_invoice;
    }

    private void animate_invoice_form(final ScrollPane pane, double layoutY) {
        Duration transition_duration = Duration.millis(500);
        Timeline time_line = new Timeline(new KeyFrame(transition_duration, new KeyValue(pane.layoutYProperty(), layoutY, Interpolator.EASE_BOTH)));
        time_line.play();
        is_active_invoice_scene = !is_active_invoice_scene;
    }

    private void animate_loading_anchor_pane(final LoadingAnchorPane pane, double opacity) {
        Duration transition_duration = Duration.millis(300);
        FadeTransition ft = new FadeTransition(transition_duration, pane);
        ft.setFromValue(pane.getOpacity());
        ft.setToValue(opacity);
        ft.play();
    }
    // </editor-fold>    

    //Clean current scenes before animate a new scene to application viewport
    private void clean_current_scenes() {
        animate_customer_pane_when_click_on_menu_button(paneCustomerInfo, -800);
        animate_final_invoice_pane_when_click_on_menu_button(paneFinalInvoice, 800);
        animate_invoice_form(editInvoiceScrollPane, 800);
        is_active_customer_scene = false;
        is_active_final_invoice = false;
        is_active_invoice_scene = false;
    }

    //Class for access table customer info search result
    private class TableSearchCustomerInfoDataModel extends RecursiveTreeObject<TableSearchCustomerInfoDataModel> {

        IntegerProperty invoice_id;
        IntegerProperty index;
        StringProperty name;
        StringProperty phone_number;
        StringProperty trip_line_name;
        IntegerProperty number_of_tickets;
        StringProperty payment_status;
        StringProperty start_date;

        TableSearchCustomerInfoDataModel(int invoice_id, int index, String name, String phone_number, String trip_line_name, int number_of_tickets, String payment_status, String start_date) {
            this.invoice_id = new SimpleIntegerProperty(invoice_id);
            this.index = new SimpleIntegerProperty(index);
            this.name = new SimpleStringProperty(name);
            this.phone_number = new SimpleStringProperty(phone_number);
            this.trip_line_name = new SimpleStringProperty(trip_line_name);
            this.number_of_tickets = new SimpleIntegerProperty(number_of_tickets);
            this.payment_status = new SimpleStringProperty(payment_status);
            this.start_date = new SimpleStringProperty(start_date);
        }
    }

    //Class for access table final invoice
    private class TableFinalInvoiceModel extends RecursiveTreeObject<TableFinalInvoiceModel> {

        IntegerProperty index;
        StringProperty ticket_name;
        IntegerProperty quantity;
        IntegerProperty seat_number;
        DoubleProperty price;

        TableFinalInvoiceModel(int index, String ticket_name, int quantity, int seat_number, double price) {
            this.index = new SimpleIntegerProperty(index);
            this.ticket_name = new SimpleStringProperty(ticket_name);
            this.quantity = new SimpleIntegerProperty(quantity);
            this.seat_number = new SimpleIntegerProperty(seat_number);
            this.price = new SimpleDoubleProperty(price);
        }
    }

    //Class for display final invoice
    private class FinalInvoice {

        String invoice_name;
        String invoice_number;
        String invoice_phone_number;
        String invoice_date;
        String trip_name;
        String departure;
        String destination;
        String start_date_time;
        String end_date_time;
        String coach_number_plate;
        String total_price;

        public FinalInvoice(String invoice_name, String invoice_number, String invoice_phone_number, String invoice_date, String trip_name, String departure, String destination, String start_date_time, String end_date_time, String coach_number_plate, String total_price) {
            this.invoice_name = invoice_name;
            this.invoice_number = invoice_number;
            this.invoice_phone_number = invoice_phone_number;
            this.invoice_date = invoice_date;
            this.trip_name = trip_name;
            this.departure = departure;
            this.destination = destination;
            this.start_date_time = start_date_time;
            this.end_date_time = end_date_time;
            this.coach_number_plate = coach_number_plate;
            this.total_price = total_price;
        }

    }

    /**
     * Make a directory in User Document directory to store printable image
     * file.
     */
    private static void make_temp_dir() {
        String path = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "TravelBusTicketing";
        File documents_dir = new File(path);
        if (documents_dir.exists()) {
            System.out.println("Directory has already existed");
        } else if (documents_dir.mkdirs()) {
            System.out.println("Directory was created successfully.");
        } else {
            System.out.println("Error");
        }
    }
}
