package presentacion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import presentacion.DateLabelFormatter;

public class DateLabelFormatterTest {
	private DateLabelFormatter dateLabel = new DateLabelFormatter();
	private String datePattern = "dd-MM-yyyy";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);


	@Test
	public void stringToValueTest() throws ParseException {
		String argumento = "10-12-2002";
		Date esperado = dateFormatter.parse(argumento);
		Object obtenido = dateLabel.stringToValue(argumento);
		assertEquals(esperado, obtenido);
	}
	
	@Test
	public void valueToStringTest1() throws ParseException {
		Date date = dateFormatter.parse("10-12-2002");
	    Calendar argumento = Calendar.getInstance();
	    argumento.setTime(date);
	    String esperado = "10-12-2002";
		String obtenido = dateLabel.valueToString(argumento);
		assertEquals(esperado, obtenido);
	}
	
	@Test
	public void valueToStringTest2() throws ParseException {
	    Calendar argumento = null;
	    String esperado = "";
		String obtenido = dateLabel.valueToString(argumento);
		assertEquals(esperado, obtenido);
	}
}
