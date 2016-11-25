package net.atos.cedei.cursospring.ws.server;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.atos.cedei.cursospring.core.service.TaskService;
import net.atos.cedei.cursospring.core.vo.TaskVO;
import net.atos.cedei.cursospring.ws.schemas.FindTasksRequest;
import net.atos.cedei.cursospring.ws.schemas.FindTasksResponse;
import net.atos.cedei.cursospring.ws.schemas.Task;
import net.atos.cedei.cursospring.ws.service.TaskPortType;

@WebService(serviceName = "taskService", portName = "taskSoap11", targetNamespace = "http://cursospring.cedei.atos.net/ws/service", wsdlLocation = "task.wsdl", endpointInterface = "net.atos.cedei.cursospring.ws.service.TaskPortType")
@Service("taskPortType")
public class TaskPortTypeImpl implements TaskPortType {

    @Autowired
    private TaskService taskService = null;

    @Override
    public FindTasksResponse findTasks(FindTasksRequest findTasksRequest) {
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
