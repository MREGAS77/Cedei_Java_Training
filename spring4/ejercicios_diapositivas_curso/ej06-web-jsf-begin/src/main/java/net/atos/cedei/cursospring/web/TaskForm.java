package net.atos.cedei.cursospring.web;

import java.util.List;

import net.atos.cedei.cursospring.core.vo.TaskVO;

// TODO 7: Anotar clase para que sea gestionada por spring
// TODO 8: Cambiar el scope del bean a “request”
public class TaskForm {

    // TODO 9: Anotar atributo para que spring inyecte la dependencia
    private TaskController taskController = null;

    private List<TaskVO> tasks = null;

    private TaskVO task = null;

    public List<TaskVO> getTasks() {
        if (tasks == null) {
            tasks = taskController.tasks();
        }
        return tasks;
    }

    public void setTasks(List<TaskVO> tasks) {
        this.tasks = tasks;
    }

    public TaskVO getTask() {
        if (task == null) {
            task = taskController.retrieveTask();
        }
        return task;
    }

    public void setTask(TaskVO task) {
        this.task = task;
    }

}
