package net.atos.cedei.cursospring.web;

import java.util.List;

import javax.faces.context.FacesContext;

import net.atos.cedei.cursospring.core.service.TaskService;
import net.atos.cedei.cursospring.core.vo.TaskVO;

// TODO 5: Anotar clase para que sea gestionada por spring
public class TaskController {

    // TODO 6: Anotar atributo para que spring inyecte la dependencia
    private TaskService taskService = null;

    /**
     * Intertal actions
     */
    public TaskVO retrieveTask() {
        String id = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        return taskService.retrieveTask(Long.valueOf(id));
    }

    public List<TaskVO> tasks() {
        return taskService.retrieveTasks();
    }

}
