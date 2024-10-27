package be.pxl.services.organization.services;

import be.pxl.services.organization.domain.Organization;
import be.pxl.services.organization.domain.dto.OrganizationRequest;
import be.pxl.services.organization.domain.dto.OrganizationResponse;
import be.pxl.services.organization.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationService implements IOrganizationService{

    private final OrganizationRepository organizationRepository;
    @Override
    public List<OrganizationResponse> getAllOrganizations() {
        List<Organization> organizations = organizationRepository.findAll();
        return organizations.stream().map(organization -> mapToOrganizationResponse(organization)).toList();
    }

    private OrganizationResponse mapToOrganizationResponse(Organization organization) {
        return OrganizationResponse.builder()
                .name(organization.getName())
                .address(organization.getAddress())
                .employees(organization.getEmployees())
                .departments(organization.getDepartments())
                .build();
    }


    @Override
    public void addOrganization(OrganizationRequest organizationRequest) {
        Organization organization= Organization.builder()
                .name(organizationRequest.getName())
                .address(organizationRequest.getAddress())
                .employees(organizationRequest.getEmployees())
                .departments(organizationRequest.getDepartments())
                .build();

        organizationRepository.save(organization);
    }

    @Override
    public OrganizationResponse getOrganizationById(Long id) {
        Organization organization = organizationRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Organization not found"));

        return mapToOrganizationResponse(organization);

    }

}
