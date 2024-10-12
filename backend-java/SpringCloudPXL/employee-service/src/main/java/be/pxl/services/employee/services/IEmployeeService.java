package be.pxl.services.employee.services;

import be.pxl.services.employee.domain.Employee;
import be.pxl.services.employee.domain.dto.EmployeeRequest;
import be.pxl.services.employee.domain.dto.EmployeeResponse;

import java.util.List;

public interface IEmployeeService {
    List<EmployeeResponse> getAllEmployees();

    void addEmployee(EmployeeRequest employeeRequest);
}
