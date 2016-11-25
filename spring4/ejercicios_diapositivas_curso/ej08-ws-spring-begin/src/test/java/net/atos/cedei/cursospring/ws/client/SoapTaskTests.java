package net.atos.cedei.cursospring.ws.client;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.client.core.WebServiceTemplate;

import net.atos.cedei.cursospring.ws.schemas.FindTasksRequest;
import net.atos.cedei.cursospring.ws.schemas.FindTasksResponse;

@ContextConfiguration(locations = { "classpath:spring-ws-client-config.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class SoapTaskTests {

    private static Logger LOG = LoggerFactory.getLogger(SoapTaskTests.class);

    @Autowired
    private WebServiceTemplate webServiceTemplate;

    @Test
    public void testWebServiceWithJAXB() throws Exception {
        FindTasksRequest request = new FindTasksRequest();
        FindTasksResponse response = (FindTasksResponse) webServiceTemplate.marshalSendAndReceive(request);
        assertNotNull(response);
        LOG.info("RESPONSE:");
        if (response.getTasks() != null && response.getTasks().size() != 0) {
            for (int i = 0; i < response.getTasks().size(); i++) {
                LOG.debug("   - Task[{}]: id={}, name={}", new Object[] { i, response.getTasks().get(i).getId(), response.getTasks().get(i).getName() });
            }
        } else {
            LOG.info("   - empty");
        }
    }

}
