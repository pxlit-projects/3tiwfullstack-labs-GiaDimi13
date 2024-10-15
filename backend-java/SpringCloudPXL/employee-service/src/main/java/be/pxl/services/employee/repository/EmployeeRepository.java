package be.pxl.services.employee.repository;

import be.pxl.services.employee.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;


@org.springframework.stereotype.Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
