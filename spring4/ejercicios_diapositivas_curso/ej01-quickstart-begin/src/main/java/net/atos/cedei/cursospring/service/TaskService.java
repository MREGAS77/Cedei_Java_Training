package net.atos.cedei.cursospring.service;

import java.util.List;

import net.atos.cedei.cursospring.vo.TaskVO;

public interface TaskService {

    /**
     * Retrieve all tasks
     * 
     * @return Task List
     */
    public List<TaskVO> retrieveTasks();

    /**
     * Find task by name
     * 
     * @param name
     * @return Task List
     */
    public List<TaskVO> findTasksByName(String name);

}
