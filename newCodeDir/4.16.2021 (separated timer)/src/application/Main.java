package application;
 

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import javafx.scene.input.MouseEvent; //uncomment when used
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
	public static void main(String[] args) 
	{
		launch(args);
	}

  @Override
  public void start(Stage stage) {
    
	  
/* Tool Bar */  
    // tool bar: message 
    Text toolBarMessage = new Text("Welcome!");
    Font fontKarlaBold = Font.loadFont(Main.class.getResource("/application/resources/Karla-Bold.ttf").toExternalForm(), 20);
    toolBarMessage.setFont(fontKarlaBold);
    
    // tool bar: logo image 
    Image img = new Image(getClass().getResourceAsStream("/application/resources/logoTransparentSmall.png"));
    ImageView imgView = new ImageView(img);
    imgView.setFitWidth(100);
    imgView.setFitHeight(100);
    
    // tool bar: logout button
    Button logoutBtn = new Button("Logout");
    logoutBtn.setFont(fontKarlaBold);
    logoutBtn.setStyle("-fx-background-color: thistle;");
    //logoutBtn.setPrefSize(, 10);

    // horizontal layout for tool bar 
    HBox toolBarContent = new HBox();
    toolBarContent.getChildren().addAll(imgView, toolBarMessage, logoutBtn);
    toolBarContent.setAlignment(Pos.CENTER);
    toolBarContent.setSpacing(100);
    toolBarContent.setStyle("-fx-background-color: lightblue;"); // change to og color !!
    toolBarContent.setPadding(new Insets(20,20,20,20));
   
/* Timer */
    // timer: blue background
    Rectangle timerBackground = new Rectangle(400, 300);
    timerBackground.setArcHeight(40.0);
    timerBackground.setArcWidth(40.0);
    timerBackground.setFill(Color.web("#DEE7EC",1));    
    
    // timer: labels 
    Label timerLbl = new Label("Timer");
    timerLbl.setFont(fontKarlaBold);
    
    Label taskLbl = new Label("Current Task: ");
    taskLbl.setFont(fontKarlaBold);
    
    // timer: buttons
    Button sButton = new Button("Start");
    sButton.setFont(fontKarlaBold);
    sButton.setStyle("-fx-background-color: thistle;");
    
    
    Button rButton = new Button("Reset");
    rButton.setFont(fontKarlaBold);
    rButton.setStyle("-fx-background-color: thistle;");
    
    
    // vertical layout for buttons 
    VBox timerButtons = new VBox();
    timerButtons.getChildren().addAll(sButton, rButton);
    timerButtons.setSpacing(20);
    timerButtons.setPadding(new Insets(20,20,20,20));
    
    // timer: stop watch
    Circle stopWatchCircle = new Circle();
    stopWatchCircle.setRadius(100);
    stopWatchCircle.setFill(Color.WHITE);
   
    Timer stopWatch = new Timer(); 
    
    
    StackPane stopWatchLayout = new StackPane();
    stopWatchLayout.setAlignment(Pos.CENTER);
    stopWatchLayout.getChildren().addAll(stopWatchCircle, stopWatch.getText());
    
    // Adding Button Actions  
	sButton.setOnAction(new EventHandler<ActionEvent>() {
	  @Override
	  public void handle(ActionEvent event) {
	  	if(stopWatch.getSos()) {
	  		stopWatch.playTimeline();
	  		stopWatch.setSos(false);
	  		sButton.setText("Stop");
	} else {
		stopWatch.pauseTimeline();
		stopWatch.setSos(true);
		sButton.setText("Start");
	      	}
	      }
	  });
	
	rButton.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
        	stopWatch.setMins(0);
        	stopWatch.setSecs(0);
        	stopWatch.setMillis(0);
        	stopWatch.pauseTimeline();
        	stopWatch.setTimerText("00:00");
        	if(stopWatch.getSos() == false) {
        		stopWatch.setSos(true);
        		sButton.setText("Start");
        	}
        }
    });
	
        
    // Timer Layout 
    BorderPane timerLayout = new BorderPane();
    timerLayout.setTop(timerLbl);
    BorderPane.setAlignment(timerLbl, Pos.CENTER);
    timerLayout.setBottom(taskLbl);
    BorderPane.setAlignment(taskLbl, Pos.CENTER);
    timerLayout.setLeft(timerButtons);
    timerLayout.setCenter(stopWatchLayout);
    
    StackPane timer = new StackPane();
    timer.getChildren().addAll(timerBackground, timerLayout);
    
/* Data Chart */
    // will be replaced with a working chart later
    Image dataChartTemp = new Image(getClass().getResourceAsStream("/application/resources/DataChartImage.png"));
    ImageView dataChartImage = new ImageView(dataChartTemp);
    dataChartImage.setFitWidth(350);
    dataChartImage.setFitHeight(300);
    
    // data: blue background
    Rectangle dataBackground = new Rectangle(400, 300);
    dataBackground.setArcHeight(40.0);
    dataBackground.setArcWidth(40.0);
    dataBackground.setFill(Color.web("#DEE7EC",1));    
    
    // data: layout
    StackPane data = new StackPane();
    data.getChildren().addAll(dataBackground, dataChartImage);

    /* Vertical layout pane for timer and data */
    VBox leftWidgets = new VBox();
    leftWidgets.getChildren().addAll(timer, data);    
    leftWidgets.setSpacing(20);
    leftWidgets.setPadding(new Insets(20,20,20,20));
    
 /*Highlights */
    
 /*Tasks */
    // Task: buttons
    Button addTaskBtn = new Button("Add Task");
    addTaskBtn.setFont(fontKarlaBold);
    addTaskBtn.setStyle("-fx-background-color: thistle;");  
    
 /* Set up Page Layout */
    BorderPane root = new BorderPane();
    root.setTop(toolBarContent);
    root.setLeft(leftWidgets);
    //root.setRight(rightWidgets);
    
    Scene scene = new Scene(root, 1280, 720);
    stage.setTitle("Login Page");
    scene.setFill(Color.TRANSPARENT);
    stage.setWidth(1280);
    stage.setHeight(850);
    stage.setScene(scene);
    stage.show();
  }
}
