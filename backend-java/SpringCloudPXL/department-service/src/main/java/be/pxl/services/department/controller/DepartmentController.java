package be.pxl.services.department.controller;

import be.pxl.services.department.services.IDepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final IDepartmentService departmentService;
    @GetMapping
    public ResponseEntity getDepartments() {
        return new ResponseEntity(departmentService.getAllDepartments(), HttpStatus.OK);
    }
}
