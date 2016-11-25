package net.atos.cedei.cursospring.test;

import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.sql.DataSource;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;

import net.atos.cedei.cursospring.service.TaskService;
import net.atos.cedei.cursospring.test.dbsetup.CommonOperations;
import net.atos.cedei.cursospring.test.dbsetup.DBSetupRule;
import net.atos.cedei.cursospring.vo.TaskVO;

public class TaskServiceXmlTest {

    protected static Logger LOG = LoggerFactory.getLogger(TaskServiceXmlTest.class);

    private static AbstractApplicationContext applicationContext = null;
    private static DataSource dataSource = null;
    private static TaskService taskService = null;

    @BeforeClass
    public static void initTest() {
        applicationContext = new ClassPathXmlApplicationContext("classpath:/application-test-config.xml", "classpath:/application-xml-config.xml");

        dataSource = applicationContext.getBean(DataSource.class);
        taskService = applicationContext.getBean(TaskService.class);
    }

    @Rule
    public DBSetupRule dbSetupRule = new DBSetupRule() {

        @Override
        protected void refresh() {
            Operation operation = sequenceOf(CommonOperations.DELETE_ALL, CommonOperations.INSERT_ALL);
            DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
            dbSetup.launch();
        }
    };

    @AfterClass
    public static void destroyTest() {
        applicationContext.close();
    }

    @Before
    public void setupTest() {
        dbSetupRule.dirty();
    }

    @Test
    public void retrieveTask() {
        TaskVO task = taskService.retrieveTask(Long.valueOf(1));

        assertEquals(Long.valueOf(1), task.getId());
        assertEquals("Task 1: Texto1", task.getName());
    }

    @Test
    public void retrieveTasks() {
        List<TaskVO> tasks = taskService.retrieveTasks();
        assertEquals(5, tasks.size());
    }

    @Test
    public void createTask() {
        TaskVO taskVO = new TaskVO();
        taskVO.setName("Task");
        taskService.createTask(taskVO);

        // TEST
        List<TaskVO> tasks = taskService.retrieveTasks();
        assertEquals(6, tasks.size());
    }

    @Test
    public void updateTask() {
        TaskVO taskVO = new TaskVO();
        taskVO.setId(Long.valueOf(1));
        taskVO.setName("Task 1: Texto1 - UPDATED");

        taskService.updateTask(taskVO);

        // TEST
        TaskVO task = taskService.retrieveTask(Long.valueOf(1));
        assertEquals("Task 1: Texto1 - UPDATED", task.getName());
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
            List<TaskVO> tasks = taskService.findTasksByName("Task%");
            assertEquals(5, tasks.size());
        }
        {
            List<TaskVO> tasks = taskService.findTasksByName("%Texto1");
            assertEquals(2, tasks.size());
        }
    }
}
