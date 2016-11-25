package net.atos.practica.initialization;

import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class Initializator implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static Logger log = org.apache.log4j.Logger.getLogger(Initializator.class);

    @Override
    public void initialize(ConfigurableApplicationContext arg0) {
        initializeLogger();
    }

    public void initializeLogger() {
        try {
            URL resource = this.getClass().getClassLoader().getResource("log4j.xml");
            String path = resource.getPath();
            // org.w3c.dom.Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
            DOMConfigurator.configure(path);
            log.info("test");
        } catch (Exception e) {
            log.error("ERROR INICIALIZANDO LOGGER", e);
        }
    }

}
