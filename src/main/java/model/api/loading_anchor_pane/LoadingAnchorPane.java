/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.api.loading_anchor_pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *Create an Anchor Pane with loading gif
 * @author User
 */
public class LoadingAnchorPane extends AnchorPane {
    public LoadingAnchorPane() {
        super();
    }
    
    /**
     *Configure the attributes of the pane, also define a loading gif at center of the pane.
     * @param width double - The width of the pane
     * @param height double - The height of the pane
     * @param background_color String - The background color of the pane, with format #asdasd or rgb(0,0,0)
     * @param opacity double - The opacity of the background, has value from 0 to 1.
     * @throws java.io.FileNotFoundException
     */
    public void configure_pane(double width, double height, String background_color, double opacity) throws FileNotFoundException {
        this.setPrefSize(width, height);
        this.setBackground(new Background(new BackgroundFill(Color.web(background_color, opacity), CornerRadii.EMPTY, Insets.EMPTY)));
        
        ImageView loading_gif = new ImageView();
        Image loading_gif_image = new Image(new FileInputStream("src/main/resources/view/image/loading_gif_2.gif")); 
        loading_gif.setImage(loading_gif_image);
        loading_gif.setFitHeight(500);
        loading_gif.setFitWidth(500);
        loading_gif.setPreserveRatio(true);
        loading_gif.setX((width - 500)/2);
        loading_gif.setY((height - 500)/2 + 30);
        loading_gif.setSmooth(true);
        
        HBox loading_label_container = new HBox();
        loading_label_container.setPrefSize(1200, 30);
        loading_label_container.setLayoutX(0);
        loading_label_container.setLayoutY((height - 150)/2 + 150);
        loading_label_container.setAlignment(Pos.CENTER);
        
        Label loading_label = new Label();
        loading_label.setText("Hệ thống đang xử lý, xin quý khách vui lòng chờ đợi trong giây lát.");
        loading_label.setTextFill(Color.web("#ffffff"));
        loading_label.setFont(new Font(20));
        
        loading_label_container.getChildren().add(loading_label);
        
        this.getChildren().add(loading_gif);
        this.getChildren().add(loading_label_container);
    }
}
