package net.atos.practica.batch;


import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@DisallowConcurrentExecution
@Component
public class ActualizarFeedsBKJob extends QuartzJobBean {
 
    @Override
    protected void executeInternal(JobExecutionContext context) {
    	System.out.println("**********************************");
    	System.out.println("**********************************");
    	System.out.println("**********************************");
    	System.out.println("**********************************");
    	System.out.println("**********************************");
    	System.out.println("**********************************");
    	System.out.println();
    }
}

