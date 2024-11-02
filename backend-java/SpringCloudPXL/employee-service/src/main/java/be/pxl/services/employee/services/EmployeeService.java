package be.pxl.services.employee.services;

import be.pxl.services.employee.client.NotificationClient;
import be.pxl.services.employee.domain.Employee;
import be.pxl.services.employee.domain.NotificationRequest;
import be.pxl.services.employee.domain.dto.EmployeeRequest;
import be.pxl.services.employee.domain.dto.EmployeeResponse;
import be.pxl.services.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor // zorgt voor de autowiring
public class EmployeeService implements IEmployeeService {

    private final EmployeeRepository employeeRepository;
    private final NotificationClient notificationClient;

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(employee -> mapToEmployeeResponse(employee)).toList();
    }

    private EmployeeResponse mapToEmployeeResponse(Employee employee) {
        return EmployeeResponse.builder()
                .age(employee.getAge())
                .name(employee.getName())
                .position(employee.getPosition())
                .build();
    }

    @Override
    public void addEmployee(EmployeeRequest employeeRequest) {
        Employee employee = Employee.builder()
                .age(employeeRequest.getAge())
                .name(employeeRequest.getName())
                .position(employeeRequest.getPosition())
                .build();

        employeeRepository.save(employee);

        NotificationRequest notificationRequest = NotificationRequest.builder()
                .message("Employee created")
                .to("Dimi")
                .build();
        notificationClient.sendNotification(notificationRequest);
    }

    @Override
    public EmployeeResponse getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
        return mapToEmployeeResponse(employee);
    }

    @Override
    public List<EmployeeResponse> getDepartmentById(Long departmentId) {
        List<Employee> employees = employeeRepository.findByDepartmentId(departmentId);

        if (employees.isEmpty()) {
            // You could log this or handle it in some way
            System.out.println("No employees found for departmentId: " + departmentId);
        }

        return employees.stream().map(employee -> mapToEmployeeResponse(employee)).toList();

    }

    @Override
    public List<EmployeeResponse> getOrganizationById(Long organizationId) {
        List<Employee> employees = employeeRepository.findByOrganizationId(organizationId);

        if (employees.isEmpty()) {
            System.out.println("No employees found for organizationId: " + organizationId);
        }

        return employees.stream().map(employee -> mapToEmployeeResponse(employee)).toList();
    }
}
