package mihnayan.ito.employees.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OrganizationType {

	@XmlElement
	private String uuid;
	
	@XmlElement
	private String typeName;
	
	public OrganizationType() {}
	
	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return typeName;
	}
}
