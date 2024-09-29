package be.pxl.services.department.DTO;

import jakarta.persistence.Embeddable;

@Embeddable
public class EmployeeDTO {
    private Long id;

    private Long organizationId;
    private Long departmentId;
    private String name;
    private int age;
    private String position;

    public Long getId() {
        return id;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPosition() {
        return position;
    }
}
