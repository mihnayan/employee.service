package mihnayan.ito.employees.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import mihnayan.ito.employees.model.Departament;
import mihnayan.ito.employees.model.Organization;

public class DepartamentDAO {
	
	public static Map<String, Departament> deptDB = new HashMap<>();
	static {
		for (int i = 1; i <= 5; i++) {
			String deptId = UUID.randomUUID().toString();
			Departament dept = new Departament(deptId);
			dept.setName("Departament #" + i);
			dept.setFullName("Full name of Departament #" + i);
			deptDB.put(deptId, dept);
		}
	}
	
	public static Departament getDepartament(String id) {
		if (id == null) {
			id = "000-000-0000";
		}
		Departament dept = new Departament(id);
		dept.setName("Example departament");
		dept.setFullName("Full name of Example departament");
		dept.setOrganization(new Organization("Boss Org LTD"));
		return dept;
	}
	
	public static List<Departament> getDepartaments() {
		List<Departament> depts = new ArrayList<>();
		Collection<Departament> deptValues = deptDB.values();
		for (Departament dept : deptValues) {
			System.out.println(dept);
			depts.add(dept);
		}
		return depts;
	}
}
