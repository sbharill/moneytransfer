package com.mvc.tasks;

import java.util.List;

import com.mvc.dao.QueryExecutor;
import com.mvc.data.Country;

public class SystemTasks {
	
	public List<Country> getCountries() {
		QueryExecutor qe = new QueryExecutor();
		List<Country> countries = qe.returnCountries();
		return countries;
		}
	
}
