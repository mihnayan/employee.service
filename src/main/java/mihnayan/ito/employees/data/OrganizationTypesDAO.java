package mihnayan.ito.employees.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import mihnayan.ito.employees.model.OrganizationType;

public class OrganizationTypesDAO {

	public static List<OrganizationType> getAll() {
		
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
	
	public static OrganizationType getById(String uuid) throws IOException {
		
		OrganizationType ouType = null;
		
		try (SqlSession session = SqlSessionFactoryHolder.getSqlSessionFactory().openSession()) {
			OrganizationTypesMapper mapper = session.getMapper(OrganizationTypesMapper.class);
			ouType = mapper.getById(uuid);
		}
		
		return ouType;
	}
	
	public static String save(OrganizationType ouType) throws IOException {
		
		ouType.setUuid(DBUtils.getUUID());
		
		try (SqlSession session = SqlSessionFactoryHolder.getSqlSessionFactory().openSession()) {
			OrganizationTypesMapper mapper = session.getMapper(OrganizationTypesMapper.class);
			mapper.saveOrganizationType(ouType);
			session.commit();
		}

		return ouType.getUuid();
	}
	
	public static void update(String uuid, OrganizationType ouType) throws IOException {
		
		ouType.setUuid(uuid);
		
		try (SqlSession session = SqlSessionFactoryHolder.getSqlSessionFactory().openSession()) {
			OrganizationTypesMapper mapper = session.getMapper(OrganizationTypesMapper.class);
			mapper.update(ouType);
			session.commit();
		}
	}
	
	public static void delete(String uuid) throws IOException {
		
		try (SqlSession session = SqlSessionFactoryHolder.getSqlSessionFactory().openSession()) {
			OrganizationTypesMapper mapper = session.getMapper(OrganizationTypesMapper.class);
			mapper.delete(uuid);
			session.commit();
		}
	}
}
