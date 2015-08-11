package mihnayan.ito.employees.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import mihnayan.ito.employees.data.DepartamentDAO;
import mihnayan.ito.employees.model.Departament;

@Path("/")
public class DepartamentResource {

	@GET
	@Path("/departament")
	@Produces(MediaType.APPLICATION_JSON)
	public Departament getDepartament(@QueryParam("id") String id) {
		Departament dept = null;
		dept = DepartamentDAO.getDepartament(id);
		System.out.println(dept);
		return dept;
	}
	
	@GET
	@Path("/depts")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Departament> getDepartaments() {
		return DepartamentDAO.getDepartaments();
	}
}
