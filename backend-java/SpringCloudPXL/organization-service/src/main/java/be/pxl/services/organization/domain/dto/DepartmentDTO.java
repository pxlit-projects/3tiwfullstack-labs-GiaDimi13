package be.pxl.services.organization.domain.dto;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {
    private Long id;
    private Long organizationId;
    private String name;
    private List<EmployeeDTO> employees;
    private String position;

    public Long getId() {
        return id;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public String getName() {
        return name;
    }

    public List<EmployeeDTO> getEmployees() {
        return employees;
    }

    public String getPosition() {
        return position;
    }
}
