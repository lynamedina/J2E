package tn.pi.ManageRecruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.pi.ManageRecruitment.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}