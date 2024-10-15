package be.pxl.services;

import be.pxl.services.organization.OrganizationServiceApplication;
import be.pxl.services.organization.domain.Organization;
import be.pxl.services.organization.domain.dto.DepartmentDTO;
import be.pxl.services.organization.domain.dto.EmployeeDTO;
import be.pxl.services.organization.repository.OrganizationRepository;
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

@SpringBootTest(classes = OrganizationServiceApplication.class)
@AutoConfigureMockMvc
@Testcontainers
public class OrganizationTests {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OrganizationRepository organizationRepository;

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
    public void createOrganization() throws Exception{
        List<EmployeeDTO> employees = new ArrayList<>();
        employees.add(EmployeeDTO.builder().name("Dimi").age(24).position("student").build());
        employees.add(EmployeeDTO.builder().name("Test").age(27).position("student").build());

        List<DepartmentDTO> departments = new ArrayList<>();
        departments.add(DepartmentDTO.builder().name("test1").employees(employees).position("test1").build());
        departments.add(DepartmentDTO.builder().name("test2").employees(employees).position("test2").build());

        Organization organization = Organization.builder()
                .name("Test")
                .address("testAddress")
                .employees(employees)
                .departments(departments)
                .build();

        String organizationString = objectMapper.writeValueAsString(organization);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/organization")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(organizationString))
                .andExpect(status().isCreated());

        assertEquals(1, organizationRepository.findAll().size());
    }

    @Test
    public void getOrganization() throws Exception {
        List<EmployeeDTO> employees = new ArrayList<>();
        employees.add(EmployeeDTO.builder().name("Dimi").age(24).position("student").build());
        employees.add(EmployeeDTO.builder().name("Test").age(27).position("student").build());

        List<DepartmentDTO> departments = new ArrayList<>();
        departments.add(DepartmentDTO.builder().name("test1").employees(employees).position("test1").build());
        departments.add(DepartmentDTO.builder().name("test2").employees(employees).position("test2").build());

        Organization organization = Organization.builder()
                .name("Test")
                .address("testAddress")
                .employees(employees)
                .departments(departments)
                .build();
        organizationRepository.save(organization);
        // Perform the GET request and capture the response
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/organization")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())  // Check status is 200 OK
                .andReturn();  // Capture the result of the request

        // Extract JSON response content
        String jsonResponse = mvcResult.getResponse().getContentAsString();

        // Convert JSON response to List<Employee> using ObjectMapper
        List<Organization> organizations = Arrays.asList(objectMapper.readValue(jsonResponse, Organization[].class));

        // Use assertions to verify the result
        assertEquals(1, organizations.size());  // Check that there is 1 employee in the list
        assertEquals("Test", organizations.get(0).getName());  // Verify the name of the employee
        assertEquals("testAddress", organizations.get(0).getAddress());  // Verify the age of the employee

    }
}
