package application;
 

import javafx.scene.control.TextField;

import java.net.URL;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
//import javafx.scene.input.MouseEvent; //uncomment when used
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HomePage extends Application {
	private final ObservableList<Task> tableData = FXCollections.observableArrayList(
			new Task("Finish Homework","04-21", "CS151"),
			new Task("Quiz", "05-18", "CS151"),
			new Task("Final", "05-20", "CS151"));
	 
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
   
    // ---------------------TABLE----------------------------------------
    VBox vbox = createTable();
    
 /* Set up Page Layout */
    BorderPane root = new BorderPane();
    root.setTop(toolBarContent);
    root.setLeft(leftWidgets);
    root.setRight(vbox);
    
    Scene scene = new Scene(root, 1280, 720);
    URL url = this.getClass().getResource("application.css");
    String css = url.toExternalForm();
    scene.getStylesheets().add(css);
    
    stage.setTitle("Login Page");
    scene.setFill(Color.TRANSPARENT);
    stage.setWidth(1280);
    stage.setHeight(850);
    stage.setScene(scene);
    stage.show();
  }
  
  public VBox createTable()
  {
	  TableView tableView = new TableView();
	    tableView.setEditable(true);
	    // Sets up table to be selectable
	    TableViewSelectionModel<Task> selectionModel = tableView.getSelectionModel();
	    selectionModel.setSelectionMode(SelectionMode.MULTIPLE);
	    tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); // Removes extra column
	    
	    
	    
	    
	    // COlUMNS
	    TableColumn<Task, String> column1 = new TableColumn<>("Task Name");
	    column1.setCellValueFactory(new PropertyValueFactory("taskName"));
	    column1.setCellFactory(TextFieldTableCell.<Task>forTableColumn());
	    column1.setMinWidth(175);
	    
	    TableColumn<Task, String> column2 = new TableColumn<>("Due Date");
	    column2.setCellValueFactory(new PropertyValueFactory("dueDate"));
	    column2.setCellFactory(TextFieldTableCell.<Task>forTableColumn());
	    column2.setMinWidth(50);
	    
	    TableColumn<Task, String> column3 = new TableColumn<>("Course");
	    column3.setCellValueFactory(new PropertyValueFactory("Course"));
	    column3.setCellFactory(TextFieldTableCell.<Task>forTableColumn());
	    
	    TableColumn<Task, Boolean> column4 = new TableColumn<>("Completed");
	    column4.setCellValueFactory(cellData -> new ReadOnlyBooleanWrapper());
	    column4.setCellFactory(CheckBoxTableCell.<Task>forTableColumn(column4));
	    column4.setMinWidth(50);
	    
	    
	    tableView.getColumns().add(column1);
	    tableView.getColumns().add(column2);
	    tableView.getColumns().add(column3);
	    tableView.getColumns().add(column4);
	    
	    tableView.setItems(tableData);
	    
	    TextField addTaskName = new TextField();
	    addTaskName.setPromptText("Task Name");
	    addTaskName.setMinWidth(200);
	    addTaskName.setFont(Font.font("Arial", FontWeight.BOLD, 13));
	    
	    TextField addDueDate = new TextField();
	    addDueDate.setMaxWidth(column2.getPrefWidth());
	    addDueDate.setPromptText("Due Date");
	    addDueDate.setFont(Font.font("Arial", FontWeight.BOLD, 13));
	    
	    TextField addCourse = new TextField();
	    addCourse.setPromptText("Add Course");
	    addCourse.setMaxWidth(column3.getPrefWidth());
	    addCourse.setFont(Font.font("Arial", FontWeight.BOLD, 13));
	    
	    Button addButton = new Button("Add");
	    addButton.setOnAction(new EventHandler<ActionEvent>() {
	    		@Override
	    		public void handle(ActionEvent e) {
	    			tableData.add(new Task(
	    					addTaskName.getText(),
	    					addDueDate.getText(),
	    					addCourse.getText()));
	    			addTaskName.clear();
	    			addDueDate.clear();
	    			addCourse.clear();
	    		}
	    });
	    addButton.setMinWidth(100);
	    addButton.setMaxHeight(10);
	    addButton.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 14));
	    addButton.setStyle("-fx-background-color: thistle;");
	    
	    Button deleteButton = new Button("Delete");
	    deleteButton.setOnAction(e -> tableView.getItems().removeAll(tableView.getSelectionModel().getSelectedItems()));
	    deleteButton.setMinWidth(100);
	    deleteButton.setMaxHeight(10);
	    deleteButton.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 14));
	    deleteButton.setStyle("-fx-background-color: thistle;");
	    
	    //ObservableList<Task> selectedItems = selectionModel.getSelectedItems();
	    //tableView.getItems().removeAll(selectedItems);
	    
	    
	    HBox hb = new HBox();
	    hb.getChildren().addAll(addTaskName, addDueDate, addCourse, addButton, deleteButton);
	    hb.setSpacing(5);
	    
	    VBox vbox = new VBox(tableView, hb);
	    vbox.setSpacing(20);
	    vbox.setPadding(new Insets(20,20,20,20));
	    return vbox;
  }
}