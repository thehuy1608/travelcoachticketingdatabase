/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import application.ApplicationConfiguration;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXPasswordField;
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
import java.io.Serializable;
import java.net.URL;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableRow;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.util.StringConverter;
import javax.imageio.ImageIO;
import model.api.date.DateToLocalDate;
import model.api.date.DateToString;
import model.api.date.GetCurrentDate;
import model.api.date.GetCurrentDateTimeString;
import model.api.date.LocalTimeToString;
import model.api.date.TimeToLocalTime;
import model.api.json.model.Metadata;
import model.api.json.read.ReadMetaData;
import model.api.loading_anchor_pane.LoadingAnchorPane;
import model.api.security.Encryption;
import model.api.validate.ValidateInput;
import model.database.dao.CoachDriverTripDAO;
import model.database.dao.CustomerDAO;
import model.database.dao.InvoiceDAO;
import model.database.dao.InvoicelineitemDAO;
import static model.database.dao.InvoicelineitemDAO.get_invoice_line_items_by_invoice;
import model.database.dao.LoginInfoDAO;
import model.database.dao.SeatDAO;
import model.database.dao.TicketDAO;
import model.database.dao.TripDAO;
import model.database.pojo.CoachDriverTrip;
import model.database.pojo.Customer;
import model.database.pojo.Invoice;
import model.database.pojo.Invoicelineitem;
import model.database.pojo.Seat;
import model.database.pojo.Ticket;
import model.database.pojo.Trip;

/**
 * FXML Controller class
 *
 * @author User
 */
public class Controller_staff_home implements Initializable, Serializable {

    // <editor-fold defaultstate="collapsed" desc="FXML Components">
    @FXML
    private AnchorPane rootPane;
    @FXML
    private JFXHamburger btnMenu;
    @FXML
    private JFXButton btnCustomerInfo;
    @FXML
    private JFXButton btnLockScreen;
    @FXML
    private JFXButton btnLogOut;
    @FXML
    private JFXButton btnCustomerInfo_Side;
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
    private Rectangle btnLockScreen_Side_Background;
    @FXML
    private Rectangle btnLogOut_Side_Background;
    @FXML
    private Rectangle btnCustomerInfo_Background;
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
    private Label lblSearchCustomerInfo_result_count;
    @FXML
    private Label lblSearchCustomerInfo_filter;
    @FXML
    private JFXTextField txtSearchCustomerInfo_filter;
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
    private TreeTableColumn<TableSearchCustomerInfoDataModel, String> tblSearchCustomerInfoResult_added_date;
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
    private ImageView iconInvoiceName;
    @FXML
    private ImageView iconInvoicePhoneNumber;
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
    private JFXTreeTableView<TableFinalInvoiceModel> tblEditInvoice;
    @FXML
    private TreeTableColumn<TableFinalInvoiceModel, Number> tblEditInvoice_Index;
    @FXML
    private TreeTableColumn<TableFinalInvoiceModel, String> tblEditInvoice_TicketName;
    @FXML
    private TreeTableColumn<TableFinalInvoiceModel, Number> tblEditInvoice_Quantity;
    @FXML
    private TreeTableColumn<TableFinalInvoiceModel, Number> tblEditInvoice_SeatNumber;
    @FXML
    private TreeTableColumn<TableFinalInvoiceModel, Number> tblEditInvoice_Price;
    @FXML
    private JFXTextField txtInvoiceTotalPrice;
    @FXML
    private Label lblTotalPriceMessage;
    @FXML
    private JFXTextField txtInvoicePaymentStatus;
    @FXML
    private JFXTextField txtInvoiceNumberPlate;
    @FXML
    private Label lblPaymentStatusMessage;
    @FXML
    private JFXButton btnInvoiceUpdate;
    @FXML
    private JFXButton btnInvoiceClose;
    @FXML
    private JFXButton btnInvoiceCheckOut;
    @FXML
    private JFXButton btnInvoiceDelete;
    @FXML
    private JFXButton btnInvoiceReset;
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
    @FXML
    private JFXButton btnExportInvoice;
    @FXML
    private AnchorPane paneSeatPlane;
    @FXML
    private JFXCheckBox cbSeat1;
    @FXML
    private JFXCheckBox cbSeat2;
    @FXML
    private JFXCheckBox cbSeat3;
    @FXML
    private JFXCheckBox cbSeat4;
    @FXML
    private JFXCheckBox cbSeat5;
    @FXML
    private JFXCheckBox cbSeat6;
    @FXML
    private JFXCheckBox cbSeat7;
    @FXML
    private JFXCheckBox cbSeat8;
    @FXML
    private JFXCheckBox cbSeat9;
    @FXML
    private JFXCheckBox cbSeat10;
    @FXML
    private JFXCheckBox cbSeat11;
    @FXML
    private JFXCheckBox cbSeat12;
    @FXML
    private JFXCheckBox cbSeat13;
    @FXML
    private JFXCheckBox cbSeat14;
    @FXML
    private JFXCheckBox cbSeat15;
    @FXML
    private JFXCheckBox cbSeat16;
    @FXML
    private JFXCheckBox cbSeat17;
    @FXML
    private JFXCheckBox cbSeat18;
    @FXML
    private JFXCheckBox cbSeat19;
    @FXML
    private JFXCheckBox cbSeat20;
    @FXML
    private JFXCheckBox cbSeat21;
    @FXML
    private JFXCheckBox cbSeat22;
    @FXML
    private JFXCheckBox cbSeat23;
    @FXML
    private JFXCheckBox cbSeat24;
    @FXML
    private JFXCheckBox cbSeat25;
    @FXML
    private JFXCheckBox cbSeat26;
    @FXML
    private JFXCheckBox cbSeat27;
    @FXML
    private JFXCheckBox cbSeat28;
    @FXML
    private JFXCheckBox cbSeat29;
    @FXML
    private JFXCheckBox cbSeat30;
    @FXML
    private JFXCheckBox cbSeat31;
    @FXML
    private JFXCheckBox cbSeat32;
    @FXML
    private JFXCheckBox cbSeat33;
    @FXML
    private JFXCheckBox cbSeat34;
    @FXML
    private JFXCheckBox cbSeat35;
    @FXML
    private JFXCheckBox cbSeat36;
    @FXML
    private JFXCheckBox cbSeat37;
    @FXML
    private JFXCheckBox cbSeat38;
    @FXML
    private JFXCheckBox cbSeat39;
    @FXML
    private JFXCheckBox cbSeat40;
    @FXML
    private JFXCheckBox cbSeat45;
    @FXML
    private JFXCheckBox cbSeat44;
    @FXML
    private JFXCheckBox cbSeat42;
    @FXML
    private JFXCheckBox cbSeat41;
    @FXML
    private JFXCheckBox cbSeat43;
    @FXML
    private AnchorPane paneLockScreen;
    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXButton btnUnlockScreen;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private Hyperlink link_login_with_another_account;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Other variables">
    private boolean is_active_customer_scene = false;
    private boolean is_active_final_invoice = false;
    private boolean is_active_invoice_scene = false;
    private final ValidateInput validate_input = new ValidateInput();
    private final ObservableList<TableSearchCustomerInfoDataModel> table_customer_info_list = FXCollections.observableArrayList();
    private ObservableList<TableFinalInvoiceModel> table_final_invoice_list = FXCollections.observableArrayList();
    private ObservableList<TableFinalInvoiceModel> original_table_final_invoice_list;
    private ObservableList<TableFinalInvoiceModel> added_table_final_invoice_list = FXCollections.observableArrayList();
    private List<Invoice> invoice_list;
    private LoadingAnchorPane loading_anchor_pane = new LoadingAnchorPane();
    private int current_selected_invoice_id = 0;
    private int current_selected_invoice_index = 0;
    private Invoice current_invoice = null;
    private FinalInvoice final_invoice = null;
    private double total_price = 0;
    private List<Byte> selected_seat_list;
    private List<Byte> current_customer_selected_seat;
    private List<Byte> added_customer_selected_seat;
    private double current_ticket_price;
    private boolean is_modified_invoice = false;
    private boolean is_modified_invoice_name = false;
    private boolean is_modified_invoice_phone_number = false;
    private boolean is_modified_invoice_seat_number = false;
    private String duplicate_seat_string;
    private int update_seat_result;
    private boolean is_animated_search_customer_info_components = false;
    private Metadata data;
    private boolean is_match_password;
    // </editor-fold>

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        //Read data from JSON file
        data = ReadMetaData.read_JSON_user_data_file();

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

        //Bring lock screen pane to back
        paneLockScreen.toBack();

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
        tblSearchCustomerInfoResult_added_date.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableSearchCustomerInfoDataModel, String> param) -> param.getValue().getValue().added_date);
        TreeItem<TableSearchCustomerInfoDataModel> table_customer_info_root = new RecursiveTreeItem<>(table_customer_info_list, (recursiveTreeObject) -> recursiveTreeObject.getChildren());
        tblSearchCustomerInfoResult.setRoot(table_customer_info_root);
        tblSearchCustomerInfoResult.setShowRoot(false);

        //Add filter by CustomerName, or PhoneNumber, or AddedDate to tblSearchCustomerInfoResult table
        txtSearchCustomerInfo_filter.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            tblSearchCustomerInfoResult.setPredicate((TreeItem<TableSearchCustomerInfoDataModel> t) -> {
                boolean flag = t.getValue().name.getValue().contains(newValue) || t.getValue().phone_number.getValue().contains(newValue) || t.getValue().added_date.getValue().contains(newValue);
                return flag;
            });
        });
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

        // <editor-fold defaultstate="collapsed" desc="Edit Invoice Table TreeTableColumn Initialization">
        tblEditInvoice_Index.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableFinalInvoiceModel, Number> param) -> param.getValue().getValue().index);
        tblEditInvoice_TicketName.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableFinalInvoiceModel, String> param) -> param.getValue().getValue().ticket_name);
        tblEditInvoice_Quantity.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableFinalInvoiceModel, Number> param) -> param.getValue().getValue().quantity);
        tblEditInvoice_SeatNumber.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableFinalInvoiceModel, Number> param) -> param.getValue().getValue().seat_number);
        tblEditInvoice_Price.setCellValueFactory((TreeTableColumn.CellDataFeatures<TableFinalInvoiceModel, Number> param) -> param.getValue().getValue().price);
        TreeItem<TableFinalInvoiceModel> table_edit_invoice_root = new RecursiveTreeItem<>(table_final_invoice_list, (recursiveTreeObject) -> recursiveTreeObject.getChildren());
        tblEditInvoice.setRoot(table_edit_invoice_root);
        tblEditInvoice.setShowRoot(false);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="txtInvoiceStartDate and txtInvoiceEndDate DateConverter">
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
        // </editor-fold>

        //Smooth Scrolling for editInvoiceScrollPane
        JFXScrollPane.smoothScrolling(editInvoiceScrollPane);

        //Set non-editable for invoice start time and end time
        txtInvoiceStartTime.setEditable(false);
        txtInvoiceEndTime.setEditable(false);

        //Show invoice form with corresponding data of row clicked
        // <editor-fold defaultstate="collapsed" desc="Add row clicking onclick action to tblSearchCustomerInfoResult">
        tblSearchCustomerInfoResult.setRowFactory(factory -> {
            TreeTableRow<TableSearchCustomerInfoDataModel> ttr = new TreeTableRow<>();
            ttr.setOnMouseClicked(event -> {
                if (!ttr.isEmpty()) {
                    TableSearchCustomerInfoDataModel selected_item = ttr.getItem();
                    current_selected_invoice_index = selected_item.index.getValue();
                    current_selected_invoice_id = selected_item.invoice_id.getValue();
                    Task<Void> edit_invoice_task = new Task<Void>() {
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
                            System.out.println(table_final_invoice_list);
                            total_price = 0;
                            line_items.forEach((item) -> {
                                total_price += item.getPrice();
                            });
                            current_customer_selected_seat = get_all_selected_seat_in_invoice_by_invoice(current_invoice);

                            //Load the selected seat list to the List define at the start of method
                            CoachDriverTrip coach_driver_trip = CoachDriverTripDAO.get_coach_driver_trip_by_trip(current_invoice.getTrip());
                            int current_coach_id = coach_driver_trip.getCoach().getCoachId();
                            selected_seat_list = SeatDAO.get_all_selected_seat_of_trip_by_coach_id(current_coach_id);
                            return null;
                        }
                    };

                    edit_invoice_task.setOnSucceeded(event2 -> {
                        //Set data of selected row in table to invoice form
                        lblInvoiceName.setText(selected_item.name.getValueSafe());
                        txtInvoiceName.setText(selected_item.name.getValueSafe());
                        txtInvoicePhoneNumber.setText(selected_item.phone_number.getValueSafe());
                        txtInvoiceTrip.setText(invoice_list.get(current_selected_invoice_index - 1).getTrip().getTripName());
                        txtInvoiceDeparture.setText(invoice_list.get(current_selected_invoice_index - 1).getTrip().getLine().getStationByDepartureStationId().getStationName() + " : " + invoice_list.get(current_selected_invoice_index - 1).getTrip().getLine().getStationByDepartureStationId().getStationAddress());
                        txtInvoiceDestination.setText(invoice_list.get(current_selected_invoice_index - 1).getTrip().getLine().getStationByDestinationStationId().getStationName() + " : " + invoice_list.get(current_selected_invoice_index - 1).getTrip().getLine().getStationByDestinationStationId().getStationAddress());
                        txtInvoiceStartTime.setValue(TimeToLocalTime.convert_date_to_local_time(invoice_list.get(current_selected_invoice_index - 1).getTrip().getSchedule().getStartTime()));
                        txtInvoiceStartDate.setValue(DateToLocalDate.convert_date_to_local_date(invoice_list.get(current_selected_invoice_index - 1).getTrip().getSchedule().getStartDate()));
                        txtInvoiceEndTime.setValue(TimeToLocalTime.convert_date_to_local_time(invoice_list.get(current_selected_invoice_index - 1).getTrip().getSchedule().getEndTime()));
                        txtInvoiceEndDate.setValue(DateToLocalDate.convert_date_to_local_date(invoice_list.get(current_selected_invoice_index - 1).getTrip().getSchedule().getEndDate()));
                        txtInvoiceNumberOfTickets.setText(selected_item.number_of_tickets.getValue().toString());
                        txtInvoiceTotalPrice.setText(Double.toString(total_price));
                        txtInvoicePaymentStatus.setText(selected_item.payment_status.getValueSafe());
                        CoachDriverTrip coach_driver_trip = CoachDriverTripDAO.get_coach_driver_trip_by_trip(current_invoice.getTrip());
                        txtInvoiceNumberPlate.setText(coach_driver_trip.getCoach().getNumberPlate());

                        //Set invoice payment status textfield color text fill based on the payment status
                        //Set invoice update and check out button state (enabled or disabled) and content text based on invoice status
                        switch (txtInvoicePaymentStatus.getText()) {
                            case "Chưa thanh toán":
                                txtInvoicePaymentStatus.setStyle("-fx-text-fill: orange;");
                                btnInvoiceCheckOut.setText("Thanh toán");
                                btnInvoiceUpdate.setDisable(false);
                                btnInvoiceDelete.setDisable(false);
                                btnInvoiceCheckOut.setDisable(false);
                                btnInvoiceReset.setDisable(false);
                                paneSeatPlane.setDisable(false);
                                break;
                            case "Đã thanh toán":
                                txtInvoicePaymentStatus.setStyle("-fx-text-fill: green;");
                                btnInvoiceCheckOut.setText("Xem hóa đơn");
                                paneSeatPlane.setDisable(true);
                                btnInvoiceUpdate.setDisable(true);
                                btnInvoiceDelete.setDisable(true);
                                btnInvoiceCheckOut.setDisable(false);
                                btnInvoiceReset.setDisable(false);
                                break;
                            case "Đã hủy":
                                txtInvoicePaymentStatus.setStyle("-fx-text-fill: red;");
                                btnInvoiceUpdate.setDisable(true);
                                paneSeatPlane.setDisable(true);
                                btnInvoiceDelete.setDisable(true);
                                btnInvoiceReset.setDisable(true);
                                break;
                            default:
                                break;
                        }

                        //check all the selected seat in seat checkboxes
                        int current_coach_id = coach_driver_trip.getCoach().getCoachId();
                        load_selected_seats_to_pane_by_coach_id(current_coach_id, selected_seat_list);
                        disable_selected_seats_of_other_customers(current_coach_id, selected_seat_list, current_customer_selected_seat);

                        //Save the table_final_invoice_list to another list to track change
                        original_table_final_invoice_list = FXCollections.observableArrayList(table_final_invoice_list);
                        clean_current_scenes();
                        if (!is_active_invoice_scene) {
                            animate_invoice_form(editInvoiceScrollPane, 0);
                        }
                    });
                    Thread thread = new Thread(edit_invoice_task);
                    thread.setDaemon(true);
                    thread.start();
                }
            });
            return ttr;
        });
        // </editor-fold>

        //Adding listener to each checkbox to track on change event
        // <editor-fold defaultstate="collapsed" desc="Add a new row to table customer info list to display it on edit invoice phase, but don't update in database yet until CheckOut button is clicked">
        //Get list of node in paneTicketPlane
        ObservableList<Node> cbSeat_node_list = paneSeatPlane.getChildren();
        //Convert cbSeat_node_list to JFXCheckBox list
        List<JFXCheckBox> cbSeat_checkbox_list = new ArrayList<>();
        cbSeat_node_list.forEach(temp_seat -> {
            JFXCheckBox temp = (JFXCheckBox) temp_seat;
            cbSeat_checkbox_list.add(temp);
        });
        //Add change listener to each checkbox
        cbSeat_checkbox_list.forEach(cbSeat -> {
            cbSeat.setOnMouseClicked(event -> {
                if (cbSeat.isSelected()) {
                    //TO-DO when the current check box is checked.
                    Task<Void> seat_check_box_checked_task = new Task<Void>() {
                        @Override
                        protected Void call() throws Exception {
                            update_table_search_customer_list_when_check_box_checked(cbSeat, current_invoice);
                            is_modified_invoice = true;
                            return null;
                        }
                    };
                    seat_check_box_checked_task.setOnSucceeded(event1 -> {
                        tblEditInvoice.setVisible(false);
                        tblEditInvoice.setVisible(true);
                        txtInvoiceNumberOfTickets.setText(Integer.toString(Integer.parseInt(txtInvoiceNumberOfTickets.getText()) + 1));
                        txtInvoiceTotalPrice.setText(Double.toString(Double.parseDouble(txtInvoiceTotalPrice.getText()) + current_ticket_price));
                    });
                    Thread thread = new Thread(seat_check_box_checked_task);
                    thread.setDaemon(true);
                    thread.start();
                } else {
                    //TO-DO when the current check box is unchecked
                    Task<Void> seat_check_box_unchecked_task = new Task<Void>() {
                        @Override
                        protected Void call() throws Exception {
                            update_table_search_customer_list_when_check_box_unchecked(cbSeat);
                            is_modified_invoice = true;
                            return null;
                        }
                    };
                    seat_check_box_unchecked_task.setOnSucceeded(event2 -> {
                        tblEditInvoice.setVisible(false);
                        tblEditInvoice.setVisible(true);
                        txtInvoiceNumberOfTickets.setText(Integer.toString(Integer.parseInt(txtInvoiceNumberOfTickets.getText()) - 1));
                        txtInvoiceTotalPrice.setText(Double.toString(Double.parseDouble(txtInvoiceTotalPrice.getText()) - current_ticket_price));
                    });
                    Thread thread = new Thread(seat_check_box_unchecked_task);
                    thread.setDaemon(true);
                    thread.start();
                }
            });
        });
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="txtInvoiceName and txtInvoicePhoneNumber Change Listener">
        //Adding listener to track change on txtInvoiceName and txtInvoiceNumber
        txtInvoiceName.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            String original_invoice_name = table_customer_info_list.get(current_selected_invoice_index - 1).name.getValue();
            is_modified_invoice_name = !newValue.equals(original_invoice_name);
            if (validate_input.check_name(newValue)) {
                btnInvoiceCheckOut.setDisable(false);
                btnInvoiceUpdate.setDisable(false);
                btnInvoiceReset.setDisable(false);
                lblInvoiceNameMessage.setText("");
                iconInvoiceName.setVisible(false);
            } else {
                btnInvoiceCheckOut.setDisable(true);
                btnInvoiceUpdate.setDisable(true);
                btnInvoiceReset.setDisable(true);
                lblInvoiceNameMessage.setText("Tên không được chứa số và kí tự đặc biệt.");
                iconInvoiceName.setVisible(true);
            }
        });

        txtInvoicePhoneNumber.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            String original_invoice_phone_number = table_customer_info_list.get(current_selected_invoice_index - 1).phone_number.getValue();
            is_modified_invoice_phone_number = !newValue.equals(original_invoice_phone_number);
            if (validate_input.check_phone(newValue)) {
                btnInvoiceCheckOut.setDisable(false);
                btnInvoiceUpdate.setDisable(false);
                lblInvoicePhoneNumberMessage.setText("");
                iconInvoicePhoneNumber.setVisible(false);
            } else {
                btnInvoiceCheckOut.setDisable(true);
                btnInvoiceUpdate.setDisable(true);
                lblInvoicePhoneNumberMessage.setText("Số điện thoại không hợp lệ.");
                iconInvoicePhoneNumber.setVisible(true);
            }
        });
        // </editor-fold>

        clean_current_scenes();
        animate_customer_pane_when_click_on_menu_button(paneCustomerInfo, 0);
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
                        int index = i + 1;
                        String name = invoice_list.get(i).getCustomer().getCustomerName();
                        String phone_number = invoice_list.get(i).getCustomer().getCustomerPhoneNumber();
                        String trip_line_name = invoice_list.get(i).getTrip().getLine().getLineName();
                        int number_of_tickets = InvoicelineitemDAO.count_invoice_line_items(invoice_list.get(i));
                        String payment_status = invoice_list.get(i).getInvoiceStatus();
                        String start_date = DateToString.convert_date_to_string(invoice_list.get(i).getTrip().getSchedule().getStartDate());
                        String added_date = DateToString.convert_date_to_string(invoice_list.get(i).getInvoiceAddedDate());
                        TableSearchCustomerInfoDataModel row = new TableSearchCustomerInfoDataModel(invoice_id, index, name, phone_number, trip_line_name, number_of_tickets, payment_status, start_date, added_date);
                        table_customer_info_list.add(row);
                    }
                }

                return table_customer_info_list;
            }

        };
        get_customer_invoice_task.setOnSucceeded(event -> {
            lblSearchCustomerInfo_result_count.setText("Tìm thấy " + table_customer_info_list.size() + " kết quả.");
            animate_loading_anchor_pane(loading_anchor_pane, 0);
            loading_anchor_pane.toBack();
            if (!is_animated_search_customer_info_components) {
                animate_customer_pane_component();
            }
        });
        Thread thread = new Thread(get_customer_invoice_task);
        thread.setDaemon(true);
        thread.start();

    }

    @FXML
    private void invoice_close_button_action(ActionEvent event) {
        //Compare table_final_invoice_list and original_table_final_invoice_list.
        is_modified_invoice_seat_number = !table_final_invoice_list.equals(original_table_final_invoice_list);
        String modified_invoice_message = "Bạn đã thay đổi những mục sau trong hóa đơn: \n";
        //If 1 of invoice name, invoice phone number or table final invoice list is modified, then set the value of is_modified_invoice to true
        //Add information to modified invoice message based on what field is changed
        if (is_modified_invoice_name) {
            is_modified_invoice = true;
            modified_invoice_message += "* Tên khách hàng.\n";
        }
        if (is_modified_invoice_phone_number) {
            is_modified_invoice = true;
            modified_invoice_message += "* Số điện thoại khách hàng.\n";
        }
        if (is_modified_invoice_seat_number) {
            is_modified_invoice = true;
            modified_invoice_message += "* Số ghế đặt.\n";
        }
        if (is_modified_invoice_name == false && is_modified_invoice_phone_number == false && is_modified_invoice_seat_number == false) {
            is_modified_invoice = false;
        }
        modified_invoice_message += "Bạn có chắc chắn muốn thoát?";
        if (is_modified_invoice) {
            //TO-DO when the current invoice is modified
            Alert modified_invoice_alert = new Alert(AlertType.CONFIRMATION);
            modified_invoice_alert.getDialogPane().getScene().getWindow().setOnCloseRequest(event_close -> event_close.consume());
            modified_invoice_alert.setTitle("CẬP NHẬT HÓA ĐƠN");
            modified_invoice_alert.setHeaderText(modified_invoice_message);
            modified_invoice_alert.setContentText("Nhấn nút Cancel và sau đó nhấn nút Cập Nhật để lưu thay đổi trong hóa đơn.\nNhấn nút OK nếu như bạn muốn thoát mà không lưu lại thay đổi.");
            Optional<ButtonType> modified_invoice_alert_action = modified_invoice_alert.showAndWait();
            if (modified_invoice_alert_action.get() == ButtonType.OK) {
                animate_invoice_form(editInvoiceScrollPane, 800);
                animate_customer_pane_when_click_on_menu_button(paneCustomerInfo, 0);
                is_active_customer_scene = true;
                is_active_invoice_scene = false;
                is_modified_invoice = false;
                is_modified_invoice_name = false;
                is_modified_invoice_phone_number = false;
                is_modified_invoice_seat_number = false;
                tblSearchCustomerInfoResult.getSelectionModel().clearSelection();
            }
        } else {
            //TO-DO when nothing happens
            animate_invoice_form(editInvoiceScrollPane, 800);
            animate_customer_pane_when_click_on_menu_button(paneCustomerInfo, 0);
            is_active_customer_scene = true;
            is_active_invoice_scene = false;
            is_modified_invoice = false;
            is_modified_invoice_name = false;
            is_modified_invoice_phone_number = false;
            is_modified_invoice_seat_number = false;
            tblSearchCustomerInfoResult.getSelectionModel().clearSelection();
            tblSearchCustomerInfoResult.setVisible(false);
            tblSearchCustomerInfoResult.setVisible(true);
        }
    }

    @FXML
    private void invoice_check_out_button_action(ActionEvent event) {
        System.out.println(btnInvoiceCheckOut.getText());
        //Different behavior based on invoice payment status
        if (btnInvoiceCheckOut.getText().equals("Xem hóa đơn")) {
            btnExportInvoice.setVisible(true);
            Task<Void> show_invoice_task = new Task<Void>() {
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

                    //Get the total price of invoice
                    total_price = 0;
                    line_items.forEach((item) -> {
                        total_price += item.getPrice();
                    });
                    CoachDriverTrip coach_driver_trip = CoachDriverTripDAO.get_coach_driver_trip_by_trip(current_invoice.getTrip());
                    //Add necessary data and create new FinalInvoice Object
                    String invoice_name = current_invoice.getCustomer().getCustomerName();
                    String invoice_number = current_invoice.getInvoiceId().toString();
                    String invoice_phone_number = current_invoice.getCustomer().getCustomerPhoneNumber();
                    String invoice_date = DateToString.convert_date_time_to_string(current_invoice.getInvoiceDueDate());
                    String trip_name = current_invoice.getTrip().getTripName();
                    String departure = current_invoice.getTrip().getLine().getStationByDepartureStationId().getStationName() + " : " + current_invoice.getTrip().getLine().getStationByDepartureStationId().getStationAddress();
                    String destination = current_invoice.getTrip().getLine().getStationByDestinationStationId().getStationName() + " : " + current_invoice.getTrip().getLine().getStationByDestinationStationId().getStationAddress();
                    String start_date_time = LocalTimeToString.time_to_string(TimeToLocalTime.convert_date_to_local_time(current_invoice.getTrip().getSchedule().getStartTime())) + " - " + DateToString.convert_date_to_string(current_invoice.getTrip().getSchedule().getStartDate());
                    String end_date_time = LocalTimeToString.time_to_string(TimeToLocalTime.convert_date_to_local_time(current_invoice.getTrip().getSchedule().getEndTime())) + " - " + DateToString.convert_date_to_string(current_invoice.getTrip().getSchedule().getEndDate());
                    String coach_number_plate = coach_driver_trip.getCoach().getNumberPlate();
                    String total_price_string = Double.toString(total_price);

                    final_invoice = new FinalInvoice(invoice_name, invoice_number, invoice_phone_number, invoice_date, trip_name, departure, destination, start_date_time, end_date_time, coach_number_plate, total_price_string);
                    return null;
                }
            };
            show_invoice_task.setOnSucceeded(event1 -> {
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
                animate_final_invoice_pane_when_click_on_menu_button(paneFinalInvoice, 0);
            });
            Thread thread = new Thread(show_invoice_task);
            thread.setDaemon(true);
            thread.start();
        } else {
            if (is_modified_invoice) {
                Alert update_invoice_alert = new Alert(AlertType.WARNING);
                update_invoice_alert.setTitle("THÔNG BÁO");
                update_invoice_alert.setHeaderText("Chưa cập nhật hóa đơn.");
                update_invoice_alert.setContentText("Bạn đã thay đổi thông tin hóa đơn. Vui lòng nhấn nút Cập Nhật trước khi thanh toán.");
                update_invoice_alert.showAndWait();
            } else {
                //Update customer info list in customer searching table result
                table_customer_info_list.get(current_selected_invoice_index - 1).payment_status = new SimpleStringProperty("Đang xử lý");
                //Add a confirm dialog will shown up each time the staff clicked on the check out button to confirm data input
                Alert invoice_check_out_alert = new Alert(AlertType.CONFIRMATION);
                invoice_check_out_alert.setTitle("XÁC NHẬN THANH TOÁN");
                invoice_check_out_alert.setHeaderText(null);
                invoice_check_out_alert.setContentText("Bạn có chắc chắn muốn thanh toán?");
                Optional<ButtonType> invoice_check_out_alert_action = invoice_check_out_alert.showAndWait();
                if (invoice_check_out_alert_action.get() == ButtonType.OK) {
                    btnExportInvoice.setVisible(true);
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

                            //Run this line of code first to synchronize the invoiceduedate value display in application and the time writed to datebase
                            String invoice_date = GetCurrentDateTimeString.get_current_datetime_string();
                            //Update invoice status to "Đã thanh toán"
                            try {
                                current_invoice.setInvoiceStatus("Đã thanh toán");
                                //Update invoice due date
                                Date invoice_due_date = new SimpleDateFormat("HH:mm dd-MM-yyyy").parse(invoice_date);
                                current_invoice.setInvoiceDueDate(invoice_due_date);
                                InvoiceDAO.update_invoice(current_invoice);
                                //Update customer info list in customer searching table result and refresh the table
                                table_customer_info_list.get(current_selected_invoice_index - 1).payment_status = new SimpleStringProperty("Đã thanh toán");
                            } catch (ParseException e) {

                            }

                            //Get the total price of invoice
                            total_price = 0;
                            line_items.forEach((item) -> {
                                total_price += item.getPrice();
                            });
                            CoachDriverTrip coach_driver_trip = CoachDriverTripDAO.get_coach_driver_trip_by_trip(current_invoice.getTrip());

                            //Add necessary data and create new FinalInvoice Object                        
                            String invoice_name = current_invoice.getCustomer().getCustomerName();
                            String invoice_number = current_invoice.getInvoiceId().toString();
                            String invoice_phone_number = current_invoice.getCustomer().getCustomerPhoneNumber();
                            String trip_name = current_invoice.getTrip().getTripName();
                            String departure = current_invoice.getTrip().getLine().getStationByDepartureStationId().getStationName() + " : " + current_invoice.getTrip().getLine().getStationByDepartureStationId().getStationAddress();
                            String destination = current_invoice.getTrip().getLine().getStationByDestinationStationId().getStationName() + " : " + current_invoice.getTrip().getLine().getStationByDestinationStationId().getStationAddress();
                            String start_date_time = LocalTimeToString.time_to_string(TimeToLocalTime.convert_date_to_local_time(current_invoice.getTrip().getSchedule().getStartTime())) + " - " + DateToString.convert_date_to_string(current_invoice.getTrip().getSchedule().getStartDate());
                            String end_date_time = LocalTimeToString.time_to_string(TimeToLocalTime.convert_date_to_local_time(current_invoice.getTrip().getSchedule().getEndTime())) + " - " + DateToString.convert_date_to_string(current_invoice.getTrip().getSchedule().getEndDate());
                            String coach_number_plate = coach_driver_trip.getCoach().getNumberPlate();
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

                            //Refresh table for showing newest record
                            tblSearchCustomerInfoResult.getColumns().get(5).setVisible(false);
                            tblSearchCustomerInfoResult.getColumns().get(5).setVisible(true);
                            tblSearchCustomerInfoResult.getSelectionModel().clearSelection();
                        }

                        animate_loading_anchor_pane(loading_anchor_pane, 0);
                        loading_anchor_pane.toBack();
                        clean_current_scenes();
                        animate_final_invoice_pane_when_click_on_menu_button(paneFinalInvoice, 0);

                        //Display a dialog to confirm export Invoice to a printable Image
                        Alert check_out_success_alert = new Alert(AlertType.INFORMATION);
                        check_out_success_alert.setHeaderText("THANH TOÁN THÀNH CÔNG");
                        check_out_success_alert.setContentText("Vui lòng xuất, đóng dấu hóa đơn và cung cấp thông tin cần thiết về chuyến đi cho khách hàng. \nXin cảm ơn!");
                        check_out_success_alert.initStyle(StageStyle.UNDECORATED);
                        Optional<ButtonType> export_image_alert_action = check_out_success_alert.showAndWait();
                    });

                    check_out_task.setOnFailed(event2 -> {
                        //Update customer info list in customer searching table result
                        table_customer_info_list.get(current_selected_invoice_index - 1).payment_status = new SimpleStringProperty("Chưa thanh toán");
                    });
                    Thread thread = new Thread(check_out_task);
                    thread.setDaemon(true);
                    thread.start();
                }
            }
        }

    }

    @FXML
    private void export_invoice_button_action(ActionEvent event) {
        boolean is_success_export_invoice = false;
        btnExportInvoice.setVisible(false);
        tblFinalInvoice.getSelectionModel().clearSelection();
        try {
            WritableImage invoice_printable_image = paneFinalInvoice.snapshot(new SnapshotParameters(), null);
            String path = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "TravelBusTicketing" + File.separator + "Invoice.png";
            File invoice_printable_image_file = new File(path);
            Files.deleteIfExists(invoice_printable_image_file.toPath());
            if (invoice_printable_image_file.createNewFile()) {
                ImageIO.write(SwingFXUtils.fromFXImage(invoice_printable_image, null), "png", invoice_printable_image_file);
            }
            is_success_export_invoice = true;
        } catch (IOException ex) {
            Logger.getLogger(Controller_staff_home.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (is_success_export_invoice) {
            Alert export_invoice_success_alert = new Alert(AlertType.INFORMATION);
            export_invoice_success_alert.setHeaderText(null);
            export_invoice_success_alert.setContentText("Vui lòng đóng dấu hóa đơn, giao hóa đơn và cung cấp thông tin cần thiết về chuyến đi cho khách hàng. \nXin cảm ơn!");
            Optional<ButtonType> export_image_alert_action = export_invoice_success_alert.showAndWait();
            if (export_image_alert_action.get() == ButtonType.OK) {
                btnExportInvoice.setVisible(true);
            }
        }

    }

    @FXML
    private void update_invoice_button_action(ActionEvent event) {
        //Compare table_final_invoice_list and original_table_final_invoice_list.
        is_modified_invoice_seat_number = !table_final_invoice_list.equals(original_table_final_invoice_list);
        String modified_invoice_message = "Bạn đã thay đổi những mục sau trong hóa đơn: \n";
        //If 1 of invoice name, invoice phone number or table final invoice list is modified, then set the value of is_modified_invoice to true
        //Add information to modified invoice message based on what field is changed
        if (is_modified_invoice_name) {
            is_modified_invoice = true;
            modified_invoice_message += "* Tên khách hàng.\n";
        }
        if (is_modified_invoice_phone_number) {
            is_modified_invoice = true;
            modified_invoice_message += "* Số điện thoại khách hàng.\n";
        }
        if (is_modified_invoice_seat_number) {
            is_modified_invoice = true;
            modified_invoice_message += "* Số ghế đặt.\n";
        }
        if (is_modified_invoice_name == false && is_modified_invoice_phone_number == false && is_modified_invoice_seat_number == false) {
            is_modified_invoice = false;
        }
        modified_invoice_message += "Bạn có chắc chắn muốn cập nhật hóa đơn?";
        if (is_modified_invoice) {
            //TO-DO when the current invoice is modified 
            Alert modified_invoice_alert = new Alert(AlertType.CONFIRMATION);
            modified_invoice_alert.getDialogPane().getScene().getWindow().setOnCloseRequest(event_close -> event_close.consume());
            modified_invoice_alert.setTitle("CẬP NHẬT HÓA ĐƠN");
            modified_invoice_alert.setHeaderText(modified_invoice_message);
            modified_invoice_alert.setContentText("Nhấn nút OK để cập nhật hóa đơn. Nhấn nút Cancel để đưa hóa đơn về trạng thái ban đầu.");
            Optional<ButtonType> modified_invoice_alert_action = modified_invoice_alert.showAndWait();
            if (modified_invoice_alert_action.get() == ButtonType.OK) {
                //TO-DO when user confirmed to update Invoice
                String invoice_name = txtInvoiceName.getText();
                String invoice_phone_number = txtInvoicePhoneNumber.getText();
                loading_anchor_pane.toFront();
                animate_loading_anchor_pane(loading_anchor_pane, 1);
                Task<Void> update_invoice_task = new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        duplicate_seat_string = "";
                        //If the invoice name or phone number is change, update it
                        if (is_modified_invoice_name || is_modified_invoice_phone_number) {
                            Customer customer = InvoiceDAO.get_invoice_by_id(current_selected_invoice_id).getCustomer();
                            if (is_modified_invoice_name) {
                                customer.setCustomerName(invoice_name);
                                table_customer_info_list.get(current_selected_invoice_index - 1).name = new SimpleStringProperty(invoice_name);
                            }
                            if (is_modified_invoice_phone_number) {
                                customer.setCustomerPhoneNumber(invoice_phone_number);
                                table_customer_info_list.get(current_selected_invoice_index - 1).phone_number = new SimpleStringProperty(invoice_phone_number);
                            }
                            customer.setModifiedDate(GetCurrentDate.get_current_date());
                            CustomerDAO.update_customer(customer);
                        }
                        //If invoice line items is changes, update it.
                        if (is_modified_invoice_seat_number) {
                            Invoice invoice = InvoiceDAO.get_invoice_by_id(current_selected_invoice_id);
                            Trip trip = InvoiceDAO.get_invoice_by_id(current_selected_invoice_id).getTrip();
                            CoachDriverTrip cdt = CoachDriverTripDAO.get_coach_driver_trip_by_trip(trip);
                            int coach_id = cdt.getCoach().getCoachId();
                            added_table_final_invoice_list.clear();
                            table_final_invoice_list.forEach(item -> {
                                if (!original_table_final_invoice_list.contains(item)) {
                                    added_table_final_invoice_list.add(item);
                                }
                            });
                            for (int i = 0; i < added_table_final_invoice_list.size(); i++) {
                                //Update seat status
                                byte seat_number = (byte) added_table_final_invoice_list.get(i).seat_number.get();
                                Seat seat = SeatDAO.get_seat_by_coach_id_and_seat_number(coach_id, seat_number);
                                byte seat_status = 1;
                                seat.setSeatStatus(seat_status);
                                seat.setModifiedDate(GetCurrentDate.get_current_date());
                                update_seat_result = added_table_final_invoice_list.get(i).seat_number.get();

                                //Will throw an exception if the Optimistic Locking occurs
                                SeatDAO.update_seat(seat);

                                //Update number of tickets int table_customer_info_list
                                int number_of_tickets = table_customer_info_list.get(current_selected_invoice_index - 1).number_of_tickets.get();
                                table_customer_info_list.get(current_selected_invoice_index - 1).number_of_tickets = new SimpleIntegerProperty(number_of_tickets + 1);

                                //Add new Invoice line item to Invoice
                                Ticket ticket = TicketDAO.get_ticket_by_seat_number_and_trip(seat_number, trip);
                                float price = ticket.getTicketPrice();
                                Invoicelineitem invoice_line_item = new Invoicelineitem(invoice, ticket, price, GetCurrentDate.get_current_date());
                                InvoicelineitemDAO.add_invoice_line_item(invoice_line_item);

                            }
                            //Update invoice price and modified date
                            invoice.setTotalPrice((float) total_price);
                            invoice.setModifiedDate(GetCurrentDate.get_current_date());
                            InvoiceDAO.update_invoice(invoice);
                        }
                        return null;
                    }
                };
                update_invoice_task.setOnSucceeded(event2 -> {
                    //Reset all table
                    tblSearchCustomerInfoResult.getColumns().get(1).setVisible(false);
                    tblSearchCustomerInfoResult.getColumns().get(1).setVisible(true);
                    tblSearchCustomerInfoResult.getColumns().get(2).setVisible(false);
                    tblSearchCustomerInfoResult.getColumns().get(2).setVisible(true);
                    tblEditInvoice.setVisible(false);
                    tblEditInvoice.setVisible(true);
                    tblFinalInvoice.setVisible(false);
                    tblFinalInvoice.setVisible(true);
                    is_modified_invoice = false;
                    is_modified_invoice_name = false;
                    is_modified_invoice_phone_number = false;
                    is_modified_invoice_seat_number = false;
                    lblInvoiceName.setText(table_customer_info_list.get(current_selected_invoice_index - 1).name.getValue());
                    animate_loading_anchor_pane(loading_anchor_pane, 0);
                    loading_anchor_pane.toBack();
                    //Save the table_final_invoice_list to another list to track change
                    original_table_final_invoice_list = FXCollections.observableArrayList(table_final_invoice_list);
                    Alert update_invoice_successfully_alert = new Alert(AlertType.INFORMATION);
                    update_invoice_successfully_alert.setTitle("THÔNG BÁO");
                    update_invoice_successfully_alert.setHeaderText("Cập nhật hóa đơn thành công.");
                    update_invoice_successfully_alert.setContentText(null);
                    update_invoice_successfully_alert.showAndWait();
                });
                update_invoice_task.setOnFailed(event2 -> {
                    reset_invoice();
                    duplicate_seat_string = duplicate_seat_string + Integer.toString(update_seat_result) + "; ";
                    Alert update_invoice_successfully_alert = new Alert(AlertType.INFORMATION);
                    update_invoice_successfully_alert.setTitle("THÔNG BÁO");
                    update_invoice_successfully_alert.setHeaderText("Ghế " + duplicate_seat_string + "đã được đặt.\nXin vui lòng chọn ghế khác.");
                    update_invoice_successfully_alert.setContentText(null);
                    update_invoice_successfully_alert.showAndWait();
                });
                Thread thread = new Thread(update_invoice_task);
                thread.setDaemon(true);
                thread.start();
            } else {
                loading_anchor_pane.toFront();
                animate_loading_anchor_pane(loading_anchor_pane, 1);
                Task<Void> reset_seat_pane_task = new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        Trip trip = InvoiceDAO.get_invoice_by_id(current_selected_invoice_id).getTrip();
                        CoachDriverTrip cdt = CoachDriverTripDAO.get_coach_driver_trip_by_trip(trip);
                        int coach_id = cdt.getCoach().getCoachId();
                        selected_seat_list = SeatDAO.get_all_selected_seat_of_trip_by_coach_id(coach_id);
                        load_selected_seats_to_pane_by_coach_id(coach_id, selected_seat_list);
                        disable_selected_seats_of_other_customers(coach_id, selected_seat_list, current_customer_selected_seat);
                        total_price = InvoiceDAO.get_total_price(current_invoice);
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
                        return null;
                    }
                };
                reset_seat_pane_task.setOnSucceeded(event2 -> {
                    lblInvoiceName.setText(table_customer_info_list.get(current_selected_invoice_index - 1).name.getValue());
                    txtInvoiceName.setText(table_customer_info_list.get(current_selected_invoice_index - 1).name.getValue());
                    txtInvoicePhoneNumber.setText(table_customer_info_list.get(current_selected_invoice_index - 1).phone_number.getValue());
                    txtInvoiceNumberOfTickets.setText(Integer.toString(table_customer_info_list.get(current_selected_invoice_index - 1).number_of_tickets.getValue()));
                    txtInvoiceTotalPrice.setText(Double.toString(total_price));
                    tblEditInvoice.setVisible(false);
                    tblEditInvoice.setVisible(true);
                    animate_loading_anchor_pane(loading_anchor_pane, 0);
                    loading_anchor_pane.toBack();
                    //Reset invoice tracking changes variables
                    is_modified_invoice = false;
                    is_modified_invoice_name = false;
                    is_modified_invoice_phone_number = false;
                    is_modified_invoice_seat_number = false;
                    original_table_final_invoice_list = FXCollections.observableArrayList(table_final_invoice_list);
                });
                Thread thread = new Thread(reset_seat_pane_task);
                thread.setDaemon(true);
                thread.start();
            }
        } else {
            //Reset invoice tracking changes variables
            is_modified_invoice = false;
            is_modified_invoice_name = false;
            is_modified_invoice_phone_number = false;
            is_modified_invoice_seat_number = false;
        }
    }

    @FXML
    private void invoice_reset_button_action(ActionEvent event) {
        Alert reset_alert = new Alert(AlertType.CONFIRMATION);
        reset_alert.setTitle("XÁC NHẬN KHÔI PHỤC");
        reset_alert.setHeaderText("Những thay đổi (nếu có) trong hóa đơn sẽ không được lưu lại.\nBạn có chắc chắn muốn khôi phục hóa đơn?");
        reset_alert.setContentText(null);
        Optional<ButtonType> reset_alert_action = reset_alert.showAndWait();
        if (reset_alert_action.get() == ButtonType.OK) {
            reset_invoice();
        }
    }

    @FXML
    private void invoice_delete_button_action(ActionEvent event) {
        Alert delete_alert = new Alert(AlertType.CONFIRMATION);
        delete_alert.setTitle("XÁC NHẬN XÓA");
        delete_alert.setHeaderText("Hóa đơn sẽ bị xóa và không thể khôi phục lại.\nBạn có chắc chắn muốn xóa hóa đơn?");
        delete_alert.setContentText("Bạn sẽ chịu mọi trách nhiệm cho việc xóa hóa đơn mà không có sự đồng ý từ phía khách hàng. \nXin hãy cân nhắc trước khi xóa hóa đơn!");
        Optional<ButtonType> delete_alert_action = delete_alert.showAndWait();
        if (delete_alert_action.get() == ButtonType.OK) {
            Task<Void> delete_invoice_task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    //Update invoice's fields to deprecate field
                    try {
                        Invoice invoice = InvoiceDAO.get_invoice_by_id(current_selected_invoice_id);

                        //Delete invoice line items
                        List<Invoicelineitem> invoice_line_item_list = InvoicelineitemDAO.get_invoice_line_items_by_invoice(invoice);
                        invoice_line_item_list.forEach(item -> {
                            InvoicelineitemDAO.delete_invoice_line_item(item);
                        });

                        //Reset selected seats state
                        Trip current_trip = invoice.getTrip();
                        CoachDriverTrip cdt = CoachDriverTripDAO.get_coach_driver_trip_by_trip(current_trip);
                        int coach_id = cdt.getCoach().getCoachId();
                        table_final_invoice_list.forEach(item -> {
                            byte seat_number = (byte) item.seat_number.get();
                            Seat seat = SeatDAO.get_seat_by_coach_id_and_seat_number(coach_id, seat_number);
                            byte seat_status = 0;
                            seat.setSeatStatus(seat_status);
                            seat.setModifiedDate(GetCurrentDate.get_current_date());
                            SeatDAO.update_seat(seat);
                        });
                        table_final_invoice_list.clear();

                        Trip trip = TripDAO.get_trip_by_trip_name("Trống");
                        invoice.setTrip(trip);
                        invoice.setTotalPrice(0);
                        invoice.setInvoiceStatus("Đã hủy");
                        InvoiceDAO.update_invoice(invoice);

                        table_customer_info_list.get(current_selected_invoice_index - 1).number_of_tickets = new SimpleIntegerProperty(0);
                        table_customer_info_list.get(current_selected_invoice_index - 1).payment_status = new SimpleStringProperty("Đã hủy");
                        table_customer_info_list.get(current_selected_invoice_index - 1).number_of_tickets = new SimpleIntegerProperty(0);
                    } catch (Exception e) {
                    }
                    return null;
                }
            };
            delete_invoice_task.setOnSucceeded(event1 -> {
                txtInvoiceTotalPrice.setText(Double.toString(0));
                txtInvoicePaymentStatus.setText("Đã hủy");
                txtInvoiceNumberOfTickets.setText(Double.toString(0));
                tblEditInvoice.setVisible(false);
                tblEditInvoice.setVisible(true);
                paneSeatPlane.setDisable(true);
                tblSearchCustomerInfoResult.getColumns().get(5).setVisible(false);
                tblSearchCustomerInfoResult.getColumns().get(5).setVisible(true);
                tblSearchCustomerInfoResult.getColumns().get(4).setVisible(false);
                tblSearchCustomerInfoResult.getColumns().get(4).setVisible(true);
                tblSearchCustomerInfoResult.getColumns().get(3).setVisible(false);
                tblSearchCustomerInfoResult.getColumns().get(3).setVisible(true);
                is_modified_invoice = false;
                is_modified_invoice_name = false;
                is_modified_invoice_phone_number = false;
                is_modified_invoice_seat_number = false;
                btnInvoiceCheckOut.setDisable(true);
                btnInvoiceUpdate.setDisable(true);
                btnInvoiceReset.setDisable(true);
                btnInvoiceDelete.setDisable(true);
                original_table_final_invoice_list = FXCollections.observableArrayList(table_final_invoice_list);
                Alert delete_alert_successfully = new Alert(AlertType.CONFIRMATION);
                delete_alert_successfully.setTitle("XÓA THÀNH CÔNG");
                delete_alert_successfully.setHeaderText("Hóa đơn đã được xóa thành công.");
                delete_alert_successfully.setContentText(null);
                Optional<ButtonType> delete_alert_successfully_action = delete_alert_successfully.showAndWait();
            });
            Thread thread = new Thread(delete_invoice_task);
            thread.setDaemon(true);
            thread.start();
        }
    }

    @FXML
    private void lock_screen_button_action(ActionEvent event) {
        paneLockScreen.toFront();
        txtUsername.setText(data.get_username());
        animate_lock_screen_anchor_pane(paneLockScreen, 1);
    }

    @FXML
    private void unlock_screen_button_action(ActionEvent event) {
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText();
        if (password == null || password.equals("")) {
            Alert null_textfield_alert = new Alert(AlertType.ERROR);
            null_textfield_alert.setTitle("Lỗi đăng nhập");
            null_textfield_alert.setHeaderText("Mật khẩu không được phép để trống.");
            null_textfield_alert.setContentText(null);
            null_textfield_alert.show();
        } else {
            loading_anchor_pane.toFront();
            animate_loading_anchor_pane(loading_anchor_pane, 1);
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
                    animate_lock_screen_anchor_pane(paneLockScreen, 1);
                    paneLockScreen.toBack();
                    txtUsername.setText("");
                    txtPassword.setText("");
                } else {
                    animate_loading_anchor_pane(loading_anchor_pane, 0);
                    loading_anchor_pane.toBack();
                    Alert failed_login_alert = new Alert(AlertType.ERROR);
                    failed_login_alert.setTitle("Lỗi đăng nhập");
                    failed_login_alert.setHeaderText("Tên đăng nhập hoặc mật khẩu không đúng.");
                    failed_login_alert.setContentText(null);
                    failed_login_alert.show();
                }
            });
            Thread thread = new Thread(check_login_task);
            thread.setDaemon(true);
            thread.start();
        }
    }

    @FXML
    private void login_with_another_account_action(ActionEvent event) {
        Alert logout_alert = new Alert(AlertType.CONFIRMATION);
        logout_alert.getDialogPane().getScene().getWindow().setOnCloseRequest(event_close -> event_close.consume());
        logout_alert.setTitle("ĐĂNG XUẤT");
        logout_alert.setHeaderText("Bạn có chắc chắn muốn đăng nhập bằng tài khoản khác không?");
        logout_alert.setContentText(null);
        Optional<ButtonType> logout_alert_action = logout_alert.showAndWait();
        if (logout_alert_action.get() == ButtonType.OK) {
            Stage current_stage = (Stage) rootPane.getScene().getWindow();
            ApplicationConfiguration app_config = new ApplicationConfiguration();
            try {
                app_config.configure_stage(current_stage, "/view/fxml/staff/login_stage.fxml", "Minh Nhut Corporation", 1200, 800);
            } catch (IOException ex) {
                Logger.getLogger(Controller_login_stage.class.getName()).log(Level.SEVERE, null, ex);
            }
            current_stage.show();
        }
    }

    @FXML
    private void logout_button_action(ActionEvent event) {
        Alert logout_alert = new Alert(AlertType.CONFIRMATION);
        logout_alert.getDialogPane().getScene().getWindow().setOnCloseRequest(event_close -> event_close.consume());
        logout_alert.setTitle("ĐĂNG XUẤT");
        logout_alert.setHeaderText("Bạn có chắc chắn muốn đăng xuất không?");
        logout_alert.setContentText(null);
        Optional<ButtonType> logout_alert_action = logout_alert.showAndWait();
        if (logout_alert_action.get() == ButtonType.OK) {
            Stage current_stage = (Stage) rootPane.getScene().getWindow();
            ApplicationConfiguration app_config = new ApplicationConfiguration();
            try {
                app_config.configure_stage(current_stage, "/view/fxml/staff/login_stage.fxml", "Minh Nhut Corporation", 1200, 800);
            } catch (IOException ex) {
                Logger.getLogger(Controller_login_stage.class.getName()).log(Level.SEVERE, null, ex);
            }
            current_stage.show();
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
        time_line.setOnFinished(event -> {
            tblSearchCustomerInfoResult.setVisible(false);
            tblSearchCustomerInfoResult.setVisible(true);
        });
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

    private void animate_lock_screen_anchor_pane(final AnchorPane pane, double opacity) {
        Duration transition_duration = Duration.millis(300);
        FadeTransition ft = new FadeTransition(transition_duration, pane);
        ft.setFromValue(pane.getOpacity());
        ft.setToValue(opacity);
        ft.play();
    }

    private void animate_customer_pane_component() {
        Duration transition_duration = Duration.millis(500);

        FadeTransition lbl_result_count_ft = new FadeTransition(transition_duration, lblSearchCustomerInfo_result_count);
        lbl_result_count_ft.setFromValue(lblSearchCustomerInfo_result_count.getOpacity());
        lbl_result_count_ft.setToValue(1);

        FadeTransition lbl_filter_ft = new FadeTransition(transition_duration, lblSearchCustomerInfo_filter);
        lbl_filter_ft.setFromValue(lblSearchCustomerInfo_filter.getOpacity());
        lbl_filter_ft.setToValue(1);

        FadeTransition txt_filter_ft = new FadeTransition(transition_duration, txtSearchCustomerInfo_filter);
        txt_filter_ft.setFromValue(txtSearchCustomerInfo_filter.getOpacity());
        txt_filter_ft.setToValue(1);

        TranslateTransition table_result_tt = new TranslateTransition(transition_duration, tblSearchCustomerInfoResult);
        table_result_tt.setFromY(tblSearchCustomerInfoResult.getTranslateY());
        table_result_tt.setToY(129);

        SequentialTransition seqT = new SequentialTransition(table_result_tt, lbl_result_count_ft, lbl_filter_ft, txt_filter_ft);
        seqT.play();
        is_animated_search_customer_info_components = true;
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

    //Check all seat check box which seat numbers are selected in the trip
    private void load_selected_seats_to_pane_by_coach_id(int coach_id, List<Byte> selected_seat_list) {
        String cbSeat_id_prefix = "cbSeat";
        ObservableList<Node> cbSeat_node_list = paneSeatPlane.getChildren();
        //Reset all seat check box
        cbSeat_node_list.forEach(node1 -> {
            JFXCheckBox check_box = (JFXCheckBox) node1;
            check_box.setSelected(false);
        });

        selected_seat_list.forEach(selected_seat -> {
            String cbSeat_id = cbSeat_id_prefix + selected_seat;
            cbSeat_node_list.forEach(node -> {
                if (node.getId().equals(cbSeat_id)) {
                    JFXCheckBox check_box = (JFXCheckBox) node;
                    check_box.setSelected(true);
                }
            });
        });
    }

    //Disable all selected seat in trip which not belongs to the current invoice
    private void disable_selected_seats_of_other_customers(int coach_id, List<Byte> selected_seat_list, List<Byte> param_current_customer_selected_seat) {
        List<Byte> other_customers_selected_seat = new ArrayList<>(selected_seat_list);
        param_current_customer_selected_seat.forEach(current_customer_seats -> {
            other_customers_selected_seat.remove(current_customer_seats);
        });

        String cbSeat_id_prefix = "cbSeat";
        ObservableList<Node> cbSeat_node_list = paneSeatPlane.getChildren();
        //reset check box state
        cbSeat_node_list.forEach(node1 -> {
            node1.setDisable(false);
        });

        other_customers_selected_seat.forEach(selected_seat -> {
            String cbSeat_id = cbSeat_id_prefix + selected_seat;
            cbSeat_node_list.forEach(node -> {
                if (node.getId().equals(cbSeat_id)) {
                    JFXCheckBox check_box = (JFXCheckBox) node;
                    check_box.setSelected(false);
                    check_box.setDisable(true);
                }
            });
        });
    }

    //Get all selected seat in trip by invoice object
    private List<Byte> get_all_selected_seat_in_invoice_by_invoice(Invoice invoice) {
        List<Invoicelineitem> items_list = get_invoice_line_items_by_invoice(invoice);
        List<Byte> temp_selected_seat_list = new ArrayList<>();
        items_list.forEach(item -> {
            Ticket ticket = item.getTicket();
            byte selected_seat = ticket.getTicketSeatNumber();
            temp_selected_seat_list.add(selected_seat);
        });
        return temp_selected_seat_list;
    }

    //Add a row corresponding to the unchecked seat check box in tblEditInvoice
    private void update_table_search_customer_list_when_check_box_checked(JFXCheckBox check_box, Invoice invoice) {
        try {
            String check_box_id = check_box.getId().substring(6);
            byte current_seat_number = Byte.parseByte(check_box_id);
            Trip trip = invoice.getTrip();
            Ticket ticket = TicketDAO.get_ticket_by_seat_number_and_trip(current_seat_number, trip);
            current_ticket_price = ticket.getTicketPrice();
            int row_count = table_final_invoice_list.size();

            int index = row_count + 1;
            String ticket_name = ticket.getTicketName();
            int quantity = 1;
            int seat_number = ticket.getTicketSeatNumber();
            double price = ticket.getTicketPrice();

            TableFinalInvoiceModel row = new TableFinalInvoiceModel(index, ticket_name, quantity, seat_number, price);
            table_final_invoice_list.add(row);
        } catch (NumberFormatException e) {
        }
    }

    //Delete a row corresponding to the unchecked seat check box in tblEditInvoice
    private void update_table_search_customer_list_when_check_box_unchecked(JFXCheckBox check_box) {
        String check_box_id = check_box.getId().substring(6);
        int current_seat_number = Integer.parseInt(check_box_id);
        for (int i = 0; i < table_final_invoice_list.size(); i++) {
            if (table_final_invoice_list.get(i).seat_number.getValue() == current_seat_number) {
                current_ticket_price = table_final_invoice_list.get(i).price.getValue();
                table_final_invoice_list.remove(i);
            }
        }

        //reset table index
        for (int i = 0; i < table_final_invoice_list.size(); i++) {
            table_final_invoice_list.get(i).index = new SimpleIntegerProperty(i + 1);
        }
    }

    //Run reset invoice task
    private void reset_invoice() {
        loading_anchor_pane.toFront();
        animate_loading_anchor_pane(loading_anchor_pane, 1);
        Task<Void> reset_seat_pane_task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Trip trip = InvoiceDAO.get_invoice_by_id(current_selected_invoice_id).getTrip();
                CoachDriverTrip cdt = CoachDriverTripDAO.get_coach_driver_trip_by_trip(trip);
                int coach_id = cdt.getCoach().getCoachId();
                selected_seat_list = SeatDAO.get_all_selected_seat_of_trip_by_coach_id(coach_id);
                load_selected_seats_to_pane_by_coach_id(coach_id, selected_seat_list);
                disable_selected_seats_of_other_customers(coach_id, selected_seat_list, current_customer_selected_seat);
                total_price = InvoiceDAO.get_total_price(current_invoice);
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
                return null;
            }
        };
        reset_seat_pane_task.setOnSucceeded(event2 -> {
            lblInvoiceName.setText(table_customer_info_list.get(current_selected_invoice_index - 1).name.getValue());
            txtInvoiceName.setText(table_customer_info_list.get(current_selected_invoice_index - 1).name.getValue());
            txtInvoicePhoneNumber.setText(table_customer_info_list.get(current_selected_invoice_index - 1).phone_number.getValue());
            txtInvoiceNumberOfTickets.setText(Integer.toString(table_customer_info_list.get(current_selected_invoice_index - 1).number_of_tickets.getValue()));
            txtInvoiceTotalPrice.setText(Double.toString(total_price));
            tblEditInvoice.setVisible(false);
            tblEditInvoice.setVisible(true);
            animate_loading_anchor_pane(loading_anchor_pane, 0);
            loading_anchor_pane.toBack();
            //Reset invoice tracking changes variables
            is_modified_invoice = false;
            is_modified_invoice_name = false;
            is_modified_invoice_phone_number = false;
            is_modified_invoice_seat_number = false;
            original_table_final_invoice_list = FXCollections.observableArrayList(table_final_invoice_list);
        });
        Thread thread = new Thread(reset_seat_pane_task);
        thread.setDaemon(true);
        thread.start();
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
        StringProperty added_date;

        TableSearchCustomerInfoDataModel(int invoice_id, int index, String name, String phone_number, String trip_line_name, int number_of_tickets, String payment_status, String start_date, String added_date) {
            this.invoice_id = new SimpleIntegerProperty(invoice_id);
            this.index = new SimpleIntegerProperty(index);
            this.name = new SimpleStringProperty(name);
            this.phone_number = new SimpleStringProperty(phone_number);
            this.trip_line_name = new SimpleStringProperty(trip_line_name);
            this.number_of_tickets = new SimpleIntegerProperty(number_of_tickets);
            this.payment_status = new SimpleStringProperty(payment_status);
            this.start_date = new SimpleStringProperty(start_date);
            this.added_date = new SimpleStringProperty(added_date);
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
