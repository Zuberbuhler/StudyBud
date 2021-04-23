package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Timer {
	private int mins = 0, secs = 0, millis = 0;
	private boolean sos;
	private Timeline timeline;
	private Text stopwatchTime;
	
	public Timer() {
		sos = true; 
		stopwatchTime = new Text("00:00");
		
		timeline = new Timeline(new KeyFrame(Duration.millis(1),
								new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				change(stopwatchTime);
			}
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.setAutoReverse(false);
	}
	
	public void change(Text text) {
		if(millis == 1000) {
			secs++;
			millis = 0;
		}
		if(secs == 60) {
			mins++;
			secs = 0;
		}
		text.setText((((mins/10) == 0) ? "0" : "") + mins + ":"
		 + (((secs/10) == 0) ? "0" : "") + secs);
		
		millis++;
	}
	public void playTimeline() {
		timeline.play();
	}
	
	public void pauseTimeline() {
		timeline.pause();
	}
	
	public boolean getSos() {
		return sos;
	}
	
	public void setSos(boolean value) {
		sos = value; 
	}
	
	public Timeline getTimeline() {
		return timeline; 
	}
	public void setMins(int value) {
		mins = value; 
	}
	public void setTimerText(String value) {
		stopwatchTime.setText(value);
	}
	
	public void setSecs(int value) {
		secs = value; 
	}
	public void setMillis(int value) {
		millis = value;
	}
	public Text getText() {
		return stopwatchTime; 
	}
	
	
}