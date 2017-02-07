package com.advanz.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @author Anil Dhakad
 *
 */
public class DataUtility {
	public static void main(String[] args)throws Exception {
		DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
    	String s = df.format(new Date());
    	System.out.println(s);
    	 Date date = null;
    	    Date formatteddate = null;
    	    DateFormat df1 = new SimpleDateFormat("dd-MMM-yyyy");
    	    try{
    	        date = df1.parse(s);
    	       
    	    }
    	    catch ( Exception ex ){
    	       
    	    }
    	
	}
	
	
public static String getDate(Date date){
		
		String s2="";
		if(date!=null){
    	try{
    		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	    	String s = df.format(date);
        	Date d = (new SimpleDateFormat("yyyy-MM-dd")).parse(s);
        	 s2 = (new SimpleDateFormat("yyyy-MM-dd")).format(d);
        	} catch(Exception e) {
        	  e.printStackTrace();
        	}
		}
		return s2;
	}
public enum Month
{
	JANUARY, FEBRUARY, MARCH, APRIL,
	MAY, JUNE, JULY,AUGUST,SEPTEMBER,OCTOBER,NOVEMBER,DECEMBER;
}

public static  Integer getMonthNumber(String str){
	 Integer month=0;
	 
	  switch (Month.valueOf(str))
	  {
	      case JANUARY:  month=0;
	       break;
	       
	      case FEBRUARY: month=1;
	       break;
	       
	      case MARCH: month=2;
	       break;
	       
	      case APRIL: month=3;
	       break;
	       
	      case MAY: month=4;
	       break;
	       
	      case JUNE: month=5;
	       break;
	       
	      case JULY: month=6;
	       break;
	       
	      case AUGUST: month=7;
	       break;
	       
	      case SEPTEMBER: month=8;
		       break;
		       
	      case OCTOBER: month=9;
		       break;
		       
	      case NOVEMBER: month=10;
		       break;
		       
	      case DECEMBER: month=11;
		       break;
		
	  } 
	return month;
}

public static  String getMonthName(int monthNo){
	String monthName=null;
	 
	  switch (monthNo)
	  {
	      case 0:  monthName="JANUARY";
	       break;
	       
	      case 1: monthName="FEBRUARY";
	       break;
	       
	      case 2: monthName="MARCH";
	       break;
	       
	      case 3: monthName="APRIL";
	       break;
	       
	      case 4: monthName="MAY";
	       break;
	       
	      case 5: monthName="JUNE";
	       break;
	       
	      case 6: monthName="JULY";
	       break;
	       
	      case 7: monthName="AUGUST";
	       break;
	       
	      case 8: monthName="SEPTEMBER";
		       break;
		       
	      case 9: monthName="OCTOBER";
		       break;
		       
	      case 10: monthName="NOVEMBER";
		       break;
		       
	      case 11: monthName="DECEMBER";
		       break;
		
	  } 
	return monthName;
}

}
