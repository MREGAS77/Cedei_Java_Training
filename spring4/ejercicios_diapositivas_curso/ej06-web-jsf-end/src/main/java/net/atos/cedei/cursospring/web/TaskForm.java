package net.atos.cedei.cursospring.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import net.atos.cedei.cursospring.core.vo.TaskVO;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class TaskForm {

    @Autowired
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
