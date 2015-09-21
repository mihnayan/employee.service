package mihnayan.ito.employees.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import mihnayan.ito.employees.data.OrganizationTypesDAO;
import mihnayan.ito.employees.model.OrganizationType;

@Path("/OUTypes")
public class OrganizationTypesResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<OrganizationType> getOrganizationTypes() {
		return OrganizationTypesDAO.getOrganizationTypes();
	}
}
