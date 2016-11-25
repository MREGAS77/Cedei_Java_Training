package net.atos.cedei.cursospring.junit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class EuroToDollarConverter {

	/****
	 * Metodo con toda la fucionalidad junta
	 * 
	 */
	
	private static Double euroToDollarConverter(String from, String to,int amount) {
		try {

			URL url = new URL("http://finance.yahoo.com/d/quotes.csv?f=l1&s=" + from + to + "=X");
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

			String line = reader.readLine();
			if (line.length() > 0) {
				return Double.parseDouble(line) * amount;
			}
			reader.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	/****
	 * 
	 * @param from
	 *            Divisa de la que partimos.
	 * @param to
	 *            Divisa a la que queremos convertir
	 * @param amount
	 *            cantida de dinero que queremos convertir
	 * @return Double, con la cantidad resultado de conversion.
	 * @throws Exception
	 */
	private static Double euroToDollarConverter_1(String from, String to, int amount) throws Exception {

		// validateDivisas(from, to);
		URL url = buildUrl("http://finance.yahoo.com/d/quotes.csv?f=l1&s=",from, to, "=X");
		String line = urlReader(url);
		return Double.parseDouble(line) * amount;
	}

	private double euroToDollarConverter__2(String from, String to, int amount)throws Exception {
		// double roundOff = Math.round(amount*100)/100;
		return getExchangeRate(from, to) * amount;

	}

	/**
	 * Calculamos para un importe que pueda contener decimales
	 * 
	 * @param from
	 * @param to
	 * @param amount
	 * @return
	 * @throws Exception
	 */
	private double euroToDollarConverter__3(String from, String to,double amount) throws Exception {
		return getExchangeRate(from, to) * amount;
	}

	/**
	 * Calculamos con importes que puedan ser decimales pero redondeando a 2
	 * decimales.
	 * 
	 * @param from
	 * @param to
	 * @param amount
	 * @return
	 * @throws Exception
	 */
	private double euroToDollarConverter__4(String from, String to,double amount) throws Exception {
		double roundOff = Math.round(amount * 100) / 100;
		return getExchangeRate(from, to) * amount;
	}

	/***
	 * 
	 * @param from
	 * @param to
	 * @return True, si la hacer el cambio de divisas, el ratio es mayor que
	 *         1,False en caso contrario.
	 * @throws Exception
	 */
	public static boolean changeRate(String from, String to) throws Exception {
		return (getExchangeRate(from, to) > 1.0 ? true : false);

	}

	/***
	 * Método que genera devuelve un URL concatenando una url con una serie de
	 * parámetros concatenados.
	 * 
	 * @param urlBase
	 * @param params
	 * @return
	 * @throws MalformedURLException
	 */
	public static URL buildUrl(String urlBase, String... params)throws MalformedURLException {
		return new URL(urlBase + params);
	}

	/***
	 * 
	 * @param url
	 * @return String
	 * @throws Exception
	 */
	public static String urlReader(URL url) throws Exception {
		String urlReader = null;
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					url.openStream()));
			String line = reader.readLine();
			if (line.length() > 0) {
				urlReader = line;
			}
			reader.close();
		} catch (Exception e) {
			throw e;
		}
		return urlReader;
	}

	/**
	 * 
	 * @param from
	 * @param to
	 * @throws Exception
	 */
	public static void validateDivisas(String from, String to) throws Exception {
		if (!(ExchangeEnum.EUR.getTipo().equalsIgnoreCase(from))) {
			throw new Exception(
					"Esta utilidad solo permite convertir desde Euros a Dolares americanos.Está tratando de convertir ");
		}

		if (!(ExchangeEnum.USD.getTipo().equalsIgnoreCase(to))) {
			throw new Exception(
					"Esta utilidad solo permite convertir de Euros a Dolares americanos.Está tratando de convertir ");
		}
	}

	/***
	 * Metodo que calcula el ratio de cambio entre divisas
	 * 
	 * @return
	 * @throws Exception
	 */
	private static Double getExchangeRate(String from, String to)throws Exception {

		URL url = buildUrl("http://finance.yahoo.com/d/quotes.csv?f=l1&s=",from, to, "=X");
		String line = urlReader(url);
		return Double.parseDouble(line);
	}
}
