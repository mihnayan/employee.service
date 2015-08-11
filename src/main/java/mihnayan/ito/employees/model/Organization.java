package mihnayan.ito.employees.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Organization {
	
	@XmlElement
	private String id;
	
	@XmlElement
	private String name;
	
	@XmlElement
	private String typeName;
	
	public Organization() {}
	
	public Organization(String name) {
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getTypeName() {
		return typeName;
	}
}
