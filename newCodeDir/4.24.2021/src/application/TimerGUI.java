package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TimerGUI {
	private Font font;
	private Rectangle timerBackground;
	private Label timerLbl; 
	private Button sButton;
	private Button rButton;
	private VBox buttonGroup;
	private StackPane stopWatchLayout; 
	private StackPane timer;
	
	public TimerGUI() {
		font = new Font("Arial", 20);
		//background 
	    timerBackground = new Rectangle(400, 300);
	    timerBackground.setArcHeight(40.0);
	    timerBackground.setArcWidth(40.0);
	    timerBackground.setFill(Color.web("#DEE7EC",1)); 
	    
	    //labels 
	    timerLbl = new Label("Timer");
	    timerLbl.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 20));
	    
	    //buttons
	    sButton = new Button("Start");
		sButton.setFont(font);
	    sButton.setStyle("-fx-background-color: thistle;");
	    rButton = new Button("Reset");
	    rButton.setFont(font);
	    rButton.setStyle("-fx-background-color: thistle;");
	    
	    buttonGroup = new VBox();
	    buttonGroup.getChildren().addAll(sButton, rButton);
	    buttonGroup.setSpacing(20);
	    buttonGroup.setPadding(new Insets(20,20,20,20)); 
	    
	    //stopwatch 
	    stopWatchLayout = new StackPane();
	    
	    //timer
	    timer = new StackPane();
	}
	
	private void buildStopWatch() {
		Circle stopWatchCircle = new Circle();
	    stopWatchCircle.setRadius(100);
	    stopWatchCircle.setFill(Color.WHITE);
	    
	    Timer stopWatch = new Timer(); 
	    
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
	}

	private void buildTimer() {
		// Timer Layout 
	    BorderPane timerLayout = new BorderPane();
	    timerLayout.setTop(timerLbl);
	    BorderPane.setAlignment(timerLbl, Pos.CENTER);
	    timerLayout.setLeft(buttonGroup);
	    timerLayout.setCenter(stopWatchLayout);
	    timer.getChildren().addAll(timerBackground, timerLayout);
	}
	
	public StackPane getTimer() {
		buildStopWatch();
		buildTimer();
	    return timer;
	}
	
}
