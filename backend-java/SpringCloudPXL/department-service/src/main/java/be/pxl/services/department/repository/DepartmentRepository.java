package be.pxl.services.department.repository;

import be.pxl.services.department.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    List<Department> findByOrganizationId(Long organizationId);


    Optional<List<Department>> findAllWithEmployeesByOrganizationId(Long organizationId);
}
