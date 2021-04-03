package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class Main extends Application {
	private double mainRectWidth = 1100, mainRectHeight = 650;
	private Scene secondScene = null;
    private Stage secondStage = null;     
    private Scene thirdScene = null;
    private Stage thirdStage = null;
    private Text loginText = null;
    private Button cancel = null;
    private Button register = null;
     
	@Override
	public void start(Stage stage) {
		
		/* Making the BlueBackGroundRectangle */
	    Rectangle rect1 = new Rectangle(mainRectWidth, mainRectHeight);
	    
	    rect1.setFill(Color.web("#DEE7EC",1));
	    rect1.setArcHeight(40.0);
	    rect1.setArcWidth(40.0);

	     /* Welcome Label */
	    Text welcomeText = new Text("Welcome");
	    Font fontKarlaBold = Font.loadFont(Main.class.getResource("/application/resources/Karla-Bold.ttf").toExternalForm(), 60);
	    welcomeText.setFont(fontKarlaBold);
	    
	    StackPane loginInputBoxPane = new StackPane();
	    
	    /* Making the OffWhite Rectangle */
	    Rectangle rect2 = new Rectangle(350,350);
	    rect2.setArcHeight(40.0);
	    rect2.setArcWidth(40.0);
	    rect2.setFill(Color.web("#EFEAE4",1));
	    
	    /* Sign up Text*/
	    loginText = new Text("Login");
	    Font fontFredoka = Font.loadFont(Main.class.getResource("/application/resources/FredokaOne-Regular.ttf").toExternalForm(), 30);
	    loginText.setFont(fontFredoka);
	    
	    TextField userNameField = new TextField("Enter User Name");
	    userNameField.setPrefWidth(200);
	    userNameField.setMaxWidth(200);

	    TextField passwordField = new TextField("Enter Password");
	    passwordField.setPrefWidth(200);
	    passwordField.setMaxWidth(200);
	    
	    /*create the buttons for sing up page*/
	    Button btn = new Button("Sign Up");
	    Button btn2 = new Button("Login  ");
	    
	    btn.setStyle("-fx-background-color: #AB81CD; -fx-background-radius: 15px; -fx-text-fill: #ffffff");
	    btn.setCursor(Cursor.HAND);

	    btn2.setStyle("-fx-background-color: #AB81CD; -fx-background-radius: 15px; -fx-text-fill: #ffffff");
	    btn2.setCursor(Cursor.HAND);
	    
	    /* Stack the OffWhite background with the labels and text field */
	   
	    StackPane registerBoxPane = new StackPane();
	    registerBoxPane.getChildren().addAll(btn2);
	    
	    StackPane registerBoxPane2 = new StackPane();
	    registerBoxPane2.getChildren().addAll(btn);
	    
	    
	    /* set register and cancel button next to each other and placed at bottom of gray square*/
	    HBox signupBox = new HBox();
	    signupBox.getChildren().addAll(registerBoxPane2,registerBoxPane);
	    HBox.setMargin(signupBox, new Insets(230,20,0,0));
	    signupBox.setSpacing(10d);
	    
	    HBox Move = new HBox(signupBox);
	    Move.setAlignment(Pos.BOTTOM_CENTER);
	    
	    /* add all other members vertically*/
	    VBox loginVBox = new VBox();
	    loginVBox.getChildren().addAll(loginText, userNameField,passwordField);
	    loginVBox.setAlignment(Pos.CENTER);
	    loginVBox.setSpacing(10d);
	    
	    /* put everything together*/
	    loginInputBoxPane.getChildren().addAll(rect2,loginVBox,Move);
	    
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
	
	    /*create scene and stage*/
	    Scene scene = new Scene(root, 1280, 720);
	   
	    stage.setTitle("Login Page");
	    scene.setFill(Color.TRANSPARENT);
	    stage.setWidth(1280);
	    stage.setHeight(720);
	    stage.setScene(scene);
	    stage.show();
	    
	    btn.setOnAction(e->{
            
	    	/* close previous pages*/
	    	stage.close();
	    	if(thirdStage != null) {
	    		thirdStage.close();		
	    	}	    
	    	
	    	/* set up the new text */
	    	loginText = new Text("Sign Up");
		    loginText.setFont(fontFredoka);
	    	
    	    /* Sign up Text for password*/
    	    TextField passwordField2 = new TextField("Re-Enter Password");
    	    passwordField2.setPrefWidth(200);
    	    passwordField2.setMaxWidth(200);
    	    
    	    /*create the buttons for sing up page*/
    	    cancel = new Button("Cancel");
    	    register = new Button("Register");
    	    
    	    cancel.setStyle("-fx-background-color: #AB81CD; -fx-background-radius: 15px; -fx-text-fill: #ffffff");
    	    cancel.setCursor(Cursor.HAND);
    	    
    	    register.setStyle("-fx-background-color: #AB81CD; -fx-background-radius: 15px; -fx-text-fill: #ffffff");
    	    register.setCursor(Cursor.HAND);
    	        	    
    	 
    	    /* add buttons to page*/
    	    registerBoxPane.getChildren().addAll(register);
    	    registerBoxPane2.getChildren().addAll(cancel);
    	    
    	    /* add all other members vertically*/
    	    loginVBox.getChildren().clear();
    	    loginVBox.getChildren().addAll(loginText, userNameField,passwordField,passwordField2);
    	    
    	    /* Put Vertical Box in the Stack Pane*/
    	    StackPane root21 = new StackPane();
    	    root21.getChildren().addAll(rect1,hbox);
            
            /* create scene and stage*/
            secondScene = new Scene(root21, 1280, 720);
            secondStage = new Stage();
            secondStage.setScene(secondScene); 
            secondStage.setTitle("Sign Up page");
            secondStage.show();
        
        });    
	    
        cancel.setOnAction(e->{
            
        	/*close sign up page*/
            secondStage.close();
            
            /* add buttons to page*/
     	    registerBoxPane.getChildren().remove(register);
   	        registerBoxPane2.getChildren().remove(cancel);
     	  
   	        /*add all other members vertically and also create the Login text again*/
   	        loginVBox.getChildren().clear();
   	        loginText = new Text("Login");
   	        loginText.setFont(fontFredoka);
 	        loginVBox.getChildren().addAll(loginText, userNameField,passwordField);
 
 	        /*Put Vertical Box in the Stack Pane*/
     	    StackPane root3 = new StackPane();
     	    root3.getChildren().addAll(rect1, vbox1, hbox);
     	   
     	    /* create scene and stage*/
		    thirdScene = new Scene(root3,1280, 720);
            thirdStage = new Stage();
            thirdStage.setScene(thirdScene); 
            thirdStage.setTitle("Welcome Page");
            thirdStage.show();
        });    
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
