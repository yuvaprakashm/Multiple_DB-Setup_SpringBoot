package net.texala.database.web.rest;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.texala.database.model.mysql.MysqlEmployee;
import net.texala.database.model.postgres.postgresEmployee;
import net.texala.database.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/postgres")
	public ResponseEntity<Object> getPostgresEmployees() {
		List<postgresEmployee> postgresData = employeeService.getPostgresEmployees();
		return ResponseEntity.ok(Collections.singletonMap("data", postgresData != null ? postgresData : null));
	}

    @GetMapping("/mysql")
    public ResponseEntity<Object> getMySQLEmployees() {
        List<MysqlEmployee> mysqlData = employeeService.getMysqlEmployees();
        return ResponseEntity.ok(Collections.singletonMap("data", mysqlData != null ? mysqlData : null));
    }
}