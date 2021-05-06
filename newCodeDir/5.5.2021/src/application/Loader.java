package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File; // Import the File class
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException; // Import the IOException class to handle errors
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.Arrays;
import java.util.Scanner;

public class Loader {

	final private String credentialFile= "src/application/credentials.txt";
	private String usernameFile;
	private String [][]credentialsArray;
	private Task[] taskArray; 
	private int taskArraySize; //initial set when reading username.txt
	
	
	public Loader()
	{
		taskArraySize = 0;
	}
	
	private Task buildTask(String taskName, String dueDate, String courseName)
	{
		Task resTask = new Task(taskName, dueDate, courseName);
		return resTask;
	}
	
	private void addTask(Task newTask) //increase taskArraySize By 1
	{
		Task[] tmpTaskArray = new Task[++taskArraySize];
		int i = 0;
		for(; i < taskArraySize - 1; i++)
		{
			tmpTaskArray[i] = taskArray[i];
		}
		tmpTaskArray[i] = newTask;
		taskArray = tmpTaskArray;
	}
	
	//removes targetTask and decrease size of taskArray
	private void removeTask(Task targetTask)
	{
		int removalIndex = findTask(targetTask);
		if(removalIndex == -1)
		{
			System.out.println("No task to remove");
			return;
		}
		
		Task[] tmpTaskArray = new Task[taskArraySize - 1];
		//we need to make a taskArray that 
		//includes everything except targetTask
		for(int i = 0, j = 0; i < taskArraySize; i++, j++)
		{
			if(i == removalIndex)
			{
				j--;
			}
			else 
			{
				tmpTaskArray[j] = taskArray[i];
			}
		}
		taskArray = tmpTaskArray;
		taskArraySize--;
	}
	
	//returns index in taskArray of targetTask
	private int findTask(Task targetTask)
	{
		for(int i = 0; i < taskArraySize; i++)
		{
			if(taskArray[i].isSameTask(targetTask))
			{
				return i;
			}
		}
		
		return -1;
	}
	
	private void printTaskArray()
	{
		for(int i = 0; i < taskArraySize; i++)
		{
			taskArray[i].printTask();
		}
		if(taskArraySize == 0) {
			System.out.println("Task Array is Empty");
		}
	}
	
	private void setUsername(String username)
	{
		usernameFile = getUsernameFile(username);
	}
	
	
	private String getUsernameFile(String username)
	{
		return "src/application/" + username + ".txt";
	}
	
	private boolean hasSpaces(String str) 
	{
		for (int i = 0; i < str.length(); i++) 
		{
			if (str.charAt(i) == ' ') 
			{
				return true;
			}
		}
		return false;
	}
	
	private void createCredentialsFile()
	{
		try {
			// File fileDirObj = new File("test");
			File myObj = new File(credentialFile);
//			if(fileDirObj.mkdir())
//			{
//				System.out.println("Directory create: " + fileDirObj.getName());
//
//			}
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	private void readCredentialsFile()
	{
		//count how many credentials(rows) are in the array
		int rows = countRows();
		String [][]arr = new String[rows][2];
		int i = 0, j = 0;
		Scanner sc2 = null;
	    try {
	        sc2 = new Scanner(new File(credentialFile));
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();  
	    }
	    while (sc2.hasNextLine()) {
	            Scanner s2 = new Scanner(sc2.nextLine());
	        while (s2.hasNext()) {
	            arr[i][j] = s2.next();
	            if(i < rows) {
	            	if(++j < 2)
	            	{
	            	}
	            	else 
	            	{
	            		i++;
	            		j = 0;
	            	}
	            }
	        }
	    }	
	    credentialsArray = arr;
	}
	
	private void writeCredentialsFile(String username, String password)
	{
		Writer output;
    	try {
			output = new BufferedWriter(new FileWriter(credentialFile, true));
			if(countRows() != 0) 
			{
				output.append("\n" + username + " " + password);
			    output.close();
			}
			else
			{
				output.append(username + " " + password);
			    output.close();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private int countRows() {
		int rows = 0;
		

	    try {
	      // create a new file object
	      File file = new File(credentialFile);

	      // create an object of Scanner
	      // associated with the file
	      Scanner sc = new Scanner(file);

	      // read each line and
	      // count number of lines
	      while(sc.hasNextLine()) {
	        sc.nextLine();
	        rows++;
	      }
	      //System.out.println("Total Number of Lines: " + rows);

	      // close scanner
	      sc.close();
	    } catch (Exception e) {
	      e.getStackTrace();
	    }
		
		return rows;
	}
	
	private void createUserInfoFile(String username)
	{
		try {
			// File fileDirObj = new File("test");
			File myObj = new File(usernameFile);
//			if(fileDirObj.mkdir())
//			{
//				System.out.println("Directory create: " + fileDirObj.getName());
//
//			}
			if (myObj.createNewFile()) {
				System.out.println("\tFile created: " + myObj.getName());
			} else {
				System.out.println("\tFile already exists.");
			}
		} catch (IOException e) {
			System.out.println("\tAn error occurred.");
			e.printStackTrace();
		}
	}
	
	private void printCredentialsArray(int rows, int cols)
	{
		System.out.println("Printing Credentials: ");
		for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
            	if(j == 1) {
            		System.out.println(", password: arr[" + i + "][" + j + "] = "
                            + credentialsArray[i][j]);
            	}
            	else
            	{
            		System.out.print("username: arr[" + i + "][" + j + "] = "
                            + credentialsArray[i][j]);
            	}
	}
	
	private boolean isDuplicateUsername(String username)
	{
		int rows = countRows();
		readCredentialsFile();
		
		for(int i = 0; i < rows; i++) {
			if(credentialsArray[i][0].equals(username))
			{
				return true;
			}
		}
		return false;
	}
	
	private boolean isValidCredential(String username, String password)
	{
		int rows = countRows();
		readCredentialsFile();
		
		for(int i = 0; i < rows; i++) {
			if(credentialsArray[i][0].equals(username))
			{
				if(credentialsArray[i][1].equals(password))
				{
					return true;
				}
				else
				{
					return false;
				}
			}
		}
		
		return false;
	}
	
	private boolean isFileEmpty(String filenameWithoutType)
	{
		File newFile = new File("src/application/" + filenameWithoutType + ".txt");

	    if (newFile.length() == 0) {
	      System.out.println("File is empty ...");
	      return true;
	    } else {
	      System.out.println("File is not empty ...");
	      return false;
	    }
	}
	
	private void printFileContents(String filenameWithoutType) throws IOException
	{
		try (BufferedReader br = new BufferedReader(new FileReader("src/permanentStorage/" + filenameWithoutType + ".txt"))) {
			   String line;
			    while ((line = br.readLine()) != null) {
			       System.out.println(line);
			   }
			}
	}
	
	public static void main(String args[])
	{
		Loader myL = new Loader();
		
		myL.addTask(myL.buildTask("testName1", "01-01", "CS151"));
		
		myL.printTaskArray();
		
		myL.addTask(myL.buildTask("testName2", "02-02", "CS151"));
		
		myL.printTaskArray();
		
		myL.removeTask(myL.buildTask("testName1", "01-01", "CS151"));
		
		myL.printTaskArray();
		
		myL.removeTask(myL.buildTask("testName2", "02-02", "CS151"));
		
		myL.printTaskArray();
		
		myL.createCredentialsFile();
		
		myL.writeCredentialsFile("Matthew", "Z");
		
		myL.readCredentialsFile();
		
		myL.printCredentialsArray(myL.countRows(), 2);
		
	}
}
