package mihnayan.ito.employees.data;

import java.util.List;

import mihnayan.ito.employees.model.OrganizationType;

public interface OrganizationTypesMapper {

	List<OrganizationType> getOrganizationTypes();
	
	OrganizationType getById(String uuid);
	
	void saveOrganizationType(OrganizationType ouType);
	
	void update(OrganizationType ouType);
	
	void delete(String uuid);
}
