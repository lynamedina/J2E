package tn.pi.ManageRecruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.pi.ManageRecruitment.model.Personnel;

public interface PersonnelRepository extends JpaRepository<Personnel, Long> {
}