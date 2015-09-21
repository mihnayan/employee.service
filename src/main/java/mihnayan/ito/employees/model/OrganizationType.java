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
	
	public String getId() {
		return uuid;
	}
	
	public String getName() {
		return typeName;
	}
}
