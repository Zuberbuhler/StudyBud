package application;

import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
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
import javafx.scene.layout.GridPane;
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
    
	@SuppressWarnings({ "rawtypes", "unchecked" })
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
	    addTaskName.setMinWidth(100);
	    addTaskName.setFont(Font.font("Arial", FontWeight.BOLD, 13));
	    
	    TextField addDueDate = new TextField();
	    addDueDate.setMaxWidth(column2.getPrefWidth());
	    addDueDate.setPromptText("Due Date");
	    addDueDate.setFont(Font.font("Arial", FontWeight.BOLD, 13));
	    
	    TextField addCourse = new TextField();
	    addCourse.setPromptText("Add Course");
	    addCourse.setMinWidth(100);
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
	    addButton.setCursor(Cursor.HAND);
	    
	    Button deleteButton = new Button("Delete");
	    deleteButton.setOnAction(e -> tableView.getItems().removeAll(tableView.getSelectionModel().getSelectedItems()));
	    deleteButton.setMinWidth(100);
	    deleteButton.setMaxHeight(10);
	    deleteButton.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 14));
	    deleteButton.setStyle("-fx-background-color: thistle;");
	    deleteButton.setCursor(Cursor.HAND);
	    
	    //ObservableList<Task> selectedItems = selectionModel.getSelectedItems();
	    //tableView.getItems().removeAll(selectedItems);
	    	    
	    HBox hb = new HBox();
	    hb.getChildren().addAll(addTaskName, addDueDate, addCourse, addButton, deleteButton);
	    hb.setSpacing(5);
	    	    
	    VBox vbox = new VBox(hb,tableView);
	    vbox.setSpacing(5);
	    return vbox; 
  }

	public StackPane highlights() {

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
		
		GridPane gridPane = new GridPane();
		
		//Setting the Grid alignment 
	    gridPane.setAlignment(Pos.CENTER); 
	    gridPane.setHgap(30);
	    gridPane.setVgap(20);
	    gridPane.setPadding(new Insets(30, 10, 10, 10));
	      
	    //Arranging all the nodes in the grid 
	    gridPane.add(hLabel,0,0); 
	    gridPane.add(hStackPane,0,1);
	    gridPane.add(rLabel,1,0);
	    gridPane.add(rStackPane,1,1);
	    		
		StackPane highlightSP = new StackPane();

		highlightSP.getChildren().addAll(gridPane);
		
	  
		return highlightSP; 
	}
}
