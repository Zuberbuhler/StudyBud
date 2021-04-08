package application;

public class Timer {
	private int currentTime;
	private int hours; 
	private int minutes;
	private int seconds;
	private int elapsedTime;
	private boolean timerRunning;
	
	public Timer() {
		currentTime = 0; // in seconds
		hours = 0;
		minutes = 0;
		seconds = 0;
		elapsedTime = 0;
		timerRunning = false; 
	}
	
	public int getMinutes() {
		return minutes;
	}
	
	public int getSeconds() {
		return seconds; 
	}
	
	public void startTimer() {
		timerRunning = true; 
		
		// start timer and update elapsedTime and 
		
		hours = (elapsedTime / 3600000);
		minutes = (elapsedTime / 60000) % 60;
		seconds = (elapsedTime / 1000) % 60;
	}
	
	public void addTime(int hours, int minutes, int seconds) {
		currentTime += (hours*360) + (minutes*60) + seconds;
	}
	
	public void stopTime() {
		timerRunning = false;
	}
	
	public void restartTimer() {
		timerRunning = false;
		startTime = 0; 
		elapsedTime = 0;
		hours = 0;
		minutes = 0;
		seconds = 0;
	}
}
