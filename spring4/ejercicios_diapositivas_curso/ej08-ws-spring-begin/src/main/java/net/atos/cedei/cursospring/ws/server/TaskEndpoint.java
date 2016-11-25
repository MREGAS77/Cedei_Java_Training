package net.atos.cedei.cursospring.ws.server;

import java.util.List;

import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import net.atos.cedei.cursospring.core.service.TaskService;
import net.atos.cedei.cursospring.core.vo.TaskVO;
import net.atos.cedei.cursospring.ws.schemas.FindTasksRequest;
import net.atos.cedei.cursospring.ws.schemas.FindTasksResponse;
import net.atos.cedei.cursospring.ws.schemas.Task;

// TODO 5: Anotar clase para que sea gestionada por spring-ws
public class TaskEndpoint {

    private static final String NAMESPACE_URI = "http://cursospring.cedei.atos.net/ws/schemas";

    // TODO 6: Anotar atributo para inyectar dependencia del servicio
    private TaskService taskService = null;

    // TODO 7: Anotar método findTaks para que pueda recibir invocaciones en donde el localpart es: “findTasksRequest”
    public FindTasksResponse findTasks(@RequestPayload FindTasksRequest findTasksRequest) throws Exception {
        FindTasksResponse response = new FindTasksResponse();
        List<TaskVO> taskVOs = taskService.retrieveTasks();

        for (TaskVO taskVO : taskVOs) {
            Task task = new Task();
            task.setId(taskVO.getId());
            task.setName(taskVO.getName());

            response.getTasks().add(task);
        }

        return response;
    }

}