package net.texala.database.repository.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.texala.database.model.mysql.MysqlEmployee;


@Repository
public interface MysqlEmployeeRepository extends JpaRepository<MysqlEmployee, Long> {
}