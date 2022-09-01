import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Util {
	public Util() {
	}
	
	public boolean validateDate(String strDate) {
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

	public boolean validateHour() {
		return false;
	}
}
