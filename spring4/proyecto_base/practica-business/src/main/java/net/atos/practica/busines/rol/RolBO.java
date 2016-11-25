package net.atos.practica.busines.rol;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.atos.common.utils.GenericUtils;
import net.atos.practica.dao.PermisosDao;
import net.atos.practica.dao.RolesDao;
import net.atos.practica.dto.FiltroRolesDto;
import net.atos.practica.entity.Permiso;
import net.atos.practica.entity.Rol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(propagation=Propagation.REQUIRED)
public class RolBO {

	@Autowired
	RolesDao rolDao;
	
	@Autowired
	PermisosDao permisoDao;
		
	public void insertarOActualizar(Rol r) {
		Set<Permiso> permisos = new HashSet<Permiso>();
		
		for (Permiso p : r.getPermisos()) {
			if (p != null && !GenericUtils.isNullOrBlank(p.getNombre())) {
				permisos.add(permisoDao.getReference(p.getNombre()));
			}
		}
		r.setPermisos(permisos);
		rolDao.insertOrUpdate(r);
	}
	
	public void insertar(Rol r) {
		rolDao.insertOrUpdate(r);
	}
	
	public void actualizar(Rol r) {
		rolDao.insertOrUpdate(r);
	}
	
	public void borrar(Rol r) {
		rolDao.deleteOne(r);
	}
	
	public List<Rol> buscar(FiltroRolesDto filtro) {
		return rolDao.buscar(filtro);
	}
}
