package be.pxl.services.organization.domain;

import be.pxl.services.organization.domain.dto.DepartmentDTO;
import be.pxl.services.organization.domain.dto.EmployeeDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "organization")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String name;
    private String address;
    @ElementCollection
    private List<EmployeeDTO> employees;
    @ElementCollection
    private List<DepartmentDTO> departments;

}
