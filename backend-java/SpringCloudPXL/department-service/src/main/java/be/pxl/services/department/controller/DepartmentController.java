package be.pxl.services.department.controller;

import be.pxl.services.department.domain.dto.DepartmentRequest;
import be.pxl.services.department.services.IDepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
