package application;
	
import java.io.FileWriter;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
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
 //   private Text loginText = null;
    private Button cancel = null;
    private Button register = null;
    private TextField userNameField = null;
    private TextField passwordField = null;
    String file = "Accounts.txt"; // I added this file to my project,
                                  //couldn't use paths as it kept giving me errors but we should eventually change it to a path
     
	@Override
	public void start(Stage stage) throws IOException {
		//File file = new File("Accounts.txt");

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
	    Text loginText = new Text("Login");
	    Font fontFredoka = Font.loadFont(Main.class.getResource("/application/resources/FredokaOne-Regular.ttf").toExternalForm(), 30);
	    //my code didnt show font properly so I tried this, didn't work but I think its because my paths are messed up
	    loginText.setFont(Font.font("/application/resources/FredokaOne-Regular.tff",30)/*fontFredoka*/);
	    
	    userNameField = new TextField("Enter User Name");
	    userNameField.setPrefWidth(200);
	    userNameField.setMaxWidth(200);

	    passwordField = new TextField("Enter Password");
	    passwordField.setPrefWidth(200);
	    passwordField.setMaxWidth(200);
	    
	    /*create the buttons for sing up page*/
	    Button signUp = new Button("Sign Up");
	    Button Login = new Button("Login  ");
	    
	    signUp.setStyle("-fx-background-color: #AB81CD; -fx-background-radius: 15px; -fx-text-fill: #ffffff");
	    signUp.setCursor(Cursor.HAND);

	    Login.setStyle("-fx-background-color: #AB81CD; -fx-background-radius: 15px; -fx-text-fill: #ffffff");
	    Login.setCursor(Cursor.HAND);
	    
	    cancel = new Button("Cancel");
	    register = new Button("Register");
	    
	    /* Stack the OffWhite background with the labels and text field */
	   
	    StackPane registerBoxPane = new StackPane();
	    registerBoxPane.getChildren().addAll(Login);
	    
	    StackPane registerBoxPane2 = new StackPane();
	    registerBoxPane2.getChildren().addAll(signUp);
	    
	    
	    /* set register and cancel button next to each other and placed at bottom of gray square*/
	    HBox signupBox = new HBox();
	    signupBox.getChildren().addAll(registerBoxPane2,registerBoxPane);
	    HBox.setMargin(signupBox, new Insets(30,20,0,0));
	    signupBox.setSpacing(10d);
	    
	    HBox Move = new HBox(signupBox);
	    Move.setAlignment(Pos.CENTER);
	    
	    /* add all other members vertically*/
	    VBox loginVBox = new VBox();
	    loginVBox.getChildren().addAll(loginText,userNameField,passwordField);
	    loginVBox.setAlignment(Pos.CENTER);
	    loginVBox.setSpacing(10d);
	    
	  
	      //Creating a Grid Pane 
	      GridPane gridPane = new GridPane();    
	
	      //Setting the Grid alignment 
	      gridPane.setAlignment(Pos.CENTER); 
	       
	      //Arranging all the nodes in the grid 
	      gridPane.add(loginVBox,0,0); 
	      gridPane.add(Move,0,1); 

	    /*put everything together*/
	    loginInputBoxPane.getChildren().addAll(rect2,gridPane);
	    
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
	    
	    
	    
	    Login.setOnAction(e->{

            StackPane root2 = new StackPane();
            Label label = new Label("Your are now signed in");
            root2.getChildren().add(label);
            Scene secondScene = new Scene(root2, 500,500);
            Stage secondStage = new Stage();
            secondStage.setScene(secondScene); // set the scene
            secondStage.setTitle("Second Form");
            secondStage.show();
            stage.close(); // close the first stage (Window)
        });
	    
	    	    
	    signUp.setOnAction(e->{
            
	    	/* close previous pages*/
	    	stage.close();
	    	if(thirdStage != null) {
	    		thirdStage.close();		
	    	}	    
	    	
	    	/* set up the new text */
	    	Text loginText2 = new Text("Sign Up");
		    loginText2.setFont(fontFredoka);
	    	
    	    /* Sign up Text for password*/
    	    TextField passwordField2 = new TextField("Re-Enter Password");
    	    passwordField2.setPrefWidth(200);
    	    passwordField2.setMaxWidth(200);
    	    
    	    /*create the buttons for sing up page
    	    cancel = new Button("Cancel");
    	    register = new Button("Register");
    	    */
    	    cancel.setStyle("-fx-background-color: #AB81CD; -fx-background-radius: 15px; -fx-text-fill: #ffffff");
    	    cancel.setCursor(Cursor.HAND);
    	    
    	    register.setStyle("-fx-background-color: #AB81CD; -fx-background-radius: 15px; -fx-text-fill: #ffffff");
    	    register.setCursor(Cursor.HAND);
    	        	    
    	 
    	    /* add buttons to page*/
    	    registerBoxPane.getChildren().addAll(register);
    	    registerBoxPane2.getChildren().addAll(cancel);
    	    
    	    /* add all other members vertically*/
    	    loginVBox.getChildren().clear();
    	    loginVBox.getChildren().addAll(loginText2, userNameField,passwordField,passwordField2);
    	    
    	    /* Put Vertical Box in the Stack Pane*/
    	    StackPane root21 = new StackPane();
    	    root21.getChildren().addAll(rect1,hbox);
            
            /* create scene and stage*/
            secondScene = new Scene(root21, 1280, 720);
            secondStage = new Stage();
            secondStage.setScene(secondScene); 
            secondStage.setTitle("Sign Up page");
            secondStage.show();
            
            register.setOnAction(e2->{
            boolean succesfull=false;
            	             
           try {
			StoreAccountInfo(MouseEvent.MOUSE_CLICKED,file);
			succesfull = true;
		} catch (IOException e1) {
			e1.printStackTrace();
		}
           
           if (succesfull = true) {
        	   StackPane root2 = new StackPane();
               Label label = new Label("Your are now signed in");
               root2.getChildren().add(label);
               Scene secondScene = new Scene(root2, 500,500);
               Stage secondStage4 = new Stage();
               secondStage4.setScene(secondScene); // set the scene
               secondStage4.setTitle("Second Form");
               secondStage4.show();
               secondStage.close(); // close the first stage (Window)   	   
           }
            
            });
        
        });    
	    
        cancel.setOnAction(e->{
            
        	/*close sign up page*/
            secondStage.close();
            
            /* add buttons to page*/
     	    registerBoxPane.getChildren().remove(register);
   	        registerBoxPane2.getChildren().remove(cancel);
     	  
   	        /*add all other members vertically and also create the Login text again*/
   	        loginVBox.getChildren().clear();
   	        Text loginText3 = new Text("Login");
   	        loginText3.setFont(fontFredoka);
 	        loginVBox.getChildren().addAll(loginText3, userNameField,passwordField);
 
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

	
	public void StoreAccountInfo(EventType<MouseEvent> mouseClicked, String file) throws IOException {
		StringBuilder username = new StringBuilder();
		StringBuilder password = new StringBuilder();
		username.append(userNameField.getText().toString());
		password.append(passwordField.getText().toString());
		
		FileWriter write = new FileWriter(file,true);
		write.write(username.toString());
		write.write(" ");
		write.write(password.toString());
		write.write("\n");
		write.close();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

