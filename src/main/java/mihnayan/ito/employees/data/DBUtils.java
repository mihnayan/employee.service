package mihnayan.ito.employees.data;

import java.util.UUID;

final class DBUtils {

	static String getUUID() {
		return UUID.randomUUID().toString();
	}
}
