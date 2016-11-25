package net.atos.practica.controller.rol;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import net.atos.practica.entity.Permiso;

@FacesConverter("PickListPermisoConverter")
public class PermisoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Permiso p = new Permiso();
		p.setNombre(arg2);
		return p;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {

		if (arg2 == null) return null;
		return ((Permiso)arg2).getNombre();
	}

}
