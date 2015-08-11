package mihnayan.ito.employees.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import mihnayan.ito.employees.model.OrganizationType;

public class OrganizationTypesDAO {

	public static List<OrganizationType> getOrganizationTypes() {
		
		List<OrganizationType> orgTypes = new ArrayList<>();
		
		try (SqlSession session = SqlSessionFactoryHolder.getSqlSessionFactory().openSession()) {
			OrganizationTypesMapper mapper = session.getMapper(OrganizationTypesMapper.class);
			return mapper.getOrganizationTypes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return orgTypes;
	}
}
