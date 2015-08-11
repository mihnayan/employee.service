package mihnayan.ito.employees.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import mihnayan.ito.employees.data.OrganizationDAO;
import mihnayan.ito.employees.model.Organization;

@Path("/")
public class OrganizationResource {

	@GET
	@Path("/orgs")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Organization> getOrganizations() {
		return OrganizationDAO.getOrganizations();
	}
}