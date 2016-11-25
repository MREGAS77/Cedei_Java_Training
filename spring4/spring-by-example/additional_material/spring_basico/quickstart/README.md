Para lanzar la aplicacion en Jetty :   https://maven.apache.org/plugins/maven-war-plugin/examples/rapid-testing-jetty6-plugin.html

1) Añade siguiente build al pom.xml

  <build>
    <plugins>
      <plugin>
        <groupId>org.mortbay.jetty</groupId>
        <artifactId>maven-jetty-plugin</artifactId>
        <version>6.1.10</version>
        <configuration>
          <scanIntervalSeconds>10</scanIntervalSeconds>
          <connectors>
            <connector implementation="org.mortbay.jetty.nio.SelectChannelConnector">
              <port>8080</port>
              <maxIdleTime>60000</maxIdleTime>
            </connector>
          </connectors>
        </configuration>
      </plugin>
    </plugins>
  </build>
  
  
2) Ejecuta mvn  jetty:run    --> por defecto se arranca en localhost:8080