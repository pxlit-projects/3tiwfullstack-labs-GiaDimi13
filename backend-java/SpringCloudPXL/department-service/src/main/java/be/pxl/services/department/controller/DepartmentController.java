package be.pxl.services.department.controller;

import be.pxl.services.department.domain.dto.DepartmentRequest;
import be.pxl.services.department.domain.dto.DepartmentResponse;
import be.pxl.services.department.services.IDepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final IDepartmentService departmentService;
    @GetMapping
    public ResponseEntity getDepartments() {
        return new ResponseEntity(departmentService.getAllDepartments(), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addDepartment(@RequestBody DepartmentRequest departmentRequest) {
        departmentService.addDepartment(departmentRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponse> getDepartmentById(@PathVariable Long id) {
        return new ResponseEntity<>(departmentService.getDepartmentById(id), HttpStatus.OK);
    }

    @GetMapping("/organization/{organizationId}")
    public ResponseEntity<List<DepartmentResponse>> getByOrganizationId(@PathVariable Long organizationId) {
        return new ResponseEntity<>(departmentService.getByOrganizationId(organizationId), HttpStatus.OK);
    }

    //Todo: add last endpoint findByIdWithEmployees
}
