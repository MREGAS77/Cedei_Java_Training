package net.atos.cedei.cursospring.junit.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import junit.framework.TestCase;
import net.atos.cedei.cursospring.junit.ExchangeConverterUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @see http://stackoverflow.com/questions/3139879/how-do-i-get-currency-exchange-rates-via-an-api-such-as-google-finance
 * 
 * Un cliente nos solicita saber el cambio de divisa de Euros a Dólares porque viaja con frecuencia a EEUU.
 *  
 * Requisitos iniciales:
 * 
 *                       1) Nos piden generar una aplicacion utilidad para convertir de Euros a Dolares.
 *                       2) Hemos de utilizar la api de Yahoo como conversor, ya que, según el cliente, tiene el cambio de divisa totalmente actualizado.
 *
 * */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_Test.xml")
public class CashConverterTest extends TestCase {

	private static final Logger logger = LoggerFactory.getLogger(CashConverterTest.class);

	@Before
	public void setUp() throws Exception {
		super.setUp();	
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}
	
/***
 *                               Caso de Uso Solicitado por el cliente.
 * 
 *********************** CONVERTIR DE EUROS A DOLARES AMERICANOS.
 * 
 *  @TODO URGENTE!!! PREGUNTAR DUDAS QUE NOS SURGEN:
 * 
 * 			1) Siempre nos habla de candidades enteras:
 * 											Se dará el caso de cantidades con decimales?.
 * 											En tal caso, hacemos redondeos?
 * 										    Cuantos decimales redondeo?
 * 											Si nos confirma que quiere poder usar decimales.Preguntamos que si quiere tener posibilidad de especificar decimales de redondeo.
 * 
 *          2) Es requisito usar la api de Yahoo:
 *          								Entendemos que siempre dispondrá de acceso a internet para realizar la conversion?
 *          								Que hacemos si no hay red? .
 *          
 *          
 *********************** Sabemos que el cliente sólo necesita convertir de Euros a dollares...  
 *  
 *  
 *********************** Y SOLO NOS HAN PEDIDO CONVERTIR DE EUROS A DOLARES!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * 
 * @throws IOException
 */
@Test
public void euroToDollarTest() throws IOException {
	  
	  String from = "EUR";
	  String to = "USD";
	  
	  URL url = new URL("http://finance.yahoo.com/d/quotes.csv?f=l1&s="+ from + to + "=X");
	  
	  assertEquals("http://finance.yahoo.com/d/quotes.csv?f=l1&s=EURUSD=X", url.toString());
	  
	  BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
	  assertNotNull(reader);
      String line = reader.readLine();
      double finalAmount = 0D;
      
      int amount = 123;
 
      if (line.length() > 0) {
    	   finalAmount = Double.parseDouble(line) * amount;
      }
      reader.close();
   
      logger.info("Euro/US Dollar: " + finalAmount);
}



public void euroToDollar_Test() throws Exception {
	 String from = "EUR";
	 String to = "USD";
	 int amount = 123;
	 
	 double exchangeRate = ExchangeConverterUtil.getExchangeRate(from, to);
	 logger.info("Euro/US Dollar: " + exchangeRate*amount); 
}


//double roundOff = Math.round( 12.123 * 100) / 100;
}

