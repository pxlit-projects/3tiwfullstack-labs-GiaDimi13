package be.pxl.services.organization.services;

import be.pxl.services.organization.domain.dto.OrganizationRequest;
import be.pxl.services.organization.domain.dto.OrganizationResponse;

import java.util.List;

public interface IOrganizationService {
    List<OrganizationResponse> getAllOrganizations();

    void addOrganization(OrganizationRequest organizationRequest);

}
