package be.pxl.services.department.domain.dto;

import be.pxl.services.department.domain.Employee;
import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentResponse {

    private Long id;
    private Long organizationId;
    private String name;
    @ElementCollection //creates a separate table to store a list of the employeeDTO
    //@Transient  // Indicates that this is not a persistent field in the DB
    private List<Employee> employees;
    private String position;
}
