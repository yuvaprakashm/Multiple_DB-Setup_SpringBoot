package net.texala.database.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.texala.database.model.postgres.postgresEmployee;


@Repository
public interface PostgresEmployeeRepository extends JpaRepository<postgresEmployee, Long> {
}