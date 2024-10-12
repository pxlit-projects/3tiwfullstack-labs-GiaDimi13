package be.pxl.services.organization.domain.dto;

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
public class OrganizationRequest {
    private Long Id;
    private String name;
    private String address;
    @ElementCollection
    private List<EmployeeDTO> employees;
    @ElementCollection
    private List<DepartmentDTO> departments;
}
