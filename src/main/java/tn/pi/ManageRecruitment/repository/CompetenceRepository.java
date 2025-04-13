package tn.pi.ManageRecruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.pi.ManageRecruitment.model.Competence;

import java.util.List;

public interface CompetenceRepository extends JpaRepository<Competence, Long> {
    List<Competence> findByPersonnelId(Long personnelId);
}