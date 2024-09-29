package be.pxl.services.department.repository;

import be.pxl.services.department.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
