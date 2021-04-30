package permanentStorage;

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

public class fileMod {
	
	static void print2DArray(String [][]arr, int rows, int cols)
	{
		System.out.println("Printing Credentials: ");
		for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
            	if(j == 1) {
            		System.out.println(", password: arr[" + i + "][" + j + "] = "
                            + arr[i][j]);
            	}
            	else
            	{
            		System.out.print("username: arr[" + i + "][" + j + "] = "
                            + arr[i][j]);
            	}
                
	}
	static boolean hasSpaces(String str) {
		for (int i = 0; i < str.length(); i++) 
		{
			if (str.charAt(i) == ' ') 
			{
				return true;
			}
		}
		return false;
	}
	static int countRows() {
		int rows = 0;
		

	    try {
	      // create a new file object
	      File file = new File("src/permanentStorage/credentials.txt");

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
	static String[][] readCredentialsFile()
	{
		//count how many credentials(rows) are in the array
		int rows = countRows();
		String [][]arr = new String[rows][2];
		int i = 0, j = 0;
		Scanner sc2 = null;
	    try {
	        sc2 = new Scanner(new File("src/permanentStorage/credentials.txt"));
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
	    return arr;
	}
	static void createCredentialsFile()
	{
		try {
			// File fileDirObj = new File("test");
			File myObj = new File("src/permanentStorage/credentials.txt");
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
	static void createUserInfoFile(String username)
	{
		try {
			// File fileDirObj = new File("test");
			File myObj = new File("src/permanentStorage/" + username + ".txt");
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
	static boolean isDuplicateUsername(String username)
	{
		int rows = countRows();
		String[][] tmpArray = readCredentialsFile();
		
		for(int i = 0; i < rows; i++) {
			if(tmpArray[i][0].equals(username))
			{
				return true;
			}
		}
		return false;
	}
	static boolean isValidCredential(String username, String password)
	{
		int rows = countRows();
		String[][] tmpArray = readCredentialsFile();
		
		for(int i = 0; i < rows; i++) {
			if(tmpArray[i][0].equals(username))
			{
				if(tmpArray[i][1].equals(password))
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
	static boolean isFileEmpty(String filenameWithoutType)
	{
		File newFile = new File("src/permanentStorage/" + filenameWithoutType + ".txt");

	    if (newFile.length() == 0) {
	      System.out.println("File is empty ...");
	      return true;
	    } else {
	      System.out.println("File is not empty ...");
	      return false;
	    }
	}
	static void printFileContents(String filenameWithoutType) throws IOException
	{
		try (BufferedReader br = new BufferedReader(new FileReader("src/permanentStorage/" + filenameWithoutType + ".txt"))) {
			   String line;
			   while ((line = br.readLine()) != null) {
			       System.out.println(line);
			   }
			}
		
	}

	public static void main(String[] args) {
		createCredentialsFile();
		String [][]credentialsArray;
		Scanner sc = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		Scanner sc3;
		String username = "", password = "";
		int rows = countRows(), cols = 2;
		if(rows == 0)
		{
			System.out.println("Empty file");
		}
		else
		{
			credentialsArray = readCredentialsFile();
			print2DArray(credentialsArray, rows, cols);
		}

		int option = -1; 
		boolean quit = false;

		do {
			System.out.printf(
								"\n* * * * * * * * * * * * * *\n" + 
								"*        Menu             *\n"	+ 
								"* [1] Sign in             *\n" + 
								"* [2] Create New Account  *\n" + 
								"* [3] Quit                *\n" + 
								"* * * * * * * * * * * * * *\n");
			
			 // System.in is a standard input stream
			System.out.print("Please enter an option (Integer Only): ");
			try {
				option = sc.nextInt(); // reads int
				sc.nextLine();//this will take the newline
				
			} catch (Exception e) {
				System.out.println("You did not enter an integer!");
				option = -1;
				sc.nextLine();
			}
			
			switch(option) {
            case 1 :
            	System.out.println("\nCase1\n"); 
            	System.out.print("Enter Username: ");
            	username = sc.nextLine();
            	System.out.print("Enter Password: ");
            	password = sc.nextLine();
            	if(isValidCredential(username, password))
            	{
					System.out.println("\tValid Credentials");
					createUserInfoFile(username);
					
					int option2;
					boolean quit2 = false;
					sc3 = new Scanner(System.in);
					do {
						if(!isFileEmpty(username)) {
							System.out.println("\tPrinting permanent storage:\n" +
												"***************************");
							try {
								printFileContents(username);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							System.out.println("***************************\n"
									+ "\tEnd of permanent storage for " + username);
						}
						System.out.printf(  "\n\t* * * * * * * * * * * * * *\n" + 
											"\t   Signed In as " + username + "\n" + 
											"\n\t* [1] Enter Data          *\n" + 
											  "\t* [2] Logout              *\n" + 
											  "\t* * * * * * * * * * * * * *\n");

						// System.in is a standard input stream
						System.out.print("\tPlease enter an option (Integer Only): ");
						try {
							option2 = sc3.nextInt(); // reads int
							sc3.nextLine();// this will take the newline

						} catch (Exception e) {
							System.out.println("\tYou did not enter an integer!");
							option2 = -1;
							sc3.nextLine();
						}

						switch (option2) {
						case 1:
							System.out.println("\tPlease Enter 1 line of data: ");
							//writeMultipleLinesToFile(username);
							String tmpStr = sc3.nextLine();
							 try {
							      FileWriter myWriter = new FileWriter("src/permanentStorage/" + username + ".txt");
							      myWriter.write(tmpStr);
							      myWriter.close();
							      System.out.println("Successfully wrote to the file.");
							    } catch (IOException e) {
							      System.out.println("An error occurred.");
							      e.printStackTrace();
							    }
							break; // optional
						case 2:
							// Statements
							System.out.println("\nSigning Out!\n");
							quit2 = true;
							break; // optional
						default: // Optional
							System.out.println("\nTry again!\n");
							// Statements
						}
						
						//sc3.close();
					} while (!quit2);
				} 
            	else
            	{
            		System.out.println("Invalid Credentials");
            	}
            	
            	break; // optional
            case 2 :
            	System.out.println("\nCase2\n");
            	String password1, password2;
            	sc2 = new Scanner(System.in);
            	boolean flag1 = true, flag2 = true;
            	while(flag1)
            	{
            		System.out.print("Please enter a username ( < 16 Chars long): ");
                	//sc2 = new Scanner(System.in);

                	username = sc2.nextLine();              //reads string 
                	
                	System.out.println("str.length() = " + username.length());
                	if((username.length() == 0) || hasSpaces(username) || (username.length() > 16) || isDuplicateUsername(username) )
                	{
                		if(isDuplicateUsername(username))
                		{
                			System.out.println("Username already taken!");
                		}
                		System.out.println("Unapproved username: " + username);    
                		System.out.println(
                			"Error: Please enter string between 1 and 16 length and without spaces!\n");    
                	}
                	else
                	{
                		System.out.println("Approved username: " + username);    
                    	
                    	flag1 = false;
                    
                	}
                	
                	
            	}
            	
            	
            	while(flag2)
            	{
            		System.out.print("Please enter a password ( < 16 Chars long): ");
                	//sc2 = new Scanner(System.in);

                	password1= sc2.nextLine();              //reads string 
                	System.out.println("password1: _" + password1 + "_");  
                	System.out.println("str.length() = " + password1.length());
                	if((password1.length() == 0) || hasSpaces(password1) || (password1.length() > 16))
                	{
                		System.out.println("Unapproved password: " + password1);    
                		System.out.println(
                			"Error: Please enter string between 1 and 16 length and without spaces!\n");    
                	}
                	else
                	{
                		System.out.print("\nPlease enter the same password: ");
                    	sc2 = new Scanner(System.in);

                    	password2= sc2.nextLine();  
                    	System.out.println("password2: _" + password2 + "_");  
                    	
                    	boolean passwordsMatch = password1.equals(password2);
                    	System.out.println("String equals: " + passwordsMatch);
                    	if(!passwordsMatch)
                    	{
                    		System.out.println("\nPasswords dont match!");
                    		flag2 = true;
                    	}
                    	else
                    	{
                    		System.out.println("\nApproved password: " + password1);    
                    		flag2 = false;
                    	}
                	}
                	if(flag2 == false)
                	{
                    	String my_file_name = "src/permanentStorage/credentials.txt";
                    	Writer output;
                    	try {
    						output = new BufferedWriter(new FileWriter(my_file_name, true));
    						if(countRows() != 0) 
    						{
    							output.append("\n" + username + " " + password1);
    						    output.close();
    						}
    						else
    						{
    							output.append(username + " " + password1);
    						    output.close();
    						}
    						
    					} catch (IOException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
                	}
                	
            	}
            	
            	credentialsArray = readCredentialsFile();
            	rows = countRows();
    			print2DArray(credentialsArray, rows, cols);
    			//sc2.close();
				break;
        	case 3 :
            // Statements
              	System.out.println("\nGoodbye!\n");
              	quit = true;
           		break; // optional
           default : // Optional
              System.out.println("\nTry again!\n");
              // Statements
         }
			

		} while (!quit);
		sc.close();
		sc2.close();
		
	}
}
