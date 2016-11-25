package net.atos.cedei.cursospring.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import net.atos.cedei.cursospring.core.service.TaskService;

// TODO 6: Anotar clase para que spring gestione el bean
public class TaskController {

    // TODO 7: Anotar atributo para que spring inyecte la dependencia
    private TaskService taskService = null;

    /**
     * <p>
     * Provide a model with a task for the task detail page.
     * </p>
     * 
     * <p>
     * The method does not explicitly select a view name because the default view name selected by Spring MVC matches to the incoming URL.
     * </p>
     * 
     * @param id
     *            the id of the account
     * @param model
     *            the "implicit" model created by Spring MVC
     */
    // TODO 8: Asociar el método a la url /task.html
    public void retrieveTask(@RequestParam("id") long id, Model model) {
        model.addAttribute("task", taskService.retrieveTask(id));
    }

    /**
     * <p>
     * Provide a model with a list of all tasks.
     * </p>
     * 
     * <p>
     * The method does not explicitly select a view name because the default view name selected by Spring MVC matches to the incoming URL.
     * </p>
     * 
     * @param model
     *            the "implicit" model created by Spring MVC
     */
    // TODO 8: Asociar el método a la url /tasks.html
    public void retrieveTasks(Model model) {
        model.addAttribute("tasks", taskService.retrieveTasks());
    }

}
