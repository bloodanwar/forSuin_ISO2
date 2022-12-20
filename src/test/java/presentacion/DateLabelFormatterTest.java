package presentacion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import presentacion.DateLabelFormatter;

public class DateLabelFormatterTest {
	private DateLabelFormatter date = new DateLabelFormatter();
	private String datePattern = "dd-MM-yyyy";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);


	@Test
	public void stringToValueTest() throws ParseException {
		String argumento = "10-12-2002";
		Object obtenido = date.stringToValue(argumento);
		System.out.println(obtenido);
	}
}
