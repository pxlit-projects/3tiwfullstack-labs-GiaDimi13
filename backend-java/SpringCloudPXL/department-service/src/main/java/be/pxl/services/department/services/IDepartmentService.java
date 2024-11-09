package be.pxl.services.department.services;

import be.pxl.services.department.domain.dto.DepartmentRequest;
import be.pxl.services.department.domain.dto.DepartmentResponse;

import java.util.List;

public interface IDepartmentService {
    List<DepartmentResponse> getAllDepartments();

    void addDepartment(DepartmentRequest departmentRequest);

    DepartmentResponse getDepartmentById(Long id);

    List<DepartmentResponse> getByOrganizationId(Long organizationId);
    List<DepartmentResponse> findByOrganizationWithEmployees(Long organizationId);
}
