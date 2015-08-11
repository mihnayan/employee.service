package mihnayan.ito.employees.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OrganizationType {

	@XmlElement
	private String name;
	
	public OrganizationType() {}
	
	public OrganizationType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
