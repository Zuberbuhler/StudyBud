package application;

public class Timer {
	// Returns if the timer is running
	private boolean running;
	// Returns if the timer is paused
	private boolean paused;
	// The start time in microseconds
	private long startTime;
	// The time for the current paused time
	private long pausedTime;
	// The end time
	private long endTime;
	
	// Constructor for Timer
	public Timer()
	{
		// Initialize time values to 0
		pausedTime = 0;
		startTime = 0;
		endTime = 0;
	}
	
	// Determines if the timer is running
	public boolean isRunning() {
		return running;
	}
	
	// Determines if the timer is paused
	public boolean isPaused() {
		return paused;
	}
	
	// Starts the timer
	public void start() {
		startTime = System.nanoTime();
		running = true;
		paused = false;
		// Set paused time to invalid value
		pausedTime = -1;
	}
	
	// Stops the timer and returns the time elapsed since start
	public long stop() {
		// If timer is not running return invalid value
		if(running == false)
			return -1;
		
		// If timer is paused then return the elapsed time
		else if(paused)
		{
			running = false;
			paused = false;
			return pausedTime - startTime;
		}
		
		// If the timer is running and has not been paused set the end time and then return the elapsed time
		else {
			endTime = System.nanoTime();
			running = false;
			return endTime - startTime;
		}
	}
	
	// Pauses the timer and returns the time elapsed so far
	public long pause()
	{
		// If the timer is already stopped invalid
		if(running == false)
			return -1;
		
		// If timer is now paused return the elapsed time
		if(paused)
		{
			return pausedTime - startTime;
		}
		
		// Else if the timer is currently running
		else {
			pausedTime = System.nanoTime();
			paused = true;
			return(pausedTime - startTime);
		}
	}
	
	// Resumes the timer from paused state
	public void resume()
	{
		// If paused but not stopped
		if(paused && running)
		{
			startTime = System.nanoTime() - (pausedTime - startTime);
			paused = false;
		}
	}
	
	// Returns the elapsed time
	public long elapsed()
	{
		if(running) 
		{
			if(paused)
				return pausedTime-startTime;
			return(System.nanoTime() - startTime);
		}
		else
			return endTime-startTime;
	}
	
	// Returns the number of seconds the timer has elapsed
	public String toString()
	{
		long enlapsed = elapsed();
		return((double) enlapsed / 1000000000.0) + " Seconds";
	}
	
	public String convertNanoseconds()
	{
		long nanoSeconds = elapsed();
		long seconds = (long) (nanoSeconds / 1e9);
		int minutes = (int) Math.floor((seconds / 60));
		
		String time = minutes+":"+seconds;
		return time;
		
	}
	
	
	
}