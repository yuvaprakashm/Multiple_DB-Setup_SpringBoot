package net.texala.database.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.texala.database.model.mysql.MysqlEmployee;
import net.texala.database.model.postgres.postgresEmployee;
import net.texala.database.repository.mysql.MysqlEmployeeRepository;
import net.texala.database.repository.postgres.PostgresEmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	private MysqlEmployeeRepository mysqlRepo;

	@Autowired
	private PostgresEmployeeRepository postgresRepo;

	public List<postgresEmployee> getPostgresEmployees() {
		try {
			return postgresRepo.findAll();
		} catch (Exception e) {
			return null;
		}
	}

	public List<MysqlEmployee> getMysqlEmployees() {
		try {
			return mysqlRepo.findAll();
		} catch (Exception e) {
			return null;
		}
	}
}
