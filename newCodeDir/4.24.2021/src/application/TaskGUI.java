package application;

import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class TaskGUI {
    private final ObservableList<Task> tableData;
    
    public TaskGUI(ObservableList<Task> table) {
    	tableData = table;
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
		vbox.setPadding(new Insets(60,60,50,90));
		
		StackPane highlightSP = new StackPane();
	    StackPane.setMargin(vbox, new Insets(8,8,8,8));

		highlightSP.getChildren().addAll(background, vbox);
		
		return highlightSP; 
	}
}
