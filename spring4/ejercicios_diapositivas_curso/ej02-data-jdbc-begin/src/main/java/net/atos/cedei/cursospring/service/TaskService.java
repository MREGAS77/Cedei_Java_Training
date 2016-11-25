package net.atos.cedei.cursospring.service;

import java.util.List;

import net.atos.cedei.cursospring.vo.TaskVO;

public interface TaskService {

    /**
     * Retrieve one task
     * 
     * @return Task
     */
    public TaskVO retrieveTask(Long id);

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

    /**
     * Create a new Task
     * 
     * @param TaskVO
     * @return task created
     */
    public TaskVO createTask(TaskVO taskVO);

    /**
     * Update a Task
     * 
     * @param TaskVO
     */
    public void updateTask(TaskVO taskVO);

}
