package tn.pi.ManageRecruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.pi.ManageRecruitment.model.FichePoste;

@Repository
public interface FichePosteRepository extends JpaRepository<FichePoste, Integer> {
}
