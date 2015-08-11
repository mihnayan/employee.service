package mihnayan.ito.employees.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import mihnayan.ito.employees.model.Organization;

public class OrganizationDAO {

	public static List<Organization> getOrganizations() {
		List<Organization> orgs = new ArrayList<>();
		
		try (SqlSession session = SqlSessionFactoryHolder.getSqlSessionFactory().openSession()) {
			OrganizationMapper mapper = session.getMapper(OrganizationMapper.class);
			return mapper.getOrganizations();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return orgs;
	}
}
