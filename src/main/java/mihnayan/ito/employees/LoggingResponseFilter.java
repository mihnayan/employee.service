package mihnayan.ito.employees;

import java.io.IOException;
import java.util.logging.Logger;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

public class LoggingResponseFilter implements ContainerResponseFilter {
	
	private static Logger log = Logger.getLogger(LoggingResponseFilter.class.getName());

	@Override
	public void filter(ContainerRequestContext requestContext,
			ContainerResponseContext responseContext) throws IOException {
		
		Class<?> entityClass = responseContext.getEntityClass();
		log.info("response status: " + responseContext.getStatus());
		if (entityClass != null) {
			log.info(responseContext.getEntityClass().getName());
		} else {
			log.info("Entity class is null");
		}
	}

}
