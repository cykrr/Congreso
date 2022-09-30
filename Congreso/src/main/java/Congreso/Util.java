package Congreso;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class Util {

	public final static DateTimeFormatter dateFormatterInput = DateTimeFormatter.ofPattern("d-M-[uuuu][uu]").withResolverStyle(ResolverStyle.STRICT);
	public final static DateTimeFormatter dateFormatterOutput = DateTimeFormatter.ofPattern("dd-MM-uuuu").withResolverStyle(ResolverStyle.STRICT);
	public final static DateTimeFormatter timeFormatterInput = DateTimeFormatter.ofPattern("H:m").withResolverStyle(ResolverStyle.STRICT);
	public final static DateTimeFormatter timeFormatterOutput = DateTimeFormatter.ofPattern("HH:mm").withResolverStyle(ResolverStyle.STRICT);
	
	public static LocalDate parseDate(String strDate) throws DateTimeParseException {
		return LocalDate.parse(strDate, dateFormatterInput);
	}
	
	public static LocalTime parseTime(String strTime) throws DateTimeParseException {
		return LocalTime.parse(strTime, timeFormatterInput);
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
	    return str.matches("[a-zA-ZÀ-ÿñÑ ]+");
	}
	
	public static boolean validateEmail(String email) {
		String pattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		return email.matches(pattern);
	}
	
	public static int countDigits(long number) {
		int count = 0;
	    while (number != 0) {
	        number /= 10;
	        count++;
	    }
	    return count;
	}
}
