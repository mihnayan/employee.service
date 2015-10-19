package mihnayan.ito.employees.resources;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import mihnayan.ito.employees.data.OrganizationTypesDAO;
import mihnayan.ito.employees.model.OrganizationType;
import mihnayan.ito.employees.model.ResourceOperationResponse;

@Path("/OUTypes")
public class OrganizationTypesResource {
	
	@Context
	UriInfo uriInfo;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<OrganizationType> getOrganizationTypes() {
		return OrganizationTypesDAO.getAll();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createOUType(OrganizationType ouType) throws URISyntaxException {
		
		Response.ResponseBuilder response = null;
		ResourceOperationResponse responseEntity;
		
		try {
			String uuid = OrganizationTypesDAO.save(ouType);
			URI createdUri = new URI(uriInfo.getAbsolutePath().toString() + "/" + uuid);
			
			responseEntity = new ResourceOperationResponse(createdUri.toString());
			responseEntity.setStatus(Response.Status.CREATED);
			responseEntity.setMessage("created");
			
			response = Response.created(createdUri).entity(responseEntity);
		} catch (Exception e) {
			responseEntity = new ResourceOperationResponse();
			responseEntity.setStatus(Response.Status.INTERNAL_SERVER_ERROR);
			responseEntity.setSuccess(false);
			responseEntity.setMessage(e.getMessage());
			
			response = Response.serverError().entity(responseEntity);
			
			e.printStackTrace();
		}
		
		return response.build();
	}
	
	@GET
	@Path("/{uuid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOrganizationType(@PathParam("uuid") String uuid) {
		
		OrganizationType ouType = null;
		try {
			ouType = OrganizationTypesDAO.getById(uuid);
		} catch (IOException e) {
			e.printStackTrace();
			ResourceOperationResponse responseEntity = new ResourceOperationResponse();
			responseEntity.setStatus(Response.Status.INTERNAL_SERVER_ERROR);
			responseEntity.setSuccess(false);
			responseEntity.setMessage(e.getMessage());
			
			return Response.serverError().entity(responseEntity).build();
		}
		
		if (ouType == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok().entity(ouType).build();
	}
	
	@PUT
	@Path("/{uuid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateOrganizationType(@PathParam("uuid") String uuid,
			OrganizationType ouType) {
		
		Response.ResponseBuilder response = null;
		ResourceOperationResponse responseEntity = new ResourceOperationResponse();
		
		try {
			OrganizationTypesDAO.update(uuid, ouType);
			responseEntity.setMessage("updated");
			response = Response.ok().entity(responseEntity);
		} catch (Exception e) {
			responseEntity.setStatus(Response.Status.INTERNAL_SERVER_ERROR);
			responseEntity.setSuccess(false);
			responseEntity.setMessage(e.getMessage());
			
			response = Response.serverError().entity(responseEntity);
			
			e.printStackTrace();
		}
		
		return response.build();
	}
	
	@DELETE
	@Path("/{uuid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteOrganizationType(@PathParam("uuid") String uuid) {
		
		Response.ResponseBuilder response = null;
		ResourceOperationResponse responseEntity = new ResourceOperationResponse();
		
		try {
			OrganizationTypesDAO.delete(uuid);
			responseEntity.setMessage("deleted");
			response = Response.ok().entity(responseEntity);
		} catch (Exception e) {
			responseEntity.setStatus(Response.Status.INTERNAL_SERVER_ERROR);
			responseEntity.setSuccess(false);
			responseEntity.setMessage(e.getMessage());
			
			response = Response.serverError().entity(responseEntity);
			
			e.printStackTrace();
		}
		
		return response.build();
	}
}
