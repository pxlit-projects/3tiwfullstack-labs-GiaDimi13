package be.pxl.services;

import be.pxl.services.department.DepartmentServiceApplication;
import be.pxl.services.department.domain.Department;
import be.pxl.services.department.domain.dto.EmployeeDTO;
import be.pxl.services.department.repository.DepartmentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = DepartmentServiceApplication.class)
@AutoConfigureMockMvc
@Testcontainers
public class DepartmentTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Container
    private static MySQLContainer sqlContainer =
            new MySQLContainer("mysql:5.7.37");

    @DynamicPropertySource
    static void registerMySQLProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", sqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", sqlContainer::getUsername);
        registry.add("spring.datasource.password", sqlContainer::getPassword);
    }

    @Test
    public void createDepartment() throws Exception{
        List<EmployeeDTO> employees = new ArrayList<>();
        employees.add(EmployeeDTO.builder().name("Dimi").age(24).position("student").build());
        employees.add(EmployeeDTO.builder().name("Test").age(27).position("student").build());

        Department department = Department.builder()
                .name("Test")
                .employees(employees)
                .position("Test")
                .build();

        String departmentString = objectMapper.writeValueAsString(department);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/department")
                .contentType(MediaType.APPLICATION_JSON)
                .content(departmentString))
                .andExpect(status().isCreated());

        assertEquals(1, departmentRepository.findAll().size());
    }

    @Test
    public void getDepartment() throws Exception {
        List<EmployeeDTO> employees = new ArrayList<>();
        employees.add(EmployeeDTO.builder().name("Dimi").age(24).position("student").build());
        employees.add(EmployeeDTO.builder().name("Test").age(27).position("student").build());

        Department department = Department.builder()
                .name("Test")
                .employees(employees)
                .position("Test")
                .build();
        departmentRepository.save(department);
        // Perform the GET request and capture the response
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/department")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())  // Check status is 200 OK
                .andReturn();  // Capture the result of the request

        // Extract JSON response content
        String jsonResponse = mvcResult.getResponse().getContentAsString();

        // Convert JSON response to List<Employee> using ObjectMapper
        List<Department> departments = Arrays.asList(objectMapper.readValue(jsonResponse, Department[].class));

        // Use assertions to verify the result
        assertEquals(1, departments.size());  // Check that there is 1 employee in the list
        assertEquals("Test", departments.get(0).getName());  // Verify the name of the employee
        assertEquals(24, departments.get(0).getEmployees().get(0).getAge());  // Verify the age of the employee
        assertEquals("Test", departments.get(0).getPosition());  // Verify the position

    }
}
