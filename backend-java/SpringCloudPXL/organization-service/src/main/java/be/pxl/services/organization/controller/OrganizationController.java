package be.pxl.services.organization.controller;

import be.pxl.services.organization.domain.dto.OrganizationRequest;
import be.pxl.services.organization.services.IOrganizationService;
import lombok.RequiredArgsConstructor;
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
}
