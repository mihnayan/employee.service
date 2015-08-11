package mihnayan.ito.employees;

import mihnayan.ito.employees.resources.DepartamentResource;
import mihnayan.ito.employees.resources.OrganizationResource;
import mihnayan.ito.employees.resources.OrganizationTypesResource;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.servlet.ServletContainer;

public class EmployeesServer {
	
	private final static int SERVER_PORT = 8080;
	
	public static void main(String[] args) throws Exception {
		
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		
		Server server = new Server(SERVER_PORT);
		server.setHandler(context);
		
		ServletHolder jerseyServlet = context.addServlet(ServletContainer.class, "/*");
		jerseyServlet.setInitOrder(0);
		
		StringBuilder providerClassnames = new StringBuilder();
		providerClassnames.append(DepartamentResource.class.getCanonicalName());
		providerClassnames.append(",").append(OrganizationTypesResource.class.getCanonicalName());
		providerClassnames.append(",").append(OrganizationResource.class.getCanonicalName());
//		providerClassnames.append(LoggingResponseFilter.class.getCanonicalName());
		
		jerseyServlet.setInitParameter(ServerProperties.PROVIDER_CLASSNAMES,
				providerClassnames.toString());
		
//		jerseyServlet.setInitParameter(ServerProperties.TRACING, "ALL");
//		jerseyServlet.setInitParameter(ServerProperties.TRACING_THRESHOLD, "VERBOSE");
		
		server.start();
		server.join();

	}

}
