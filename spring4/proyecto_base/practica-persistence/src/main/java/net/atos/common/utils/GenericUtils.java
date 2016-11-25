package net.atos.common.utils;

public class GenericUtils {

	
	public static boolean isNullOrBlank(Object obj) {
		if (obj == null) {
			return true;
		}
		
		if ("".equals(obj)) {
			return true;
		}
		
		return false;
	}
}
