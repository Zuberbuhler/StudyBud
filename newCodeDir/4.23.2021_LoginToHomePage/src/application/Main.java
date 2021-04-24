package application;

import java.net.URL;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class Main extends Application {
	private double mainRectWidth = 1100, mainRectHeight = 650;
	private Scene secondScene = null;
    private Stage secondStage = null;     
    private Scene thirdScene = null;
    private Stage thirdStage = null;
    private Button cancel = null;
    private Button register = null;
    private TextField userNameField = null;
    private TextField passwordField = null;
    private TextField passwordField2 = null;
    private int flag = 0; // flag used to see what type of error it is. 0 = incorrect username or password 
    					  // 1 =  password entries did not match when creating account 2 = user username already exist 3 = account already exist
   
    String file = "Accounts.txt"; // I added this file to my project,
                                  //couldn't use paths as it kept giving me errors but we should eventually change it to a path
    
    private final ObservableList<Task> tableData = FXCollections.observableArrayList(
			new Task("Finish Homework","04-21", "CS151"),
			new Task("Quiz", "05-18", "CS151"),
			new Task("Final", "05-20", "CS151"));

     
	@Override
	public void start(Stage stage) throws IOException {


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

            boolean succesfull=false;
            
            try {
            	/*checks if login credentials match with account in file, false tells method it is not creating a new account*/
 			succesfull=StoreAccountInfo(MouseEvent.MOUSE_CLICKED,file,false);
 		} catch (IOException e1) {
 			e1.printStackTrace();
 		}
            
            /*if the login credentials match then open main menu*/
            if (succesfull == true) {
            	
            	/* Tool Bar */  
        		HomePageToolBarGUI toolBarGUI = new HomePageToolBarGUI();
        		HBox toolBarContent = toolBarGUI.toolBar(stage, scene);
        		
        		
        		/* Timer */
        		TimerGUI timerGUI = new TimerGUI();
        		StackPane timer = timerGUI.getTimer();
            
        		/* Data Chart */
        		DataChartGUI dataGUI = new DataChartGUI();
        		StackPane data = dataGUI.dataChart();

        		/* Vertical layout pane for timer and data */
        		 VBox leftWidgets = new VBox();
        		 leftWidgets.getChildren().addAll(timer, data);    
        		 leftWidgets.setSpacing(20);
        		 leftWidgets.setPadding(new Insets(20,20,20,20));
        	
        		/*Highlights */
        		 StackPane highlights = highlights();
        		 
        		/*Tasks Table */
           		 VBox taskTable =createTable();

           		 // stack task and highlights
           		 VBox rightWidgets = new VBox();
           		 rightWidgets.getChildren().addAll(highlights, taskTable);
           		 rightWidgets.setSpacing(20);
           		 rightWidgets.setPadding(new Insets(20,20,20,20));
           		 
        		 /* Set up Page Layout */
        		 BorderPane rootHomePage = new BorderPane();
        		 rootHomePage.setTop(toolBarContent);
        		 rootHomePage.setLeft(leftWidgets);
        		 rootHomePage.setRight(rightWidgets);
        		
        		 Scene sceneHomePage = new Scene(rootHomePage, 1280, 720);
        		 URL url = this.getClass().getResource("application.css");
        		 String css = url.toExternalForm();
        		 sceneHomePage.getStylesheets().add(css);
        		 
        		 stage.setTitle("Login Page");
        		 sceneHomePage.setFill(Color.TRANSPARENT);
        		 stage.setWidth(1280);
        		 stage.setHeight(850);
        		 stage.setScene(sceneHomePage);
        		 stage.show();
            	
            	
//            	 StackPane root2 = new StackPane();
//                 Label label = new Label("Your are now signed in");
//                 root2.getChildren().add(label);
//                 Scene secondScene = new Scene(root2, 500,500);
//                 Stage secondStage = new Stage();
//                 secondStage.setScene(secondScene);
//                 secondStage.setTitle("Second Form");
//                 secondStage.show();
//                 stage.close(); // close the first stage (Window)	   
            }else {
            		/* show error if login failed*/
            		if(flag == 0) {
            			String error = "Error, incorrect username or password please try again.";
            			errorMSG(error);
            		}
            }
             
            
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
    	    passwordField2 = new TextField("Re-Enter Password");
    	    passwordField2.setPrefWidth(200);
    	    passwordField2.setMaxWidth(200);
    	    
    	    /*create the buttons for sing up page*/
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
        	   /* calls method that creates accounts while also making sure account is brand new and passwords entry match*/
			succesfull=StoreAccountInfo(MouseEvent.MOUSE_CLICKED,file,true);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
           
           /*If account is successfully made then it opens the main menu*/
           if (succesfull == true) {
        	   StackPane root2 = new StackPane();
               Label label = new Label("Your are now signed in");
               root2.getChildren().add(label);
               Scene secondScene = new Scene(root2, 500,500);
               Stage secondStage4 = new Stage();
               secondStage4.setScene(secondScene);
               secondStage4.setTitle("Second Form");
               secondStage4.show();
               secondStage.close(); // close the first stage (Window)   	   
           }else {
        	   
        	   /*displays the error that occured when making an account*/
               if(flag == 3) {
            	   String error = "Error, this account already exist";
               	errorMSG(error);
               }else if (flag == 1) {
   					String error = "Error,your password entries did not match. Please try again.";
   					errorMSG(error);
       		   }else if (flag == 2) {
       			   String error = "Error, that user username already exist. Please sign in or use a new username.";
       			   errorMSG(error);
       		}
           }
           
            });
        
        });    
	   
	    cancelButton(registerBoxPane,registerBoxPane2,loginVBox,vbox1,hbox,rect1,fontFredoka);

	}

	
	public boolean StoreAccountInfo(EventType<MouseEvent> mouseClicked, String file, boolean create) throws IOException {
		
		StringBuilder username = new StringBuilder();
		StringBuilder password = new StringBuilder();
		StringBuilder reEnterPass = new StringBuilder();
		boolean isItDuplicate =false;
		
		/*gets input from textbox and stores them in username and password*/
		username.append(userNameField.getText().toString());
		password.append(passwordField.getText().toString());
		
		/*if passwordField is not null then the user must be signing up which the checks to see if passwords match*/
		if(passwordField2 != null) {
			
			reEnterPass.append(passwordField2.getText().toString());
			
			if (!password.toString().contentEquals(reEnterPass)) {
				flag = 1;
				return false;
			}
				
		}
		
		/*checks if username already exist*/
		if(checkIfUsernameDup(file,username) && create == true) {
			flag=2;
			return false;
		}
		
		/*checks if an account with exact same name and password exist*/
		if(create == true) {
		isItDuplicate = readFile(file,username,password);
		if(isItDuplicate == true) {
			flag = 0;
			return false;
		}else{
		/*no duplicate account is detected so it beings saving the information*/	
		FileWriter write = new FileWriter(file,true);
		write.write(username.toString());
		write.write(" ");
		write.write(password.toString());
		write.write("\n");
		write.close();
		return true;
		}
		}else{
			/*the user was not creating a class, instead he was signing in so we check if login credentials match and exist*/
			return readFile(file,username,password);
		}
	}
	public boolean readFile(String file,StringBuilder username,StringBuilder password) 
	{    
		String testUser,Pass = null;
		
		try {
		
			FileReader Accountfile = new FileReader(file);
		    BufferedReader buff = new BufferedReader(Accountfile);
		    boolean eof = false;
		
		while(!eof) //does this while its not the end of file
		{
			
			String line = buff.readLine(); // gets a line from the file

			if(line==null)
				eof = true; // if the line is null then its the end of the file 
			
			if(line != null)
			{
				StringTokenizer st = new StringTokenizer(line);	// converts line into tokens 
				
					while(st.hasMoreTokens()) // while their is tokens it will continue 
					{
						testUser = st.nextToken(); // gets token from converted line and puts it in testUser
						if(st.hasMoreTokens())
							Pass = st.nextToken();
						
						/* checks to see if login information matches an account in the file*/
						if(testUser.contentEquals(username) && Pass.contentEquals(password)) {
							flag = 3;
							buff.close();
							return true;
						}
					}			
			}		
}
		buff.close();
		}catch(IOException e){
			
			System.out.printf("Error -- %s ", e.toString()); // prints out exception if their is one 
		}
    
		return false;
	   }

	public boolean checkIfUsernameDup(String file,StringBuilder username) 
	{    
		String testUser;
		
		try {
		
			FileReader Accountfile = new FileReader(file);
		    BufferedReader buff = new BufferedReader(Accountfile);
		    boolean eof = false;
		
		while(!eof) //does this while its not the end of file
		{
			
			String line = buff.readLine(); // gets a line from the file

			if(line==null)
				eof = true; // if the line is null then its the end of the file 
			
			if(line != null)
			{
				StringTokenizer st = new StringTokenizer(line);	// converts line into tokens 
				
					while(st.hasMoreTokens()) // while their is tokens it will continue 
					{
						testUser = st.nextToken(); // gets token from converted line and puts it in testUser
						
						/*checks if username already exist*/
						if(testUser.contentEquals(username)) {
							buff.close();
							return true;
						}
					}			
			}		
}
		buff.close();
		}catch(IOException e){
			
			System.out.printf("Error -- %s ", e.toString()); // prints out exception if their is one 
		}
    
		return false;
	   }

    public void cancelButton(StackPane registerBoxPane,StackPane registerBoxPane2,VBox loginVBox,VBox vbox1,HBox hbox,Rectangle rect1,Font fontFredoka ) {
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
    public void errorMSG(String msg) {
    	
    	StackPane root10 = new StackPane();
        Label label = new Label(msg);
        
        /*create background*/
        Rectangle background = new Rectangle(mainRectWidth, mainRectHeight);
        background.setFill(Color.web("#DEE7EC",1));
        background.setArcHeight(40.0);
        background.setArcWidth(40.0);
       
        /*create button*/
        Button okButton = new Button("Ok");
        
        okButton.setStyle("-fx-background-color: #AB81CD; -fx-background-radius: 15px; -fx-text-fill: #ffffff");
	    okButton.setCursor(Cursor.HAND);
	    
	    StackPane stack = new StackPane();
	    VBox layout = new VBox();
	    BorderPane center= new BorderPane();
	    
	    /*align button and label in the correct spot*/
	    layout.getChildren().addAll(label,okButton);
	    layout.setSpacing(50);
	    layout.setAlignment(Pos.CENTER);
	    
	    /*put background,button, and label layout together*/
	    stack.getChildren().addAll(background,layout);
	    center.setCenter(stack);

	    /*create scene and stage*/
        root10.getChildren().add(center);
        Scene secondScene = new Scene(root10,600, 600);
        Stage secondStage5 = new Stage();
        secondStage5.setScene(secondScene); 
        secondStage5.setTitle("Second Form");
        secondStage5.show();
     	 
        /*close error pop up page*/
        okButton.setOnAction(e3->{  
        	
            secondStage5.close();
        });       	   
    	
    }
    
    
	public VBox createTable() {
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

	public StackPane highlights() {
		//background :
		Rectangle background = new Rectangle(500,200);
		background.setArcHeight(40.0);
		background.setArcWidth(40.0);
		background.setFill(Color.web("#DEE7EC",1));
		
		//labels :
		Label hLabel = new Label("Highlights: ");
		hLabel.setFont(Font.font("Arial",FontWeight.EXTRA_BOLD, 20));
		Label rLabel = new Label("Reminders: ");
		rLabel.setFont(Font.font("Arial",FontWeight.EXTRA_BOLD, 20));

		//task completion
		int taskCompleted = 0;
		
		for (int i = 0 ; i < tableData.size(); i++) {
			if (tableData.get(i).getCompletedStatus() == true) {
				taskCompleted++;
			}
		}
		
		int taskUnfinished = tableData.size() - taskCompleted;

		//update text :
		Rectangle hRectangle = new Rectangle(250,50);
		hRectangle.setArcHeight(40.0);
		hRectangle.setArcWidth(40.0);
		hRectangle.setFill(Color.WHITE);
		
		Rectangle rRectangle = new Rectangle(250,50);
		rRectangle.setArcHeight(40.0);
		rRectangle.setArcWidth(40.0);
		rRectangle.setFill(Color.WHITE);
		
		Text hMessage = new Text();
		hMessage.setText("You've completed " + taskCompleted + " tasks.");
		
		Text rMessage = new Text();
		rMessage.setText("You have " + taskUnfinished + " uncompleted tasks.");
		
		// organize GUI elements 
		StackPane hStackPane = new StackPane();
		hStackPane.getChildren().addAll(hRectangle, hMessage);
		
		StackPane rStackPane = new StackPane(); 
		rStackPane.getChildren().addAll(rRectangle, rMessage);
		
		HBox highlightHBox = new HBox();
		highlightHBox.getChildren().addAll(hLabel, hStackPane);
		
		HBox remindersHBox = new HBox();
		remindersHBox.getChildren().addAll(rLabel, rStackPane);
		
		VBox vbox = new VBox();
		vbox.getChildren().addAll(highlightHBox, remindersHBox);
		vbox.setSpacing(30);
		//vbox.setStyle("-fx-background-color:blue;");
		vbox.setPadding(new Insets(60,60,50,90));
		
		StackPane highlightSP = new StackPane();
	    StackPane.setMargin(vbox, new Insets(8,8,8,8));

		highlightSP.getChildren().addAll(background, vbox);
		
		return highlightSP; 
	}
	
    
	public static void main(String[] args) {
		launch(args);
	}
}
