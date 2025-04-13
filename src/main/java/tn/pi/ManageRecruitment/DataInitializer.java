package tn.pi.ManageRecruitment;

import tn.pi.ManageRecruitment.model.Role;
import tn.pi.ManageRecruitment.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final RoleRepository roleRepository;

    public DataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {
        createRoleIfNotFound("ADMIN");
        createRoleIfNotFound("RECRUTEUR");
        createRoleIfNotFound("PARTICIPANT");
    }

    private void createRoleIfNotFound(String roleName) {
        roleRepository.findByName(roleName).orElseGet(() -> {
            Role role = new Role();
            role.setName(roleName);
            return roleRepository.save(role);
        });
    }
}