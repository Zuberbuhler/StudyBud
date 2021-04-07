package application;
 
//Matthew's Code: 

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginPage extends Application {
  public static void main(String[] args) {
    launch(args);
  }
  
  double mainRectWidth = 1100, mainRectHeight = 650;

  @Override
  public void start(Stage stage) {

      
    /* Making the BlueBackGroundRectangle */
    Rectangle rect1 = new Rectangle(mainRectWidth, mainRectHeight);
    
    rect1.setFill(Color.web("#DEE7EC",1));
    rect1.setArcHeight(40.0);
    rect1.setArcWidth(40.0);

     /* Welcome Label */
    Text welcomeText = new Text("Welcome");
    Font fontKarlaBold = Font.loadFont(LoginPage.class.getResource("/application/resources/Karla-Bold.ttf").toExternalForm(), 60);
    welcomeText.setFont(fontKarlaBold);
    
    StackPane loginInputBoxPane = new StackPane();
    
    /* Making the OffWhite Rectangle */
    Rectangle rect2 = new Rectangle(350, 350);
    rect2.setArcHeight(40.0);
    rect2.setArcWidth(40.0);
    rect2.setFill(Color.web("#EFEAE4",1));
    /* Login Text*/
    Text loginText = new Text("Login");
    Font fontFredoka = Font.loadFont(LoginPage.class.getResource("/application/resources/FredokaOne-Regular.ttf").toExternalForm(), 30);
    loginText.setFont(fontFredoka);
    
    TextField userNameField = new TextField("Enter User Name");
    userNameField.setPrefWidth(200);
    userNameField.setMaxWidth(200);

    TextField passwordField = new TextField("Enter Password");
    passwordField.setPrefWidth(200);
    passwordField.setMaxWidth(200);
    
    /* Making the purple register Rectangle */
    
    Rectangle rect3 = new Rectangle(193, 66);
    rect3.setArcHeight(40.0);
    rect3.setArcWidth(40.0);
    rect3.setFill(Color.web("#AB81CD",1));
    
    /* Register Text*/
    Label registerLabel = new Label("Register");
    registerLabel.setFont(fontFredoka);
    
    /* Stack the OffWhite background with the labels and text field */
    StackPane registerBoxPane = new StackPane();
    registerBoxPane.getChildren().addAll(rect3, registerLabel);
    
    VBox loginVBox = new VBox();
    loginVBox.getChildren().addAll(loginText, userNameField, 
                                    passwordField, registerBoxPane);
    loginVBox.setAlignment(Pos.CENTER);
    loginVBox.setSpacing(10d);
    
    loginInputBoxPane.getChildren().addAll(rect2, loginVBox);
    
    
    /* Making the Logo */
    Image img = new Image(getClass().getResourceAsStream("/application/resources/logoTransparentSmall.png"));
    ImageView imgView = new ImageView(img);

    /* Make logo and Off white box horizontal to each other */
    HBox hbox = new HBox();
    hbox.getChildren().addAll(imgView, loginInputBoxPane);
    hbox.setAlignment(Pos.CENTER);
    
    
    /* Make Welcome vertical to the horizontal logo and OffWhite box */
    VBox vbox1 = new VBox();
    vbox1.getChildren().addAll(welcomeText, hbox);
    vbox1.setPadding(new Insets(90, 0, 0, 0));
    vbox1.setSpacing(100);
    vbox1.setAlignment(Pos.TOP_CENTER);
    
    /* Put Vertical Box in the Stack Pane*/
    StackPane root = new StackPane();
    root.getChildren().addAll(rect1, vbox1, hbox);

    Scene scene = new Scene(root, 1280, 720);
    stage.setTitle("Login Page");
    scene.setFill(Color.TRANSPARENT);
    stage.setWidth(1280);
    stage.setHeight(720);
    stage.setScene(scene);
    stage.show();
  }
}
