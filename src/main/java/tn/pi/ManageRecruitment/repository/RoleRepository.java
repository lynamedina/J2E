package tn.pi.ManageRecruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.pi.ManageRecruitment.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}