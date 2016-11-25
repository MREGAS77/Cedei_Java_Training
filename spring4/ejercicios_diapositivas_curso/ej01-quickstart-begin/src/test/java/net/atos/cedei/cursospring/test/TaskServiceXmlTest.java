package net.atos.cedei.cursospring.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.atos.cedei.cursospring.service.TaskService;
import net.atos.cedei.cursospring.vo.TaskVO;

public class TaskServiceXmlTest {

    private static AbstractApplicationContext applicationContext = null;
    private static TaskService taskService = null;

    @BeforeClass
    public static void initTest() {
        applicationContext = new ClassPathXmlApplicationContext("classpath:/application-test-config.xml", "classpath:/application-xml-config.xml");
        taskService = applicationContext.getBean(TaskService.class);
    }

    @AfterClass
    public static void destroyTest() {
        applicationContext.close();
    }

    @Test
    public void test() {
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
