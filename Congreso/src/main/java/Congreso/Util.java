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
	
	/* 
	 * Transforma un String recibido en un atributo LocalDate con
	 * el formato dd-mm-yyyy. Despues retorna la variable
	 * LocalDate  
	 * */
	public static LocalDate parseDate(String strDate) throws DateTimeParseException {
		return LocalDate.parse(strDate, dateFormatterInput);
	}
	
	/* 
	 * Transforma un String recibido en un atributo LocalTime con
	 * el formato hr:min. Despues retorna la variable
	 * LocaTime
	 * */
	public static LocalTime parseTime(String strTime) throws DateTimeParseException {
		return LocalTime.parse(strTime, timeFormatterInput);
	}
	
	/* 
	 * Recibe un File, y retorna el texto despues de "." de su nombre
	 * , es decir, retorna el tipo de archivo que es File en forma de String.
	 * */
	public static String getFileExtension(File file) {
	    String name = file.getName();
	    int lastIndexOf = name.lastIndexOf(".");
	    if (lastIndexOf == -1) {
	        return "";
	    }
	    return name.substring(lastIndexOf+1);
	}
	
	/* @brief Comprueba si al cadena es numerica
	 * 
	 * Retorna true si la cadena es numerica y 
	 * retorna false en caso contrario
	 * */
	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?");
	}
	
	/* @brief Comprueba si al cadena contiene un caracter extrañp
	 * 
	 * Retorna true si la cadena no presenta caracteres que no debe tener y 
	 * retorna false en caso de que la cadena contenga caracteres que no deberia
	 * */
	public static boolean isAlphaOrSpace(String str) {
	    return str.matches("[a-zA-ZÀ-ÿñÑ ]+");
	}
	
	/* @brief Comprueba si al cadena es un correo electronico valido
	 * 
	 * Retorna true si la cadena es un correo electronico valido y 
	 * retorna false en caso contrario, por ejemplo, que no tenga arroa
	 * */
	public static boolean validateEmail(String email) {
		String pattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		return email.matches(pattern);
	}
	
	/* Cuenta y retorna la cantidad de digitos que posee el atributo numerico recibido.*/
	public static int countDigits(long number) {
		int count = 0;
	    while (number != 0) {
	        number /= 10;
	        count++;
	    }
	    return count;
	}
}
