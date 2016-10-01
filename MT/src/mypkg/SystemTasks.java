package mypkg;

import java.util.List;

public class SystemTasks {
	
	public List<Country> getCountries() {
		QueryExecutor qe = new QueryExecutor();
		List<Country> countries = qe.returnCountries();
		return countries;
		}
	
}
