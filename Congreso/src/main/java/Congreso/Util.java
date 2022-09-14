package Congreso;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class Util {

	public final static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");

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
	
	public static String getFileExtension(File file) {
	    String name = file.getName();
	    int lastIndexOf = name.lastIndexOf(".");
	    if (lastIndexOf == -1) {
	        return "";
	    }
	    return name.substring(lastIndexOf+1);
	}
}
