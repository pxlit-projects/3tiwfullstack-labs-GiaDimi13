package be.pxl.services.department.domain.dto;

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
public class DepartmentRequest {

    private Long id;
    private Long organizationId;
    private String name;
    @ElementCollection //creates a separate table to store a list of the employeeDTO
    //@Transient  // Indicates that this is not a persistent field in the DB
    private List<EmployeeDTO> employees;
    private String position;
}
