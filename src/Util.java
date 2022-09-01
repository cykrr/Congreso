import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class Util {
	public Util() {
	
	}
	
	public static boolean validateDate(String strDate) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
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
}
