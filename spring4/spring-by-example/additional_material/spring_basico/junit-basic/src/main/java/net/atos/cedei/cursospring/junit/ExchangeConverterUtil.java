package net.atos.cedei.cursospring.junit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class ExchangeConverterUtil {

	 private static final String YAHOO_EXCHANGE_RATE = "http://finance.yahoo.com/d/quotes.csv?f=l1&s=";
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
	 * @TODO , hacer generico..
	 * @param from
	 * @param to
	 * @throws Exception
	 */
	public static void validateExchange(String from, String to) throws Exception {
		if (!(ExchangeEnum.EUR.getTipo().equalsIgnoreCase(from))) {
			throw new Exception("Esta utilidad solo permite convertir desde Euros a Dolares americanos.Está tratando de convertir ");
		}

		if (!(ExchangeEnum.USD.getTipo().equalsIgnoreCase(to))) {
			throw new Exception("Esta utilidad solo permite convertir de Euros a Dolares americanos.Está tratando de convertir ");
		}
	}

	/***
	 * Metodo que calcula el ratio de cambio entre divisas
	 * 
	 * @param from , divisa desde la que se quiere realizar el cambio
	 * @param to, divisa de la que se quiere obtener el equivalente.
	 * @param "=X", parametro necesario para completar la url que accede a la APi de Yahoo.
	 * 
	 * @return devuelve el ratio de conversion entre dos divisas distintas.
	 * 
	 * @throws Exception
	 * 
	 * @TODO Añadiremos la comprobacion --> validateExchange(from,to); ????
	 */
	public static Double getExchangeRate(String from, String to)throws Exception {
		
		URL url = buildUrl(YAHOO_EXCHANGE_RATE,from, to, "=X");
		String line = urlReader(url);
		return Double.parseDouble(line);
	}

}
