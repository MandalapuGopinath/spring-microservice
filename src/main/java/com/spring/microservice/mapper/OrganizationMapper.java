package com.spring.microservice.mapper;

import com.spring.microservice.dto.OrganizationDto;
import com.spring.microservice.entity.Organization;

public class OrganizationMapper {

    public OrganizationDto mapToOrganizationDto(Organization organization, OrganizationDto organizationDto) {
        organizationDto.setOrganizationName(organization.getOrganizationName());
        organizationDto.setCreatedBy(organization.getCreatedBy());
        organizationDto.setCreatedDate(organization.getCreatedDate());
        organizationDto.setUpdatedBy(organization.getUpdatedBy());
        organizationDto.setUpdatedDate(organization.getUpdatedDate());
        organizationDto.setEmailId(organization.getEmailId());
        organizationDto.setContactNo(organization.getContactNo());
        organizationDto.setCasStatusId(organization.getCasStatusId());
        organizationDto.setPartyNbr(organization.getPartyNbr());
        return organizationDto;
    }

    public Organization mapToOrganization(OrganizationDto organizationDto, Organization organization) {
        organization.setContactNo(organizationDto.getContactNo());
        organization.setOrganizationName(organizationDto.getOrganizationName());
        organization.setCasStatusId(organizationDto.getCasStatusId());
        organization.setCreatedBy(organizationDto.getCreatedBy());
        organization.setCreatedDate(organizationDto.getCreatedDate());
        organization.setUpdatedBy(organization.getUpdatedBy());
        organization.setUpdatedDate(organizationDto.getUpdatedDate());
        organization.setPartyNbr(organizationDto.getPartyNbr());
        organization.setEmailId(organizationDto.getEmailId());
        return organization;
    }
}
