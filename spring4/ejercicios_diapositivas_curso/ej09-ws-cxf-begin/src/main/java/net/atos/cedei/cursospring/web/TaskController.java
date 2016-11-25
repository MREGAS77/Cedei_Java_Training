package net.atos.cedei.cursospring.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.atos.cedei.cursospring.core.service.TaskService;
import net.atos.cedei.cursospring.core.vo.TaskVO;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService = null;

    /**
     * <p>Provide a model with a task for the task detail page.</p>
     * 
     * <p>The method does not explicitly select a view name because the default
     * view name selected by Spring MVC matches to the incoming URL.</p>
     * 
     * @param id the id of the account
     * @param model the "implicit" model created by Spring MVC
     */
    @RequestMapping(value="/task.html", method=RequestMethod.GET)
    public void retrieveTask(@RequestParam("id") long id, @RequestParam(value="editing", required=false) Boolean editing, Model model) {
        model.addAttribute("task", taskService.retrieveTask(id));
        model.addAttribute("editing", editing);
    }
    
    @RequestMapping(value="/task.html", method=RequestMethod.POST)
    public void updateTask(@RequestParam("id") long id, @RequestParam(value="editing", required=false) Boolean editing, TaskVO taskVO, Model model) {
        taskService.updateTask(taskVO);
        
        model.addAttribute("task", taskVO);
        model.addAttribute("editing", Boolean.FALSE);
    }
    
    /**
     * <p>Provide a model with a list of all tasks.</p>
     * 
     * <p>The method does not explicitly select a view name because the default
     * view name selected by Spring MVC matches to the incoming URL.</p>
     * 
     * @param model the "implicit" model created by Spring MVC
     */
    @RequestMapping("/tasks.html")
    public void tasks(Model model) {
        model.addAttribute("tasks", taskService.retrieveTasks());
    }

}
