package com.advanz;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * @author Anil Dhakad
 *
 */
public class DataUtility {
public static Date getDate(Date date) {
		
	Calendar calendar =Calendar.getInstance();
	int hour= calendar.getTime().getHours();
	int minute= calendar.getTime().getMinutes();
	int second= calendar.getTime().getSeconds();
	
	DateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
	String stringDate=dft.format(date);
    stringDate=	stringDate+" "+hour+":"+minute+":"+second;
	
	
	
		Date s2=null;
		if(stringDate!=null){
    	try{
    		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		s2 = df.parse(stringDate);
        	} catch(Exception e) {
        	  e.printStackTrace();
        	}
		}
		return  s2;
	}
	public static Date addMonths(Date aDate, int number){  
	    Calendar aCalendar = Calendar.getInstance();  
	    aCalendar.setTime(aDate);  
	    aCalendar.add(Calendar.MONTH, number); 
	return aCalendar.getTime();  
	}  
	
	public static void main(String[] args)throws Exception {
		
		String fromDate="2013/11/18";
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		Date d = df.parse(fromDate);
		//d=addMonths(d, 11);
	Date dd=addMonthInMonth(d,1);
Date date= new Date();
Date date2= new Date();
	if(d.getTime() <= date2.getTime()){
		System.out.println("Inside condition :::::::::");
	}
	
		}
	public static Date addDayInDate(Date date, int days){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(date); // Now use today date.
		c.add(Calendar.DATE, days); // Adding 5 days
		Date d= c.getTime();
		return d;
	}
	public static Date getLastDateOfMonth(Date date){  
		//Date today = new Date();  
	    Calendar calendar = Calendar.getInstance();  
	    calendar.setTime(date);  
	    calendar.add(Calendar.MONTH, 1);  
	    calendar.set(Calendar.DAY_OF_MONTH, 1);  
	    calendar.add(Calendar.DATE, -1);  
	    Date lastDayOfMonth = calendar.getTime();  
	return lastDayOfMonth;  
	}
public static Date addMonthInMonth(Date date,int months)throws Exception{
	SimpleDateFormat ssd = new SimpleDateFormat("yyyy/MM/dd");
	//Date date =ssd.parse(sdate);
	Calendar cal = Calendar.getInstance();
	cal.setTime(date);
	cal.add(Calendar.MONTH, months);
	SimpleDateFormat sdf = new SimpleDateFormat("MM");
	//int month = Integer.parseInt(sdf.format(cal.getTime()));
	return cal.getTime();
  }
	public static int differenceInMonths(Date d1, Date d2) {
		/*SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
	     d1 = f.parse("2012-01-01");
		DateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		String stringDate=dft.format(new Date());
	     d2 = f.parse(stringDate);
	    int n = DataUtility.differenceInMonths(d1, d2);
	    System.out.println(n);*/
		
	    Calendar c1 = Calendar.getInstance();
	    c1.setTime(d1);
	    Calendar c2 = Calendar.getInstance();
	    c2.setTime(d2);
	    int diff = 0;
	    if (c2.after(c1)) {
	        while (c2.after(c1)) {
	            c1.add(Calendar.MONTH, 1);
	            if (c2.after(c1)) {
	                diff++;
	            }
	        }
	    } else if (c2.before(c1)) {
	        while (c2.before(c1)) {
	            c1.add(Calendar.MONTH, -1);
	            if (c1.before(c2)) {
	                diff--;
	            }
	        }
	    }
	    return diff;
	}
	public static int getDayOfAnyMonth(int month){
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int year = cal.get(Calendar.YEAR);
		//int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		
		
		//Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.YEAR, year);
		int monthMaxDays= cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		return monthMaxDays;
	}
	public static Map getFirstDateAndLastDate(int month) {
		Map map=new HashMap();
		
		    Calendar calendar = Calendar.getInstance();
		    calendar.set(Calendar.MONTH, month);
		    calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		    Date lastDate = calendar.getTime();
		    calendar.set(Calendar.DAY_OF_MONTH, 1);
		    Date firstDate = calendar.getTime();
		    map.put("firstDate", firstDate);
		    map.put("lastDate",lastDate);
		 return map;
	}
	public static Date getDateSimpleFormate(Date date)throws Exception{
		
		 DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    	 String cur_date=formatter.format(date);
    	//System.out.println(cur_date);
    	
    	
    	
    	//String fromDate = "15/03/2014";
    	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    	java.util.Date dtt = df.parse(cur_date);
    	java.sql.Date ds = new java.sql.Date(dtt.getTime());
    	return ds;
    	
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
}
