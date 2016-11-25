package net.atos.common.base;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

public class BeanUtils  {
	
	protected static Logger log = Logger.getLogger(BeanUtils.class);
	
   public static void copyProperties(Object dest, Object orig)
     throws IllegalAccessException, InvocationTargetException
   {
	   
	  BeanUtilsBean buInstance = BeanUtilsBean.getInstance();
	   
     if (dest == null) {
       throw new IllegalArgumentException("No destination bean specified");
     }
 
     if (orig == null) {
       throw new IllegalArgumentException("No origin bean specified");
     }
     if (log.isDebugEnabled()) {
       log.debug("BeanUtils.copyProperties(" + dest + ", " + orig + ")");
     }
 
     if (orig instanceof DynaBean) {
       DynaProperty[] origDescriptors = ((DynaBean)orig).getDynaClass().getDynaProperties();
 
       for (int i = 0; i < origDescriptors.length; ++i) {
         String name = origDescriptors[i].getName();
         if (buInstance.getPropertyUtils().isWriteable(dest, name)) {
           Object value = ((DynaBean)orig).get(name);
           buInstance.copyProperty(dest, name, value);
         }
       }
     } else if (orig instanceof Map) {
       Iterator<?> names = ((Map<?,?>)orig).keySet().iterator();
       while (names.hasNext()) {
         String name = (String)names.next();
         instantiateNestedProperties(dest, name);
         if (buInstance.getPropertyUtils().isWriteable(dest, name)) {
           Object value = ((Map<?,?>)orig).get(name);
           buInstance.copyProperty(dest, name, value);
         }
       }
     } else {
       PropertyDescriptor[] origDescriptors = buInstance.getPropertyUtils().getPropertyDescriptors(orig);
 
       for (int i = 0; i < origDescriptors.length; ++i) {
         String name = origDescriptors[i].getName();
         if ("class".equals(name)) {
           continue;
         }
         if ((!(buInstance.getPropertyUtils().isReadable(orig, name))) || (!(buInstance.getPropertyUtils().isWriteable(dest, name))))
           continue;
         try {
           Object value = buInstance.getPropertyUtils().getSimpleProperty(orig, name);
           if (value == null) {
        	   continue;
           }
           
           
           buInstance.copyProperty(dest, name, value);
         }
         catch (NoSuchMethodException e)
         {
         }
       }
     }
   }
	
	
   private static void instantiateNestedProperties(Object obj, String fieldName) {
	    try {
	        String[] fieldNames = fieldName.split("\\.");
	        if (fieldNames.length > 1) {
	            StringBuffer nestedProperty = new StringBuffer();
	            for (int i = 0; i < fieldNames.length - 1; i++) {
	                String fn = fieldNames[i];
	                if (i != 0) {
	                    nestedProperty.append(".");
	                }
	                nestedProperty.append(fn);

	                Object value = PropertyUtils.getProperty(obj, nestedProperty.toString());

	                if (value == null) {
	                    PropertyDescriptor propertyDescriptor = PropertyUtils.getPropertyDescriptor(obj, nestedProperty.toString());
	                    Class<?> propertyType = propertyDescriptor.getPropertyType();
	                    Object newInstance = propertyType.newInstance();
	                    PropertyUtils.setProperty(obj, nestedProperty.toString(), newInstance);
	                }
	            }
	        }
	    } catch (IllegalAccessException e) {
	        throw new RuntimeException(e);
	    } catch (InvocationTargetException e) {
	        throw new RuntimeException(e);
	    } catch (NoSuchMethodException e) {
	        throw new RuntimeException(e);
	    } catch (InstantiationException e) {
	        throw new RuntimeException(e);
	    }
	}   
   
	
	public static Object getProperty(Object bean, String name) {
		try {
			if (name == null) {
				return bean;
			}
				
			String[] metodos = name.split("\\.");
			Object obj = bean;
			for (int i = 0; i < metodos.length; i++) {
				String metodo = metodos[i];
				String getterName = metodo;
				if (metodo.endsWith("()") && metodo.startsWith("get")) {
					getterName = metodo.replaceAll("\\(\\)","");
				} else {
					getterName = "get" + metodo.substring(0,1).toUpperCase() + metodo.substring(1);
				}
				Method m = obj.getClass().getMethod(getterName);
				if (m == null) {
					throw new RuntimeException("El objeto de tipo " + bean.getClass() + " no contiene la propiedad " + name);
				}
				obj = m.invoke(obj);
			}
			
			return obj;
			} catch (Exception e) {
				throw new RuntimeException("Error accediendo a BeanUtils para el objeto de tipo " + bean.getClass() + " y la propiedad " + name);
			}
		}
	
}