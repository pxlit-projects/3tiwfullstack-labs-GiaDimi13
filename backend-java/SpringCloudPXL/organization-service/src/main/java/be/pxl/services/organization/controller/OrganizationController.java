package be.pxl.services.organization.controller;

import be.pxl.services.organization.domain.dto.OrganizationRequest;
import be.pxl.services.organization.domain.dto.OrganizationResponse;
import be.pxl.services.organization.services.IOrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organization")
@RequiredArgsConstructor
public class OrganizationController {

    private final IOrganizationService organizationService;

    @GetMapping
    public ResponseEntity getOrganizations() {
        return new ResponseEntity(organizationService.getAllOrganizations(), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addOrganization(@RequestBody OrganizationRequest organizationRequest) {
         organizationService.addOrganization(organizationRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizationResponse> getOrganizationById(@PathVariable Long id) {
        return new ResponseEntity<>(organizationService.getOrganizationById(id), HttpStatus.OK);
    }

    //TODO: implement endpoints

    /*
    public ResponseEntity<OrganizationResponse> findByIdWithDepartments(@PathVariable Long id) {
        OrganizationResponse response = organizationService.findByIdWithDepartments(id);
        return ResponseEntity.ok(response);
    }

    // Endpoint for getting organization with departments and employees
    @GetMapping("/{id}/with-departments-and-employees")
    public ResponseEntity<OrganizationResponse> findByIdWithDepartmentsAndEmployees(@PathVariable Long id) {
        OrganizationResponse response = organizationService.findByIdWithDepartmentsAndEmployees(id);
        return ResponseEntity.ok(response);
    }

    // Endpoint for getting organization with employees only
    @GetMapping("/{id}/with-employees")
    public ResponseEntity<OrganizationResponse> findByIdWithEmployees(@PathVariable Long id) {
        OrganizationResponse response = organizationService.findByIdWithEmployees(id);
        return ResponseEntity.ok(response);
    }

     */
}
