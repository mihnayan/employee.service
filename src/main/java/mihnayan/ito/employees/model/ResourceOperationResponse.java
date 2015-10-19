package mihnayan.ito.employees.model;

import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResourceOperationResponse {

	@XmlElement
	private boolean success = true;
	
	@XmlElement
	private int status;
	
	@XmlElement
	private String href = "";
	
	@XmlElement
	private String message = "";
	
	public ResourceOperationResponse() {}
	
	public ResourceOperationResponse(String href) {
		this.href = href;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public void setStatus(Response.Status status) {
		this.status = status.getStatusCode();
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
