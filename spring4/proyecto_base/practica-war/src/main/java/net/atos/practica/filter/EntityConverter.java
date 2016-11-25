package net.atos.practica.filter;

import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import net.atos.practica.entity.Rol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@FacesConverter(value="entityConverter")
@Component(value="entityConverter")
@Scope(value="session")
public class EntityConverter implements Converter {

	@Autowired
	protected EntityManager em;
	
    public Object getAsObject(javax.faces.context.FacesContext facesContext, javax.faces.component.UIComponent uiComponent, java.lang.String s) {
        Object ret = null;

        if (!"".equals(s)) {
            ret = em.find(Rol.class, s);
        }

        return ret;
    }

    public String getAsString(javax.faces.context.FacesContext facesContext, javax.faces.component.UIComponent uiComponent, java.lang.Object o) {
        if (o != null) {
            return ((Rol) o).getNombre() + "";
        } else {
            return "";
        }
    }
}