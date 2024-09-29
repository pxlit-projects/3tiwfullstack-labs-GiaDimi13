package be.pxl.services.department.services;

import be.pxl.services.department.domain.Department;
import be.pxl.services.department.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService implements IDepartmentService{


    private final DepartmentRepository departmentRepository;
    @Override
    public List<Department> getAllDepartments() {
        return null;
    }
}
