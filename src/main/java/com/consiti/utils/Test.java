package com.consiti.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	/*public static void main(String[] args) {
		String fecha = "2022-04";
		

	}*/
	
	public static boolean
    validMonthDate(String fecha)
    {
		
		Pattern pat = Pattern.compile("^\\d{4}-\\d{2}");
	     Matcher mat = pat.matcher(fecha);                                                                           
	     if (mat.matches()) {
	    	 
	         return true;
	     }
	     return false;
       
    }
	public static String[] getYearMonth(String fecha) {
		String ano,mes;
		String [] datos= new String[3];
		ano=fecha.substring(0, 4);
        mes=fecha.substring(5,7);
        datos[0]=mes;
        datos[1]=ano;
        return datos;
	}

}
