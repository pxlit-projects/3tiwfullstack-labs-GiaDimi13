package be.pxl.services.employee.repository;

import be.pxl.services.employee.domain.Employee;
import be.pxl.services.employee.domain.dto.EmployeeResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;


@org.springframework.stereotype.Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByOrganizationId(Long organizationId);
    List<Employee> findByDepartmentId(Long departmentId);
}
