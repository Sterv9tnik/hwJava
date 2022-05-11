package connect.jpa.service.impl;

import connect.jpa.entity.Organization;
import connect.jpa.entity.exception.RequiredFieldMissedException;
import connect.jpa.repository.OrganizationsRepository;
import connect.jpa.service.OrganizationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationsServiceImpl implements OrganizationsService {
    @Autowired
    private OrganizationsRepository organizationsRepository;

    @Override
    public Organization create(Organization organization) {
        validation(organization);
        organizationsRepository.save(organization);
        return organization;
    }

    public void validation(Organization organization){
        if (organization.getId() == null){throw new RequiredFieldMissedException("Id missed");}
        if (organization.getName() == null){throw new RequiredFieldMissedException("Name missed");}
        if (organization.getImage() == null){throw new RequiredFieldMissedException("Image missed");}
        if (organization.getContactPersonId() == null){throw new RequiredFieldMissedException("ContactPersonId missed");}
        if (organization.getDirector() == null){throw new RequiredFieldMissedException("Director missed");}
        if (organization.getInn() == null){throw new RequiredFieldMissedException("Inn missed");}
        if (organization.getOgrn() == null){throw new RequiredFieldMissedException("Ogrn missed");}
        if (organization.getKpp() == null){throw new RequiredFieldMissedException("Kpp missed");}
        if (organization.getOkved() == null){throw new RequiredFieldMissedException("Okved missed");}
        if (organization.getOkpo() == null){throw new RequiredFieldMissedException("Okpo missed");}
        if (organization.getBank() == null){throw new RequiredFieldMissedException("Bank missed");}
        if (organization.getBik() == null){throw new RequiredFieldMissedException("Bik missed");}
        if (organization.getPhone() == null){throw new RequiredFieldMissedException("Phone missed");}
        if (organization.getEmail() == null){throw new RequiredFieldMissedException("Email missed");}
        if (organization.getEmployeesCount() == null){throw new RequiredFieldMissedException("EmployeesCount missed");}
        if (organization.getAddress() == null){throw new RequiredFieldMissedException("Address missed");}
        if (organization.getCreationDate() == null){throw new RequiredFieldMissedException("CreationDate missed");}
        if (organization.getUpdateDate() == null){throw new RequiredFieldMissedException("UpdateDate missed");}
        if (organization.getDescription() == null){throw new RequiredFieldMissedException("Description missed");}
    }
}
