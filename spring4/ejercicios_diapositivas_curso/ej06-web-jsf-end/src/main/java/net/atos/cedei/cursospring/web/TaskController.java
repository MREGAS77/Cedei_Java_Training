package net.atos.cedei.cursospring.web;

import java.util.List;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import net.atos.cedei.cursospring.core.service.TaskService;
import net.atos.cedei.cursospring.core.vo.TaskVO;

@Controller
public class TaskController {

    @Autowired
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
