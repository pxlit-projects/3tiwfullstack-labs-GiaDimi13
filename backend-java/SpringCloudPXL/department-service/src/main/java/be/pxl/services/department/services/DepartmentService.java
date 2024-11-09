package be.pxl.services.department.services;

import be.pxl.services.department.client.EmployeeClient;
import be.pxl.services.department.domain.Department;
import be.pxl.services.department.domain.Employee;
import be.pxl.services.department.domain.dto.DepartmentRequest;
import be.pxl.services.department.domain.dto.DepartmentResponse;
import be.pxl.services.department.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService implements IDepartmentService{


    private final DepartmentRepository departmentRepository;

    private final EmployeeClient employeeClient;
    @Override
    public List<DepartmentResponse> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream().map(department -> mapToDepartmentResponse(department)).toList();
    }
    private DepartmentResponse mapToDepartmentResponse(Department department) {
        return DepartmentResponse.builder()
                .name(department.getName())
                .employees(department.getEmployees())
                .position(department.getPosition())
                .build();
    }

    @Override
    public void addDepartment(DepartmentRequest departmentRequest) {
        Department department = Department.builder()
                .name(departmentRequest.getName())
                .employees(departmentRequest.getEmployees())
                .position(departmentRequest.getPosition())
                .build();
        departmentRepository.save(department);
    }

    @Override
    public DepartmentResponse getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found"));

        return mapToDepartmentResponse(department);
    }

    @Override
    public List<DepartmentResponse> getByOrganizationId(Long organizationId) {
        List<Department> departments = departmentRepository.findByOrganizationId(organizationId);

        if(departments.isEmpty()) {
            System.out.println("No departments found for organizationId: " + organizationId);
        }

        return departments.stream().map(department -> mapToDepartmentResponse(department)).toList();
    }

    @Override
    public List<DepartmentResponse> findByOrganizationWithEmployees(Long organizationId) {
        List<Department> departments = departmentRepository.findAllWithEmployeesByOrganizationId(organizationId).orElse(null);
        //.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found"));
        if(departments == null || departments.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found");
        }

        List<Employee> employees = employeeClient.getEmployeesByOrganizationId(organizationId);

        for(var department : departments) {
            List<Employee> employeesByDepartment = employees.stream()
                    .filter(e -> e.getDepartmentId() == department.getId())
                    .toList();
            department.setEmployees(employeesByDepartment);
        }
        return  departments.stream().map((this::mapToDepartmentResponse)).toList();
    }

}
