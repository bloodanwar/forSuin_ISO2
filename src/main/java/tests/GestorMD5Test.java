package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import negocio.controllers.GestorMD5;

public class GestorMD5Test {
	
	@Test
	public void getMD5Test() {
		String argumento = "Hola Mundo";
		String esperado = "d501194c987486789bb01b50dc1a0adb";
		String obtenido = GestorMD5.getMd5(argumento);
		assertEquals(esperado, obtenido);
	}
	
}
