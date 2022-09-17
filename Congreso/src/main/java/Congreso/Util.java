package Congreso;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class Util {

	public final static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-YYYY").withResolverStyle(ResolverStyle.STRICT);
	public final static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm").withResolverStyle(ResolverStyle.STRICT);

	public static boolean validateDate(String strDate) {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		format.setLenient(false);

	    try {
	        format.parse(strDate); 
	    } 
	    catch (ParseException e) {
	        return false;
	    }
	    
	    return true;
	}

	public static boolean validateTime(String strTime) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm")
	            .withResolverStyle(ResolverStyle.STRICT);
		
		try {
			LocalTime.parse(strTime, format);
		}
		catch (DateTimeParseException | NullPointerException e) {
	        return false;
	    }
	    
		return true;
	}
	
	public static LocalTime parseTime(String strTime) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm")
	            .withResolverStyle(ResolverStyle.STRICT);
		LocalTime time;
		
		try {
			time = LocalTime.parse(strTime, format);
		} catch (DateTimeParseException | NullPointerException e) {
			e.printStackTrace();
			return null;
	    }
		
		return time;
	}
	
	public static String getFileExtension(File file) {
	    String name = file.getName();
	    int lastIndexOf = name.lastIndexOf(".");
	    if (lastIndexOf == -1) {
	        return "";
	    }
	    return name.substring(lastIndexOf+1);
	}
	
	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?");
	}
	
	public static boolean isAlphaOrSpace(String str) {
	    return str.matches("[a-zA-Z ]+");
	}
	
	public static boolean validateEmail(String email) {
		String pattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		return email.matches(pattern);
	}
}
