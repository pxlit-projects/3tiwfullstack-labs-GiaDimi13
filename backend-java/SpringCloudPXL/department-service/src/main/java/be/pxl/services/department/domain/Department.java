package be.pxl.services.department.domain;

import be.pxl.services.department.DTO.EmployeeDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "department")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private Long organizationId;
    private String name;
    @ElementCollection //creates a separate table to store a list of the employeeDTO
    //@Transient  // Indicates that this is not a persistent field in the DB
    private List<EmployeeDTO> employees;
    private String position;
}
