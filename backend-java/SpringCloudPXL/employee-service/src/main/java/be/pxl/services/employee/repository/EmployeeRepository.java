package be.pxl.services.employee.repository;

import be.pxl.services.employee.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
