package application;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class DateTester {
	
	
	
	
	public static void main(String [] args)
	{
		//Gets Date
		//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/uuuu");
		LocalDate localDate = LocalDate.now();
		String dateStr = localDate.toString();
		
		//Gets Day of Week
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
		String dayOfWeekStr = sdf.format(now).toString();
		
		String dateAndDay = dateStr + " " + dayOfWeekStr;
		
		System.out.println(dateAndDay + " 2.5");
		
	}
	
}
