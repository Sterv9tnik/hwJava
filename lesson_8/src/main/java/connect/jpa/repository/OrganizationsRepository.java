package connect.jpa.repository;

import connect.jpa.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface OrganizationsRepository extends JpaRepository<Organization, String> {
}
