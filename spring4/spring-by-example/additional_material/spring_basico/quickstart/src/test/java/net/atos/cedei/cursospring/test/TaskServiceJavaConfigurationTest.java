package net.atos.cedei.cursospring.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.atos.cedei.cursospring.quickstart.ApplicationConfiguration;
import net.atos.cedei.cursospring.service.TaskService;
import net.atos.cedei.cursospring.vo.TaskVO;

@ContextConfiguration(classes = ApplicationConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TaskServiceJavaConfigurationTest {

    @Autowired
    private TaskService taskService = null;

    @Test
    public void retrieveTasks() {
        List<TaskVO> tasks = taskService.retrieveTasks();
        assertEquals(5, tasks.size());
    }

    @Test
    public void findTasksByName() {
        {
            List<TaskVO> tasks = taskService.findTasksByName(null);
            assertEquals(5, tasks.size());
        }
        {
            List<TaskVO> tasks = taskService.findTasksByName("Task");
            assertEquals(0, tasks.size());
        }
        {
            List<TaskVO> tasks = taskService.findTasksByName("Task.*");
            assertEquals(5, tasks.size());
        }
        {
            List<TaskVO> tasks = taskService.findTasksByName(".*Texto1");
            assertEquals(2, tasks.size());
        }
    }

}
