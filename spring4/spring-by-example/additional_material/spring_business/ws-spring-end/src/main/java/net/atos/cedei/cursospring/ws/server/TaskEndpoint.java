package net.atos.cedei.cursospring.ws.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import net.atos.cedei.cursospring.core.service.TaskService;
import net.atos.cedei.cursospring.core.vo.TaskVO;
import net.atos.cedei.cursospring.ws.schemas.FindTasksRequest;
import net.atos.cedei.cursospring.ws.schemas.FindTasksResponse;
import net.atos.cedei.cursospring.ws.schemas.Task;

@Endpoint
public class TaskEndpoint {

    private static final String NAMESPACE_URI = "http://cursospring.cedei.atos.net/ws/schemas";

    @Autowired
    private TaskService taskService = null;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "findTasksRequest")
    @ResponsePayload
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