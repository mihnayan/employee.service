package mihnayan.ito.employees.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

final class SqlSessionFactoryHolder {
	
	private final static String CONFIG_FILE	= "mybatis-config.xml";
	private final static String PROPERTY_FILE = "config.properties";

	private static SqlSessionFactory sqlSessionFactory;
	
	public static SqlSessionFactory getSqlSessionFactory() throws IOException {
		if (sqlSessionFactory == null) {
			return buildSqlSessionFactory();
		} else {
			return sqlSessionFactory;
		}
	}
	
	private SqlSessionFactoryHolder() {
		throw new AssertionError();
	}
	
	private static SqlSessionFactory buildSqlSessionFactory() throws IOException {
		InputStream configResource = Resources.getResourceAsStream(CONFIG_FILE);
		Properties propResource = Resources.getResourceAsProperties(PROPERTY_FILE);
		return new SqlSessionFactoryBuilder().build(configResource, propResource);
	}
}
