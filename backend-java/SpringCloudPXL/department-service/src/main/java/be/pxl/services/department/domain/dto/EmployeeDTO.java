package be.pxl.services.department.domain.dto;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long id;

    private Long organizationId;
    private Long departmentId;
    private String name;
    private int age;
    private String position;

}
