package be.pxl.services;


import be.pxl.services.employee.EmployeeServiceApplication;
import be.pxl.services.employee.domain.Employee;
import be.pxl.services.employee.repository.EmployeeRepository;
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

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = EmployeeServiceApplication.class)
@AutoConfigureMockMvc
@Testcontainers
public class EmployeeTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EmployeeRepository employeeRepository;


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
    public void testCreateEmployee() throws Exception {
        Employee employee = Employee.builder()
                .age(24)
                .name("dimi")
                .position("student")
                .build();

        String employeeString = objectMapper.writeValueAsString(employee);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(employeeString))
                .andExpect(status().isCreated());

        assertEquals(1,employeeRepository.findAll().size());
    }
    @Test
    public void getEmployee() throws Exception {
        Employee employee = Employee.builder()
                .age(24)
                .name("dimi")
                .position("student")
                .build();
        employeeRepository.save(employee);
        // Perform the GET request and capture the response
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/employee")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())  // Check status is 200 OK
                .andReturn();  // Capture the result of the request

        // Extract JSON response content
        String jsonResponse = mvcResult.getResponse().getContentAsString();

        // Convert JSON response to List<Employee> using ObjectMapper
        List<Employee> employees = Arrays.asList(objectMapper.readValue(jsonResponse, Employee[].class));

        // Use assertions to verify the result
        assertEquals(1, employees.size());  // Check that there is 1 employee in the list
        assertEquals("dimi", employees.get(0).getName());  // Verify the name of the employee
        assertEquals(24, employees.get(0).getAge());  // Verify the age of the employee
        assertEquals("student", employees.get(0).getPosition());  // Verify the position

    }
    @Test
    public void getByDepartmentId() throws Exception {
        Employee employee = Employee.builder()
                .age(24)
                .name("dimi")
                .position("studentPXL")
                .departmentId(1L)
                .build();

        employeeRepository.save(employee);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/department/{departmentId}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = mvcResult.getResponse().getContentAsString();

        List<Employee> employees = Arrays.asList(objectMapper.readValue(jsonResponse, Employee[].class));

        assertEquals(1,employees.size());
        assertEquals("dimi", employees.get(0).getName());
        assertEquals(24, employees.get(0).getAge());
        assertEquals("studentPXL", employees.get(0).getPosition());
    }

    @Test
    public void getByOrganizationId() throws Exception {
        Employee employee = Employee.builder()
                .age(24)
                .name("dimi")
                .position("studentPXL")
                .organizationId(1L)
                .build();

        employeeRepository.save(employee);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/organization/{organizationId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = mvcResult.getResponse().getContentAsString();

        List<Employee> employees = Arrays.asList(objectMapper.readValue(jsonResponse, Employee[].class));

        assertEquals(1,employees.size());
        assertEquals("dimi", employees.get(0).getName());
        assertEquals(24, employees.get(0).getAge());
        assertEquals("studentPXL", employees.get(0).getPosition());
    }
}
